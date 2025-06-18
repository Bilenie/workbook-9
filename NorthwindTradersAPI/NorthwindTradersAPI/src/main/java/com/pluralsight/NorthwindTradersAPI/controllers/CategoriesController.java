package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class CategoriesController {


    @GetMapping("/")
    public List<Category> getAllCategories(@RequestParam(defaultValue = " World") String country) {
        return "Hello tbd "; //categoryDao.getAll() //Get all category
    }
    @GetMapping("/")
    public Category getCategoryById() {

        Category category;
        return category;//categoryDao.getCategoryById()
    }

}
