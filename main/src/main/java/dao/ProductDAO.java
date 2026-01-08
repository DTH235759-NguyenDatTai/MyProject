package dao;

import DBConnection.DBConnection;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategory(rs.getString("category"));
                product.setDescription(rs.getString("description"));
                product.setImagePath(rs.getString("image_path"));
                product.setEntryDate(rs.getDate("entry_date"));
                product.setExpiryDate(rs.getDate("expiry_date"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products (name, price, quantity, category, description, image_path, entry_date, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getCategory());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getImagePath());
            ps.setDate(7, product.getEntryDate());
            ps.setDate(8, product.getExpiryDate());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET name=?, price=?, quantity=?, category=?, description=?, image_path=?, entry_date=?, expiry_date=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getCategory());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getImagePath());
            ps.setDate(7, product.getEntryDate());
            ps.setDate(8, product.getExpiryDate());
            ps.setInt(9, product.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
