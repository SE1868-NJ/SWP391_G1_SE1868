/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author Đạt
 */
public class ConfigVNPay {
    public static final String vnp_Version = "2.1.0";
    public static final String vnp_Command = "pay";
    public static final String vnp_TmnCode = "X7K47OSS"; // Lấy từ VNPAY
    public static final String vnp_HashSecret = "12NXS1K58IEOZIMXHJBZ90AC2MM91N1G"; // Lấy từ VNPAY
    public static final String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html"; // URL thanh toán VNPAY
    public static final String vnp_ReturnUrl = "http://localhost:9999/home"; // URL nhận callback từ VNPAY
}

