/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.Properties;                      // Quản lý cấu hình SMTP
import javax.mail.Session;                        // Phiên làm việc với server SMTP
import javax.mail.Authenticator;                 // Xác thực người dùng
import javax.mail.PasswordAuthentication;        // Lưu thông tin username và password
import javax.mail.Message;                       // Đại diện cho một email
import javax.mail.internet.MimeMessage;          // Email với định dạng MIME
import javax.mail.internet.InternetAddress;      // Địa chỉ email
import javax.mail.Transport;                     // Gửi email qua server SMTP
import javax.mail.MessagingException;           // Ngoại lệ khi gửi email

import config.EmailConfig;

/**
 *
 * @author Đạt
 */
public class EmailService {

    private Session session;

    public EmailService() {
        Properties props = EmailConfig.getMailProperties(); // Lấy cấu hình từ EmailConfig

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConfig.getEmailUsername(), EmailConfig.getEmailPassword());
            }
        });

    }

    public boolean sendEmail(String to, String subject, String messageContent, String username, String confirmationLink) {
        try {
            // Nội dung email HTML
            String emailContent = "<html><body>"
                    + "<h2>Xác nhận tài khoản của bạn</h2>"
                    + "<p>Chào <strong>" + username + "</strong>,</p>"
                    + "<p>Cảm ơn bạn đã đăng ký tài khoản. Vui lòng xác nhận email của bạn bằng cách nhấn vào nút bên dưới:</p>"
                    + "<a href='" + confirmationLink + "' style='display: inline-block; padding: 12px 20px; font-size: 18px; color: white; background-color: #28a745; text-decoration: none; border-radius: 5px; font-weight: bold;'>Xác nhận tài khoản</a>"
                    + "<p>Nếu bạn không đăng ký tài khoản này, vui lòng bỏ qua email này.</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><strong>Đội ngũ hỗ trợ</strong></p>"
                    + "</body></html>";
            
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(EmailConfig.getEmailUsername())); // Người gửi

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // Người nhận
            message.setSubject(subject); // Tiêu đề email
//            message.setText(messageContent); // Nội dung email

            message.setContent(messageContent, "text/html");    // Nếu muốn gửi nội dung HTML
            Transport.send(message); // Gửi email
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public static void main(String[] args) {
//         EmailService emailService = new EmailService();
//        boolean success = emailService.sendEmail("huudat285@gmail.com", "Test", "ádgayusdguasd");
//        if(success){
//            System.out.println("DOne");
//        }else{
//            System.out.println("Not");
//        }
//    }
}
