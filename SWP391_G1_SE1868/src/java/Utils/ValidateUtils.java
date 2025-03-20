/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author Giang123
 */

import jakarta.servlet.http.Part;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class ValidateUtils {
    private static final List<String> VALID_IMAGE_TYPES = Arrays.asList("image/jpeg", "image/png");
    private static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_ADDRESS_LENGTH = 200;

    public static boolean isValidName(String name) {
        if (name == null) return false;
        name = name.trim();
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) return false;
        return name.matches("^[\\p{L}\\s'-]+$"); // Accepts letters, spaces, hyphens and apostrophes
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        phone = phone.trim();
        return phone.matches("^\\d{10,11}$"); // 10 or 11 digits
    }

    public static boolean isValidAddress(String address) {
        if (address == null) return false;
        address = address.trim();
        if (address.isEmpty() || address.length() > MAX_ADDRESS_LENGTH) return false;
        return address.matches("^[\\p{L}\\d\\s,.-]+$"); // Letters, numbers, spaces, commas, dots, hyphens
    }

    public static boolean isValidDate(String dateStr) {
        if (dateStr == null) return false;
        try {
            LocalDate date = LocalDate.parse(dateStr);
            LocalDate now = LocalDate.now();
            LocalDate minDate = now.minusYears(100);
            LocalDate maxDate = now.minusYears(18); // Must be at least 18 years old

            return !date.isAfter(maxDate) && !date.isBefore(minDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidGender(String gender) {
        if (gender == null) return false;
        return gender.equals("Male") || gender.equals("Female");
    }

    public static boolean isValidImageFile(Part filePart) {
        if (filePart == null) return false;

        // Check file size
        if (filePart.getSize() > MAX_IMAGE_SIZE) {
            return false;
        }

        // Check file type
        String contentType = filePart.getContentType();
        if (!VALID_IMAGE_TYPES.contains(contentType)) {
            return false;
        }

        // Check file name
        String fileName = filePart.getSubmittedFileName();
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }

        // Additional security check for file extension
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png");
    }

    public static boolean isValidPassword(String password) {
        // If password is empty, it means no password change requested
        if (password == null || password.trim().isEmpty()) {
            return true;
        }

        // Password requirements:
        // - At least 8 characters
        // - At least one uppercase letter
        // - At least one lowercase letter
        // - At least one number
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
    }
}