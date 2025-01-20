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
public class Product {
  private Integer productId;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Category category;
    private List<ProductReview> reviews;
    private List<ProductImage> images;
    private List<OrderDetail> orderDetails;
    private List<FavoriteDetail> favoriteDetails;
    private List<CartItem> cartItems;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category=" + (category != null ? category.getName() : "null") +
                '}';
    }
}
