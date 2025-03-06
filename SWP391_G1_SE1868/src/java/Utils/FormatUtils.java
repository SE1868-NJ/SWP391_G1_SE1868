/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author Giang123
 */

import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtils {
    public static String formatCurrency(double price) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN")); // Định dạng tiền Việt Nam
        return formatter.format(price) + "₫";
    }
}
