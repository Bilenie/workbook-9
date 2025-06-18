package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {


    //setting the attribute for the connection
    private BasicDataSource dataSource;

    //Generate constructor to use the connection
    public JdbcProductDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    //List<Objects> getAll()
    // List<Object> searchById(int id);

    @Override
    public List<Product> getAll() {

        List<Product> products = new ArrayList<>();
        String sql = " SELECT p.* FROM products";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //run the query
          ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through each row in the ResultSet.
            while (resultSet.next()) {
                // Create a new object.
                Product product = new Product();

                product.setProductId(resultSet.getInt("ProductID"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setCategoryId(resultSet.getInt("CategoryID"));
                product.setUnitPrice(resultSet.getDouble("UnitPrice"));

                // Add the Film object to our list.
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
