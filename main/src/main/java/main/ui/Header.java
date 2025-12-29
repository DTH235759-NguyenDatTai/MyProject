package main.ui;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;

import account.Session;
import main.Main;

public class Header extends JPanel {

    private Main mainFrame;
    private JButton activeBtn;

    public Header(Main mainFrame) {
        this.mainFrame = mainFrame;

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

        // Menu: Text hiển thị -> Page name
        Map<String, String> menuItems = new LinkedHashMap<>();
        menuItems.put("Quản lý sản phẩm", Main.PAGE_PRODUCTS);
        menuItems.put("Quản lý nhân viên", Main.PAGE_EMPLOYEES);
        menuItems.put("Báo cáo", Main.PAGE_REPORTS);

        for (Map.Entry<String, String> entry : menuItems.entrySet()) {
            String buttonText = entry.getKey();
            String pageName = entry.getValue();

            JButton btn = new JButton(buttonText);
            
            setNormalStyle(btn);

            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            btn.addActionListener(e -> {
                if(activeBtn != null){
                    setNormalStyle(activeBtn);
                }
                setActiveStyle(btn);
                activeBtn = btn;

                this.mainFrame.showPage(pageName);
            });

            centerPanel.add(btn);

            if(activeBtn == null){
                setActiveStyle(btn);
                activeBtn = btn;
            }
        }

        /* ===== RIGHT ===== */
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);

        String username = (Session.username != null) ? Session.username : "Khách";
        JLabel lblUserName = new JLabel("Xin chào, " + username);
        lblUserName.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lblUserName.setForeground(Color.WHITE);

        rightPanel.add(lblUserName);

        /* ===== ADD ===== */
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private void setActiveStyle(JButton btn) {
        btn.setForeground(new Color(34, 134, 34)); 
        btn.setFont(new Font("Segoe UI", Font.BOLD, 24)); // to hơn
    }

    private void setNormalStyle(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    }
}
