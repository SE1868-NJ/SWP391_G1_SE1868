/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author Đạt
 */
public class Cart {
    private Integer cartId;
    private Customer customer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<CartItem> cartItems;
}
