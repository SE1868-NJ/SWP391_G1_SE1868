/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
/**
 *
 * @author Đạt
 */
public class VNPayUtils {
    public static String hmacSHA512(String key, String data) {
        try {
            //  instance của thuật toán HmacSHA512
            Mac hmac = Mac.getInstance("HmacSHA512");
            
            // định nghĩa key cho thuật toán mã hóa
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA512");
            // tạo thuật toán HMAC với key
            hmac.init(secretKey);
            // tạo mã băm HMAC-SHA512
            byte[] hash = hmac.doFinal(data.getBytes());
            
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                // // Chuyển mỗi byte thành 2 ký tự hex
      
                hexString.append(String.format("%02x", b)); 
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while hashing HMAC SHA512", e);
        }
    }

    public static String createQueryString(Map<String, String> params) {
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        StringBuilder query = new StringBuilder();

        for (String fieldName : fieldNames) {
            String value = params.get(fieldName);
            try {
                if (value != null && !value.isEmpty()) {
                    // mã hóa giá trị của tham số trước khi thêm vào query 
                    query.append(fieldName).append('=').append(URLEncoder.encode(value, "UTF-8")).append('&');
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Error encoding parameter value", e);
            }
        }
        // loại bỏ dấu '&'  ở cuối query 
        if (query.length() > 0) {
            query.deleteCharAt(query.length() - 1);
        }

        return query.toString();
    }
    public static void main(String[] args) {
      
        String a = hmacSHA512(config.ConfigVNPay.vnp_HashSecret, "123@@123");
    
        System.out.println(a);
    }
            
}
