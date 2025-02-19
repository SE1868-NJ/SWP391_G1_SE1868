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
      
        String a = hmacSHA512("12NXS1K58IEOZIMXHJBZ90AC2MM91N1G", "vnp_Amount=10000000&vnp_BankCode=NCB&vnp_BankTranNo=VNP14802533&vnp_CardType=ATM&vnp_OrderInfo=Nguyen+Huu+Dat&vnp_PayDate=20250203233600&vnp_ResponseCode=00&vnp_TmnCode=X7K47OSS&vnp_TransactionNo=14802533&vnp_TransactionStatus=00&vnp_TxnRef=1738600574936");
    
        if(a.equalsIgnoreCase("c8ac9073b001e8b3b8d81c646e3be292e2f49e6e8777927629abd3a3314e56bd697619344ae8678f8a20127339e433065dfed67a84f0f2db7ad33ba6b2832609")){
            System.out.println("true");
        }else{
            System.out.println("False");
        }
    }
            
}
