package dao;

import java.sql.*;

import DBConnection.DBConnection;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

public class AccountDAO {

    public User login(String email, String passwordInput) {

        String sql = "SELECT id, name, email, password FROM users WHERE email=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String passwordHash = rs.getString("password");

                // So sánh password nhập với hash trong DB
                if (BCrypt.checkpw(passwordInput, passwordHash)) {

                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    return user;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // login fail
    }
}
