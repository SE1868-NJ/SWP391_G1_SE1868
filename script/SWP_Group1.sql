CREATE DATABASE  IF NOT EXISTS `swp391_g1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `swp391_g1`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: swp391_g1
-- ------------------------------------------------------
-- Server version	8.4.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blogdetail`
--

DROP TABLE IF EXISTS `blogdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogdetail` (
  `IdBlogDetail` int NOT NULL AUTO_INCREMENT,
  `IdBlog` int NOT NULL,
  `Title` varchar(255) NOT NULL,
  `ContentFilePath` varchar(255) NOT NULL,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdBlogDetail`),
  KEY `IdBlog` (`IdBlog`),
  CONSTRAINT `blogdetail_ibfk_1` FOREIGN KEY (`IdBlog`) REFERENCES `bloglist` (`IdBlog`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogdetail`
--

LOCK TABLES `blogdetail` WRITE;
/*!40000 ALTER TABLE `blogdetail` DISABLE KEYS */;
INSERT INTO `blogdetail` VALUES (1,1,'Top 10 Món Ăn Đường Phố Đậm Chất Việt','contents/blog1_detail1.txt','2025-03-13 02:30:00'),(2,2,'Học Pha Trà Sữa Ngon Như Quán','contents/blog2_detail1.txt','2025-03-13 02:35:00'),(3,3,'Làm Bánh Mì Bơ Tỏi Thơm Nức Mũi','contents/blog3_detail1.txt','2025-03-13 02:40:00'),(4,4,'Khám Phá Hải Sản Miền Trung','contents/blog4_detail1.txt','2025-03-13 02:45:00'),(5,5,'Cách Pha Cà Phê Phin Chuẩn Việt','contents/blog5_detail1.txt','2025-03-13 02:50:00'),(6,6,'Nấu Phở Bò Thơm Ngon Tại Nhà','contents/blog6_detail1.txt','2025-03-13 02:55:00'),(7,7,'Gói Bánh Chưng Ngày Tết Đúng Chuẩn','contents/blog7_detail1.txt','2025-03-13 03:00:00'),(8,8,'Món Ngon Từ Dừa Miền Tây','contents/blog8_detail1.txt','2025-03-13 03:05:00'),(9,9,'Bí Quyết Làm Bánh Flan Mềm Mịn','contents/blog9_detail1.txt','2025-03-13 03:10:00'),(10,10,'Nấu Lẩu Thái Chua Cay Đúng Điệu','contents/blog10_detail1.txt','2025-03-13 03:15:00'),(11,11,'Làm Bánh Cuốn Nóng Hổi Tại Nhà','contents/blog11_detail1.txt','2025-03-13 03:20:00'),(12,12,'Bún Bò Huế Chuẩn Vị Miền Trung','contents/blog12_detail1.txt','2025-03-13 03:25:00'),(13,13,'Làm Chè Khúc Bạch Thanh Mát','contents/blog13_detail1.txt','2025-03-13 03:30:00'),(14,14,'Gỏi Cuốn Tươi Ngon Lành Mạnh','contents/blog14_detail1.txt','2025-03-13 03:35:00'),(15,15,'Cách Làm Xôi Xéo Dẻo Thơm','contents/blog15_detail1.txt','2025-03-13 03:40:00');
/*!40000 ALTER TABLE `blogdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bloglist`
--

DROP TABLE IF EXISTS `bloglist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bloglist` (
  `IdBlog` int NOT NULL AUTO_INCREMENT,
  `NameBlog` varchar(255) NOT NULL,
  `DescriptionBlog` text NOT NULL,
  `CustomerID` int DEFAULT NULL,
  `ImageURL` varchar(255) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdBlog`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `bloglist_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bloglist`
--

LOCK TABLES `bloglist` WRITE;
/*!40000 ALTER TABLE `bloglist` DISABLE KEYS */;
INSERT INTO `bloglist` VALUES (1,'Top 10 Món Ăn Đường Phố Việt Nam','Danh sách những món ăn đường phố nổi tiếng và hấp dẫn tại Việt Nam.',1,'assets/img/product/mon_an_duong_pho.jpg','2025-03-13 02:27:07'),(2,'Cách Pha Chế Trà Sữa Tại Nhà','Hướng dẫn chi tiết cách pha chế trà sữa đơn giản mà ngon như ngoài tiệm.',2,'assets/img/product/cach-pha-tra-sua.jpg','2025-03-13 02:27:07'),(3,'Bí Quyết Làm Bánh Mì Bơ Tỏi','Chia sẻ cách làm bánh mì bơ tỏi giòn ngon, thơm phức tại nhà.',3,'assets/img/product/canhlambanhmibo.png','2025-03-13 02:27:07'),(4,'Những Món Hải Sản Nổi Tiếng Miền Trung','Khám phá các món hải sản đặc trưng của miền Trung Việt Nam.',4,'assets/img/product/dacsanvungmien.png','2025-03-13 02:27:07'),(5,'Cà Phê Pha Phin - Văn Hóa Cà Phê Việt','Tìm hiểu về cách pha cà phê phin và văn hóa thưởng thức cà phê tại Việt Nam.',5,'assets/img/product/caphe_1.jpg','2025-03-13 02:27:07'),(6,'Cách Làm Phở Bò Tại Nhà','Hướng dẫn nấu phở bò thơm ngon, chuẩn vị như ngoài hàng.',6,'assets/img/product/2-cach-nau-pho-bo-bang-goi-gia-vi.png','2025-03-13 02:27:07'),(7,'Bánh Chưng - Hương Vị Ngày Tết','Câu chuyện về bánh chưng và cách gói bánh đúng chuẩn.',7,'assets/img/product/banhtrung.jpg','2025-03-13 02:27:07'),(8,'Món Ngon Từ Dừa','Khám phá các món ăn ngon làm từ dừa, đặc sản miền Tây.',8,'assets/img/product/dac-san-mien-tay-1.jpg','2025-03-13 02:27:07'),(9,'Cách Làm Bánh Flan Mịn Màng','Công thức làm bánh flan béo mịn, thơm ngon tại nhà.',9,'assets/img/product/cachlambanhflan.jpg','2025-03-13 02:27:07'),(10,'Lẩu Thái Chua Cay','Cách nấu lẩu Thái chua cay ngon đúng vị.',10,'assets/img/product/lauthai.jpg','2025-03-13 02:27:07'),(11,'Hướng Dẫn Làm Bánh Cuốn','Công thức làm bánh cuốn nóng hổi, thơm ngon.',11,'assets/img/product/cachlambanhcuon.png','2025-03-13 02:27:07'),(12,'Bún Bò Huế - Đặc Sản Miền Trung','Tìm hiểu về bún bò Huế và cách nấu ngon đúng điệu.',12,'assets/img/product/cachnaubunbohue.jpg','2025-03-13 02:27:07'),(13,'Cách Làm Chè Khúc Bạch','Hướng dẫn cách làm chè khúc bạch thanh mát, hấp dẫn.',13,'assets/img/product/huongdannauchekhucbach.jpg','2025-03-13 02:27:07'),(14,'Gỏi Cuốn - Món Ăn Lành Mạnh','Cách làm gỏi cuốn tươi ngon, phù hợp với chế độ ăn healthy.',14,'assets/img/product/goi-cuon-thumb.jpg','2025-03-13 02:27:07'),(15,'Hướng Dẫn Làm Xôi Xéo','Công thức làm xôi xéo vàng ươm, dẻo thơm.',15,'assets/img/product/cachnauxoixeo.jpg','2025-03-13 02:27:07');
/*!40000 ALTER TABLE `bloglist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `CartID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int NOT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ProductID` int NOT NULL,
  `Quantity` int NOT NULL,
  PRIMARY KEY (`CartID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `carts_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (2,2,'2025-04-01 10:05:00',7,1),(3,3,'2025-04-01 10:10:00',15,3),(4,4,'2025-04-01 10:15:00',12,4),(5,5,'2025-04-01 10:20:00',9,2),(6,6,'2025-04-01 10:25:00',20,5),(7,7,'2025-04-01 10:30:00',2,3),(8,8,'2025-04-01 10:35:00',6,1),(9,9,'2025-04-01 10:40:00',17,2),(10,10,'2025-04-01 10:45:00',11,4),(11,11,'2025-04-01 10:50:00',5,3),(12,12,'2025-04-01 10:55:00',22,1),(13,13,'2025-04-01 11:00:00',10,5),(14,14,'2025-04-01 11:05:00',24,2),(15,15,'2025-04-01 11:10:00',13,3),(16,16,'2025-04-01 11:15:00',27,1),(17,17,'2025-04-01 11:20:00',18,4),(18,18,'2025-04-01 11:25:00',8,3),(19,19,'2025-04-01 11:30:00',26,2),(20,20,'2025-04-01 11:35:00',14,5);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `CategoryID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` mediumtext,
  PRIMARY KEY (`CategoryID`),
  UNIQUE KEY `CategoryID` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Đồ uống','Các loại đồ uống như cà phê, trà, sinh tố, nước ép.'),(2,'Món ăn nhanh','Các món ăn nhanh như pizza, burger, khoai tây chiên, sandwich.'),(3,'Món chính','Các món ăn chính như cơm, phở, bún, mì, và các món đặc sản.'),(4,'Tráng miệng','Các món tráng miệng như bánh, kem, chè, sữa chua.'),(5,'Hải sản','Các món ăn từ hải sản như cua, tôm, mực, hàu nướng.'),(6,'Món nướng','Các món nướng như gà nướng, ba chỉ nướng, xiên que, cá nướng.'),(7,'Món lẩu','Các loại lẩu như lẩu Thái, lẩu hải sản, lẩu nấm chay.'),(8,'Món chay','Các món ăn chay như bún riêu chay, lẩu nấm chay, cơm chay.'),(9,'Bánh ngọt','Các loại bánh như bánh flan, bánh bông lan, bánh tart.'),(10,'Bánh mặn','Các loại bánh mặn như bánh mì kẹp, bánh xèo, bánh tráng trộn.'),(11,'Đồ ăn vặt','Các món ăn vặt như cá viên chiên, snack rong biển, khô gà.'),(12,'Món truyền thống','Các món đặc sản Việt Nam như bún bò Huế, bánh cuốn, xôi xéo.'),(13,'Súp & Cháo','Các món súp, cháo dinh dưỡng như cháo sườn, súp cua.'),(14,'Mì & Nui','Các món mì xào, mì cay, nui xào bò.'),(15,'Thức uống dinh dưỡng','Các loại sữa chua, nước detox, nước ép thiên nhiên.');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `FullName` mediumtext,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `PhoneNumber` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `BirthDate` date DEFAULT NULL,
  `Gender` enum('Male','Female') DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ProfileImage` varchar(255) DEFAULT NULL,
  `IsVerify` tinyint DEFAULT '0',
  PRIMARY KEY (`CustomerID`),
  UNIQUE KEY `CustomerID` (`CustomerID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Nguyễn Văn An','nguyenvanan@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0987654321','123 Đường Chính, TP. Hồ Chí Minh','1990-01-01','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-12.jpg',1),(2,'Trần Thị Bích','tranthibich@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0987654322','456 Đường Lê Lợi, TP. Hà Nội','1992-05-15','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-11.jpg',1),(3,'Lê Hữu Minh','lehuuminh@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0988765432','789 Đường Nguyễn Huệ, TP. Đà Nẵng','1985-02-20','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-5.jpg',1),(4,'Phạm Thị Lan','phamthilan@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0977334455','101 Đường Trần Phú, TP. Hải Phòng','1993-07-30','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-details-4.jpg',1),(5,'Ngô Minh Hải','ngominhhai@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0944223344','102 Đường Võ Văn Kiệt, TP. Cần Thơ','1987-11-11','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/thumb-1.jpg',1),(6,'Hoàng Thị Như Ý','hoangthinhuy@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0933445566','103 Đường Nguyễn Thị Minh Khai, TP. Nha Trang','1995-09-25','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-6.jpg',1),(7,'Vũ Minh Tuấn','vuminhtuan@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0922556677','104 Đường Cách Mạng Tháng 8, TP. Huế','1988-08-09','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-10.jpg',1),(8,'Đặng Thanh Hương','dangthanhhuong@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0911667788','105 Đường Điện Biên Phủ, TP. Đà Lạt','1996-12-17','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/thumb-3.jpg',1),(9,'Trần Văn Long','tranvanlong@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0908778899','106 Đường Lý Tự Trọng, TP. Quảng Ngãi','1994-04-23','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-details-1.jpg',1),(10,'Lê Văn Hùng','levanhung@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0978111222','108 Đường Lê Lai, TP. Hà Nội','1985-06-20','Male','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user1.jpg',1),(11,'Nguyễn Thị Hằng','nguyenthihang@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0978222333','109 Đường Võ Văn Kiệt, TP. Hồ Chí Minh','1993-03-15','Female','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user2.jpg',1),(12,'Phạm Minh Đức','phamminhduc@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0978333444','110 Đường Trần Hưng Đạo, TP. Đà Nẵng','1991-09-12','Male','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user3.jpg',1),(13,'Trần Thị Mai','tranthimai@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0978444555','111 Đường Hoàng Văn Thụ, TP. Hải Phòng','1998-05-30','Female','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user4.jpg',1),(14,'Lý Quốc Bảo','lyquocbao@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0978555666','112 Đường Phan Đình Phùng, TP. Huế','1996-01-18','Male','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user5.jpg',1),(15,'Đinh Thị Huyền','dinhthihuyen@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0978666777','113 Đường Nguyễn Văn Cừ, TP. Cần Thơ','1994-07-22','Female','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user6.jpg',1),(16,'Võ Hoàng Nam','vohoangnam@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0978777888','114 Đường Cách Mạng Tháng 8, TP. Vinh','1989-02-28','Male','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user7.jpg',1),(17,'Lâm Thanh Phương','lamthanhphuong@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0978888999','115 Đường Nguyễn Thị Minh Khai, TP. Đà Lạt','1997-11-09','Female','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user8.jpg',1),(18,'Bùi Văn Dũng','buivandung@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0978999000','116 Đường Lý Tự Trọng, TP. Quảng Ngãi','1986-08-10','Male','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user9.jpg',1),(19,'Hoàng Minh Tuấn','hoangminhtuan@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0987111222','117 Đường Trường Chinh, TP. Hà Nội','1990-12-05','Male','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user11.jpg',1),(20,'Trần Phương Linh','tranphuonglinh@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0987222333','118 Đường Nguyễn Huệ, TP. Hồ Chí Minh','1995-07-19','Female','2025-02-21 02:20:42','2025-02-21 23:50:45','assets/img/profile/user12.jpg',1);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `FavoriteID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`FavoriteID`),
  UNIQUE KEY `FavoriteID` (`FavoriteID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (1,1,'Yêu thích cà phê','2025-04-01 08:00:00','2025-04-01 08:00:00'),(2,2,'Yêu thích trà sữa','2025-04-01 08:10:00','2025-04-01 08:10:00'),(3,3,'Món nướng yêu thích','2025-04-01 08:20:00','2025-04-01 08:20:00'),(4,4,'Hải sản ngon nhất','2025-04-01 08:30:00','2025-04-01 08:30:00'),(5,5,'Bánh ngọt tuyệt vời','2025-04-01 08:40:00','2025-04-01 08:40:00'),(6,6,'Món chay tốt cho sức khỏe','2025-04-01 08:50:00','2025-04-01 08:50:00'),(7,7,'Đồ ăn nhanh khoái khẩu','2025-04-01 09:00:00','2025-04-01 09:00:00'),(8,8,'Tráng miệng yêu thích','2025-04-01 09:10:00','2025-04-01 09:10:00'),(9,9,'Bún bò ngon nhất','2025-04-01 09:20:00','2025-04-01 09:20:00'),(10,10,'Pizza hải sản đặc biệt','2025-04-01 09:30:00','2025-04-01 09:30:00'),(11,11,'Cơm gà số 1','2025-04-01 09:40:00','2025-04-01 09:40:00'),(12,12,'Mì xào bò đậm đà','2025-04-01 09:50:00','2025-04-01 09:50:00'),(13,13,'Chè bưởi ngon','2025-04-01 10:00:00','2025-04-01 10:00:00'),(14,14,'Lẩu Thái cay ngon','2025-04-01 10:10:00','2025-04-01 10:10:00'),(15,15,'Món nướng đậm vị','2025-04-01 10:20:00','2025-04-01 10:20:00'),(16,16,'Đồ ăn vặt khoái khẩu','2025-04-01 10:30:00','2025-04-01 10:30:00'),(17,17,'Món ngon mỗi ngày','2025-04-01 10:40:00','2025-04-01 10:40:00'),(18,18,'Món chay yêu thích','2025-04-01 10:50:00','2025-04-01 10:50:00'),(19,19,'Tráng miệng mát lạnh','2025-04-01 11:00:00','2025-04-01 11:00:00'),(20,20,'Nước ép tốt cho sức khỏe','2025-04-01 11:10:00','2025-04-01 11:10:00');
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoritesdetails`
--

DROP TABLE IF EXISTS `favoritesdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favoritesdetails` (
  `FavoriteID` int NOT NULL,
  `ProductID` int NOT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`FavoriteID`,`ProductID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `favoritesdetails_ibfk_1` FOREIGN KEY (`FavoriteID`) REFERENCES `favorites` (`FavoriteID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favoritesdetails_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritesdetails`
--

LOCK TABLES `favoritesdetails` WRITE;
/*!40000 ALTER TABLE `favoritesdetails` DISABLE KEYS */;
INSERT INTO `favoritesdetails` VALUES (1,1,'2025-04-01 08:00:00','2025-04-01 08:00:00'),(1,21,'2025-04-01 11:20:00','2025-04-01 11:20:00'),(2,2,'2025-04-01 08:10:00','2025-04-01 08:10:00'),(2,22,'2025-04-01 11:30:00','2025-04-01 11:30:00'),(3,3,'2025-04-01 08:20:00','2025-04-01 08:20:00'),(3,23,'2025-04-01 11:40:00','2025-04-01 11:40:00'),(4,4,'2025-04-01 08:30:00','2025-04-01 08:30:00'),(4,24,'2025-04-01 11:50:00','2025-04-01 11:50:00'),(5,5,'2025-04-01 08:40:00','2025-04-01 08:40:00'),(5,25,'2025-04-01 12:00:00','2025-04-01 12:00:00'),(6,6,'2025-04-01 08:50:00','2025-04-01 08:50:00'),(6,26,'2025-04-01 12:10:00','2025-04-01 12:10:00'),(7,7,'2025-04-01 09:00:00','2025-04-01 09:00:00'),(7,27,'2025-04-01 12:20:00','2025-04-01 12:20:00'),(8,8,'2025-04-01 09:10:00','2025-04-01 09:10:00'),(8,28,'2025-04-01 12:30:00','2025-04-01 12:30:00'),(9,9,'2025-04-01 09:20:00','2025-04-01 09:20:00'),(9,29,'2025-04-01 12:40:00','2025-04-01 12:40:00'),(10,10,'2025-04-01 09:30:00','2025-04-01 09:30:00'),(10,30,'2025-04-01 12:50:00','2025-04-01 12:50:00'),(11,11,'2025-04-01 09:40:00','2025-04-01 09:40:00'),(12,12,'2025-04-01 09:50:00','2025-04-01 09:50:00'),(13,13,'2025-04-01 10:00:00','2025-04-01 10:00:00'),(14,14,'2025-04-01 10:10:00','2025-04-01 10:10:00'),(15,15,'2025-04-01 10:20:00','2025-04-01 10:20:00'),(16,16,'2025-04-01 10:30:00','2025-04-01 10:30:00'),(17,17,'2025-04-01 10:40:00','2025-04-01 10:40:00'),(18,18,'2025-04-01 10:50:00','2025-04-01 10:50:00'),(19,19,'2025-04-01 11:00:00','2025-04-01 11:00:00'),(20,20,'2025-04-01 11:10:00','2025-04-01 11:10:00');
/*!40000 ALTER TABLE `favoritesdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `OrderDetailID` int NOT NULL AUTO_INCREMENT,
  `OrderID` int DEFAULT NULL,
  `ProductID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `UnitPrice` decimal(10,0) DEFAULT NULL,
  `SubTotal` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`OrderDetailID`),
  UNIQUE KEY `OrderDetailID` (`OrderDetailID`),
  KEY `OrderID` (`OrderID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (1,1,1,1,25000,25000),(2,2,2,1,35000,35000),(3,3,3,1,30000,30000),(4,4,4,1,150000,150000),(5,5,5,1,60000,60000),(6,6,6,1,30000,30000),(7,7,7,1,50000,50000),(8,8,8,1,40000,40000),(9,9,9,1,35000,35000),(10,10,10,1,180000,180000),(11,11,11,1,120000,120000),(12,12,12,1,90000,90000),(13,13,13,1,250000,250000),(14,14,14,1,50000,50000),(15,15,15,1,50000,50000),(16,16,16,1,75000,75000),(17,17,17,1,100000,100000),(18,18,18,1,200000,200000),(19,19,19,1,160000,160000),(20,20,20,1,45000,45000),(21,21,21,1,150000,150000),(22,22,22,1,85000,85000),(23,23,23,1,120000,120000),(24,24,24,2,25000,50000),(25,25,25,2,35000,70000),(26,26,26,2,30000,60000),(27,27,27,2,150000,300000),(28,28,28,2,60000,120000),(29,29,29,2,30000,60000),(30,30,30,2,50000,100000),(31,31,1,1,25000,25000),(32,32,2,1,35000,35000),(33,33,3,1,30000,30000),(34,34,4,1,150000,150000),(35,35,5,1,60000,60000),(36,36,6,1,30000,30000),(37,37,7,1,50000,50000),(38,38,8,1,40000,40000),(39,39,9,1,35000,35000),(40,40,10,1,180000,180000),(41,41,11,1,120000,120000),(42,42,12,1,90000,90000),(43,43,13,1,250000,250000),(44,44,14,1,50000,50000),(45,45,15,1,50000,50000),(46,46,16,1,75000,75000),(47,47,17,1,100000,100000),(48,48,18,1,200000,200000),(49,49,19,1,160000,160000),(50,50,20,1,45000,45000),(51,51,21,1,150000,150000),(52,52,22,1,85000,85000),(53,53,23,1,120000,120000),(54,54,24,2,25000,50000),(55,55,25,2,35000,70000),(56,56,26,2,30000,60000),(57,57,27,2,150000,300000),(58,58,28,2,60000,120000),(59,59,29,2,30000,60000),(60,60,30,2,50000,100000),(61,60,29,1,30000,30000),(62,60,28,1,60000,60000),(63,60,27,2,150000,300000),(64,60,23,4,120000,480000),(65,61,1,1,25000,25000);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `OrderDate` datetime DEFAULT NULL,
  `TotalAmount` decimal(10,0) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `ShippingAddress` varchar(255) DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ShipperID` int DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  UNIQUE KEY `OrderID` (`OrderID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `fk_orders_shipper` (`ShipperID`),
  CONSTRAINT `fk_orders_shipper` FOREIGN KEY (`ShipperID`) REFERENCES `shipper` (`ShipperID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'2025-01-01 08:00:00',25000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-01 08:00:00','2025-03-13 23:40:53',1),(2,1,'2025-01-01 10:15:00',35000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-01 10:15:00','2025-03-13 23:40:53',1),(3,1,'2025-01-02 15:30:00',30000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-02 15:30:00','2025-03-13 23:40:53',1),(4,1,'2025-02-03 09:45:00',150000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-03 09:45:00','2025-03-13 23:40:53',1),(5,1,'2025-01-03 14:00:00',60000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-03 14:00:00','2025-03-13 23:40:53',1),(6,1,'2025-01-04 18:45:00',30000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-04 18:45:00','2025-03-13 23:40:53',1),(7,1,'2025-02-05 10:20:00',50000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-05 10:20:00','2025-03-13 23:40:53',1),(8,1,'2025-02-06 20:15:00',40000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-06 20:15:00','2025-03-13 23:40:53',1),(9,1,'2025-01-07 14:00:00',35000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-07 14:00:00','2025-03-13 23:40:53',1),(10,1,'2025-01-08 16:10:00',180000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-08 16:10:00','2025-03-13 23:40:53',1),(11,1,'2025-01-09 12:30:00',120000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-09 12:30:00','2025-03-13 23:40:53',1),(12,1,'2025-01-10 09:45:00',90000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-10 09:45:00','2025-03-13 23:40:53',1),(13,1,'2025-01-11 18:20:00',250000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-11 18:20:00','2025-03-13 23:40:53',1),(14,1,'2025-02-12 14:50:00',50000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-12 14:50:00','2025-03-13 23:40:53',1),(15,1,'2025-01-13 10:15:00',50000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-13 10:15:00','2025-03-13 23:40:53',1),(16,1,'2025-02-14 12:45:00',75000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-14 12:45:00','2025-03-13 23:40:53',1),(17,1,'2025-01-15 08:30:00',100000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-15 08:30:00','2025-03-13 23:40:53',1),(18,1,'2025-01-16 15:00:00',200000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-16 15:00:00','2025-03-13 23:40:53',1),(19,1,'2025-01-17 10:30:00',160000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-17 10:30:00','2025-03-13 23:40:53',1),(20,1,'2025-02-18 14:40:00',45000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-18 14:40:00','2025-03-13 23:40:53',1),(21,1,'2025-02-19 12:00:00',150000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-19 12:00:00','2025-03-13 23:40:53',1),(22,1,'2025-01-20 16:50:00',85000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-20 16:50:00','2025-03-13 23:40:53',1),(23,1,'2025-02-21 10:15:00',120000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-21 10:15:00','2025-03-13 23:40:53',1),(24,1,'2025-03-01 08:00:00',25000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-01 08:00:00','2025-03-01 10:00:00',1),(25,1,'2025-03-01 10:15:00',35000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-01 10:15:00','2025-03-01 12:30:00',1),(26,1,'2025-03-02 15:30:00',30000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-02 15:30:00','2025-03-02 18:00:00',1),(27,1,'2025-03-03 09:45:00',150000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-03 09:45:00','2025-03-03 11:30:00',1),(28,1,'2025-03-03 14:00:00',60000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-03 14:00:00','2025-03-03 16:30:00',1),(29,1,'2025-03-04 18:45:00',30000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-04 18:45:00','2025-03-04 21:00:00',1),(30,1,'2025-02-05 10:20:00',50000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-05 10:20:00','2025-03-13 23:39:12',1),(31,1,'2025-02-06 20:15:00',40000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-06 20:15:00','2025-03-13 23:39:12',1),(32,1,'2025-01-07 14:00:00',35000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-07 14:00:00','2025-03-13 23:39:12',1),(33,1,'2025-01-08 16:10:00',180000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-08 16:10:00','2025-03-13 23:39:12',1),(34,1,'2025-01-09 12:30:00',120000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-09 12:30:00','2025-03-13 23:39:12',1),(35,1,'2025-02-10 09:45:00',90000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-10 09:45:00','2025-03-13 23:39:12',1),(36,1,'2025-02-11 18:20:00',250000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-11 18:20:00','2025-03-13 23:39:12',1),(37,1,'2025-01-12 14:50:00',50000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-12 14:50:00','2025-03-13 23:39:12',1),(38,1,'2025-01-13 10:15:00',50000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-13 10:15:00','2025-03-13 23:39:12',1),(39,1,'2025-02-14 12:45:00',75000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-14 12:45:00','2025-03-13 23:39:12',1),(40,1,'2025-01-15 08:30:00',100000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-15 08:30:00','2025-03-13 23:39:12',1),(41,1,'2025-01-16 15:00:00',200000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-16 15:00:00','2025-03-13 23:39:12',1),(42,1,'2025-01-17 10:30:00',160000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-17 10:30:00','2025-03-13 23:39:12',1),(43,1,'2025-01-18 14:40:00',45000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-18 14:40:00','2025-03-13 23:39:12',1),(44,1,'2025-01-19 12:00:00',150000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-19 12:00:00','2025-03-13 23:39:12',1),(45,1,'2025-01-20 16:50:00',85000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-20 16:50:00','2025-03-13 23:39:12',1),(46,1,'2025-01-21 10:15:00',120000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-21 10:15:00','2025-03-13 23:39:12',1),(47,1,'2025-02-22 11:15:00',130000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-22 11:15:00','2025-03-13 23:39:12',1),(48,1,'2025-02-23 16:30:00',75000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-23 16:30:00','2025-03-13 23:39:12',1),(49,1,'2025-02-24 19:45:00',200000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-24 19:45:00','2025-03-13 23:39:12',1),(50,1,'2025-01-25 09:00:00',175000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-25 09:00:00','2025-03-13 23:39:12',1),(51,1,'2025-01-26 13:45:00',125000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-26 13:45:00','2025-03-13 23:39:12',1),(52,1,'2025-02-27 17:10:00',185000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-27 17:10:00','2025-03-13 23:39:12',1),(53,1,'2025-01-28 08:30:00',95000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-28 08:30:00','2025-03-13 23:39:12',1),(54,1,'2025-01-29 14:20:00',210000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-29 14:20:00','2025-03-13 23:39:12',1),(55,1,'2025-01-30 10:15:00',115000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-30 10:15:00','2025-03-13 23:39:12',1),(56,1,'2025-01-31 19:30:00',145000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-03-31 19:30:00','2025-03-13 23:39:12',1),(57,1,'2025-02-01 12:45:00',85000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-04-01 12:45:00','2025-03-13 23:39:12',1),(58,1,'2025-01-02 16:15:00',225000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-04-02 16:15:00','2025-03-13 23:39:12',1),(59,1,'2025-01-03 09:30:00',175000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-04-03 09:30:00','2025-03-13 23:39:12',1),(60,1,'2025-01-04 11:45:00',140000,'Đã giao','123 Đường Chính, TP. Hồ Chí Minh','2025-04-04 11:45:00','2025-03-13 23:39:12',1),(61,1,'2025-03-13 00:00:00',25000,'Pending','123 Đường Chính, TP. Hồ Chí Minh','2025-03-13 00:00:00','2025-03-13 00:00:00',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PaymentID` int NOT NULL AUTO_INCREMENT,
  `OrderID` int DEFAULT NULL,
  `PaymentDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `Amount` decimal(10,0) DEFAULT NULL,
  `PaymentMethod` varchar(255) DEFAULT NULL,
  `PaymentStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PaymentID`),
  UNIQUE KEY `PaymentID` (`PaymentID`),
  KEY `OrderID` (`OrderID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,'2025-03-01 10:05:00',25000,'ATM','Đã thanh toán'),(2,2,'2025-03-01 12:35:00',35000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(3,3,'2025-03-02 18:10:00',30000,'ATM','Đã thanh toán'),(4,4,'2025-03-03 11:45:00',150000,'ATM','Đã thanh toán'),(5,5,'2025-03-03 16:40:00',60000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(6,6,'2025-03-04 21:05:00',30000,'ATM','Đã thanh toán'),(7,7,'2025-03-05 13:15:00',50000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(8,8,'2025-03-06 23:10:00',40000,'ATM','Đã thanh toán'),(9,9,'2025-03-07 17:45:00',35000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(10,10,'2025-03-08 18:50:00',180000,'ATM','Đã thanh toán'),(11,11,'2025-03-09 15:05:00',120000,'ATM','Đã thanh toán'),(12,12,'2025-03-10 12:20:00',90000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(13,13,'2025-03-11 21:40:00',250000,'ATM','Đã thanh toán'),(14,14,'2025-03-12 17:15:00',50000,'ATM','Đã thanh toán'),(15,15,'2025-03-13 12:45:00',50000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(16,16,'2025-03-14 15:35:00',75000,'ATM','Đã thanh toán'),(17,17,'2025-03-15 11:50:00',100000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(18,18,'2025-03-16 18:20:00',200000,'ATM','Đã thanh toán'),(19,19,'2025-03-17 13:10:00',160000,'ATM','Đã thanh toán'),(20,20,'2025-03-18 17:05:00',45000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(21,21,'2025-03-19 15:10:00',150000,'ATM','Đã thanh toán'),(22,22,'2025-03-20 19:40:00',85000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(23,23,'2025-03-21 13:20:00',120000,'ATM','Đã thanh toán'),(24,24,'2025-03-22 14:00:00',130000,'ATM','Đã thanh toán'),(25,25,'2025-03-23 18:50:00',75000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(26,26,'2025-03-24 07:40:00',200000,'ATM','Đã thanh toán'),(27,27,'2025-03-25 12:25:00',175000,'ATM','Đã thanh toán'),(28,28,'2025-03-26 16:10:00',125000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(29,29,'2025-03-27 19:40:00',185000,'ATM','Đã thanh toán'),(30,30,'2025-03-28 10:50:00',95000,'ATM','Đã thanh toán'),(31,31,'2025-03-29 17:10:00',210000,'ATM','Đã thanh toán'),(32,32,'2025-03-30 13:30:00',115000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(33,33,'2025-03-31 21:55:00',145000,'ATM','Đã thanh toán'),(34,34,'2025-04-01 14:40:00',85000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(35,35,'2025-04-02 18:50:00',225000,'ATM','Đã thanh toán'),(36,36,'2025-04-03 12:20:00',175000,'ATM','Đã thanh toán'),(37,37,'2025-04-04 14:10:00',140000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(38,38,'2025-04-05 09:30:00',120000,'ATM','Đã thanh toán'),(39,39,'2025-04-06 16:45:00',95000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(40,40,'2025-04-07 11:25:00',180000,'ATM','Đã thanh toán'),(41,41,'2025-04-08 14:50:00',160000,'ATM','Đã thanh toán'),(42,42,'2025-04-09 18:20:00',145000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(43,43,'2025-04-10 13:15:00',130000,'ATM','Đã thanh toán'),(44,44,'2025-04-11 19:30:00',115000,'ATM','Đã thanh toán'),(45,45,'2025-04-12 10:55:00',105000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(46,46,'2025-04-13 12:30:00',195000,'ATM','Đã thanh toán'),(47,47,'2025-04-14 16:15:00',175000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(48,48,'2025-04-15 09:40:00',90000,'ATM','Đã thanh toán'),(49,49,'2025-04-16 15:55:00',220000,'ATM','Đã thanh toán'),(50,50,'2025-04-17 13:20:00',155000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(51,51,'2025-04-18 18:30:00',135000,'ATM','Đã thanh toán'),(52,52,'2025-04-19 12:00:00',105000,'ATM','Đã thanh toán'),(53,53,'2025-04-20 14:10:00',95000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(54,54,'2025-04-21 19:15:00',200000,'ATM','Đã thanh toán'),(55,55,'2025-04-22 11:50:00',175000,'ATM','Đã thanh toán'),(56,56,'2025-04-23 17:35:00',150000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(57,57,'2025-04-24 09:40:00',180000,'ATM','Đã thanh toán'),(58,58,'2025-04-25 14:55:00',125000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(59,59,'2025-04-26 13:10:00',115000,'ATM','Đã thanh toán'),(60,60,'2025-04-27 15:20:00',95000,'Thanh toán khi nhận hàng','Chưa thanh toán'),(61,61,'2025-03-13 00:00:00',25000,'Thanh toán khi nhận hàng','Pending');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productimages`
--

DROP TABLE IF EXISTS `productimages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productimages` (
  `ProductImageID` int NOT NULL AUTO_INCREMENT,
  `ProductID` int DEFAULT NULL,
  `ImageURL` varchar(255) DEFAULT NULL,
  `CreatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`ProductImageID`),
  UNIQUE KEY `ProductImageID` (`ProductImageID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `productimages_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productimages`
--

LOCK TABLES `productimages` WRITE;
/*!40000 ALTER TABLE `productimages` DISABLE KEYS */;
INSERT INTO `productimages` VALUES (1,1,'assets/img/product/capheden_1.jpg','2025-03-04 21:23:20'),(2,1,'assets/img/product/capheden_2.jpg','2025-03-04 21:23:20'),(3,1,'assets/img/product/capheden_3.jpg','2025-03-04 21:23:20'),(4,2,'assets/img/product/trasuachanchau_1.jpg','2025-03-04 21:23:20'),(5,2,'assets/img/product/trasuachanchau_2.jpg','2025-03-04 21:23:20'),(6,2,'assets/img/product/trasuachanchau_3.jpg','2025-03-04 21:23:20'),(7,3,'assets/img/product/nuocepcam_1.jpg','2025-03-04 21:23:20'),(8,3,'assets/img/product/nuocepcam_2.jpg','2025-03-04 21:23:20'),(9,3,'assets/img/product/nuocepcam_3.jpg','2025-03-04 21:23:20'),(10,4,'assets/img/product/pizzahaisan_1.jpg','2025-03-04 21:23:20'),(11,4,'assets/img/product/pizzahaisan_2.jpg','2025-03-04 21:23:20'),(12,4,'assets/img/product/pizzahaisan_3.jpg','2025-03-04 21:23:20'),(13,5,'assets/img/product/burgerbo_1.jpg','2025-03-04 21:23:20'),(14,5,'assets/img/product/burgerbo_2.jpg','2025-03-04 21:23:20'),(15,5,'assets/img/product/burgerbo_3.jpg','2025-03-04 21:23:20'),(16,6,'assets/img/product/khoaitaychien_1.jpg','2025-03-04 21:23:20'),(17,6,'assets/img/product/khoaitaychien_2.jpg','2025-03-04 21:23:20'),(18,6,'assets/img/product/khoaitaychien_3.jpg','2025-03-04 21:23:20'),(19,7,'assets/img/product/bunbohue_1.jpg','2025-03-04 21:23:20'),(20,7,'assets/img/product/bunbohue_2.jpg','2025-03-04 21:23:20'),(21,7,'assets/img/product/bunbohue_3.jpg','2025-03-04 21:23:20'),(22,8,'assets/img/product/comgaxoimo_1.jpg','2025-03-04 21:23:20'),(23,8,'assets/img/product/comgaxoimo_2.jpg','2025-03-04 21:23:20'),(24,8,'assets/img/product/comgaxoimo_3.jpg','2025-03-04 21:23:20'),(25,9,'assets/img/product/mixaobo_1.jpg','2025-03-04 21:23:20'),(26,9,'assets/img/product/mixaobo_2.jpg','2025-03-04 21:23:20'),(27,9,'assets/img/product/mixaobo_3.jpg','2025-03-04 21:23:20'),(28,10,'assets/img/product/banhflan_1.jpg','2025-03-04 21:23:20'),(29,10,'assets/img/product/banhflan_2.jpg','2025-03-04 21:23:20'),(30,10,'assets/img/product/banhflan_3.jpg','2025-03-04 21:23:20'),(31,11,'assets/img/product/kemdua_1.jpg','2025-03-04 21:23:20'),(32,11,'assets/img/product/kemdua_2.jpg','2025-03-04 21:23:20'),(33,11,'assets/img/product/kemdua_3.jpg','2025-03-04 21:23:20'),(34,12,'assets/img/product/chebuoi_1.jpg','2025-03-04 21:23:20'),(35,12,'assets/img/product/chebuoi_2.jpg','2025-03-04 21:23:20'),(36,12,'assets/img/product/chebuoi_3.jpg','2025-03-04 21:23:20'),(37,13,'assets/img/product/tomnuongmuoiot_1.jpg','2025-03-04 21:23:20'),(38,13,'assets/img/product/tomnuongmuoiot_2.jpg','2025-03-04 21:23:20'),(39,13,'assets/img/product/tomnuongmuoiot_3.jpg','2025-03-04 21:23:20'),(40,14,'assets/img/product/mucxaosate_1.jpg','2025-03-04 21:23:20'),(41,14,'assets/img/product/mucxaosate_2.jpg','2025-03-04 21:23:20'),(42,14,'assets/img/product/mucxaosate_3.jpg','2025-03-04 21:23:20'),(43,15,'assets/img/product/haunuongphomai_1.jpg','2025-03-04 21:23:20'),(44,15,'assets/img/product/haunuongphomai_2.jpg','2025-03-04 21:23:20'),(45,15,'assets/img/product/haunuongphomai_3.jpg','2025-03-04 21:23:20'),(46,16,'assets/img/product/bachinuong_1.jpg','2025-03-04 21:23:20'),(47,16,'assets/img/product/bachinuong_2.jpg','2025-03-04 21:23:20'),(48,16,'assets/img/product/bachinuong_3.jpg','2025-03-04 21:23:20'),(49,17,'assets/img/product/xienquetonghop_1.jpg','2025-03-04 21:23:20'),(50,17,'assets/img/product/xienquetonghop_2.jpg','2025-03-04 21:23:20'),(51,17,'assets/img/product/xienquetonghop_3.jpg','2025-03-04 21:23:20'),(52,18,'assets/img/product/suonnuongbbq_1.jpg','2025-03-04 21:23:20'),(53,18,'assets/img/product/suonnuongbbq_2.jpg','2025-03-04 21:23:20'),(54,18,'assets/img/product/suonnuongbbq_3.jpg','2025-03-04 21:23:20'),(55,19,'assets/img/product/lautthaichaocay_1.jpg','2025-03-04 21:23:20'),(56,19,'assets/img/product/lautthaichaocay_2.jpg','2025-03-04 21:23:20'),(57,19,'assets/img/product/lautthaichaocay_3.jpg','2025-03-04 21:23:20'),(58,20,'assets/img/product/laubonhungdam_1.jpg','2025-03-04 21:23:20'),(59,20,'assets/img/product/laubonhungdam_2.jpg','2025-03-04 21:23:20'),(60,20,'assets/img/product/laubonhungdam_3.jpg','2025-03-04 21:23:20'),(61,21,'assets/img/product/launamchay_1.jpg','2025-03-04 21:23:20'),(62,21,'assets/img/product/launamchay_2.jpg','2025-03-04 21:23:20'),(63,21,'assets/img/product/launamchay_3.jpg','2025-03-04 21:23:20'),(64,22,'assets/img/product/comchaythapcam_1.jpg','2025-03-04 21:23:20'),(65,22,'assets/img/product/comchaythapcam_2.jpg','2025-03-04 21:23:20'),(66,22,'assets/img/product/comchaythapcam_3.jpg','2025-03-04 21:23:20'),(67,23,'assets/img/product/bunrieuchay_1.jpg','2025-03-04 21:23:20'),(68,23,'assets/img/product/bunrieuchay_2.jpg','2025-03-04 21:23:20'),(69,23,'assets/img/product/bunrieuchay_3.jpg','2025-03-04 21:23:20'),(70,24,'assets/img/product/goicuonchay_1.jpg','2025-03-04 21:23:20'),(71,24,'assets/img/product/goicuonchay_2.jpg','2025-03-04 21:23:20'),(72,24,'assets/img/product/goicuonchay_3.jpg','2025-03-04 21:23:20'),(73,25,'assets/img/product/banhbonglantrungmuoi_1.jpg','2025-03-04 21:23:20'),(74,25,'assets/img/product/banhbonglantrungmuoi_2.jpg','2025-03-04 21:23:20'),(75,25,'assets/img/product/banhbonglantrungmuoi_3.jpg','2025-03-04 21:23:20'),(76,26,'assets/img/product/banhmousselanhday_1.jpg','2025-03-04 21:23:20'),(77,26,'assets/img/product/banhmousselanhday_2.jpg','2025-03-04 21:23:20'),(78,26,'assets/img/product/banhmousselanhday_3.jpg','2025-03-04 21:23:20'),(79,27,'assets/img/product/banhtartphomai_1.jpg','2025-03-04 21:23:20'),(80,27,'assets/img/product/banhtartphomai_2.jpg','2025-03-04 21:23:20'),(81,27,'assets/img/product/banhtartphomai_3.jpg','2025-03-04 21:23:20'),(82,28,'assets/img/product/cavienchien_1.jpg','2025-03-04 21:23:20'),(83,28,'assets/img/product/cavienchien_2.jpg','2025-03-04 21:23:20'),(84,28,'assets/img/product/cavienchien_3.jpg','2025-03-04 21:23:20'),(85,29,'assets/img/product/khogalachanh_1.jpg','2025-03-04 21:23:20'),(86,29,'assets/img/product/khogalachanh_2.jpg','2025-03-04 21:23:20'),(87,29,'assets/img/product/khogalachanh_3.jpg','2025-03-04 21:23:20'),(88,30,'assets/img/product/snackrongbien_1.jpg','2025-03-04 21:23:20'),(89,30,'assets/img/product/snackrongbien_2.jpg','2025-03-04 21:23:20'),(90,30,'assets/img/product/snackrongbien_3.jpg','2025-03-04 21:23:20');
/*!40000 ALTER TABLE `productimages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productreviews`
--

DROP TABLE IF EXISTS `productreviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productreviews` (
  `ReviewID` int NOT NULL AUTO_INCREMENT,
  `OrderDetailID` int NOT NULL,
  `ProductID` int NOT NULL,
  `CustomerID` int NOT NULL,
  `Rating` int DEFAULT NULL,
  `Comment` mediumtext,
  `ProductReviewsImage` varchar(255) DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReviewID`),
  UNIQUE KEY `ReviewID` (`ReviewID`),
  KEY `OrderDetailID` (`OrderDetailID`),
  KEY `ProductID` (`ProductID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `productreviews_ibfk_1` FOREIGN KEY (`OrderDetailID`) REFERENCES `orderdetails` (`OrderDetailID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productreviews_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productreviews_ibfk_3` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productreviews_chk_1` CHECK ((`Rating` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productreviews`
--

LOCK TABLES `productreviews` WRITE;
/*!40000 ALTER TABLE `productreviews` DISABLE KEYS */;
INSERT INTO `productreviews` VALUES (1,1,1,1,5,'Cà phê đậm đà, thơm ngon!','assets/img/reviews/review-1.jpg','2025-04-01 10:00:00','2025-04-01 10:00:00'),(2,2,2,2,4,'Trà sữa ngon nhưng hơi ngọt!','assets/img/reviews/review-2.jpg','2025-04-02 12:00:00','2025-04-02 12:00:00'),(3,3,3,3,5,'Nước ép cam rất tươi mát!','assets/img/reviews/review-3.jpg','2025-04-03 15:00:00','2025-04-03 15:00:00'),(4,4,4,4,4,'Pizza hải sản ngon nhưng hơi ít topping.','assets/img/reviews/review-4.jpg','2025-04-04 18:30:00','2025-04-04 18:30:00'),(5,5,5,5,5,'Burger bò sốt đặc biệt rất ngon!','assets/img/reviews/review-5.jpg','2025-04-05 14:00:00','2025-04-05 14:00:00'),(6,6,6,6,5,'Khoai tây chiên giòn và chấm sốt ngon.','assets/img/reviews/review-6.jpg','2025-04-06 17:00:00','2025-04-06 17:00:00'),(7,7,7,7,4,'Bún bò Huế chuẩn vị, nước dùng đậm đà.','assets/img/reviews/review-7.jpg','2025-04-07 11:00:00','2025-04-07 11:00:00'),(8,8,8,8,3,'Cơm gà xối mỡ ngon nhưng hơi khô.','assets/img/reviews/review-8.jpg','2025-04-08 09:30:00','2025-04-08 09:30:00'),(9,9,9,9,5,'Mì xào bò rất thơm và không bị dai.','assets/img/reviews/review-9.jpg','2025-04-09 13:15:00','2025-04-09 13:15:00'),(10,10,10,10,4,'Bánh flan mềm mịn, caramel rất thơm.','assets/img/reviews/review-10.jpg','2025-04-10 16:45:00','2025-04-10 16:45:00'),(11,11,11,1,5,'Kem dừa béo ngậy, rất ngon!','assets/img/reviews/review-11.jpg','2025-04-11 11:00:00','2025-04-11 11:00:00'),(12,12,12,2,4,'Chè bưởi ngon, vị thanh mát.','assets/img/reviews/review-12.jpg','2025-04-12 14:20:00','2025-04-12 14:20:00'),(13,13,13,3,5,'Tôm nướng muối ớt siêu ngon, cay vừa miệng!','assets/img/reviews/review-13.jpg','2025-04-13 18:00:00','2025-04-13 18:00:00'),(14,14,14,4,3,'Mực xào sa tế hơi mặn một chút.','assets/img/reviews/review-14.jpg','2025-04-14 19:45:00','2025-04-14 19:45:00'),(15,15,15,5,5,'Hàu nướng phô mai tuyệt vời!','assets/img/reviews/review-15.jpg','2025-04-15 20:30:00','2025-04-15 20:30:00'),(16,16,16,6,5,'Ba chỉ nướng ngon, thịt mềm!','assets/img/reviews/review-16.jpg','2025-04-16 12:45:00','2025-04-16 12:45:00'),(17,17,17,7,4,'Xiên que tổng hợp đa dạng, ngon.','assets/img/reviews/review-17.jpg','2025-04-17 14:30:00','2025-04-17 14:30:00'),(18,18,18,8,3,'Sườn nướng BBQ hơi khô, nhưng sốt ngon.','assets/img/reviews/review-18.jpg','2025-04-18 16:10:00','2025-04-18 16:10:00'),(19,19,19,9,5,'Lẩu Thái chua cay đúng vị!','assets/img/reviews/review-19.jpg','2025-04-19 18:30:00','2025-04-19 18:30:00'),(20,20,20,10,5,'Lẩu bò nhúng dấm ngon và không bị ngấy.','assets/img/reviews/review-20.jpg','2025-04-20 20:15:00','2025-04-20 20:15:00'),(21,21,21,1,5,'Lẩu nấm chay thanh đạm, rất ngon.','assets/img/reviews/review-21.jpg','2025-04-21 09:30:00','2025-04-21 09:30:00'),(22,22,22,2,4,'Cơm chay thập cẩm ngon, đồ ăn đa dạng.','assets/img/reviews/review-22.jpg','2025-04-22 12:00:00','2025-04-22 12:00:00'),(23,23,23,3,3,'Bún riêu chay hơi nhạt nhưng đầy đủ topping.','assets/img/reviews/review-23.jpg','2025-04-23 15:45:00','2025-04-23 15:45:00'),(24,24,24,4,5,'Gỏi cuốn chay ngon, sốt đậm vị!','assets/img/reviews/review-24.jpg','2025-04-24 17:30:00','2025-04-24 17:30:00'),(25,25,25,5,5,'Bánh bông lan trứng muối béo ngậy.','assets/img/reviews/review-25.jpg','2025-04-25 19:00:00','2025-04-25 19:00:00'),(26,26,26,6,4,'Bánh mousse chanh dây chua ngọt, ngon!','assets/img/reviews/review-26.jpg','2025-04-26 11:45:00','2025-04-26 11:45:00'),(27,27,27,7,5,'Bánh tart phô mai tan chảy!','assets/img/reviews/review-27.jpg','2025-04-27 14:15:00','2025-04-27 14:15:00'),(28,28,28,8,5,'Cá viên chiên giòn, sốt me ngon!','assets/img/reviews/review-28.jpg','2025-04-28 16:00:00','2025-04-28 16:00:00'),(29,29,29,9,4,'Khô gà lá chanh ngon nhưng hơi cay.','assets/img/reviews/review-29.jpg','2025-04-29 18:30:00','2025-04-29 18:30:00'),(30,30,30,10,5,'Snack rong biển thơm, giòn, ngon.','assets/img/reviews/review-30.jpg','2025-04-30 20:45:00','2025-04-30 20:45:00'),(31,31,1,2,4,'Cà phê hơi đậm nhưng vẫn ngon.','assets/img/reviews/review-31.jpg','2025-05-01 10:00:00','2025-05-01 10:00:00'),(32,32,2,3,5,'Trà sữa rất ngon, không quá ngọt!','assets/img/reviews/review-32.jpg','2025-05-02 12:00:00','2025-05-02 12:00:00'),(33,33,3,4,3,'Nước ép cam hơi chua so với khẩu vị của mình.','assets/img/reviews/review-33.jpg','2025-05-03 15:00:00','2025-05-03 15:00:00'),(34,34,4,5,5,'Pizza hải sản ngon, lớp phô mai tan chảy!','assets/img/reviews/review-34.jpg','2025-05-04 18:30:00','2025-05-04 18:30:00'),(35,35,5,6,4,'Burger bò khá ngon, nhưng hơi nhiều sốt.','assets/img/reviews/review-35.jpg','2025-05-05 14:00:00','2025-05-05 14:00:00'),(36,36,6,7,5,'Khoai tây chiên nóng giòn, không bị ỉu.','assets/img/reviews/review-36.jpg','2025-05-06 17:00:00','2025-05-06 17:00:00'),(37,37,7,8,3,'Bún bò hơi ít thịt, nhưng nước dùng ngon.','assets/img/reviews/review-37.jpg','2025-05-07 11:00:00','2025-05-07 11:00:00'),(38,38,8,9,5,'Cơm gà xối mỡ giòn ngon, nước sốt rất hợp.','assets/img/reviews/review-38.jpg','2025-05-08 09:30:00','2025-05-08 09:30:00'),(39,39,9,10,5,'Mì xào bò ngon, thịt mềm, sợi mì dai vừa phải.','assets/img/reviews/review-39.jpg','2025-05-09 13:15:00','2025-05-09 13:15:00'),(40,40,10,1,4,'Bánh flan mịn, caramel rất vừa miệng.','assets/img/reviews/review-40.jpg','2025-05-10 16:45:00','2025-05-10 16:45:00'),(41,41,11,2,5,'Kem dừa béo ngậy, ăn không bị ngấy.','assets/img/reviews/review-41.jpg','2025-05-11 11:00:00','2025-05-11 11:00:00'),(42,42,12,3,5,'Chè bưởi ngọt vừa, hạt bưởi giòn sật.','assets/img/reviews/review-42.jpg','2025-05-12 14:20:00','2025-05-12 14:20:00'),(43,43,13,4,5,'Tôm nướng muối ớt cay vừa, tôm tươi!','assets/img/reviews/review-43.jpg','2025-05-13 18:00:00','2025-05-13 18:00:00'),(44,44,14,5,4,'Mực xào sa tế ngon, nhưng hơi cay quá.','assets/img/reviews/review-44.jpg','2025-05-14 19:45:00','2025-05-14 19:45:00'),(45,45,15,6,5,'Hàu nướng phô mai thơm béo, tuyệt vời!','assets/img/reviews/review-45.jpg','2025-05-15 20:30:00','2025-05-15 20:30:00'),(46,46,16,7,5,'Ba chỉ nướng cháy cạnh, rất thơm.','assets/img/reviews/review-46.jpg','2025-05-16 12:45:00','2025-05-16 12:45:00'),(47,47,17,8,4,'Xiên que tổng hợp đa dạng, nước chấm ngon.','assets/img/reviews/review-47.jpg','2025-05-17 14:30:00','2025-05-17 14:30:00'),(48,48,18,9,5,'Sườn nướng BBQ sốt đậm đà, thịt mềm.','assets/img/reviews/review-48.jpg','2025-05-18 16:10:00','2025-05-18 16:10:00'),(49,49,19,10,4,'Lẩu Thái có nhiều topping, nước lẩu đậm đà.','assets/img/reviews/review-49.jpg','2025-05-19 18:30:00','2025-05-19 18:30:00'),(50,50,20,1,5,'Lẩu bò nhúng dấm ngon, thịt bò mềm.','assets/img/reviews/review-50.jpg','2025-05-20 20:15:00','2025-05-20 20:15:00'),(51,51,21,2,5,'Lẩu nấm chay thích hợp cho người ăn chay.','assets/img/reviews/review-51.jpg','2025-05-21 09:30:00','2025-05-21 09:30:00'),(52,52,22,3,4,'Cơm chay thập cẩm ngon, nhưng hơi ít.','assets/img/reviews/review-52.jpg','2025-05-22 12:00:00','2025-05-22 12:00:00'),(53,53,23,4,3,'Bún riêu chay hơi nhạt, cần thêm gia vị.','assets/img/reviews/review-53.jpg','2025-05-23 15:45:00','2025-05-23 15:45:00'),(54,54,24,5,5,'Gỏi cuốn chay tươi ngon, nước chấm ngon!','assets/img/reviews/review-54.jpg','2025-05-24 17:30:00','2025-05-24 17:30:00'),(55,55,25,6,5,'Bánh bông lan trứng muối béo ngậy.','assets/img/reviews/review-55.jpg','2025-05-25 19:00:00','2025-05-25 19:00:00'),(56,56,26,7,4,'Bánh mousse chanh dây thơm ngon.','assets/img/reviews/review-56.jpg','2025-05-26 11:45:00','2025-05-26 11:45:00'),(57,57,27,8,5,'Bánh tart phô mai ngon, béo!','assets/img/reviews/review-57.jpg','2025-05-27 14:15:00','2025-05-27 14:15:00'),(58,58,28,9,5,'Cá viên chiên giòn, không bị dầu.','assets/img/reviews/review-58.jpg','2025-05-28 16:00:00','2025-05-28 16:00:00'),(59,59,29,10,4,'Khô gà lá chanh hơi cay nhưng rất ngon.','assets/img/reviews/review-59.jpg','2025-05-29 18:30:00','2025-05-29 18:30:00'),(60,60,30,1,5,'Snack rong biển giòn, ăn hoài không chán.','assets/img/reviews/review-60.jpg','2025-05-30 20:45:00','2025-05-30 20:45:00'),(61,31,1,2,5,'Cà phê rất thơm, vị đậm đà.','assets/img/reviews/review-1.jpg','2025-05-01 10:00:00','2025-05-01 10:00:00'),(62,32,1,3,4,'Cà phê ngon nhưng hơi đắng.','assets/img/reviews/review-2.jpg','2025-05-02 12:00:00','2025-05-02 12:00:00'),(63,33,1,4,5,'Thơm nức mũi, đúng gu mình thích.','assets/img/reviews/review-3.jpg','2025-05-03 15:00:00','2025-05-03 15:00:00'),(64,34,1,5,3,'Uống được nhưng không đặc biệt lắm.','assets/img/reviews/review-4.jpg','2025-05-04 18:30:00','2025-05-04 18:30:00'),(65,35,1,6,4,'Mình thích cà phê này, giá ổn.','assets/img/reviews/review-5.jpg','2025-05-05 14:00:00','2025-05-05 14:00:00'),(66,36,1,7,5,'Một ly buổi sáng là tỉnh cả ngày.','assets/img/reviews/review-6.jpg','2025-05-06 17:00:00','2025-05-06 17:00:00'),(67,37,2,8,5,'Trà sữa rất ngon, không quá ngọt.','assets/img/reviews/review-7.jpg','2025-05-07 11:00:00','2025-05-07 11:00:00'),(68,38,2,9,3,'Trân châu hơi cứng, cần nấu kỹ hơn.','assets/img/reviews/review-8.jpg','2025-05-08 09:30:00','2025-05-08 09:30:00'),(69,39,2,10,4,'Ngon nhưng hơi ít trân châu.','assets/img/reviews/review-9.jpg','2025-05-09 13:15:00','2025-05-09 13:15:00'),(70,40,2,1,5,'Món tủ của mình, rất ngon!','assets/img/reviews/review-10.jpg','2025-05-10 16:45:00','2025-05-10 16:45:00'),(71,41,2,2,5,'Trà thơm, sữa béo, trân châu dai ngon.','assets/img/reviews/review-11.jpg','2025-05-11 11:00:00','2025-05-11 11:00:00'),(72,42,2,3,4,'Không quá xuất sắc nhưng khá ổn.','assets/img/reviews/review-12.jpg','2025-05-12 14:20:00','2025-05-12 14:20:00'),(73,43,3,4,4,'Vị cam tự nhiên, uống rất mát.','assets/img/reviews/review-13.jpg','2025-05-13 18:00:00','2025-05-13 18:00:00'),(74,44,3,5,5,'Nước ép tươi ngon, không quá ngọt.','assets/img/reviews/review-14.jpg','2025-05-14 19:45:00','2025-05-14 19:45:00'),(75,45,3,6,3,'Hơi chua quá, cần thêm chút đường.','assets/img/reviews/review-15.jpg','2025-05-15 20:30:00','2025-05-15 20:30:00'),(76,46,3,7,4,'Sáng nào mình cũng uống, rất tốt.','assets/img/reviews/review-16.jpg','2025-05-16 12:45:00','2025-05-16 12:45:00'),(77,47,3,8,5,'Nước ép cam rất tươi, tốt cho sức khỏe.','assets/img/reviews/review-17.jpg','2025-05-17 14:30:00','2025-05-17 14:30:00'),(78,48,3,9,4,'Hương vị thơm mát, đáng thử.','assets/img/reviews/review-18.jpg','2025-05-18 16:10:00','2025-05-18 16:10:00'),(79,49,4,10,5,'Pizza hải sản quá ngon, phô mai chảy!','assets/img/reviews/review-19.jpg','2025-05-19 18:30:00','2025-05-19 18:30:00'),(80,50,4,1,4,'Hải sản tươi nhưng hơi ít sốt.','assets/img/reviews/review-20.jpg','2025-05-20 20:15:00','2025-05-20 20:15:00'),(81,51,4,2,5,'Mình thích pizza này, topping đầy đặn.','assets/img/reviews/review-21.jpg','2025-05-21 09:30:00','2025-05-21 09:30:00'),(82,52,4,3,5,'Vỏ bánh giòn, nhân đầy đặn.','assets/img/reviews/review-22.jpg','2025-05-22 12:00:00','2025-05-22 12:00:00'),(83,53,4,4,4,'Phô mai ngon nhưng hơi nhiều dầu.','assets/img/reviews/review-23.jpg','2025-05-23 15:45:00','2025-05-23 15:45:00'),(84,54,4,5,5,'Rất ngon, ăn kèm tương ớt là tuyệt.','assets/img/reviews/review-24.jpg','2025-05-24 17:30:00','2025-05-24 17:30:00'),(85,55,5,6,4,'Burger bò thịt dày, sốt ngon.','assets/img/reviews/review-25.jpg','2025-05-25 19:00:00','2025-05-25 19:00:00'),(86,56,5,7,5,'Bánh mì mềm, thịt bò rất ngon.','assets/img/reviews/review-26.jpg','2025-05-26 11:45:00','2025-05-26 11:45:00'),(87,57,5,8,3,'Hơi ít rau, sốt ngon nhưng hơi mặn.','assets/img/reviews/review-27.jpg','2025-05-27 14:15:00','2025-05-27 14:15:00'),(88,58,5,9,5,'Burger này là số 1, cực kỳ ngon!','assets/img/reviews/review-28.jpg','2025-05-28 16:00:00','2025-05-28 16:00:00'),(89,59,5,10,4,'Tốt, nhưng nên có thêm phô mai.','assets/img/reviews/review-29.jpg','2025-05-29 18:30:00','2025-05-29 18:30:00'),(90,60,5,1,5,'Burger bò rất đáng tiền, rất ngon!','assets/img/reviews/review-30.jpg','2025-05-30 20:45:00','2025-05-30 20:45:00');
/*!40000 ALTER TABLE `productreviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` mediumtext,
  `Price` decimal(10,0) DEFAULT NULL,
  `StockQuantity` int DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CategoryID` int DEFAULT NULL,
  `ShopID` int NOT NULL,
  PRIMARY KEY (`ProductID`),
  UNIQUE KEY `ProductID` (`ProductID`),
  KEY `ShopID` (`ShopID`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`ShopID`) REFERENCES `shop` (`ShopID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Cà phê đen','Cà phê pha phin nguyên chất, đậm đà.',25000,99,'2025-03-04 21:05:13','2025-03-13 23:35:55',1,1),(2,'Trà sữa trân châu','Trà sữa thơm ngon, trân châu dai giòn.',35000,80,'2025-03-04 21:05:13','2025-03-04 21:05:13',1,1),(3,'Nước ép cam','Nước ép cam nguyên chất, không đường.',30000,90,'2025-03-04 21:05:13','2025-03-04 21:05:13',1,1),(4,'Pizza hải sản','Pizza với nhân tôm, mực, phô mai.',150000,40,'2025-03-04 21:05:13','2025-03-04 21:05:13',2,2),(5,'Burger bò','Burger kẹp thịt bò tươi ngon, sốt đặc biệt.',60000,50,'2025-03-04 21:05:13','2025-03-04 21:05:13',2,2),(6,'Khoai tây chiên','Khoai tây chiên giòn rụm, chấm sốt đặc biệt.',30000,100,'2025-03-04 21:05:13','2025-03-04 21:05:13',2,2),(7,'Bún bò Huế','Bún bò truyền thống với nước dùng đậm đà.',50000,70,'2025-03-04 21:05:13','2025-03-04 21:05:13',3,3),(8,'Cơm gà xối mỡ','Cơm gà giòn rụm, nước sốt đậm vị.',60000,60,'2025-03-04 21:05:13','2025-03-04 21:05:13',3,3),(9,'Mì xào bò','Mì xào mềm dai với thịt bò và rau củ.',55000,65,'2025-03-04 21:05:13','2025-03-04 21:05:13',3,3),(10,'Bánh flan','Bánh flan mềm mịn, caramel thơm ngon.',25000,80,'2025-03-04 21:05:13','2025-03-04 21:05:13',4,4),(11,'Kem dừa','Kem dừa mát lạnh, béo ngậy.',40000,75,'2025-03-04 21:05:13','2025-03-04 21:05:13',4,4),(12,'Chè bưởi','Chè bưởi thanh mát, hạt bưởi giòn ngon.',35000,85,'2025-03-04 21:05:13','2025-03-04 21:05:13',4,4),(13,'Tôm nướng muối ớt','Tôm nướng cay nồng, thơm ngon.',180000,30,'2025-03-04 21:05:13','2025-03-04 21:05:13',5,5),(14,'Mực xào sa tế','Mực xào cay cay, hấp dẫn.',150000,40,'2025-03-04 21:05:13','2025-03-04 21:05:13',5,5),(15,'Hàu nướng phô mai','Hàu tươi nướng với lớp phô mai béo ngậy.',200000,25,'2025-03-04 21:05:13','2025-03-04 21:05:13',5,5),(16,'Ba chỉ nướng','Thịt ba chỉ nướng sốt mật ong.',120000,50,'2025-03-04 21:05:13','2025-03-04 21:05:13',6,6),(17,'Xiên que tổng hợp','Các loại xiên que nướng thơm ngon.',90000,60,'2025-03-04 21:05:13','2025-03-04 21:05:13',6,6),(18,'Sườn nướng BBQ','Sườn nướng sốt BBQ đặc biệt.',160000,35,'2025-03-04 21:05:13','2025-03-04 21:05:13',6,6),(19,'Lẩu Thái chua cay','Lẩu Thái chua cay, hải sản tươi ngon.',250000,20,'2025-03-04 21:05:13','2025-03-04 21:05:13',7,7),(20,'Lẩu bò nhúng dấm','Lẩu bò nhúng dấm chua thanh.',230000,25,'2025-03-04 21:05:13','2025-03-04 21:05:13',7,7),(21,'Lẩu nấm chay','Lẩu nấm chay thanh đạm.',200000,30,'2025-03-04 21:05:13','2025-03-04 21:05:13',7,7),(22,'Cơm chay thập cẩm','Cơm chay với nhiều món rau củ.',50000,40,'2025-03-04 21:05:13','2025-03-04 21:05:13',8,8),(23,'Bún riêu chay','Bún riêu chay với nước dùng thanh ngọt.',45000,50,'2025-03-04 21:05:13','2025-03-04 21:05:13',8,8),(24,'Gỏi cuốn chay','Gỏi cuốn rau củ, chấm sốt bơ đậu phộng.',40000,60,'2025-03-04 21:05:13','2025-03-04 21:05:13',8,8),(25,'Bánh bông lan trứng muối','Bánh bông lan trứng muối béo ngậy.',70000,45,'2025-03-04 21:05:13','2025-03-04 21:05:13',9,9),(26,'Bánh mousse chanh dây','Bánh mousse chanh dây chua ngọt.',60000,50,'2025-03-04 21:05:13','2025-03-04 21:05:13',9,9),(27,'Bánh tart phô mai','Bánh tart phô mai tan chảy.',75000,40,'2025-03-04 21:05:13','2025-03-04 21:05:13',9,9),(28,'Cá viên chiên','Cá viên chiên giòn, sốt me chua ngọt.',30000,100,'2025-03-04 21:05:13','2025-03-04 21:05:13',11,10),(29,'Khô gà lá chanh','Khô gà lá chanh thơm ngon.',45000,80,'2025-03-04 21:05:13','2025-03-04 21:05:13',11,10),(30,'Snack rong biển','Rong biển sấy giòn, vị cay nhẹ.',35000,90,'2025-03-04 21:05:13','2025-03-04 21:05:13',11,10);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipper`
--

DROP TABLE IF EXISTS `shipper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipper` (
  `ShipperID` int NOT NULL AUTO_INCREMENT,
  `FullName` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `PhoneNumber` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ShipperID`),
  UNIQUE KEY `ShipperID` (`ShipperID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipper`
--

LOCK TABLES `shipper` WRITE;
/*!40000 ALTER TABLE `shipper` DISABLE KEYS */;
INSERT INTO `shipper` VALUES (1,'Nguyễn Văn A','nguyenvana@example.com','0987654321','Đang hoạt động','2025-03-04 21:25:51','2025-03-04 21:25:51');
/*!40000 ALTER TABLE `shipper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipperreviews`
--

DROP TABLE IF EXISTS `shipperreviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipperreviews` (
  `ReviewID` int NOT NULL AUTO_INCREMENT,
  `ShipperID` int DEFAULT NULL,
  `CustomerID` int DEFAULT NULL,
  `OrderID` int DEFAULT NULL,
  `Rating` int DEFAULT NULL,
  `Comment` mediumtext,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReviewID`),
  UNIQUE KEY `ReviewID` (`ReviewID`),
  KEY `ShipperID` (`ShipperID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `OrderID` (`OrderID`),
  CONSTRAINT `shipperreviews_ibfk_1` FOREIGN KEY (`ShipperID`) REFERENCES `shipper` (`ShipperID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shipperreviews_ibfk_2` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shipperreviews_ibfk_3` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipperreviews`
--

LOCK TABLES `shipperreviews` WRITE;
/*!40000 ALTER TABLE `shipperreviews` DISABLE KEYS */;
INSERT INTO `shipperreviews` VALUES (1,1,1,1,5,'Giao hàng nhanh, đóng gói cẩn thận.','2025-05-01 10:00:00','2025-05-01 10:00:00'),(2,1,2,2,4,'Đúng hẹn nhưng hơi trễ một chút.','2025-05-02 12:00:00','2025-05-02 12:00:00'),(3,1,3,3,5,'Rất thân thiện, hỗ trợ tốt.','2025-05-03 15:00:00','2025-05-03 15:00:00'),(4,1,4,4,3,'Hàng đến nơi bị méo hộp, cần cẩn thận hơn.','2025-05-04 18:30:00','2025-05-04 18:30:00'),(5,1,5,5,4,'Giao hàng nhanh, đóng gói tốt.','2025-05-05 14:00:00','2025-05-05 14:00:00'),(6,1,6,6,5,'Shipper vui vẻ, tận tình.','2025-05-06 17:00:00','2025-05-06 17:00:00'),(7,1,7,7,5,'Giao hàng sớm hơn mong đợi.','2025-05-07 11:00:00','2025-05-07 11:00:00'),(8,1,8,8,4,'Hơi chậm nhưng vẫn ổn.','2025-05-08 09:30:00','2025-05-08 09:30:00'),(9,1,9,9,3,'Không nhiệt tình lắm.','2025-05-09 13:15:00','2025-05-09 13:15:00'),(10,1,10,10,5,'Shipper rất chuyên nghiệp.','2025-05-10 16:45:00','2025-05-10 16:45:00'),(11,1,11,11,5,'Dịch vụ tuyệt vời.','2025-05-11 11:00:00','2025-05-11 11:00:00'),(12,1,12,12,4,'Giao hàng đúng giờ.','2025-05-12 14:20:00','2025-05-12 14:20:00'),(13,1,13,13,5,'Shipper thân thiện, dễ thương.','2025-05-13 18:00:00','2025-05-13 18:00:00'),(14,1,14,14,5,'Nhiệt tình, chu đáo.','2025-05-14 19:45:00','2025-05-14 19:45:00'),(15,1,15,15,3,'Giao hàng trễ.','2025-05-15 20:30:00','2025-05-15 20:30:00'),(16,1,16,16,4,'Ổn, không có gì để chê.','2025-05-16 12:45:00','2025-05-16 12:45:00'),(17,1,17,17,5,'Giao hàng đúng thời gian.','2025-05-17 14:30:00','2025-05-17 14:30:00'),(18,1,18,18,5,'Rất tốt, sẽ tiếp tục đặt hàng.','2025-05-18 16:10:00','2025-05-18 16:10:00'),(19,1,19,19,4,'Cẩn thận, nhiệt tình.','2025-05-19 18:30:00','2025-05-19 18:30:00'),(20,1,20,20,5,'Dịch vụ tuyệt vời.','2025-05-20 20:15:00','2025-05-20 20:15:00'),(21,1,1,21,5,'Dịch vụ ổn, không có gì phàn nàn.','2025-05-21 09:30:00','2025-03-09 01:39:40'),(22,1,2,22,3,'Thái độ chưa tốt lắm.','2025-05-22 12:00:00','2025-05-22 12:00:00'),(23,1,3,23,5,'Quá tuyệt vời, 10/10.','2025-05-23 15:45:00','2025-05-23 15:45:00'),(24,1,4,24,5,'Giao hàng nhanh, không có vấn đề gì.','2025-05-24 17:30:00','2025-05-24 17:30:00'),(25,1,5,25,4,'Ổn áp, hàng nguyên vẹn.','2025-05-25 19:00:00','2025-05-25 19:00:00'),(26,1,6,26,5,'Shipper thân thiện, sẽ tiếp tục ủng hộ.','2025-05-26 11:45:00','2025-05-26 11:45:00'),(27,1,7,27,3,'Giao hàng trễ một chút, nhưng hàng ổn.','2025-05-27 14:15:00','2025-05-27 14:15:00'),(28,1,8,28,5,'Giao hàng nhanh chóng.','2025-05-28 16:00:00','2025-05-28 16:00:00'),(29,1,9,29,4,'Không có gì để phàn nàn.','2025-05-29 18:30:00','2025-05-29 18:30:00'),(30,1,10,30,5,'Tuyệt vời!','2025-05-30 20:45:00','2025-05-30 20:45:00'),(31,1,11,31,5,'Shipper nhiệt tình, hàng đúng giờ.','2025-06-01 09:30:00','2025-06-01 09:30:00'),(32,1,12,32,4,'Giao hàng nhanh.','2025-06-02 14:00:00','2025-06-02 14:00:00'),(33,1,13,33,3,'Không nhiệt tình lắm.','2025-06-03 17:30:00','2025-06-03 17:30:00'),(34,1,14,34,5,'Giao hàng rất nhanh, cẩn thận.','2025-06-04 11:15:00','2025-06-04 11:15:00'),(35,1,15,35,5,'Tốt, shipper vui vẻ.','2025-06-05 12:30:00','2025-06-05 12:30:00'),(36,1,16,36,4,'Hàng đến đúng hẹn.','2025-06-06 15:45:00','2025-06-06 15:45:00'),(37,1,17,37,3,'Chậm hơn mong đợi.','2025-06-07 18:00:00','2025-06-07 18:00:00'),(38,1,18,38,5,'Shipper dễ thương.','2025-06-08 20:15:00','2025-06-08 20:15:00'),(39,1,19,39,4,'Đóng gói cẩn thận.','2025-06-09 11:00:00','2025-06-09 11:00:00'),(40,1,20,40,5,'Giao hàng nhanh.','2025-06-10 13:45:00','2025-06-10 13:45:00');
/*!40000 ALTER TABLE `shipperreviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `ShopID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Logo` varchar(255) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `Owner` varchar(255) DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ShopID`),
  UNIQUE KEY `ShopID` (`ShopID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'Cà phê Hoàng Gia','assets/img/shop/cafe_hoang_gia.png','Hà Nội','Nguyễn Văn A','2025-03-04 21:01:08','2025-03-04 21:01:08'),(2,'Pizza Đại Phát','assets/img/shop/pizza_daiphat.png','TP. HCM','Trần Thị B','2025-03-04 21:01:08','2025-03-04 21:01:08'),(3,'Bún Bò Ngon','assets/img/shop/bun_bo_ngon.png','Đà Nẵng','Lê Hữu C','2025-03-04 21:01:08','2025-03-04 21:01:08'),(4,'Tráng Miệng 5 Sao','assets/img/shop/trang_mieng_5sao.png','Hải Phòng','Phạm Thị D','2025-03-04 21:01:08','2025-03-04 21:01:08'),(5,'Hải Sản Tươi Sống','assets/img/shop/hai_san_tuoi_song.png','Nha Trang','Đỗ Mạnh E','2025-03-04 21:01:08','2025-03-04 21:01:08'),(6,'BBQ Nướng Ngon','assets/img/shop/bbq_nuong_ngon.png','TP. HCM','Võ Hồng F','2025-03-04 21:01:08','2025-03-04 21:01:08'),(7,'Lẩu Đệ Nhất','assets/img/shop/lau_de_nhat.png','Huế','Hoàng Văn G','2025-03-04 21:01:08','2025-03-04 21:01:08'),(8,'Nhà Hàng Chay An Lạc','assets/img/shop/chay_an_lac.png','Đà Lạt','Nguyễn Hữu H','2025-03-04 21:01:08','2025-03-04 21:01:08'),(9,'Tiệm Bánh Ngọt Sài Gòn','assets/img/shop/banh_ngot_sg.png','TP. HCM','Trần Quỳnh I','2025-03-04 21:01:08','2025-03-04 21:01:08'),(10,'Quán Ăn Vặt Thanh Xuân','assets/img/shop/an_vat_thanh_xuan.png','Hà Nội','Phan Minh J','2025-03-04 21:01:08','2025-03-04 21:01:08');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-13 23:42:16
