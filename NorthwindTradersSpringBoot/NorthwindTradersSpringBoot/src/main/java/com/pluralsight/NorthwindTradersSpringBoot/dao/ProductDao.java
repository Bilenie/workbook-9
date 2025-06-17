package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;

import java.util.List;

public interface ProductDao {

    void add(Product product);

    List<Product> getAll();

    boolean deleteById(int id);

    boolean update(Product product);

    List<Product> searchByName(String name);

}
