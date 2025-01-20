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
public class Shipper {
     private Integer shipperId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Order> orders;

    @Override
    public String toString() {
        return "Shipper{" +
                "shipperId=" + shipperId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
