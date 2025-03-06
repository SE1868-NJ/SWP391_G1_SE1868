/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Giang123
 */
public class Favorite {
    
    private int productId;
    private String name;
    private String imageURL;
    private double price;  // Kiểu dữ liệu phải là double hoặc BigDecimal
    private int stockQuantity;
    private String shopName; // Thêm tên cửa hàng
    private String formattedPrice; // Thêm biến để chứa giá đã format
   
    
    


    public Favorite(int productId, String name, String imageURL, double price, int stockQuantity, String shopName) {
        this.productId = productId;
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        
        this.stockQuantity = stockQuantity;
        this.shopName = shopName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }
    


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

   
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }



    // 🔹 Ghi đè phương thức toString() để dễ debug
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", price=" + price +
                
                ", stockQuantity=" + stockQuantity +
                ", shopName=" + shopName +    
                '}';
    }
}

