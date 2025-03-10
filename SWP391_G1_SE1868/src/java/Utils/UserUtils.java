/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import entity.Customer;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import models.CustomerDAO;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        return customer != null ? true : false;
    }

    public static boolean isValidAge(LocalDate birthDate, int requiredAge) {
        if (birthDate == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();
        return age >= requiredAge;
    }

    //  Kiểm tra nếu `UpdatedAt` còn hiệu lực hay không
    public static boolean isUpdatedAtValid(int customerId, int allowedMinutes) throws SQLException {
        CustomerDAO customerDAO = new CustomerDAO();
        Timestamp dbTimestamp = customerDAO.getUpdatedAtById(customerId);

        if (dbTimestamp == null) {
            System.out.println(" Không tìm thấy dữ liệu UpdatedAt trong database.");
            return false;
        }

        // Chuyển Timestamp sang LocalDateTime
        LocalDateTime dbTime = dbTimestamp.toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();

        // Tính khoảng cách thời gian
        long minutesDiff = Duration.between(dbTime, now).toMinutes();

        // Trả về tue nếu thời gian chưa vượt quá allowedMinutes
        return minutesDiff <= allowedMinutes;
    }

    // Hàm tạo mật khẩu ngẫu nhiên
    public static String generateRandomPassword(int length) {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*(),.?\":{}|<>";

        // Tất cả các ký tự có thể dùng
        String allChars = lowerCase + upperCase + numbers + specialChars;

        // Đảm bảo mật khẩu có ít nhất một ký tự số, một ký tự đặc biệt và một chữ cái
        StringBuilder password = new StringBuilder();

        password.append(numbers.charAt((int) (Math.random() * numbers.length()))); // ít nhất một số
        password.append(specialChars.charAt((int) (Math.random() * specialChars.length()))); // ít nhất một ký tự đặc biệt
        password.append(lowerCase.charAt((int) (Math.random() * lowerCase.length()))); // ít nhất một chữ cái

        // Tạo các ký tự còn lại ngẫu nhiên
        for (int i = password.length(); i < length; i++) {
            password.append(allChars.charAt((int) (Math.random() * allChars.length())));
        }

        // Trộn các ký tự trong mật khẩu để đảm bảo tính ngẫu nhiên
        List<String> passwordList = new ArrayList<>(Arrays.asList(password.toString().split(""))); // Chuyển thành danh sách
        Collections.shuffle(passwordList); // Xáo trộn danh sách

        // Tạo một StringBuilder để lưu mật khẩu đã được trộn
        StringBuilder shuffledPassword = new StringBuilder();
        for (String s : passwordList) {
            shuffledPassword.append(s); // Duyệt qua danh sách đã xáo trộn
        }

        return shuffledPassword.toString(); // Trả lại mật khẩu đã xáo trộn
    }

    
    public static void main(String[] args) throws SQLException {
        System.out.println(UserUtils.isUpdatedAtValid(21, 60));
    }

}
