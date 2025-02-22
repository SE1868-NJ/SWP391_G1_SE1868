/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Đạt
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Customer {

    private int customerId;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
    private String gender;  // 'Male' or 'Female'
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String profileImage;
    private boolean isVerify;
    private List<Order> orders;
    private List<Favorite> favorites;
    private List<Cart> carts;

    public Customer() {
    }

    public Customer(int customerId, String fullName, String email, String password, String phoneNumber, String address, LocalDate birthDate, String gender, LocalDate createdAt, LocalDate updatedAt, String profileImage, boolean isVerify) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profileImage = profileImage;
        this.isVerify = isVerify;
    }

    public Customer(int customerId, String fullName, String email, String password, String phoneNumber, String address, LocalDate birthDate, String gender, LocalDate createdAt, LocalDate updatedAt, String profileImage, boolean isVerify, List<Order> orders, List<Favorite> favorites, List<Cart> carts) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profileImage = profileImage;
        this.isVerify = isVerify;
        this.orders = orders;
        this.favorites = favorites;
        this.carts = carts;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isIsVerify() {
        return isVerify;
    }

    public void setIsVerify(boolean isVerify) {
        this.isVerify = isVerify;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        StringBuilder cartsInfo = new StringBuilder();

        if (carts != null && !carts.isEmpty()) {
            for (Cart cart : carts) {
                cartsInfo.append("\n  Cart ID: ").append(cart.getCartId())
                        .append(", Created At: ").append(cart.getCreatedAt());
                
                
                // Hiển thị danh sách CartItem của mỗi Cart
                if (cart.getCartItems() != null && !cart.getCartItems().isEmpty()) {
                    cartsInfo.append("\n   Cart Items:");
                    for (CartItem item : cart.getCartItems()) {
                        cartsInfo.append("\n     - Item ID: ").append(item.getCartItemId())
                                .append(", Product: ").append(item.getProduct().getName())
                                .append(", Quantity: ").append(item.getQuantity())
                                .append(", Added At: ").append(item.getAddedAt());
                    }
                } else {
                    cartsInfo.append("\n    No Cart Items.");
                }
            }
        } else {
            cartsInfo.append("\n  No Carts.");
        }

        return "Customer{"
                + "customerId=" + customerId
                + ", fullName='" + fullName + '\''
                + ", email='" + email + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", address='" + address + '\''
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + ", carts=" + cartsInfo.toString()
                + "\n}";
    }

}
