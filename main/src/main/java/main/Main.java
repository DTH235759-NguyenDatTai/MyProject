package main;

import java.awt.*;
import javax.swing.*;

import main.ui.Header;

public class Main extends JFrame {

    public Main() {
        setTitle("Hệ thống quản lý Siêu Thị");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Header header = new Header(this);
        add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        add(content, BorderLayout.CENTER);
    }
}
