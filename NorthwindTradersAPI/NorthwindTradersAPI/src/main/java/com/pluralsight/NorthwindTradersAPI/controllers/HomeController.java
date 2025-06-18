package com.pluralsight.NorthwindTradersAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String defaultRequest(@RequestParam (defaultValue = " World") String country) {
        return "Hello" + " "  + country;
    }

// get all get id for the product
}
