package account;

import javax.swing.*;
import java.awt.*;
import model.User;

public class Login extends JFrame {

    private static final String LOGIN = "login";
    private static final String REGISTER = "register";

    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Login fields
    private JTextField tfEmail;
    private JPasswordField tfPass;
    private LoginService loginService;

    // Register fields
    private JTextField tfUsernameReg;
    private JTextField tfEmailReg;
    private JPasswordField tfPassReg;
    private RegisService regisService;

    public Login() {
        setTitle("Hệ thống tài khoản");
        setSize(400, 380);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        loginService = new LoginService();
        regisService = new RegisService();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createLoginPanel(), LOGIN);
        cardPanel.add(createRegisterPanel(), REGISTER);

        add(cardPanel);
        cardLayout.show(cardPanel, LOGIN); // 👉 chủ động hiển thị Login

        setVisible(true);
    }

    // ================= LOGIN PANEL =================
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(200, 230, 255));
        GridBagConstraints gbc = baseGbc();

        JLabel lblTitle = createTitle("LOGIN");
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        gbc.gridy = 1;
        panel.add(new JLabel("Email:"), gbc);

        tfEmail = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        tfPass = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(tfPass, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttons.setOpaque(false);

        JButton btnLogin = new JButton("Đăng nhập");
        JButton btnRegis = new JButton("Đăng ký");

        buttons.add(btnLogin);
        buttons.add(btnRegis);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(buttons, gbc);

        btnLogin.addActionListener(e -> performLogin());
        btnRegis.addActionListener(e ->
                cardLayout.show(cardPanel, REGISTER)
        );

        return panel;
    }

    // ================= REGISTER PANEL =================
    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(230, 245, 255));
        GridBagConstraints gbc = baseGbc();

        JLabel lblTitle = createTitle("REGISTER");
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        gbc.gridy = 1;
        panel.add(new JLabel("Tên người dùng:"), gbc);

        tfUsernameReg = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfUsernameReg, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Email:"), gbc);

        tfEmailReg = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfEmailReg, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Password:"), gbc);

        tfPassReg = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(tfPassReg, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttons.setOpaque(false);

        JButton btnRegister = new JButton("Đăng ký");
        JButton btnBack = new JButton("Quay lại");

        buttons.add(btnRegister);
        buttons.add(btnBack);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(buttons, gbc);

        btnRegister.addActionListener(e -> performRegis());
        btnBack.addActionListener(e ->
                cardLayout.show(cardPanel, LOGIN)
        );

        return panel;
    }

    // ================= LOGIN LOGIC =================
    private void performLogin() {
        String email = tfEmail.getText().trim();
        String password = new String(tfPass.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng không để trống Email/Password!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        User user = loginService.login(email, password);

        if (user != null) {
            Session.isLogin = true;
            Session.username = user.getUsername();
            Session.email = user.getEmail();
            JOptionPane.showMessageDialog(this,
                    "Đăng nhập thành công!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Tài khoản hoặc mật khẩu không chính xác!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performRegis() {
        String name = tfUsernameReg.getText().trim();
        String email = tfEmailReg.getText().trim();
        String password = new String(tfPassReg.getPassword()).trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập đầy đủ thông tin!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        boolean isSuccess = regisService.checkRegis(name, email, password);

        if (isSuccess) {
            JOptionPane.showMessageDialog(this,
                    "Đăng ký thành công!\nChào mừng " + name,
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            tfUsernameReg.setText("");
            tfEmailReg.setText("");
            tfPassReg.setText("");

            cardLayout.show(cardPanel, LOGIN);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Email đã tồn tại hoặc lỗi hệ thống!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }


    // ================= UTILS =================
    private GridBagConstraints baseGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        return gbc;
    }

    private JLabel createTitle(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 26));
        lbl.setForeground(new Color(0, 51, 153));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        return lbl;
    }
}
