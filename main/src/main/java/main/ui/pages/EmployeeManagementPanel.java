package main.ui.pages;

import javax.swing.*;
import java.awt.*;

public class EmployeeManagementPanel extends JPanel {

    public EmployeeManagementPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createFilterPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createActionPanel(), BorderLayout.SOUTH);
    }

    /* ================= FILTER PANEL ================= */
    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));

        JTextField txtSearch = new JTextField(20);
        JComboBox<String> cbDepartment = new JComboBox<>(
                new String[]{"Tất cả phòng ban", "Bán hàng", "Kho", "Kế toán"}
        );
        JComboBox<String> cbStatus = new JComboBox<>(
                new String[]{"Tất cả trạng thái", "Đang làm", "Nghỉ việc"}
        );

        panel.add(new JLabel("🔍 Tìm kiếm:"));
        panel.add(txtSearch);
        panel.add(new JLabel("Phòng ban:"));
        panel.add(cbDepartment);
        panel.add(new JLabel("Trạng thái:"));
        panel.add(cbStatus);

        return panel;
    }

    /* ================= TABLE PANEL ================= */
    private JScrollPane createTablePanel() {
        String[] columns = {
                "Mã NV", "Họ tên", "Phòng ban", "Chức vụ", "Lương", "Trạng thái"
        };

        Object[][] data = {};

        JTable table = new JTable(data, columns);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return new JScrollPane(table);
    }

    /* ================= ACTION PANEL ================= */
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton btnAdd = new JButton("➕ Thêm");
        JButton btnEdit = new JButton("✏️ Sửa");
        JButton btnView = new JButton("👁 Xem");
        JButton btnDeactivate = new JButton("⏸ Nghỉ việc");
        JButton btnDelete = new JButton("❌ Xóa");

        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnView);
        panel.add(btnDeactivate);
        panel.add(btnDelete);

        return panel;
    }
}
