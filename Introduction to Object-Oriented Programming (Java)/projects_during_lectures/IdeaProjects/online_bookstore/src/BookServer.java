import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class BookServer {

    // ⚠️请将这里的密码修改为你电脑上真实的 MySQL 密码
    private static final String DB_URL = "jdbc:mysql://localhost:3306/online_bookstore?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "你的数据库密码";

    public static void main(String[] args) throws Exception {
        // 创建一个监听 8080 端口的轻量级 HTTP 服务器
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 注册路由：当前端访问 http://localhost:8080/api/books 时触发
        server.createContext("/api/books", new BookHandler());

        server.setExecutor(null);
        System.out.println("🚀 纯净版 Java 后端服务已启动！监听端口: 8080");
        System.out.println("👉 请直接在浏览器双击打开前端 index.html 网页进行联动测试。");
        server.start();
    }

    static class BookHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 允许前端网页跨域请求 (CORS)
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // 只处理 GET 请求：从 MySQL 查数据并拼装成 JSON 返回给网页
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                StringBuilder json = new StringBuilder("[");

                // 经典 JDBC 连接数据库机制
                try {
                    // 加载你本地 lib 里的 9.7.0 驱动
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

                        boolean first = true;
                        while (rs.next()) {
                            if (!first) json.append(",");
                            json.append("{")
                                    .append("\"bookId\":").append(rs.getInt("book_id")).append(",")
                                    .append("\"title\":\"").append(rs.getString("title")).append("\",")
                                    .append("\"author\":\"").append(rs.getString("author")).append("\",")
                                    .append("\"price\":").append(rs.getBigDecimal("price")).append(",")
                                    .append("\"stock\":").append(rs.getInt("stock")).append(",")
                                    .append("\"description\":\"").append(rs.getString("description")).append("\"")
                                    .append("}");
                            first = false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                json.append("]");

                byte[] responseBytes = json.toString().getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, responseBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(responseBytes);
                }
            }
        }
    }
}