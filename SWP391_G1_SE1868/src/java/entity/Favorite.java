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
public class Favorite {
    private Integer favoriteId;
    private Customer customer;
    private String name;
    private LocalDateTime createdAt;

    private List<FavoriteDetail> favoriteDetails;
}
