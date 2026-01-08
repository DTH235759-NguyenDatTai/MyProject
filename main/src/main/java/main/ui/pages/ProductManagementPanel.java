package main.ui.pages;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class ProductManagementPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private ProductDAO productDAO;

    public ProductManagementPanel() {
        productDAO = new ProductDAO();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createFilterPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createActionPanel(), BorderLayout.SOUTH);

        loadData();
    }

    /* ================= FILTER PANEL ================= */
    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));

        JTextField txtSearch = new JTextField(20);
        JComboBox<String> cbProductType = new JComboBox<>(
            new String[]{"Tất cả sản phẩm", "Thực phẩm tươi sống", "Đồ ăn chế biến", "Bơ - Trứng - Sữa", "Thực phẩm đông lạnh",
            "Bánh kẹo các loại", "Đồ hộp đồ khô", "Dầu ăn - gia vị - nước chấm", "Đồ uống tổng hợp", "Nước giải khát", 
            "Đồ uống có cồn", "Chăm sóc cá nhân", "Vệ sinh nhà cửa", "Thực phẩm chức năng", "Đồ gia dụng", "Thiết bị gia dụng điện tử" 
        });
        JComboBox<String> cbStatus = new JComboBox<>(
                new String[]{"Tất cả trạng thái", "Còn hàng", "Hết hàng"}
        );

        panel.add(new JLabel("🔍 Tìm kiếm:"));
        panel.add(txtSearch);
        panel.add(new JLabel("Danh mục sản phẩm:"));
        panel.add(cbProductType);
        panel.add(new JLabel("Trạng thái:"));
        panel.add(cbStatus);

        return panel;
    }

    /* ================= TABLE PANEL ================= */
    private JScrollPane createTablePanel() {
        String[] columns = {
                "ID", "Tên sản phẩm", "Giá", "Số lượng", "Danh mục", "Mô tả", "Ngày nhập", "HSD"
        };

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return new JScrollPane(table);
    }

    /* ================= ACTION PANEL ================= */
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton btnAdd = new JButton("➕ Thêm");
        JButton btnEdit = new JButton("✏️ Sửa");
        JButton btnDelete = new JButton("❌ Xóa");
        JButton btnRefresh = new JButton("🔄 Làm mới");

        btnAdd.addActionListener(e -> showProductDialog(null));
        btnEdit.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                // In a real app, you might want to fetch fresh data from DB or store Product objects in the model
                // Here we can fetch from DB again or reconstruct from table if columns match
                // Let's simplified by getting basic info from table or just fetching from DB if needed
                // For now, let's create a Product object from table data
                Product p = new Product();
                p.setId(id);
                p.setName((String) tableModel.getValueAt(selectedRow, 1));
                p.setPrice((Double) tableModel.getValueAt(selectedRow, 2));
                p.setQuantity((Integer) tableModel.getValueAt(selectedRow, 3));
                p.setCategory((String) tableModel.getValueAt(selectedRow, 4));
                p.setDescription((String) tableModel.getValueAt(selectedRow, 5));
                p.setEntryDate((Date) tableModel.getValueAt(selectedRow, 6));
                p.setExpiryDate((Date) tableModel.getValueAt(selectedRow, 7));
                // Note: Image path is missing in table, might be null or we need to handle it
                showProductDialog(p);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để sửa");
            }
        });
        btnDelete.addActionListener(e -> deleteProduct());
        btnRefresh.addActionListener(e -> loadData());

        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnDelete);
        panel.add(btnRefresh);

        return panel;
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Product> list = productDAO.getAllProducts();
        for (Product p : list) {
            tableModel.addRow(new Object[]{
                    p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getCategory(),
                    p.getDescription(), p.getEntryDate(), p.getExpiryDate()
            });
        }
    }

    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (productDAO.deleteProduct(id)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa");
        }
    }

    private void showProductDialog(Product product) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), product == null ? "Thêm sản phẩm" : "Sửa sản phẩm", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtName = new JTextField(product != null ? product.getName() : "");
        JTextField txtPrice = new JTextField(product != null ? String.valueOf(product.getPrice()) : "");
        JTextField txtQuantity = new JTextField(product != null ? String.valueOf(product.getQuantity()) : "");
        // Use the same categories
         String[] categories = {"Thực phẩm tươi sống", "Đồ ăn chế biến", "Bơ - Trứng - Sữa", "Thực phẩm đông lạnh",
            "Bánh kẹo các loại", "Đồ hộp đồ khô", "Dầu ăn - gia vị - nước chấm", "Đồ uống tổng hợp", "Nước giải khát", 
            "Đồ uống có cồn", "Chăm sóc cá nhân", "Vệ sinh nhà cửa", "Thực phẩm chức năng", "Đồ gia dụng", "Thiết bị gia dụng điện tử"};
        JComboBox<String> cbCategory = new JComboBox<>(categories);
        if (product != null) cbCategory.setSelectedItem(product.getCategory());

        JTextArea txtDesc = new JTextArea(product != null ? product.getDescription() : "");
        JTextField txtEntryDate = new JTextField(product != null && product.getEntryDate() != null ? product.getEntryDate().toString() : "2023-01-01");
        JTextField txtExpiryDate = new JTextField(product != null && product.getExpiryDate() != null ? product.getExpiryDate().toString() : "2023-12-31");

        formPanel.add(new JLabel("Tên sản phẩm:")); formPanel.add(txtName);
        formPanel.add(new JLabel("Giá:")); formPanel.add(txtPrice);
        formPanel.add(new JLabel("Số lượng:")); formPanel.add(txtQuantity);
        formPanel.add(new JLabel("Danh mục:")); formPanel.add(cbCategory);
        formPanel.add(new JLabel("Mô tả:")); formPanel.add(new JScrollPane(txtDesc));
        formPanel.add(new JLabel("Ngày nhập (YYYY-MM-DD):")); formPanel.add(txtEntryDate);
        formPanel.add(new JLabel("HSD (YYYY-MM-DD):")); formPanel.add(txtExpiryDate);

        JPanel btnPanel = new JPanel();
        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");

        btnSave.addActionListener(e -> {
            try {
                String name = txtName.getText();
                double price = Double.parseDouble(txtPrice.getText());
                int quantity = Integer.parseInt(txtQuantity.getText());
                String category = (String) cbCategory.getSelectedItem();
                String desc = txtDesc.getText();
                Date entryDate = Date.valueOf(txtEntryDate.getText());
                Date expiryDate = Date.valueOf(txtExpiryDate.getText());

                Product p = product != null ? product : new Product();
                p.setName(name);
                p.setPrice(price);
                p.setQuantity(quantity);
                p.setCategory(category);
                p.setDescription(desc);
                p.setEntryDate(entryDate);
                p.setExpiryDate(expiryDate);
                p.setImagePath(""); // Placeholder

                boolean success;
                if (product == null) {
                    success = productDAO.addProduct(p);
                } else {
                    success = productDAO.updateProduct(p);
                }

                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Lưu thành công!");
                    dialog.dispose();
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Lưu thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Lỗi nhập liệu: " + ex.getMessage());
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(btnPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
