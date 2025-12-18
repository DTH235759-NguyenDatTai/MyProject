package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 1. Cấu hình thông số (Thay đổi theo DB của bạn)
            String url = "jdbc:mysql://localhost:3306/sieuthi";
            String user = "root";
            String password = "vertrigo";

            // 2. Thiết lập kết nối
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
            
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        getConnection(); // Chạy thử để kiểm tra
    }
}