/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

//import java.security.Timestamp;
//
//
//
//
//public class Customer {
//
//    private int customerId;
//    private String fullName;
//    private String email;
//    private String password;
//    private String phoneNumber;
//    private String address;
//    private Timestamp createdAt;
//    private Timestamp updatedAt;
//    private String otp;
//    private Timestamp otpExpiration;
//
//    // Getter and Setter Methods
//    public int getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Timestamp createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Timestamp getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Timestamp updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public String getOtp() {
//        return otp;
//    }
//
//    public void setOtp(String otp) {
//        this.otp = otp;
//    }
//
//    public Timestamp getOtpExpiration() {
//        return otpExpiration;
//    }
//
//    public void setOtpExpiration(Timestamp otpExpiration) {
//        this.otpExpiration = otpExpiration;
//    }
//}
import java.sql.*;

public class Customer {

    private int customerId;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Date birthDate;
    private String gender;
    private Date createdAt;
    private Date updatedAt;
    private String otp;
    private Date optExpiration;

    // Constructor yêu cầu 10 tham số
    public Customer(int customerId, String fullName, String email,
            String password,
            String phoneNumber,
            String address,
            Date birthDate,
            String gender,
            Date createdAt,
            Date updatedAt,
            String otp,
            Date optExpiration
    ) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getOptExpiration() {
        return optExpiration;
    }

    public void setOptExpiration(Date optExpiration) {
        this.optExpiration = optExpiration;
    }

    public String getGender() {
        return gender;
    }

    // Getters và Setters
    public void setGender(String gender) {
        this.gender = gender;
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

}
