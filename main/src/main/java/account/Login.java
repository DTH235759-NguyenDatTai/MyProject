package account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
    // Khai báo các thành phần cần truy cập trong hàm xử lý
    private JTextField tfEmail;
    private JPasswordField tfPass;
    private LoginService loginService;
    private register regis;

    public Login() {
        // 1. Cấu hình cơ bản cho Frame
        setTitle("Đăng nhập hệ thống");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(200, 230, 255)); // Màu xanh biển nhẹ

        // Khởi tạo service xử lý logic
        loginService = new LoginService();

        // 2. Thiết lập Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Dòng 0: Tiêu đề LOGIN ---
        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(new Color(0, 51, 153));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 25, 10);
        add(lblTitle, gbc);

        // Reset lại insets cho các thành phần bên dưới
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);

        // --- Dòng 1: Email ---
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);

        tfEmail = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        add(tfEmail, gbc);

        // --- Dòng 2: Password ---
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        tfPass = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        add(tfPass, gbc);

        // --- Dòng 3: Nút bấm ---
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        pnlButtons.setOpaque(false);

        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setPreferredSize(new Dimension(110, 35));
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);

        JButton btnRegis = new JButton("Đăng ký");
        btnRegis.setPreferredSize(new Dimension(110, 35));
        btnRegis.setBackground(new Color(207, 100, 12));
        btnRegis.setForeground(Color.WHITE);
        btnRegis.setFocusPainted(false);

        pnlButtons.add(btnLogin);
        pnlButtons.add(btnRegis);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(pnlButtons, gbc);

        // 3. Xử lý sự kiện
        // Nút Hủy bỏ
        btnRegis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regis = new register();
                regis.setVisible(true);
                Login.this.dispose();
            }
        });

        // Nút Đăng nhập
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String email = tfEmail.getText().trim();
        String password = new String(tfPass.getPassword());

        // Kiểm tra nhanh rỗng
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống Email/Password!");
            return;
        }

        // Gọi Service để kiểm tra DB
        boolean success = loginService.checkLogin(email, password);

        if (success) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            this.dispose(); 
            // Ở đây bạn có thể gọi màn hình Dashboard mới: new Dashboard().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}