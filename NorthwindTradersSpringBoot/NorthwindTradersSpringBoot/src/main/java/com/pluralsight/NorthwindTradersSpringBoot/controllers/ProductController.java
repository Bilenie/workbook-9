package com.pluralsight.NorthwindTradersSpringBoot.controllers;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    //set attribute
    @Autowired
    @Qualifier("jdbcProductDao")
    private ProductDao productDao;

    // GET all products
    @GetMapping("/api/Products")
    public List<Product> getProduct() {
        return productDao.getAll();
    }

    //  DELETE a product by ID
    @DeleteMapping("/api/Products/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean deleted = productDao.deleteById(id);
        if (deleted) {
            return "Product with ID " + id + " deleted.";
        } else {
            return "Product with ID " + id + " not found.";
        }
    }

    //  UPDATE a product by ID (must send JSON in request body)
    @PutMapping("/api/Products/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setProductId(id); // Set the path ID
        boolean updated = productDao.update(product);
        if (updated) {
            return "Product with ID " + id + " updated.";
        } else {
            return "Product with ID " + id + " not found.";
        }
    }

    @GetMapping("/")
    public String defaultRequest(@RequestParam(defaultValue = "Java is cool")String name){
        return "Beautiful Woman/man" + " " + name;
    }

}
