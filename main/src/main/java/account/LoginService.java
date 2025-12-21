package account;

import java.sql.*;
import DBConnection.DBConnection;

public class LoginService {
    public boolean checkLogin(String email, String password){
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn  = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            
                // Truyền tham số vào dấu ?
                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
