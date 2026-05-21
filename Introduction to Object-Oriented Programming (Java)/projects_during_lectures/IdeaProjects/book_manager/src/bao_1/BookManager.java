package bao_1;

import java.sql.*;
import java.util.Scanner;

public class BookManager {
    // ================= 数据库配置（请务必修改 db_name） =================
    // 1. 确保 localhost:3306 后面跟着的是你真实的数据库名字
    // 2. serverTimezone 解决了 MySQL 8+ 常见的时区报错问题
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/bookdb?serverTimezone=GMT%2B8&useSSL=false&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "948377";
    // =================================================================

    // 获取数据库连接
    public static Connection getConnection() {
        try {
            // 加载驱动（MySQL 8/9 使用 com.mysql.cj.jdbc.Driver）
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println(">>> 错误：未找到驱动包！请确认已右键点击 jar 包并选择 'Add as Library'。");
        } catch (SQLException e) {
            System.err.println(">>> 错误：数据库连接失败！请检查 URL、用户名、密码或 MySQL 服务是否启动。");
            e.printStackTrace();
        }
        return null;
    }

    // ---------------- 1. 查询所有图书 ----------------
    public static void queryAll() {
        String sql = "SELECT * FROM bookinfo";
        try (Connection conn = getConnection()) {
            if (conn == null) return;

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("\n序号\t图书编号\t图书名称\t\t作者\t出版社\t\t单价\t出版日期\t\tISBN\t\t\t库存");
                while (rs.next()) {
                    System.out.printf("%d\t%s\t%s\t\t%s\t%s\t%.2f\t%s\t%s\t%d\n",
                            rs.getInt("id"), rs.getString("book_id"), rs.getString("book_name"),
                            rs.getString("author"), rs.getString("publisher"), rs.getFloat("price"),
                            rs.getDate("pub_date"), rs.getString("isbn"), rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- 2. 添加图书（使用 PreparedStatement） ----------------
    public static void addBook(Scanner sc) {
        System.out.println("请依次输入：图书编号 图书名称 作者 出版社 单价 出版日期(yyyy-MM-dd) ISBN 库存");
        System.out.println("(用空格分隔):");

        try {
            String bookId = sc.next();
            String name   = sc.next();
            String author = sc.next();
            String pub    = sc.next();
            float price   = sc.nextFloat();
            String date   = sc.next();
            String isbn   = sc.next();
            int stock     = sc.nextInt();

            String sql = "INSERT INTO bookinfo(book_id, book_name, author, publisher, price, pub_date, isbn, stock) VALUES(?,?,?,?,?,?,?,?)";

            try (Connection conn = getConnection()) {
                if (conn == null) return;
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, bookId);
                    pstmt.setString(2, name);
                    pstmt.setString(3, author);
                    pstmt.setString(4, pub);
                    pstmt.setFloat(5, price);
                    pstmt.setString(6, date); // 数据库会自动转换字符串为日期格式
                    pstmt.setString(7, isbn);
                    pstmt.setInt(8, stock);

                    pstmt.executeUpdate();
                    System.out.println(">>> 添加成功！");
                }
            }
        } catch (Exception e) {
            System.err.println(">>> 添加失败，请检查输入格式或数据库连接。");
            e.printStackTrace();
        }
    }

    // ---------------- 3. 修改图书 ----------------
    public static void updateBook(Scanner sc) {
        System.out.print("请输入要修改的图书序号(id)：");
        int id = sc.nextInt();
        System.out.print("请输入新的价格：");
        float price = sc.nextFloat();
        System.out.print("请输入新的库存：");
        int stock = sc.nextInt();

        String sql = "UPDATE bookinfo SET price=?, stock=? WHERE id=?";
        try (Connection conn = getConnection()) {
            if (conn == null) return;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setFloat(1, price);
                pstmt.setInt(2, stock);
                pstmt.setInt(3, id);

                int n = pstmt.executeUpdate();
                if (n > 0) System.out.println(">>> 修改成功！");
                else       System.out.println(">>> 未找到该序号，修改失败。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- 4. 删除图书 ----------------
    public static void deleteBook(Scanner sc) {
        System.out.print("请输入要删除的图书序号(id)：");
        int id = sc.nextInt();

        String sql = "DELETE FROM bookinfo WHERE id=?";
        try (Connection conn = getConnection()) {
            if (conn == null) return;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int n = pstmt.executeUpdate();
                if (n > 0) System.out.println(">>> 删除成功！");
                else       System.out.println(">>> 未找到该序号，删除失败。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= 主程序 =================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========== 图书信息管理系统 ==========");
            System.out.println("1. 查询所有图书");
            System.out.println("2. 添加图书");
            System.out.println("3. 修改图书（价格+库存）");
            System.out.println("4. 删除图书");
            System.out.println("0. 退出系统");
            System.out.print("请选择操作：");

            if (!sc.hasNextInt()) {
                sc.next(); // 消耗错误输入
                continue;
            }
            int choice = sc.nextInt();

            switch (choice) {
                case 1: queryAll(); break;
                case 2: addBook(sc); break;
                case 3: updateBook(sc); break;
                case 4: deleteBook(sc); break;
                case 0: System.out.println("再见！"); return;
                default: System.out.println("输入错误，请重新选择！");
            }
        }
    }
}