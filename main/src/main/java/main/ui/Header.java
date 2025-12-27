package main.ui;

import java.awt.*;
import javax.swing.*;

import account.*;

public class Header extends JPanel {

    public Header(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(new Color(140, 190, 95));
        setPreferredSize(new Dimension(0, 70));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        /* ===== LEFT ===== */
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftPanel.setOpaque(false);

        JLabel lblLogo = new JLabel("🏪");
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 26));

        JLabel lblTitle = new JLabel("SuperMart");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);

        leftPanel.add(lblLogo);
        leftPanel.add(lblTitle);

        /* ===== CENTER ===== */
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        centerPanel.setOpaque(false);

        String[] menus = {"Quản lý Cửa hàng", "Quản lý nhân viên", "Báo cáo"};
        for (String menu : menus) {
            JButton btn = new JButton(menu);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            centerPanel.add(btn);
        }

        /* ===== RIGHT ===== */
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);

        JLabel lbluserName = new JLabel("Xin chào, " + Session.username);
        lbluserName.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lbluserName.setForeground(Color.WHITE);
        rightPanel.add(lbluserName);

        /* ===== ADD ===== */
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

}
