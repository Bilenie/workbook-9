package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    //setting attribute
    private BasicDataSource dataSource;

//Generate Constructor

    // The @Autowired annotation tells Spring to "inject" the DataSource Bean here.
    @Autowired
    public JdbcProductDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    //Abstract custom method that we are implementing the interface from.

    @Override
    public void add(Product product) {

        // This is the SQL INSERT statement we will run.
        // We are inserting the film title, rental rate, and language_id.

        // This is a "try-with-resources" block.
        // It ensures that the Connection and PreparedStatement are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("""
                     
                             INSERT INTO products ( ProductName, CategoryID, UnitPrice) VALUES ( ?, ?,?);
                     
                     """)) {

            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setDouble(3, product.getUnitPrice());

            // Execute the INSERT statement — this will add the row to the database.
            stmt.executeUpdate();

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

    }

    @Override
    public List<Product> getAll() {
        // Create an empty list to hold the Product objects we will retrieve.
        List<Product> products = new ArrayList<>();

        // This is the SQL SELECT statement we will run.
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM products";
/*  try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {*/

        // This is a "try-with-resources" block.
        // It ensures that the Connection, Statement, and ResultSet are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            // Loop through each row in the ResultSet.
            while (rs.next()) {
                // Create a new Film object.
                Product product = new Product();

                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setCategoryId(rs.getInt("CategoryID"));
                product.setUnitPrice(rs.getDouble("UnitPrice"));

                // Add the Film object to our list.
                products.add(product);
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        // Return the list of Film objects.
        return products;
    }

    // DELETE a product by ID
    public boolean deleteById(int id) {

        try (Connection conn = dataSource.getConnection()) {

            // First delete from order details the parent that hold productID
            try (PreparedStatement deleteDetails = conn.prepareStatement("""
                            DELETE FROM `order details` WHERE ProductID = ?;
                    """)) {
                deleteDetails.setInt(1, id);
                deleteDetails.executeUpdate();
            }

            String sql = "DELETE FROM products WHERE ProductID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //  UPDATE a product by ID
    public boolean update(Product product) {
        String sql = """
                    UPDATE products 
                    SET ProductName = ?, CategoryID = ?, UnitPrice = ?
                    WHERE ProductID = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setDouble(3, product.getUnitPrice());
            stmt.setInt(4, product.getProductId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //  SEARCH products by name
    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM products WHERE ProductName LIKE ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("ProductID"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setCategoryId(rs.getInt("CategoryID"));
                    product.setUnitPrice(rs.getDouble("UnitPrice"));
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

}


