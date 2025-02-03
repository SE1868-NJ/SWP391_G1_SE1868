/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

/**
 *
 * @author Đạt
 */
public class VNPayResponse {
    private String vnp_TxnRef;
    private String vnp_Amount;
    private String vnp_OrderInfo;
    private String vnp_ResponseCode;

    public VNPayResponse(String vnp_TxnRef, String vnp_Amount, String vnp_OrderInfo, String vnp_ResponseCode) {
        this.vnp_TxnRef = vnp_TxnRef;
        this.vnp_Amount = vnp_Amount;
        this.vnp_OrderInfo = vnp_OrderInfo;
        this.vnp_ResponseCode = vnp_ResponseCode;
    }

    // Getters and Setters
    public String getVnp_TxnRef() {
        return vnp_TxnRef;
    }

    public String getVnp_Amount() {
        return vnp_Amount;
    }

    public String getVnp_OrderInfo() {
        return vnp_OrderInfo;
    }

    public String getVnp_ResponseCode() {
        return vnp_ResponseCode;
    }

    @Override
    public String toString() {
        return "VNPayResponse{" + "vnp_TxnRef=" + vnp_TxnRef + ", vnp_Amount=" + vnp_Amount + ", vnp_OrderInfo=" + vnp_OrderInfo + ", vnp_ResponseCode=" + vnp_ResponseCode + '}';
    }
    
}


