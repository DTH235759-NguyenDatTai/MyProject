package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import account.Login;

public class Main extends JFrame{
    public Main() {
        setTitle("Hệ thống quản lý Siêu Thị");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridBagLayout());

        JButton button = new JButton("Đăng nhập");
        button.setPreferredSize(new Dimension(100, 80));
        button.setBackground(new Color(0, 12,123));
        button.setForeground(new Color(255, 255, 255));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
            }
        });
        add(button);
    }
    public static void main(String[] args) {
        // Sử dụng SwingUtilities để đảm bảo luồng giao diện (UI Thread) chạy ổn định
        SwingUtilities.invokeLater(() -> {
            Main mainApp = new Main();
            mainApp.setVisible(true);
        });
    }
}