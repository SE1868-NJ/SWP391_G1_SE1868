/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.util.Properties;

/**
 *
 * @author Đạt
 */
public class EmailConfig {
      public static Properties getMailProperties() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");  //  Thiết lập địa chỉ của SMTP(Simple Mail Transfer Protocol) Server
        props.setProperty("mail.smtp.port", "587");  // Thiết lập cổng (port) để giao tiếp với SMTP server
        props.setProperty("mail.smtp.auth", "true");  // Bật chế độ xác thực (authentication).
        props.setProperty("mail.smtp.starttls.enable", "true");  //  Bật STARTTLS để bảo mật kết nối và mã hóa dữ liệu giữa ứng dụng và SMTP server
        return props;
    }
      
      
      public static String getEmailUsername() {
        return "datnhhe172337@fpt.edu.vn"; // Thay bằng email người gửi
    }

    public static String getEmailPassword() {
        return "brzc pusz tupb rvli  11"; // Thay bằng mật khẩu email 
    }
}
