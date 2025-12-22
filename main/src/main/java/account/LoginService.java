package account;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

import DBConnection.DBConnection;

public class LoginService {
    public boolean checkLogin(String email, String password){
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn  = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            
                // Truyền tham số vào dấu ?
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    String storeHash = rs.getString("password");
                    return BCrypt.checkpw(password, storeHash);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
