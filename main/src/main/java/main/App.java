package main;

import javax.swing.*;
import java.awt.event.*;
import account.Login;
import account.Session;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Mở form Login trước
            Login login = new Login();

            // Theo dõi khi Login đóng
            login.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    // Nếu login thành công mới mở Main
                    if (Session.isLogin) {
                        new Main().setVisible(true);
                    } else {
                        System.exit(0); // không login thì thoát app
                    }
                }
            });
        });
    }
}
