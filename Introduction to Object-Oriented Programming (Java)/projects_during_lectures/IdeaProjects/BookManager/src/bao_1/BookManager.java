import java.sql.*;
import java.util.Scanner;

public class BookManager {
    // ================= 数据库配置（根据你的环境改这里） =================
    private static final String URL = "127.0.0.1:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "948377";
    // =================================================================

    // 获取数据库连接
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, root, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ---------------- 1. 查询所有图书 ----------------
    public static void queryAll() {
        String sql = "select * from bookinfo";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n序号\t图书编号\t图书名称\t\t作者\t出版社\t\t单价\t出版日期\t\tISBN\t\t\t库存");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("book_id") + "\t"
                        + rs.getString("book_name") + "\t\t"
                        + rs.getString("author") + "\t"
                        + rs.getString("publisher") + "\t"
                        + rs.getFloat("price") + "\t"
                        + rs.getDate("pub_date") + "\t"
                        + rs.getString("isbn") + "\t"
                        + rs.getInt("stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- 2. 添加图书 ----------------
    public static void addBook(Scanner sc) {
        System.out.println("请依次输入：图书编号 图书名称 作者 出版社 单价 出版日期(yyyy-MM-dd) ISBN 库存");
        System.out.println("（用空格分隔）:");
        String bookId = sc.next();
        String name   = sc.next();
        String author = sc.next();
        String pub    = sc.next();
        float price   = sc.nextFloat();
        String date   = sc.next();
        String isbn   = sc.next();
        int stock     = sc.nextInt();

        String sql = "insert into bookinfo(book_id,book_name,author,publisher,price,pub_date,isbn,stock) values('"
                + bookId + "','" + name + "','" + author + "','" + pub + "',"
                + price + ",'" + date + "','" + isbn + "'," + stock + ")";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println(">>> 添加成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- 3. 修改图书（按序号改价格和库存） ----------------
    public static void updateBook(Scanner sc) {
        System.out.print("请输入要修改的图书序号(id)：");
        int id = sc.nextInt();
        System.out.print("请输入新的价格：");
        float price = sc.nextFloat();
        System.out.print("请输入新的库存：");
        int stock = sc.nextInt();

        String sql = "update bookinfo set price=" + price + ", stock=" + stock + " where id=" + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int n = stmt.executeUpdate(sql);
            if (n > 0) System.out.println(">>> 修改成功！");
            else       System.out.println(">>> 未找到该序号，修改失败。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------- 4. 删除图书（按序号删） ----------------
    public static void deleteBook(Scanner sc) {
        System.out.print("请输入要删除的图书序号(id)：");
        int id = sc.nextInt();

        String sql = "delete from bookinfo where id=" + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int n = stmt.executeUpdate(sql);
            if (n > 0) System.out.println(">>> 删除成功！");
            else       System.out.println(">>> 未找到该序号，删除失败。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= 主程序：菜单选择 =================
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
