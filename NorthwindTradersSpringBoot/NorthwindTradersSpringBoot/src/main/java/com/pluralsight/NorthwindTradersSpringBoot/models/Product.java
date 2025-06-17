package com.pluralsight.NorthwindTradersSpringBoot.models;

public class Product {

    //Set attribute

    private int productId;
    private String productName;
    private int categoryId;
    private double unitPrice;

    //Generate Constructor

    //default constructor with no parameters
    public Product() {
    }

    public Product(int productId, String productName, int categoryId, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;

    }

    //Generate getter and setter

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s | %-20s | %-12s | $%8.2f",
                "ID: " + productId,
                "Name: " + productName,
                "Category ID: " + categoryId,
                unitPrice);
    }
    //String.format("%-12s | %-12s | %-18s | %-18s | %18.2f, %s", date.toString(), time.toString(), description, vendor, amount, type);
}
