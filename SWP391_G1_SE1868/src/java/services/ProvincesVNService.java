/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author Đạt
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import response.District;
import response.Province;
import response.Ward;

public class ProvincesVNService {

    public static List<Province> loadProvincesFromFile(String filePath) {
        Gson gson = new Gson();
        // đọc nội dung của file JSON
        try (FileReader reader = new FileReader(filePath)) {
            // Chuyển đổi JSON từ file thành danh sách Province
            //chuyển đổi từ JSON sang đối tượng Java
            return gson.fromJson(reader, new TypeToken<List<Province>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Province> getProvincesByNamePart(List<Province> provinces, String provinceName) {
        List<Province> matchingProvinces = new ArrayList<>();
        for (Province province : provinces) {
            if (province.getName().toLowerCase().contains(provinceName.toLowerCase())) {
                matchingProvinces.add(province);
            }
        }
        return matchingProvinces;  // Trả về danh sách các tỉnh thành tìm thấy
    }

    public static List<District> getDistrictsByName(List<Province> provinces, String provinceName, String districtName) {
        List<District> matchingDistricts = new ArrayList<>();

        for (Province province : provinces) {
            // Kiểm tra xem tên tỉnh thành có khớp với tên người dùng nhập vào không
           if (province.getName().toLowerCase().contains(provinceName.toLowerCase())) {

                // Tìm quận huyện trong tỉnh thành đó
                for (District district : province.getDistricts()) {
                    // Kiểm tra tên quận huyện
                    if (district.getName().toLowerCase().contains(districtName.toLowerCase())) {
                        matchingDistricts.add(district);  // Thêm quận huyện vào danh sách
                    }
                }
            }
        }
        return matchingDistricts;  // Trả về danh sách quận huyện tìm được
    }

    public static List<Ward> getWardsByName(List<Province> provinces, String provinceName, String districtName, String wardName) {
        List<Ward> matchingWard = new ArrayList<>();

        for (Province province : provinces) {
            // Kiểm tra xem tên tỉnh thành có khớp với tên người dùng nhập vào không
           if (province.getName().toLowerCase().contains(provinceName.toLowerCase())) {

                // Tìm quận huyện trong tỉnh thành đó
                for (District district : province.getDistricts()) {
                    // Kiểm tra tên quận huyện
                   if (district.getName().toLowerCase().contains(districtName.toLowerCase())) {

                        
                        for (Ward ward : district.getWards()) {
                            
                            // kiểm tra tên xã phường
                            if (ward.getName().toLowerCase().contains(wardName.toLowerCase())) {
                                matchingWard.add(ward);  // Thêm quận huyện vào danh sách
                            }
                        }
                    }
                }
            }
        }
        return matchingWard;  // Trả về danh sách xã tìm được
    }

    public static void main(String[] args) {
        // Đọc dữ liệu từ file JSON và chuyển thành danh sách Province
        List<Province> provincesCha = loadProvincesFromFile("D:/SWP391_G1_SE1868/SWP391_G1_SE1868/web/province/province.json");

        List<Ward>  provinceCon = getWardsByName(provincesCha, "", "","an");
        // In ra thông tin tỉnh thành, quận huyện, phường xã
        if (provinceCon != null) {
            for (Ward province : provinceCon) {
                System.out.println("Province: " + province.getName());

            }
        }
    }
}
