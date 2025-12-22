package account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class register extends JFrame{
    private JTextField tfEmail;
    private JTextField tfName;
    private JPasswordField tfPass;
    private regisService regis;

    public register(){
        // 1. Cấu hình cơ bản cho Frame
        setTitle("Đăng ký tài khoản");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(200, 230, 255)); // Màu xanh biển nhẹ

        // Khởi tạo service xử lý logic
        regis = new regisService();

        // 2. Thiết lập Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Dòng 0: Tiêu đề REGIS ---
        JLabel lblTitle = new JLabel("REGISTER");
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

        // --- Dòng 1: Name ---
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Tên người dùng:"), gbc);

        tfName = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        add(tfName, gbc);

        // --- Dòng 2: Email ---
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Email:"), gbc);

        tfEmail = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        add(tfEmail, gbc);

        // --- Dòng 3: Pass ---
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Password:"), gbc);

        tfPass = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 3;
        add(tfPass, gbc);        

        // --- Dòng 4: Btn ---
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
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(pnlButtons, gbc);

        // 3. Xử lý sự kiện
        btnRegis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performRegis();
            }
        });

        // Nút Đăng nhập
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                register.this.dispose();
            }
        });
    }

    public void performRegis(){
        String name = tfName.getText().trim();
        String email = tfEmail.getText().trim();
        String password = new String(tfPass.getPassword());

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!",
                 "Thông báo", JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        boolean isSuccess = regis.checkRegis(name, email, password);
        
        if(isSuccess){
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!" + "\nChào mừng " + name,
                 "Thông báo", JOptionPane.INFORMATION_MESSAGE
            );
            this.dispose();
            new Login().setVisible(true);           
        }

        else{
            JOptionPane.showMessageDialog(this, "Đăng ký thất bại. Lỗi hệ thống!",
                 "Thông báo", JOptionPane.ERROR_MESSAGE
            );
            return;
        }
    }
}
