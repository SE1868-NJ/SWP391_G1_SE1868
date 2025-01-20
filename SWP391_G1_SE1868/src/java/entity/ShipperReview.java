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
public class ShipperReview {
      private Integer reviewId;
    private Shipper shipper; // Many-to-One
    private Customer customer; // Many-to-One
    private Order order; // Many-to-One
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

}
