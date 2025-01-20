/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Đạt
 */
import java.time.LocalDateTime;
import java.util.List;
public class ProductReview {
    private Integer reviewId;
    private Product product;
    private Customer customer;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

}
