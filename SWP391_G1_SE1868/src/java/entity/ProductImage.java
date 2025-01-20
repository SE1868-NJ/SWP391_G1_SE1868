/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDateTime;

/**
 *
 * @author Đạt
 */
public class ProductImage {
     private Integer productImageId;
    private Product product; // Many-to-One
    private String imageUrl;
    private LocalDateTime createdAt;
}
