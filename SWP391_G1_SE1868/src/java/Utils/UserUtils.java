/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import entity.Customer;
import models.CustomerDAO;

/**
 *
 * @author Đạt
 */
public class UserUtils {
      // Kiểm tra tên người dùng (Name)
    public static boolean isNameValid(String name) {
        return name != null && !name.trim().isEmpty(); 
    }

    
    public static boolean isPhoneValid(String phone) {
        // Kiểm tra số điện thoại bắt đầu bằng '0' và có 10 chữ số
        return phone != null && phone.matches("^0\\d{9}$"); 
    }

    
    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA0-9]{2,7}$";
        return email != null && email.matches(emailRegex); 
    }

    
   
    public static boolean isPasswordValid(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+={}:;,.<>?/~])[A-Za-z0-9!@#$%^&*()_+={}:;,.<>?/~]{8,}$";
        // một ký tự đặc biệt và một chữ số
        return password != null && password.matches(passwordRegex); 
    }

    
    public static boolean isRepeatPasswordValid(String password, String repeatPassword) {
        return password != null && repeatPassword != null && password.equals(repeatPassword); 
    }

    
    public static boolean isTermsAccepted(boolean isChecked) {
        return isChecked; // Kiểm tra người dùng đã chọn đồng ý với điều khoản
    }
    
       // Phương thức kiểm tra email đã tồn tại trong danh sách khách hàng
    public static boolean checkEmailExists(String email) {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.checkEmailExists(email);
        
        return customer!=null ? true: false ;
    }
    
}
