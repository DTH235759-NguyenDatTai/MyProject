package main.ui.pages;

import javax.swing.*;

import java.awt.*;

public class ProductManagementPanel extends JPanel{
    public ProductManagementPanel(){
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("Quản lý sản phẩm");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        // Thêm một chút khoảng trống trên và dưới tiêu đề
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        add(titleLabel, BorderLayout.NORTH);

        // Khu vực nội dung chính, sau này bạn có thể thêm bảng (JTable), nút (JButton), v.v.
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("Nội dung chi tiết cho quản lý sản phẩm sẽ được hiển thị ở đây."));
        add(contentPanel, BorderLayout.CENTER);
    }
}
