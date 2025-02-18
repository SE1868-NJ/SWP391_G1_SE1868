/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


/**
 *
 * @author Admin
 */
public class Product {
    private String name;
    private String imageURL;
    private double price;
    private int stockQuantity;

    public Product(String name, String imageURL, double price, int stockQuantity) {
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}

