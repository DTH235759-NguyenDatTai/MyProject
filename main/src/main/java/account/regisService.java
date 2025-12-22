package account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import DBConnection.DBConnection;

public class regisService {
    public boolean checkRegis(String name, String email, String password){
        String hashPass = BCrypt.hashpw(password, BCrypt.gensalt());

        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, hashPass);

            int rowAffected = ps.executeUpdate();
            return rowAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
        
    }
}
