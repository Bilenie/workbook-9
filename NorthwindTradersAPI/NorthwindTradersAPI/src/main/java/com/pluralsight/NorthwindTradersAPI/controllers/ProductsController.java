package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Autowired

@RestController
public class ProductsController {
//@RequestParam(defaultValue = " World") String country
@Autowired
@Qualifier("ProductDao")
privat ProductCategory productDao;


    @GetMapping("/")
    public ProductDao getAllProducts() {

        return "hello tbd";
        //productDao.getAll();//getAll product
    }


    @GetMapping("/")
    public ProductDao getAllProducts() {
        return "hello tbd";
        //productDao.getAll();//getAll product
    }


}
