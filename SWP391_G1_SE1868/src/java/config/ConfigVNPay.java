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
    public static final String vnp_HashSecret = "MYZ1HCUPOPZGN067ID1B175IDZV759I7"; // Lấy từ VNPAY
    public static final String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html"; // URL thanh toán VNPAY
    public static final String vnp_ReturnUrl = "https://www.google.com.vn/?hl=vi"; // URL nhận callback từ VNPAY
}

