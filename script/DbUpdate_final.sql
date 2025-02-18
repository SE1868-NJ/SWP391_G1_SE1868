CREATE DATABASE IF NOT EXISTS SWP391_G1;
USE SWP391_G1;

	CREATE TABLE `Customers` (
    `CustomerID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
    `FullName` TEXT(65535),
    `Email` VARCHAR(255) UNIQUE,
    `Password` VARCHAR(255),
    `PhoneNumber` VARCHAR(255),
    `Address` VARCHAR(255),
    `BirthDate` DATE,
    `Gender` ENUM('Male', 'Female'),
    `CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `ProfileImage` VARCHAR(255) DEFAULT NULL,  -- column for storing the image path
    PRIMARY KEY (`CustomerID`)
);
CREATE TABLE `Shop` (
    `ShopID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
    `Name` VARCHAR(255) NOT NULL,
    `Logo` VARCHAR(255) ,
    `Location` VARCHAR(255),
    `Owner` VARCHAR(255),
    `CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`ShopID`)
);

CREATE TABLE `Categories` (
	`CategoryID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`Name` VARCHAR(255),
	`Description` TEXT(65535),
	PRIMARY KEY (`CategoryID`)
);

CREATE TABLE `Products` (
	`ProductID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`Name` VARCHAR(255),
	`Description` TEXT(65535),
	`Price` DECIMAL,
	`StockQuantity` INTEGER,
	`CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`CategoryID` INTEGER,
    `ShopID` INTEGER NOT NULL,
	PRIMARY KEY (`ProductID`),
    FOREIGN KEY (`ShopID`) REFERENCES `Shop` (`ShopID`) 
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`CategoryID`) REFERENCES `Categories` (`CategoryID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE `Orders` (
	`OrderID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`CustomerID` INTEGER,
	`OrderDate` DATETIME,
	`TotalAmount` DECIMAL,
	`Status` VARCHAR(255),
	`ShippingAddress` VARCHAR(255),
	`CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`ShipperID` INTEGER,
	PRIMARY KEY (`OrderID`),
	FOREIGN KEY (`CustomerID`) REFERENCES `Customers` (`CustomerID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `OrderDetails` (
	`OrderDetailID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`OrderID` INTEGER,
	`ProductID` INTEGER,
	`Quantity` INTEGER,
	`UnitPrice` DECIMAL,
	`SubTotal` DECIMAL,
	PRIMARY KEY (`OrderDetailID`),
	FOREIGN KEY (`OrderID`) REFERENCES `Orders` (`OrderID`) 
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`ProductID`) REFERENCES `Products` (`ProductID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `ProductReviews` (
	`ReviewID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`ProductID` INTEGER,
	`CustomerID` INTEGER,
	`Rating` INTEGER,
	`Comment` TEXT(65535),
    `ProductReviewsImage` VARCHAR(255) DEFAULT NULL,
	`CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`ReviewID`),
	FOREIGN KEY (`ProductID`) REFERENCES `Products` (`ProductID`) 
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`CustomerID`) REFERENCES `Customers` (`CustomerID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE `Payment` (
	`PaymentID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`OrderID` INTEGER,
	`PaymentDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`Amount` DECIMAL,
	`PaymentMethod` VARCHAR(255),
	`PaymentStatus` VARCHAR(255),
	PRIMARY KEY (`PaymentID`),
	FOREIGN KEY (`OrderID`) REFERENCES `Orders` (`OrderID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `Favorites` (
	`FavoriteID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`CustomerID` INTEGER,
	`Name` VARCHAR(255),
	`CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`FavoriteID`),
	FOREIGN KEY (`CustomerID`) REFERENCES `Customers` (`CustomerID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `Carts` (
	`CartID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`CustomerID` INTEGER,
	`CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`CartID`),
	FOREIGN KEY (`CustomerID`) REFERENCES `Customers` (`CustomerID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `CartItems` (
	`CartItemID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`CartID` INTEGER,
	`ProductID` INTEGER,
	`Quantity` INTEGER,
	`AddedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`CartItemID`),
	FOREIGN KEY (`CartID`) REFERENCES `Carts` (`CartID`) 
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`ProductID`) REFERENCES `Products` (`ProductID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `Shipper` (
	`ShipperID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`FullName` VARCHAR(255),
	`Email` VARCHAR(255),
	`PhoneNumber` VARCHAR(255),
	`Status` VARCHAR(255),
	`CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`ShipperID`)
);

CREATE TABLE `ShipperReviews` (
	`ReviewID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`ShipperID` INTEGER,
	`CustomerID` INTEGER,
	`OrderID` INTEGER,
	`Rating` INTEGER,
	`Comment` TEXT(65535),
	`CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`ReviewID`),
	FOREIGN KEY (`ShipperID`) REFERENCES `Shipper` (`ShipperID`) 
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`CustomerID`) REFERENCES `Customers` (`CustomerID`) 
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`OrderID`) REFERENCES `Orders` (`OrderID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `ProductImages` (
	`ProductImageID` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`ProductID` INTEGER,
	`ImageURL` VARCHAR(255),
	`CreatedAt` DATETIME,
	PRIMARY KEY (`ProductImageID`),
	FOREIGN KEY (`ProductID`) REFERENCES `Products` (`ProductID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `FavoritesDetails` (
	`FavoriteID` INTEGER NOT NULL,
	`ProductID` INTEGER NOT NULL,
	`CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`FavoriteID`, `ProductID`),
	FOREIGN KEY (`FavoriteID`) REFERENCES `Favorites` (`FavoriteID`) 
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`ProductID`) REFERENCES `Products` (`ProductID`) 
		ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `Customers` (`FullName`, `Email`, `Password`, `PhoneNumber`, `Address`, `BirthDate`, `Gender`, `ProfileImage`) 
VALUES 
('John Doe', 'john.doe@example.com', 'password123', '1234567890', '123 Main St, Thành phố', '1990-01-01', 'Male', 'assets/img/profile/john_doe.png'),
('Jane Smith', 'jane.smith@example.com', 'password123', '0987654321', '456 Oak St, Thành phố', '1992-05-15', 'Female', 'assets/img/profile/jane_smith.png'),
('David Lee', 'david.lee@example.com', 'password123', '1122334455', '789 Pine St, Thành phố', '1985-02-20', 'Male', 'assets/img/profile/david_lee.png'),
('Mary Johnson', 'mary.johnson@example.com', 'password123', '6677889900', '101 Maple St, Thành phố', '1993-07-30', 'Female', 'assets/img/profile/mary_johnson.png'),
('Michael Brown', 'michael.brown@example.com', 'password123', '2233445566', '102 Birch St, Thành phố', '1987-11-11', 'Male', 'assets/img/profile/michael_brown.png'),
('Emily Davis', 'emily.davis@example.com', 'password123', '3344556677', '103 Cedar St, Thành phố', '1995-09-25', 'Female', 'assets/img/profile/emily_davis.png'),
('James White', 'james.white@example.com', 'password123', '4455667788', '104 Elm St, Thành phố', '1988-08-09', 'Male', 'assets/img/profile/james_white.png'),
('Olivia Brown', 'olivia.brown@example.com', 'password123', '5566778899', '105 Fir St, Thành phố', '1996-12-17', 'Female', 'assets/img/profile/olivia_brown.png'),
('Liam Green', 'liam.green@example.com', 'password123', '6677889900', '106 Pine St, Thành phố', '1994-04-23', 'Male', 'assets/img/profile/liam_green.png'),
('Sophia Wilson', 'sophia.wilson@example.com', 'password123', '7788990011', '107 Oak St, Thành phố', '1992-02-13', 'Female', 'assets/img/profile/sophia_wilson.png'),
('Ethan Scott', 'ethan.scott@example.com', 'password123', '8899001122', '108 Birch St, Thành phố', '1997-11-01', 'Male', 'assets/img/profile/ethan_scott.png'),
('Ava Harris', 'ava.harris@example.com', 'password123', '9900112233', '109 Cedar St, Thành phố', '1999-05-25', 'Female', 'assets/img/profile/ava_harris.png'),
('Noah Turner', 'noah.turner@example.com', 'password123', '1122334455', '110 Elm St, Thành phố', '1989-06-18', 'Male', 'assets/img/profile/noah_turner.png'),
('Lily King', 'lily.king@example.com', 'password123', '2233445566', '111 Maple St, Thành phố', '1994-03-14', 'Female', 'assets/img/profile/lily_king.png'),
('Mason Lee', 'mason.lee@example.com', 'password123', '3344556677', '112 Pine St, Thành phố', '1992-08-10', 'Male', 'assets/img/profile/mason_lee.png'),
('Chloe Adams', 'chloe.adams@example.com', 'password123', '4455667788', '113 Birch St, Thành phố', '1998-01-21', 'Female', 'assets/img/profile/chloe_adams.png'),
('William Brown', 'william.brown@example.com', 'password123', '5566778899', '114 Oak St, Thành phố', '1985-11-15', 'Male', 'assets/img/profile/william_brown.png'),
('Isabella Carter', 'isabella.carter@example.com', 'password123', '6677889900', '115 Fir St, Thành phố', '1991-04-30', 'Female', 'assets/img/profile/isabella_carter.png'),
('Lucas Harris', 'lucas.harris@example.com', 'password123', '7788990011', '116 Cedar St, Thành phố', '1995-07-17', 'Male', 'assets/img/profile/lucas_harris.png'),
('Grace Mitchell', 'grace.mitchell@example.com', 'password123', '8899001122', '117 Elm St, Thành phố', '1999-02-05', 'Female', 'assets/img/profile/grace_mitchell.png');


INSERT INTO `Categories` (`Name`, `Description`) 
VALUES 
('Điện tử', 'Thiết bị và đồ công nghệ'),
('Thời trang', 'Quần áo và phụ kiện thời trang'),
('Đồ gia dụng', 'Đồ điện tử và công cụ cho gia đình'),
('Mỹ phẩm', 'Sản phẩm làm đẹp và chăm sóc da'),
('Thể thao', 'Thiết bị và trang phục thể thao'),
('Đồ chơi', 'Đồ chơi và trò chơi cho trẻ em'),
('Sách', 'Sách các thể loại'),
('Ô tô', 'Phụ kiện và linh kiện xe hơi'),
('Nhạc cụ', 'Nhạc cụ và phụ kiện'),
('Nội thất', 'Nội thất cho nhà và văn phòng'),
('Sức khỏe', 'Sản phẩm sức khỏe và bổ sung'),
('Thực phẩm', 'Đồ ăn vặt và thực phẩm cao cấp'),
('Đồ cho thú cưng', 'Sản phẩm cho thú cưng'),
('Văn phòng phẩm', 'Văn phòng phẩm và đồ dùng học tập'),
('Sản phẩm cho bé', 'Sản phẩm cho trẻ em và bé'),
('Trang sức', 'Nhẫn, vòng cổ và các phụ kiện trang sức'),
('Cây cối', 'Công cụ và cây cảnh cho vườn'),
('Nghệ thuật', 'Sản phẩm nghệ thuật và sáng tạo'),
('DIY', 'Bộ dụng cụ và công cụ làm việc thủ công');

INSERT INTO `Shop` (`Name`, `Logo`, `Location`, `Owner`) 
VALUES 
('Cửa hàng điện tử', 'assets/img/shop/shop_a_logo.png', '123 Chợ điện tử, Thành phố', 'John Doe'),
('Thời trang nữ', 'assets/img/shop/shop_b_logo.png', '456 Đại lộ Thời trang, Thành phố', 'Jane Smith'),
('Cửa hàng đồ thể thao', 'assets/img/shop/shop_c_logo.png', '789 Khu thể thao, Thành phố', 'David Lee'),
('Mỹ phẩm đẹp', 'assets/img/shop/shop_d_logo.png', '101 Phố làm đẹp, Thành phố', 'Mary Johnson'),
('Đồ gia dụng', 'assets/img/shop/shop_e_logo.png', '102 Đại lộ Gia dụng, Thành phố', 'Michael Brown'),
('Cửa hàng đồ chơi', 'assets/img/shop/shop_f_logo.png', '103 Quảng trường, Thành phố', 'Emily Davis'),
('Sách hay', 'assets/img/shop/shop_g_logo.png', '104 Đường sách, Thành phố', 'James White'),
('Nhạc cụ', 'assets/img/shop/shop_h_logo.png', '105 Phố nhạc, Thành phố', 'Olivia Brown'),
('Nội thất cao cấp', 'assets/img/shop/shop_i_logo.png', '106 Đại lộ Nội thất, Thành phố', 'Liam Green'),
('Nội thất nhà', 'assets/img/shop/shop_j_logo.png', '107 Đường nội thất, Thành phố', 'Sophia Wilson'),
('Cửa hàng điện tử 2', 'assets/img/shop/shop_k_logo.png', '108 Đại lộ Công nghệ, Thành phố', 'James Brown'),
('Tạp hóa', 'assets/img/shop/shop_l_logo.png', '109 Quảng trường Tạp hóa, Thành phố', 'Chris Martin'),
('Cửa hàng giày', 'assets/img/shop/shop_m_logo.png', '110 Phố giày, Thành phố', 'Sarah Johnson'),
('Dụng cụ thể thao', 'assets/img/shop/shop_n_logo.png', '111 Đường thể thao, Thành phố', 'William Green'),
('Thực phẩm', 'assets/img/shop/shop_o_logo.png', '112 Chợ thực phẩm, Thành phố', 'Daniel Brown'),
('Nước hoa', 'assets/img/shop/shop_p_logo.png', '113 Đường nước hoa, Thành phố', 'Jessica Taylor'),
('Đồ chơi trẻ em', 'assets/img/shop/shop_q_logo.png', '114 Đường đồ chơi, Thành phố', 'Emily Wilson'),
('Bút vẽ', 'assets/img/shop/shop_r_logo.png', '115 Đường vẽ, Thành phố', 'Andrew Scott'),
('Mỹ phẩm cao cấp', 'assets/img/shop/shop_s_logo.png', '116 Đại lộ Mỹ phẩm, Thành phố', 'Hannah Adams');


INSERT INTO `Products` (`Name`, `Description`, `Price`, `StockQuantity`, `CategoryID`, `ShopID`) 
VALUES 
('Điện thoại', 'Mẫu mới nhất với hiệu suất cao', 16999000, 50, 1, 1),
('Laptop', 'Laptop mạnh mẽ cho công việc và chơi game', 29999000, 30, 1, 2),
('Áo thun', 'Áo thun cotton, có sẵn nhiều màu sắc', 499000, 100, 2, 3),
('Máy xay sinh tố', 'Máy xay sinh tố tốc độ cao', 2290000, 20, 3, 4),
('Tai nghe', 'Tai nghe chống ồn', 4590000, 70, 1, 5),
('Giày thể thao', 'Giày chạy bộ thoải mái', 1890000, 150, 2, 6),
('Đồng hồ', 'Đồng hồ đeo tay phong cách với dây da', 3490000, 80, 1, 7),
('Áo khoác', 'Áo khoác mùa đông với lớp lót ấm', 1890000, 60, 2, 8),
('Máy giặt', 'Máy giặt tiết kiệm năng lượng', 11990000, 10, 3, 9),
('Điều hòa', 'Điều hòa làm mát mùa hè', 7490000, 15, 3, 10),
('Quần jeans', 'Jeans thoải mái, có sẵn các size', 899000, 120, 2, 11),
('Tủ lạnh', 'Tủ lạnh 2 cửa với ngăn đá', 17990000, 8, 3, 12),
('Máy xay cà phê', 'Máy xay cà phê tự động', 1490000, 50, 1, 13),
('Bàn làm việc', 'Bàn làm việc hiện đại', 2790000, 25, 2, 14),
('Tủ quần áo', 'Tủ quần áo gỗ cao cấp', 4990000, 30, 3, 15),
('Đèn ngủ', 'Đèn ngủ LED tiết kiệm năng lượng', 499000, 100, 1, 16),
('Máy chiếu', 'Máy chiếu mini cho gia đình', 1590000, 40, 1, 17),
('Màn hình máy tính', 'Màn hình LED Full HD', 2490000, 60, 2, 18),
('Đồ chơi trẻ em', 'Đồ chơi giáo dục cho trẻ em', 199000, 200, 4, 19);

INSERT INTO `Orders` (`CustomerID`, `OrderDate`, `TotalAmount`, `Status`, `ShippingAddress`, `ShipperID`) 
VALUES 
(1, '2023-07-01 10:00:00', 16999000, 'Đang chờ', '123 Main St, Thành phố', 1),
(2, '2023-07-02 14:30:00', 29999000, 'Đã giao', '456 Oak St, Thành phố', 2),
(3, '2023-07-03 16:45:00', 499000, 'Đã giao', '789 Pine St, Thành phố', 3),
(4, '2023-07-04 09:15:00', 2290000, 'Đang chờ', '101 Maple St, Thành phố', 4),
(5, '2023-07-05 11:00:00', 4590000, 'Đã giao', '102 Birch St, Thành phố', 5),
(6, '2023-07-06 18:30:00', 1890000, 'Đã giao', '103 Cedar St, Thành phố', 6),
(7, '2023-07-07 13:00:00', 3490000, 'Đang chờ', '104 Elm St, Thành phố', 7),
(8, '2023-07-08 10:15:00', 1890000, 'Đã giao', '105 Fir St, Thành phố', 8),
(9, '2023-07-09 14:30:00', 7490000, 'Đã giao', '106 Pine St, Thành phố', 9),
(10, '2023-07-10 16:45:00', 899000, 'Đang chờ', '107 Birch St, Thành phố', 10),
(11, '2023-07-11 11:00:00', 4990000, 'Đã giao', '108 Oak St, Thành phố', 11),
(12, '2023-07-12 18:30:00', 2490000, 'Đã giao', '109 Maple St, Thành phố', 12),
(13, '2023-07-13 14:15:00', 17990000, 'Đang chờ', '110 Cedar St, Thành phố', 13),
(14, '2023-07-14 09:30:00', 1590000, 'Đã giao', '111 Elm St, Thành phố', 14),
(15, '2023-07-15 16:00:00', 1200000, 'Đang chờ', '112 Pine Ave, Thành phố', 15),
(16, '2023-07-16 12:30:00', 2490000, 'Đã giao', '113 Birch Ave, Thành phố', 16),
(17, '2023-07-17 14:30:00', 3490000, 'Đã giao', '114 Oak Ave, Thành phố', 17),
(18, '2023-07-18 16:45:00', 2490000, 'Đang chờ', '115 Fir St, Thành phố', 18),
(19, '2023-07-19 09:00:00', 3490000, 'Đã giao', '116 Cedar Rd, Thành phố', 19),
(20, '2023-07-20 18:30:00', 3990000, 'Đã giao', '117 Elm Rd, Thành phố', 20);

INSERT INTO `OrderDetails` (`OrderID`, `ProductID`, `Quantity`, `UnitPrice`, `SubTotal`) 
VALUES 
(1, 1, 1, 16999000, 16999000),
(2, 2, 1, 29999000, 29999000),
(3, 3, 2, 499000, 998000),
(4, 4, 1, 2290000, 2290000),
(5, 5, 1, 4590000, 4590000),
(6, 6, 1, 1890000, 1890000),
(7, 7, 1, 3490000, 3490000),
(8, 8, 1, 1890000, 1890000),
(9, 9, 1, 7490000, 7490000),
(10, 10, 1, 899000, 899000),
(11, 11, 1, 4990000, 4990000),
(12, 12, 1, 2490000, 2490000),
(13, 13, 1, 17990000, 17990000),
(14, 14, 1, 1590000, 1590000),
(15, 15, 1, 1200000, 1200000),
(16, 16, 1, 2490000, 2490000),
(17, 17, 1, 3490000, 3490000),
(18, 18, 1, 2490000, 2490000),
(19, 19, 1, 3490000, 3490000),
(20, 20, 1, 3990000, 3990000);

INSERT INTO `ProductReviews` (`ProductID`, `CustomerID`, `Rating`, `Comment`, `ProductReviewsImage`) 
VALUES 
(1, 1, 5, 'Điện thoại này rất tuyệt vời! Màn hình sắc nét, hiệu năng mượt mà.', 'assets/img/productReview/smartphone_review.jpg'),
(2, 2, 4, 'Laptop tốt, nhưng hơi nặng một chút.', 'assets/img/productReview/laptop_review.jpg'),
(3, 3, 3, 'Áo thun cotton chất lượng khá ổn nhưng hơi rộng.', 'assets/img/productReview/tshirt_review.jpg'),
(4, 4, 5, 'Máy xay sinh tố rất mạnh mẽ và dễ sử dụng.', 'assets/img/productReview/blender_review.jpg'),
(5, 5, 5, 'Tai nghe chống ồn tuyệt vời, âm thanh rõ ràng.', 'assets/img/productReview/headphones_review.jpg'),
(6, 6, 4, 'Giày thể thao rất thoải mái nhưng hơi nhỏ một chút.', 'assets/img/productReview/sneakers_review.jpg'),
(7, 7, 5, 'Đồng hồ đẹp và sang trọng, rất hài lòng với sản phẩm.', 'assets/img/productReview/watch_review.jpg'),
(8, 8, 4, 'Áo khoác ấm áp, phù hợp với mùa đông.', 'assets/img/productReview/jacket_review.jpg'),
(9, 9, 5, 'Máy giặt tiết kiệm điện, giặt sạch hiệu quả.', 'assets/img/productReview/washing_machine_review.jpg'),
(10, 10, 4, 'Điều hòa làm mát nhanh nhưng hơi ồn.', 'assets/img/productReview/air_conditioner_review.jpg'),
(11, 11, 5, 'Quần jeans vừa vặn, dễ chịu khi mặc.', 'assets/img/productReview/pants_review.jpg'),
(12, 12, 4, 'Tủ lạnh rất rộng, nhưng hơi tốn điện.', 'assets/img/productReview/refrigerator_review.jpg'),
(13, 13, 5, 'Máy xay cà phê xay rất mịn, cà phê ngon hơn rất nhiều.', 'assets/img/productReview/coffee_maker_review.jpg'),
(14, 14, 3, 'Bàn làm việc đẹp nhưng hơi chật so với mong đợi.', 'assets/img/productReview/desk_review.jpg'),
(15, 15, 4, 'Tủ quần áo đẹp, chất liệu gỗ tốt nhưng hơi cứng.', 'assets/img/productReview/wardrobe_review.jpg'),
(16, 16, 5, 'Đèn ngủ rất đẹp, ánh sáng dịu nhẹ.', 'assets/img/productReview/night_light_review.jpg'),
(17, 17, 5, 'Máy chiếu mini rất tiện lợi, hình ảnh rõ nét.', 'assets/img/productReview/projector_review.jpg'),
(18, 18, 5, 'Màn hình LED sắc nét, phù hợp cho công việc văn phòng.', 'assets/img/productReview/monitor_review.jpg'),
(19, 19, 5, 'Đồ chơi rất thú vị và an toàn cho trẻ em.', 'assets/img/productReview/toy_review.jpg'),
(20, 20, 5, 'Bàn phím cơ rất nhạy, thiết kế đẹp.', 'assets/img/productReview/keyboard_review.jpg'),
-- 20 bản ghi còn lại
(1, 2, 4, 'Điện thoại ổn, nhưng giá hơi cao.', 'assets/img/productReview/smartphone_review_2.jpg'),
(2, 3, 5, 'Laptop tuyệt vời cho công việc và giải trí.', 'assets/img/productReview/laptop_review_2.jpg'),
(3, 4, 3, 'Áo thun khá tốt nhưng cần có size nhỏ hơn.', 'assets/img/productReview/tshirt_review_2.jpg'),
(4, 5, 5, 'Máy xay sinh tố rất mạnh mẽ, dễ sử dụng.', 'assets/img/productReview/blender_review_2.jpg'),
(5, 6, 5, 'Tai nghe tuyệt vời, âm thanh rất chất lượng.', 'assets/img/productReview/headphones_review_2.jpg'),
(6, 7, 4, 'Giày thể thao rất thoải mái, nhưng thiết kế chưa thật sự nổi bật.', 'assets/img/productReview/sneakers_review_2.jpg'),
(7, 8, 5, 'Đồng hồ rất đẹp và phù hợp với tất cả các trang phục.', 'assets/img/productReview/watch_review_2.jpg'),
(8, 9, 4, 'Áo khoác khá đẹp, nhưng màu sắc hơi tối.', 'assets/img/productReview/jacket_review_2.jpg'),
(9, 10, 5, 'Máy giặt hoạt động rất hiệu quả, tiết kiệm nước.', 'assets/img/productReview/washing_machine_review_2.jpg'),
(10, 11, 5, 'Điều hòa làm lạnh nhanh và tiết kiệm điện.', 'assets/img/productReview/air_conditioner_review_2.jpg'),
(11, 12, 5, 'Quần jeans rất thoải mái, chất liệu rất tốt.', 'assets/img/productReview/pants_review_2.jpg'),
(12, 13, 4, 'Tủ lạnh rất tiện lợi, nhưng hơi ồn.', 'assets/img/productReview/refrigerator_review_2.jpg'),
(13, 14, 5, 'Máy xay cà phê rất tốt, mùi cà phê thơm.', 'assets/img/productReview/coffee_maker_review_2.jpg'),
(14, 15, 5, 'Bàn làm việc rất chắc chắn, đẹp và bền.', 'assets/img/productReview/desk_review_2.jpg'),
(15, 16, 5, 'Tủ quần áo rất rộng rãi, chất liệu tốt.', 'assets/img/productReview/wardrobe_review_2.jpg'),
(16, 17, 5, 'Đèn ngủ sáng dịu, không gây khó chịu.', 'assets/img/productReview/night_light_review_2.jpg'),
(17, 18, 4, 'Máy chiếu đẹp nhưng không đủ sáng trong phòng sáng.', 'assets/img/productReview/projector_review_2.jpg'),
(18, 19, 5, 'Màn hình sắc nét, không bị mờ khi chơi game.', 'assets/img/productReview/monitor_review_2.jpg'),
(19, 20, 5, 'Đồ chơi rất an toàn, trẻ em rất thích.', 'assets/img/productReview/toy_review_2.jpg'),
(20, 1, 5, 'Bàn phím cơ rất tốt, không có gì phải phàn nàn.', 'assets/img/productReview/keyboard_review_2.jpg');


INSERT INTO `Favorites` (`CustomerID`, `Name`, `CreatedAt`) 
VALUES 
(1, 'Yêu thích 1', '2023-07-01 10:00:00'),
(2, 'Yêu thích 2', '2023-07-02 14:30:00'),
(3, 'Yêu thích 3', '2023-07-03 16:45:00'),
(4, 'Yêu thích 4', '2023-07-04 09:15:00'),
(5, 'Yêu thích 5', '2023-07-05 11:00:00'),
(6, 'Yêu thích 6', '2023-07-06 18:30:00'),
(7, 'Yêu thích 7', '2023-07-07 13:00:00'),
(8, 'Yêu thích 8', '2023-07-08 10:15:00'),
(9, 'Yêu thích 9', '2023-07-09 14:30:00'),
(10, 'Yêu thích 10', '2023-07-10 16:45:00'),
(11, 'Yêu thích 11', '2023-07-11 11:00:00'),
(12, 'Yêu thích 12', '2023-07-12 18:30:00'),
(13, 'Yêu thích 13', '2023-07-13 14:15:00'),
(14, 'Yêu thích 14', '2023-07-14 09:30:00'),
(15, 'Yêu thích 15', '2023-07-15 16:00:00'),
(16, 'Yêu thích 16', '2023-07-16 12:30:00'),
(17, 'Yêu thích 17', '2023-07-17 14:00:00'),
(18, 'Yêu thích 18', '2023-07-18 16:00:00'),
(19, 'Yêu thích 19', '2023-07-19 09:00:00'),
(20, 'Yêu thích 20', '2023-07-20 18:30:00');

INSERT INTO `Carts` (`CustomerID`, `CreatedAt`) 
VALUES 
(1, '2023-07-01 10:00:00'),
(2, '2023-07-02 14:30:00'),
(3, '2023-07-03 16:45:00'),
(4, '2023-07-04 09:15:00'),
(5, '2023-07-05 11:00:00'),
(6, '2023-07-06 18:30:00'),
(7, '2023-07-07 13:00:00'),
(8, '2023-07-08 10:15:00'),
(9, '2023-07-09 14:30:00'),
(10, '2023-07-10 16:45:00'),
(11, '2023-07-11 11:00:00'),
(12, '2023-07-12 18:30:00'),
(13, '2023-07-13 14:15:00'),
(14, '2023-07-14 09:30:00'),
(15, '2023-07-15 16:00:00'),
(16, '2023-07-16 12:30:00'),
(17, '2023-07-17 14:00:00'),
(18, '2023-07-18 16:00:00'),
(19, '2023-07-19 09:00:00'),
(20, '2023-07-20 18:30:00');

INSERT INTO `Shipper` (`FullName`, `Email`, `PhoneNumber`, `Status`) 
VALUES 
('John Shipper', 'john.shipper@example.com', '1234567890', 'Available'),
('Jane Shipper', 'jane.shipper@example.com', '0987654321', 'Busy'),
('David Shipper', 'david.shipper@example.com', '1122334455', 'Available'),
('Emily Shipper', 'emily.shipper@example.com', '6677889900', 'Available'),
('Chris Shipper', 'chris.shipper@example.com', '2233445566', 'Busy'),
('Sarah Shipper', 'sarah.shipper@example.com', '7788990011', 'Available'),
('Michael Shipper', 'michael.shipper@example.com', '4455667788', 'Available'),
('Sophie Shipper', 'sophie.shipper@example.com', '5566778899', 'Busy'),
('Daniel Shipper', 'daniel.shipper@example.com', '6677889900', 'Available'),
('Olivia Shipper', 'olivia.shipper@example.com', '3344556677', 'Available'),
('Lucas Shipper', 'lucas.shipper@example.com', '8899001122', 'Available'),
('Emma Shipper', 'emma.shipper@example.com', '2233445566', 'Busy'),
('Ryan Shipper', 'ryan.shipper@example.com', '1100112233', 'Available'),
('Liam Shipper', 'liam.shipper@example.com', '7766554433', 'Busy'),
('Sophia Shipper', 'sophia.shipper@example.com', '9988776655', 'Available'),
('Mason Shipper', 'mason.shipper@example.com', '2233665500', 'Available'),
('Ava Shipper', 'ava.shipper@example.com', '3355778899', 'Busy'),
('Noah Shipper', 'noah.shipper@example.com', '6677885544', 'Available'),
('Grace Shipper', 'grace.shipper@example.com', '2233779988', 'Available');

INSERT INTO `ProductImages` (`ProductID`, `ImageURL`, `CreatedAt`) 
VALUES 
(1, 'assets/img/product/smartphone_image.jpg', '2023-07-01 10:00:00'),
(2, 'assets/img/product/laptop_image.jpg', '2023-07-02 14:30:00'),
(3, 'assets/img/product/tshirt_image.jpg', '2023-07-03 16:45:00'),
(4, 'assets/img/product/blender_image.jpg', '2023-07-04 09:15:00'),
(5, 'assets/img/product/headphones_image.jpg', '2023-07-05 11:00:00'),
(6, 'assets/img/product/sneakers_image.jpg', '2023-07-06 18:30:00'),
(7, 'assets/img/product/watch_image.jpg', '2023-07-07 13:00:00'),
(8, 'assets/img/product/jacket_image.jpg', '2023-07-08 10:15:00'),
(9, 'assets/img/product/washing_machine_image.jpg', '2023-07-09 14:30:00'),
(10, 'assets/img/product/air_conditioner_image.jpg', '2023-07-10 16:45:00'),
(11, 'assets/img/product/pants_image.jpg', '2023-07-11 11:00:00'),
(12, 'assets/img/product/refrigerator_image.jpg', '2023-07-12 18:30:00'),
(13, 'assets/img/product/coffee_maker_image.jpg', '2023-07-13 14:15:00'),
(14, 'assets/img/product/desk_image.jpg', '2023-07-14 09:30:00'),
(15, 'assets/img/product/wardrobe_image.jpg', '2023-07-15 16:00:00'),
(16, 'assets/img/product/night_light_image.jpg', '2023-07-16 12:30:00'),
(17, 'assets/img/product/projector_image.jpg', '2023-07-17 14:00:00'),
(18, 'assets/img/product/monitor_image.jpg', '2023-07-18 16:00:00'),
(19, 'assets/img/product/toy_image.jpg', '2023-07-19 09:00:00'),
(20, 'assets/img/product/keyboard_image.jpg', '2023-07-20 18:30:00');

INSERT INTO `FavoritesDetails` (`FavoriteID`, `ProductID`) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20);

INSERT INTO `ShipperReviews` (`ShipperID`, `CustomerID`, `OrderID`, `Rating`, `Comment`) 
VALUES 
(1, 1, 1, 5, 'Dịch vụ tuyệt vời, giao hàng nhanh chóng và đúng giờ.'),
(2, 2, 2, 4, 'Dịch vụ tốt, nhưng thời gian giao hơi lâu.'),
(3, 3, 3, 5, 'Giao hàng nhanh, nhân viên rất thân thiện.'),
(4, 4, 4, 3, 'Giao hàng chậm, cần cải thiện tốc độ.'),
(5, 5, 5, 4, 'Nhận hàng đúng hạn, tuy nhiên không có thông báo trước.'),
(6, 6, 6, 5, 'Giao hàng rất nhanh, đúng hẹn, hài lòng với dịch vụ.'),
(7, 7, 7, 4, 'Dịch vụ giao hàng tốt, nhưng đóng gói chưa chắc chắn.'),
(8, 8, 8, 5, 'Giao hàng tuyệt vời, rất chuyên nghiệp.'),
(9, 9, 9, 4, 'Giao hàng ổn, nhưng chưa đáp ứng hoàn toàn yêu cầu của tôi.'),
(10, 10, 10, 3, 'Giao hàng lâu hơn so với dự kiến, nhưng vẫn hài lòng.'),
(11, 11, 11, 5, 'Dịch vụ giao hàng hoàn hảo, rất hài lòng.'),
(12, 12, 12, 5, 'Giao hàng rất nhanh chóng và đúng hẹn, dịch vụ tuyệt vời.'),
(13, 13, 13, 4, 'Giao hàng không đúng thời gian nhưng bù lại chất lượng tốt.'),
(14, 14, 14, 3, 'Giao hàng chậm, nhưng nhân viên giao hàng rất lịch sự.'),
(15, 15, 15, 5, 'Giao hàng nhanh chóng, sẽ tiếp tục ủng hộ dịch vụ này.'),
(16, 16, 16, 4, 'Dịch vụ ổn, nhưng có thể cải thiện thêm.'),
(17, 17, 17, 5, 'Giao hàng nhanh chóng, dịch vụ rất tốt.'),
(18, 18, 18, 4, 'Giao hàng đúng hẹn nhưng cần cải thiện đóng gói.'),
(19, 19, 19, 5, 'Dịch vụ giao hàng rất tuyệt vời.'),
(20, 20, 20, 3, 'Giao hàng chậm, cần cải thiện thời gian giao hàng.');


INSERT INTO `Carts` (`CustomerID`, `CreatedAt`) 
VALUES 
(1, '2023-07-01 10:00:00'),
(2, '2023-07-02 14:30:00'),
(3, '2023-07-03 16:45:00'),
(4, '2023-07-04 09:15:00'),
(5, '2023-07-05 11:00:00'),
(6, '2023-07-06 18:30:00'),
(7, '2023-07-07 13:00:00'),
(8, '2023-07-08 10:15:00'),
(9, '2023-07-09 14:30:00'),
(10, '2023-07-10 16:45:00'),
(11, '2023-07-11 11:00:00'),
(12, '2023-07-12 18:30:00'),
(13, '2023-07-13 14:15:00'),
(14, '2023-07-14 09:30:00'),
(15, '2023-07-15 16:00:00'),
(16, '2023-07-16 12:30:00'),
(17, '2023-07-17 14:00:00'),
(18, '2023-07-18 16:00:00'),
(19, '2023-07-19 09:00:00'),
(20, '2023-07-20 18:30:00');


INSERT INTO `CartItems` (`CartID`, `ProductID`, `Quantity`, `AddedAt`) 
VALUES 
(1, 1, 1, '2023-07-01 10:00:00'),
(2, 2, 2, '2023-07-02 14:30:00'),
(3, 3, 3, '2023-07-03 16:45:00'),
(4, 4, 1, '2023-07-04 09:15:00'),
(5, 5, 1, '2023-07-05 11:00:00'),
(6, 6, 1, '2023-07-06 18:30:00'),
(7, 7, 2, '2023-07-07 13:00:00'),
(8, 8, 1, '2023-07-08 10:15:00'),
(9, 9, 1, '2023-07-09 14:30:00'),
(10, 10, 1, '2023-07-10 16:45:00'),
(11, 11, 1, '2023-07-11 11:00:00'),
(12, 12, 2, '2023-07-12 18:30:00'),
(13, 13, 1, '2023-07-13 14:15:00'),
(14, 14, 3, '2023-07-14 09:30:00'),
(15, 15, 2, '2023-07-15 16:00:00'),
(16, 16, 1, '2023-07-16 12:30:00'),
(17, 17, 1, '2023-07-17 14:00:00'),
(18, 18, 1, '2023-07-18 16:00:00'),
(19, 19, 1, '2023-07-19 09:00:00'),
(20, 20, 1, '2023-07-20 18:30:00');
