
package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SimpleProductDao implements ProductDao {

    private List<Product> products;

    // This is used to assign a unique ID to each new Product
    // Every time we add a Product, we will increase this number by 1.
    private int nextId = 1;

    // This is the constructor.
    // It runs when we create a new SimpleProductDao object.

    public SimpleProductDao() {

        // We create an empty list to hold our products.
        products = new ArrayList<>();

        // Let's add some sample products so the list is not empty when the app starts.
        products.add(new Product(nextId++, "Coke", 1, 2.99));
        products.add(new Product(nextId++, "Turkey", 6, 20.00));
        products.add(new Product(nextId++, "Milk", 4, 9.99));
    }

    // This method is required because we are implementing the ProductDao interface.
    // It allows us to add a new Film to the list.
    @Override
    public void add(Product product) {

        // We assign the next unique ID to the product.
        product.setProductId(nextId++);

        // We add the product to the list.
        products.add(product);
    }

    // This method is required because we are implementing the ProductDao interface.
    // It returns the list of all stored products.
    @Override
    public List<Product> getAll() {
        // Return the list of products.
        return products;
    }
}




