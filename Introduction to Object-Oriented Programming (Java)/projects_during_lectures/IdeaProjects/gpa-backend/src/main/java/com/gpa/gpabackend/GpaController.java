package com.gpa.gpabackend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/gpa")
@CrossOrigin(origins = "*")
public class GpaController {

    private static final String STORAGE_DIR = "gpa_users_data/";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 💡 安全的密码哈希算法（SHA-256）
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("密码加密出错", e);
        }
    }

    // 1. 创建云端账户
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> payload) {
        try {
            String username = (String) payload.get("username");
            String password = (String) payload.get("password");
            Object courses = payload.get("courses");

            if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("用户名或密码不能为空");
            }

            Files.createDirectories(Paths.get(STORAGE_DIR));
            Path path = Paths.get(STORAGE_DIR + username + ".json");
            if (Files.exists(path)) {
                return ResponseEntity.badRequest().body("该用户名在云端已被占用");
            }

            ObjectNode rootNode = objectMapper.createObjectNode();
            rootNode.put("passwordHash", hashPassword(password));

            JsonNode coursesNode = objectMapper.valueToTree(courses != null ? courses : new ArrayList<>());
            if (coursesNode.has("userData")) {
                coursesNode = coursesNode.get("userData");
            }
            rootNode.set("userData", coursesNode);

            Files.writeString(path, objectMapper.writeValueAsString(rootNode));
            return ResponseEntity.ok("云端账户创建并同步成功");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器写入失败: " + e.getMessage());
        }
    }

    // 2. 登录云端账户
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> payload) {
        try {
            String username = payload.get("username");
            String password = payload.get("password");

            Path path = Paths.get(STORAGE_DIR + username + ".json");
            if (!Files.exists(path)) {
                return ResponseEntity.status(404).body("云端用户不存在");
            }

            String fileContent = Files.readString(path);
            JsonNode rootNode = objectMapper.readTree(fileContent);

            String storedHash = rootNode.get("passwordHash").asText();
            if (!storedHash.equals(hashPassword(password))) {
                return ResponseEntity.status(401).body("密码验证不通过");
            }

            JsonNode userData = rootNode.get("userData");
            return ResponseEntity.ok(objectMapper.writeValueAsString(userData));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器读取失败: " + e.getMessage());
        }
    }

    // 3. 覆盖保存已登录云端用户的数据
    // ✨ 补齐关键核心接口：读取某个用户的独立 JSON 数据并优雅解包
    @GetMapping("/data")
    public ResponseEntity<?> getUserData(@RequestParam String user) {
        try {
            Path path = Paths.get(STORAGE_DIR + user + ".json");
            if (!Files.exists(path)) {
                // 如果云端还没文件，返回一个干净的初始结构
                return ResponseEntity.ok("{\"semestersPool\":[\"24秋\",\"25春\",\"25秋\",\"26春\"],\"courses\":[]}");
            }

            // 读取云端文件内容
            String fileContent = Files.readString(path);
            JsonNode rootNode = objectMapper.readTree(fileContent);

            // 💡 健壮性防线：如果文件里包含高层级的包裹（比如你手动拷入的带密码的大对象），
            // 我们只把其中的 userData 数据节点吐给前端，和前端的读取完美对接，绝不白屏！
            if (rootNode.has("userData")) {
                return ResponseEntity.ok(objectMapper.writeValueAsString(rootNode.get("userData")));
            }

            // 如果是个老版本的纯数据文件，原样吐给前端
            return ResponseEntity.ok(fileContent);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("{\"error\":\"无法读取云端数据: " + e.getMessage() + "\"}");
        }
    }
    @PostMapping("/save")
    public Map<String, String> saveData(@RequestParam String user, @RequestBody String rawJson) {
        Map<String, String> res = new HashMap<>();
        try {
            Path path = Paths.get(STORAGE_DIR + user + ".json");
            if (!Files.exists(path)) {
                res.put("status", "error");
                res.put("message", "用户未在云端注册");
                return res;
            }

            JsonNode rootNode = objectMapper.readTree(Files.readString(path));
            if (rootNode instanceof ObjectNode) {
                JsonNode newUserData = objectMapper.readTree(rawJson);
                if (newUserData.has("userData")) {
                    newUserData = newUserData.get("userData");
                }
                ((ObjectNode) rootNode).set("userData", newUserData);
                Files.writeString(path, objectMapper.writeValueAsString(rootNode));
                res.put("status", "success");
            } else {
                res.put("status", "error");
            }
        } catch (IOException e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
        }
        return res;
    }

    // 4. 💡 新增/优化：修改用户名（带防重名检查）
    @PostMapping("/rename")
    public ResponseEntity<?> renameUser(@RequestParam String oldUser, @RequestParam String newUser) {
        try {
            File oldFile = new File(STORAGE_DIR + oldUser + ".json");
            File newFile = new File(STORAGE_DIR + newUser + ".json");

            if (!oldFile.exists()) {
                return ResponseEntity.status(404).body("原云端用户不存在");
            }
            if (newFile.exists()) {
                return ResponseEntity.badRequest().body("新用户名已被占用");
            }

            if (oldFile.renameTo(newFile)) {
                return ResponseEntity.ok(Map.of("status", "success"));
            } else {
                return ResponseEntity.status(500).body("物理重命名文件失败");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("修改用户名出错: " + e.getMessage());
        }
    }

    // 5. 💡 新增：修改密码
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> payload) {
        try {
            String username = payload.get("username");
            String oldPassword = payload.get("oldPassword");
            String newPassword = payload.get("newPassword");

            Path path = Paths.get(STORAGE_DIR + username + ".json");
            if (!Files.exists(path)) {
                return ResponseEntity.status(404).body("云端用户不存在");
            }

            JsonNode rootNode = objectMapper.readTree(Files.readString(path));
            String storedHash = rootNode.get("passwordHash").asText();
            if (!storedHash.equals(hashPassword(oldPassword))) {
                return ResponseEntity.status(401).body("原密码验证错误");
            }

            if (rootNode instanceof ObjectNode) {
                ((ObjectNode) rootNode).put("passwordHash", hashPassword(newPassword));
                Files.writeString(path, objectMapper.writeValueAsString(rootNode));
                return ResponseEntity.ok(Map.of("status", "success"));
            }
            return ResponseEntity.badRequest().body("数据格式异常");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器处理失败: " + e.getMessage());
        }
    }

    // 6. 💡 新增：删除用户（销户）
    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestParam String username) {
        try {
            Path path = Paths.get(STORAGE_DIR + username + ".json");
            if (Files.deleteIfExists(path)) {
                return ResponseEntity.ok(Map.of("status", "success"));
            } else {
                return ResponseEntity.status(404).body("云端用户文件不存在，可能已被删除");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("彻底删除云端档案失败: " + e.getMessage());
        }
    }

    // 获取所有可选的用户列表（保留调试逻辑）
    @GetMapping("/users")
    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        File folder = new File(STORAGE_DIR);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File f : files) {
                    users.add(f.getName().replace(".json", ""));
                }
            }
        }
        return users;
    }
}