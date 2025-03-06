package models;

import dbcontext.GiangDBcontext;
import entity.Shop;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO extends GiangDBcontext {

    // ✅ Lấy danh sách tất cả các shop từ database
    public static List<Shop> getAllShops() {
        List<Shop> shopList = new ArrayList<>();
        String sql = "SELECT * FROM shop";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Shop shop = new Shop();
                shop.setShopId(rs.getInt("ShopID"));
                shop.setName(rs.getString("Name"));
                shop.setLogo(rs.getString("Logo"));
                shop.setLocation(rs.getString("Location"));
                shop.setOwner(rs.getString("Owner"));
                shopList.add(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shopList;
    }

    // ✅ Lấy shop theo ID
    public Shop getShopById(int shopId) {
        String sql = "SELECT * FROM Shop WHERE ShopID = ?";
        String sqlProducts = "SELECT * FROM Products WHERE ShopID = ?";  

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shopId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Shop shop = new Shop();
                    shop.setShopId(rs.getInt("ShopID"));
                    shop.setName(rs.getString("Name"));
                    shop.setLogo(rs.getString("Logo"));
                    shop.setLocation(rs.getString("Location"));
                    shop.setOwner(rs.getString("Owner"));

                    // Lấy danh sách sản phẩm của shop
                    try (PreparedStatement stmtProducts = conn.prepareStatement(sqlProducts)) {
                        stmtProducts.setInt(1, shopId);
                        try (ResultSet rsProducts = stmtProducts.executeQuery()) {
                            List<Product> products = new ArrayList<>();
                            while (rsProducts.next()) {
                                Product product = new Product();
                                product.setProductId(rsProducts.getInt("ProductID"));
                                product.setName(rsProducts.getString("Name"));
                                product.setDescription(rsProducts.getString("Description"));
                                product.setPrice(rsProducts.getDouble("Price"));
                                product.setStockQuantity(rsProducts.getInt("StockQuantity"));
                                products.add(product);
                            }
                            shop.setProducts(products);
                        }
                    }
                    return shop;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }
}
