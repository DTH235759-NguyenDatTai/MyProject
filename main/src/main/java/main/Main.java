package main;

import java.awt.*;
import javax.swing.*;

import main.ui.Header;
import main.ui.pages.EmployeeManagementPanel;
import main.ui.pages.ProductManagementPanel;
import main.ui.pages.ReportManagementPanel;

public class Main extends JFrame {

    public static final String PAGE_PRODUCTS = "products";
    public static final String PAGE_EMPLOYEES = "employees";
    public static final String PAGE_REPORTS = "reports";

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Main() {
        setTitle("Hệ thống quản lý Siêu Thị");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Header header = new Header(this);
        add(header, BorderLayout.NORTH);

        // Setup card panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        ProductManagementPanel productPanel = new ProductManagementPanel();
        EmployeeManagementPanel employeePanel = new EmployeeManagementPanel();
        ReportManagementPanel reportsPanel = new ReportManagementPanel();

        cardPanel.add(productPanel, PAGE_PRODUCTS);
        cardPanel.add(employeePanel, PAGE_EMPLOYEES);
        cardPanel.add(reportsPanel, PAGE_REPORTS);

        add(cardPanel, BorderLayout.CENTER);

        // Show the default page
        cardLayout.show(cardPanel, PAGE_PRODUCTS);
    }

    public void showPage(String pageName) {
        cardLayout.show(cardPanel, pageName);
    }
}
