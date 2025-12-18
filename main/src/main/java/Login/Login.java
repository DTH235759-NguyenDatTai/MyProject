package Login;

import java.awt.Color;
import javax.swing.*;

public class Login extends JFrame{
    public Login() {
        this.setTitle("Đăng nhập hệ thống");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(200, 230, 255));
    }
}
