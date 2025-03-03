/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Đạt
 */
public class Cart {

    private int cartId;
    private Customer customer;
    private LocalDate createdAt;
    private Product product;
    private int quantity;  

    @Override
    public String toString() {
        return "Cart{" + "cartId=" + cartId + ", customer=" + customer + ", createdAt=" + createdAt + ", product=" + product + ", quantity=" + quantity + '}';
    }

    

    public Cart() {
    }

    public Cart(int cartId, Customer customer, LocalDate createdAt, Product product, int quantity) {
        this.cartId = cartId;
        this.customer = customer;
        this.createdAt = createdAt;
        this.product = product;
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
}
