/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import config.ConfigVNPay;
import Utils.VNPayUtils;
import response.VNPayResponse;

/**
 *
 * @author Đạt
 */
public class VNPayService {

    //  tạo URL thanh toán
    public static String createPaymentUrl(int amount, String orderInfo, String ipAddress) {
        String vnp_TxnRef = String.valueOf(System.currentTimeMillis()); // Mã giao dịch duy nhất
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", ConfigVNPay.vnp_Command);
        vnp_Params.put("vnp_TmnCode", ConfigVNPay.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount * 100)); // VNPay yêu cầu số tiền nhân 100
        vnp_Params.put("vnp_BankCode", "VNBANK");
        vnp_Params.put("vnp_CreateDate", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_IpAddr", ipAddress);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_ReturnUrl", ConfigVNPay.vnp_ReturnUrl);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);

        String hashData = VNPayUtils.createQueryString(vnp_Params);

        StringBuilder query = new StringBuilder(VNPayUtils.createQueryString(vnp_Params));

        // tạo chữ ký (secure hash)
        String vnp_SecureHash = VNPayUtils.hmacSHA512(ConfigVNPay.vnp_HashSecret, hashData);

        query.append("&vnp_SecureHash=").append(vnp_SecureHash);

        // trả về URL thanh toán hoàn chỉnh
        return ConfigVNPay.vnp_PayUrl + "?" + query.toString();
    }

    public static VNPayResponse validateResponse(Map<String, String> fields, String vnp_SecureHash) {
        // Tạo chuỗi signValue từ các tham số trong fields
        
        //fields.remove("vnp_SecureHash");
        
        String signValue = String.join("&", fields.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sắp xếp theo key
                .map(entry -> entry.getKey() + "=" + entry.getValue()) // Tạo chuỗi key=value
                .toArray(String[]::new));

        
        String computedHash = VNPayUtils.hmacSHA512(ConfigVNPay.vnp_HashSecret, signValue);

            
        // So sánh chữ ký tính toán với chữ ký từ VNPay
        if (computedHash.equalsIgnoreCase(vnp_SecureHash)) {
            // Nếu hợp lệ, lấy các dữ liệu cần thiết từ fields
            String vnp_TxnRef = fields.get("vnp_TxnRef");  // Mã giao dịch
            String vnp_Amount = fields.get("vnp_Amount");
            String vnp_OrderInfo = fields.get("vnp_OrderInfo");
            String vnp_ResponseCode = fields.get("vnp_ResponseCode");  // Mã phản hồi

            // Tạo đối tượng VNPayTransaction và trả về
            VNPayResponse transaction = new VNPayResponse(vnp_TxnRef, vnp_Amount, vnp_OrderInfo, vnp_ResponseCode);
            return transaction;
        } else {
            return null;
        }
    }

}
