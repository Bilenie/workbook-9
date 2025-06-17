package com.pluralsight.NorthwindTradersSpringBoot.controllers;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    //set attribute
    @Autowired
    @Qualifier("jdbcProductDao")
    private ProductDao productDao;

    @GetMapping("/api/Products")

    public List<Product> getProduct() {
        return productDao.getAll();
    }


}
