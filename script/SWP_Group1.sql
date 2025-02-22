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
-- Table structure for table `cartitems`
--

DROP TABLE IF EXISTS `cartitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartitems` (
  `CartItemID` int NOT NULL AUTO_INCREMENT,
  `CartID` int DEFAULT NULL,
  `ProductID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `AddedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CartItemID`),
  UNIQUE KEY `CartItemID` (`CartItemID`),
  KEY `CartID` (`CartID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `cartitems_ibfk_1` FOREIGN KEY (`CartID`) REFERENCES `carts` (`CartID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cartitems_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartitems`
--

LOCK TABLES `cartitems` WRITE;
/*!40000 ALTER TABLE `cartitems` DISABLE KEYS */;
INSERT INTO `cartitems` VALUES (1,1,1,1,'2023-07-01 10:00:00'),(2,2,2,2,'2023-07-02 14:30:00'),(3,3,3,3,'2023-07-03 16:45:00'),(4,4,4,1,'2023-07-04 09:15:00'),(5,5,5,1,'2023-07-05 11:00:00'),(6,6,6,1,'2023-07-06 18:30:00'),(7,7,7,2,'2023-07-07 13:00:00'),(8,8,8,1,'2023-07-08 10:15:00'),(9,9,9,1,'2023-07-09 14:30:00'),(10,10,10,1,'2023-07-10 16:45:00'),(11,11,11,1,'2023-07-11 11:00:00'),(12,12,12,2,'2023-07-12 18:30:00'),(13,13,13,1,'2023-07-13 14:15:00'),(14,14,14,3,'2023-07-14 09:30:00'),(15,15,15,2,'2023-07-15 16:00:00'),(16,16,16,1,'2023-07-16 12:30:00'),(17,17,17,1,'2023-07-17 14:00:00'),(18,18,18,1,'2023-07-18 16:00:00'),(19,19,19,1,'2023-07-19 09:00:00'),(20,20,20,1,'2023-07-20 18:30:00');
/*!40000 ALTER TABLE `cartitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `CartID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CartID`),
  UNIQUE KEY `CartID` (`CartID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,1,'2023-07-01 10:00:00'),(2,2,'2023-07-02 14:30:00'),(3,3,'2023-07-03 16:45:00'),(4,4,'2023-07-04 09:15:00'),(5,5,'2023-07-05 11:00:00'),(6,6,'2023-07-06 18:30:00'),(7,7,'2023-07-07 13:00:00'),(8,8,'2023-07-08 10:15:00'),(9,9,'2023-07-09 14:30:00'),(10,10,'2023-07-10 16:45:00'),(11,11,'2023-07-11 11:00:00'),(12,12,'2023-07-12 18:30:00'),(13,13,'2023-07-13 14:15:00'),(14,14,'2023-07-14 09:30:00'),(15,15,'2023-07-15 16:00:00'),(16,16,'2023-07-16 12:30:00'),(17,17,'2023-07-17 14:00:00'),(18,18,'2023-07-18 16:00:00'),(19,19,'2023-07-19 09:00:00'),(20,20,'2023-07-20 18:30:00'),(21,1,'2023-07-01 10:00:00'),(22,2,'2023-07-02 14:30:00'),(23,3,'2023-07-03 16:45:00'),(24,4,'2023-07-04 09:15:00'),(25,5,'2023-07-05 11:00:00'),(26,6,'2023-07-06 18:30:00'),(27,7,'2023-07-07 13:00:00'),(28,8,'2023-07-08 10:15:00'),(29,9,'2023-07-09 14:30:00'),(30,10,'2023-07-10 16:45:00'),(31,11,'2023-07-11 11:00:00'),(32,12,'2023-07-12 18:30:00'),(33,13,'2023-07-13 14:15:00'),(34,14,'2023-07-14 09:30:00'),(35,15,'2023-07-15 16:00:00'),(36,16,'2023-07-16 12:30:00'),(37,17,'2023-07-17 14:00:00'),(38,18,'2023-07-18 16:00:00'),(39,19,'2023-07-19 09:00:00'),(40,20,'2023-07-20 18:30:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Điện tử','Thiết bị và đồ công nghệ'),(2,'Thời trang','Quần áo và phụ kiện thời trang'),(3,'Đồ gia dụng','Đồ điện tử và công cụ cho gia đình'),(4,'Mỹ phẩm','Sản phẩm làm đẹp và chăm sóc da'),(5,'Thể thao','Thiết bị và trang phục thể thao'),(6,'Đồ chơi','Đồ chơi và trò chơi cho trẻ em'),(7,'Sách','Sách các thể loại'),(8,'Ô tô','Phụ kiện và linh kiện xe hơi'),(9,'Nhạc cụ','Nhạc cụ và phụ kiện'),(10,'Nội thất','Nội thất cho nhà và văn phòng'),(11,'Sức khỏe','Sản phẩm sức khỏe và bổ sung'),(12,'Thực phẩm','Đồ ăn vặt và thực phẩm cao cấp'),(13,'Đồ cho thú cưng','Sản phẩm cho thú cưng'),(14,'Văn phòng phẩm','Văn phòng phẩm và đồ dùng học tập'),(15,'Sản phẩm cho bé','Sản phẩm cho trẻ em và bé'),(16,'Trang sức','Nhẫn, vòng cổ và các phụ kiện trang sức'),(17,'Cây cối','Công cụ và cây cảnh cho vườn'),(18,'Nghệ thuật','Sản phẩm nghệ thuật và sáng tạo'),(19,'DIY','Bộ dụng cụ và công cụ làm việc thủ công');
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'John Doe','john.doe@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','1234567890','123 Main St, Thành phố','1990-01-01','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-12.jpg',1),(2,'Jane Smith','jane.smith@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','0987654321','456 Oak St, Thành phố','1992-05-15','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-11.jpg',1),(3,'David Lee','david.lee@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','1122334455','789 Pine St, Thành phố','1985-02-20','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-5.jpg',1),(4,'Mary Johnson','mary.johnson@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','6677889900','101 Maple St, Thành phố','1993-07-30','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-details-4.jpg',1),(5,'Michael Brown','michael.brown@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','2233445566','102 Birch St, Thành phố','1987-11-11','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/thumb-1.jpg',1),(6,'Emily Davis','emily.davis@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','3344556677','103 Cedar St, Thành phố','1995-09-25','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-6.jpg',1),(7,'James White','james.white@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','4455667788','104 Elm St, Thành phố','1988-08-09','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-10.jpg',1),(8,'Olivia Brown','olivia.brown@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','5566778899','105 Fir St, Thành phố','1996-12-17','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/thumb-3.jpg',1),(9,'Liam Green','liam.green@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','6677889900','106 Pine St, Thành phố','1994-04-23','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-details-1.jpg',1),(10,'Sophia Wilson','sophia.wilson@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','7788990011','107 Oak St, Thành phố','1992-02-13','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/sophia_wilson.png',1),(11,'Ethan Scott','ethan.scott@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','8899001122','108 Birch St, Thành phố','1997-11-01','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-7.jpg',1),(12,'Ava Harris','ava.harris@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','9900112233','109 Cedar St, Thành phố','1999-05-25','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-3.jpg',1),(13,'Noah Turner','noah.turner@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','1122334455','110 Elm St, Thành phố','1989-06-18','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/thumb-2.jpg',1),(14,'Lily King','lily.king@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','2233445566','111 Maple St, Thành phố','1994-03-14','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-details-2.jpg',1),(15,'Mason Lee','mason.lee@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','3344556677','112 Pine St, Thành phố','1992-08-10','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-details-5.jpg',1),(16,'Chloe Adams','chloe.adams@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','4455667788','113 Birch St, Thành phố','1998-01-21','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-4.jpg',1),(17,'William Brown','william.brown@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','5566778899','114 Oak St, Thành phố','1985-11-15','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/william_brown.png',1),(18,'Isabella Carter','isabella.carter@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','6677889900','115 Fir St, Thành phố','1991-04-30','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-9.jpg',1),(19,'Lucas Harris','lucas.harris@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','7788990011','116 Cedar St, Thành phố','1995-07-17','Male','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-details-3.jpg',1),(20,'Grace Mitchell','grace.mitchell@example.com','acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3','8899001122','117 Elm St, Thành phố','1999-02-05','Female','2025-02-18 23:22:03','2025-02-21 00:17:57','assets/img/profile/product-8.jpg',1);
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
INSERT INTO `favorites` VALUES (1,1,'Yêu thích 1','2023-07-01 10:00:00','2025-02-18 23:22:03'),(2,2,'Yêu thích 2','2023-07-02 14:30:00','2025-02-18 23:22:03'),(3,3,'Yêu thích 3','2023-07-03 16:45:00','2025-02-18 23:22:03'),(4,4,'Yêu thích 4','2023-07-04 09:15:00','2025-02-18 23:22:03'),(5,5,'Yêu thích 5','2023-07-05 11:00:00','2025-02-18 23:22:03'),(6,6,'Yêu thích 6','2023-07-06 18:30:00','2025-02-18 23:22:03'),(7,7,'Yêu thích 7','2023-07-07 13:00:00','2025-02-18 23:22:03'),(8,8,'Yêu thích 8','2023-07-08 10:15:00','2025-02-18 23:22:03'),(9,9,'Yêu thích 9','2023-07-09 14:30:00','2025-02-18 23:22:03'),(10,10,'Yêu thích 10','2023-07-10 16:45:00','2025-02-18 23:22:03'),(11,11,'Yêu thích 11','2023-07-11 11:00:00','2025-02-18 23:22:03'),(12,12,'Yêu thích 12','2023-07-12 18:30:00','2025-02-18 23:22:03'),(13,13,'Yêu thích 13','2023-07-13 14:15:00','2025-02-18 23:22:03'),(14,14,'Yêu thích 14','2023-07-14 09:30:00','2025-02-18 23:22:03'),(15,15,'Yêu thích 15','2023-07-15 16:00:00','2025-02-18 23:22:03'),(16,16,'Yêu thích 16','2023-07-16 12:30:00','2025-02-18 23:22:03'),(17,17,'Yêu thích 17','2023-07-17 14:00:00','2025-02-18 23:22:03'),(18,18,'Yêu thích 18','2023-07-18 16:00:00','2025-02-18 23:22:03'),(19,19,'Yêu thích 19','2023-07-19 09:00:00','2025-02-18 23:22:03'),(20,20,'Yêu thích 20','2023-07-20 18:30:00','2025-02-18 23:22:03');
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
INSERT INTO `favoritesdetails` VALUES (1,1,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(2,2,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(3,3,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(4,4,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(5,5,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(6,6,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(7,7,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(8,8,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(9,9,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(10,10,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(11,11,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(12,12,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(13,13,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(14,14,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(15,15,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(16,16,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(17,17,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(18,18,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(19,19,'2025-02-18 23:22:03','2025-02-18 23:22:03'),(20,20,'2025-02-18 23:22:03','2025-02-18 23:22:03');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (1,1,1,1,16999000,16999000),(2,2,2,1,29999000,29999000),(3,3,3,2,499000,998000),(4,4,4,1,2290000,2290000),(5,5,5,1,4590000,4590000),(6,6,6,1,1890000,1890000),(7,7,7,1,3490000,3490000),(8,8,8,1,1890000,1890000),(9,9,9,1,7490000,7490000),(10,10,10,1,899000,899000),(11,11,11,1,4990000,4990000),(12,12,12,1,2490000,2490000),(13,13,13,1,17990000,17990000),(14,14,14,1,1590000,1590000),(15,15,15,1,1200000,1200000),(16,16,16,1,2490000,2490000),(17,17,17,1,3490000,3490000),(18,18,18,1,2490000,2490000),(19,19,19,1,3490000,3490000),(20,20,20,1,3990000,3990000);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'2023-07-01 10:00:00',16999000,'Đang chờ','123 Main St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',1),(2,2,'2023-07-02 14:30:00',29999000,'Đã giao','456 Oak St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',2),(3,3,'2023-07-03 16:45:00',499000,'Đã giao','789 Pine St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',3),(4,4,'2023-07-04 09:15:00',2290000,'Đang chờ','101 Maple St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',4),(5,5,'2023-07-05 11:00:00',4590000,'Đã giao','102 Birch St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',5),(6,6,'2023-07-06 18:30:00',1890000,'Đã giao','103 Cedar St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',6),(7,7,'2023-07-07 13:00:00',3490000,'Đang chờ','104 Elm St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',7),(8,8,'2023-07-08 10:15:00',1890000,'Đã giao','105 Fir St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',8),(9,9,'2023-07-09 14:30:00',7490000,'Đã giao','106 Pine St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',9),(10,10,'2023-07-10 16:45:00',899000,'Đang chờ','107 Birch St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',10),(11,11,'2023-07-11 11:00:00',4990000,'Đã giao','108 Oak St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',11),(12,12,'2023-07-12 18:30:00',2490000,'Đã giao','109 Maple St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',12),(13,13,'2023-07-13 14:15:00',17990000,'Đang chờ','110 Cedar St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',13),(14,14,'2023-07-14 09:30:00',1590000,'Đã giao','111 Elm St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',14),(15,15,'2023-07-15 16:00:00',1200000,'Đang chờ','112 Pine Ave, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',15),(16,16,'2023-07-16 12:30:00',2490000,'Đã giao','113 Birch Ave, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',16),(17,17,'2023-07-17 14:30:00',3490000,'Đã giao','114 Oak Ave, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',17),(18,18,'2023-07-18 16:45:00',2490000,'Đang chờ','115 Fir St, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',18),(19,19,'2023-07-19 09:00:00',3490000,'Đã giao','116 Cedar Rd, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',19),(20,20,'2023-07-20 18:30:00',3990000,'Đã giao','117 Elm Rd, Thành phố','2025-02-18 23:22:03','2025-02-18 23:22:03',20);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productimages`
--

LOCK TABLES `productimages` WRITE;
/*!40000 ALTER TABLE `productimages` DISABLE KEYS */;
INSERT INTO `productimages` VALUES (1,1,'assets/img/product/product-4.jpg','2023-07-01 10:00:00'),(2,2,'assets/img/product/laptop_image.jpg','2023-07-02 14:30:00'),(3,3,'assets/img/product/tshirt_image.jpg','2023-07-03 16:45:00'),(4,4,'assets/img/product/blender_image.jpg','2023-07-04 09:15:00'),(5,5,'assets/img/product/headphones_image.jpg','2023-07-05 11:00:00'),(6,6,'assets/img/product/sneakers_image.jpg','2023-07-06 18:30:00'),(7,7,'assets/img/product/watch_image.jpg','2023-07-07 13:00:00'),(8,8,'assets/img/product/jacket_image.jpg','2023-07-08 10:15:00'),(9,9,'assets/img/product/washing_machine_image.jpg','2023-07-09 14:30:00'),(10,10,'assets/img/product/air_conditioner_image.jpg','2023-07-10 16:45:00'),(11,11,'assets/img/product/pants_image.jpg','2023-07-11 11:00:00'),(12,12,'assets/img/product/refrigerator_image.jpg','2023-07-12 18:30:00'),(13,13,'assets/img/product/coffee_maker_image.jpg','2023-07-13 14:15:00'),(14,14,'assets/img/product/desk_image.jpg','2023-07-14 09:30:00'),(15,15,'assets/img/product/wardrobe_image.jpg','2023-07-15 16:00:00'),(16,16,'assets/img/product/night_light_image.jpg','2023-07-16 12:30:00'),(17,17,'assets/img/product/projector_image.jpg','2023-07-17 14:00:00'),(18,18,'assets/img/product/monitor_image.jpg','2023-07-18 16:00:00'),(19,19,'assets/img/product/toy_image.jpg','2023-07-19 09:00:00'),(20,20,'assets/img/product/keyboard_image.jpg','2023-07-20 18:30:00'),(21,1,'assets/img/product/product-5.jpg','2023-07-20 18:30:00'),(22,1,'assets/img/product/product-6.jpg','2023-07-20 18:30:00'),(23,1,'assets/img/product/product-7.jpg','2023-07-20 18:30:00');
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
  `ProductID` int DEFAULT NULL,
  `CustomerID` int DEFAULT NULL,
  `Rating` int DEFAULT NULL,
  `Comment` mediumtext,
  `ProductReviewsImage` varchar(255) DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReviewID`),
  UNIQUE KEY `ReviewID` (`ReviewID`),
  KEY `ProductID` (`ProductID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `productreviews_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productreviews_ibfk_2` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productreviews`
--

LOCK TABLES `productreviews` WRITE;
/*!40000 ALTER TABLE `productreviews` DISABLE KEYS */;
INSERT INTO `productreviews` VALUES (1,1,1,5,'Điện thoại này rất tuyệt vời! Màn hình sắc nét, hiệu năng mượt mà.',NULL,'2025-02-18 23:22:03','2025-02-20 16:34:58'),(2,2,2,4,'Laptop tốt, nhưng hơi nặng một chút.','assets/img/productReview/laptop_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(3,3,3,3,'Áo thun cotton chất lượng khá ổn nhưng hơi rộng.','assets/img/productReview/tshirt_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(4,4,4,5,'Máy xay sinh tố rất mạnh mẽ và dễ sử dụng.','assets/img/productReview/blender_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(5,5,5,5,'Tai nghe chống ồn tuyệt vời, âm thanh rõ ràng.','assets/img/productReview/headphones_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(6,6,6,4,'Giày thể thao rất thoải mái nhưng hơi nhỏ một chút.','assets/img/productReview/sneakers_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(7,7,7,5,'Đồng hồ đẹp và sang trọng, rất hài lòng với sản phẩm.','assets/img/productReview/watch_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(8,8,8,4,'Áo khoác ấm áp, phù hợp với mùa đông.','assets/img/productReview/jacket_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(9,9,9,5,'Máy giặt tiết kiệm điện, giặt sạch hiệu quả.','assets/img/productReview/washing_machine_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(10,10,10,4,'Điều hòa làm mát nhanh nhưng hơi ồn.','assets/img/productReview/air_conditioner_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(11,11,11,5,'Quần jeans vừa vặn, dễ chịu khi mặc.','assets/img/productReview/pants_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(12,12,12,4,'Tủ lạnh rất rộng, nhưng hơi tốn điện.','assets/img/productReview/refrigerator_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(13,13,13,5,'Máy xay cà phê xay rất mịn, cà phê ngon hơn rất nhiều.','assets/img/productReview/coffee_maker_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(14,14,14,3,'Bàn làm việc đẹp nhưng hơi chật so với mong đợi.','assets/img/productReview/desk_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(15,15,15,4,'Tủ quần áo đẹp, chất liệu gỗ tốt nhưng hơi cứng.','assets/img/productReview/wardrobe_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(16,16,16,5,'Đèn ngủ rất đẹp, ánh sáng dịu nhẹ.','assets/img/productReview/night_light_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(17,17,17,5,'Máy chiếu mini rất tiện lợi, hình ảnh rõ nét.','assets/img/productReview/projector_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(18,18,18,5,'Màn hình LED sắc nét, phù hợp cho công việc văn phòng.','assets/img/productReview/monitor_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(19,19,19,5,'Đồ chơi rất thú vị và an toàn cho trẻ em.','assets/img/productReview/toy_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(20,20,20,5,'Bàn phím cơ rất nhạy, thiết kế đẹp.','assets/img/productReview/keyboard_review.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(21,1,2,4,'Điện thoại ổn, nhưng giá hơi cao.','assets/img/productReview/smartphone_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(22,2,3,5,'Laptop tuyệt vời cho công việc và giải trí.','assets/img/productReview/laptop_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(23,3,4,3,'Áo thun khá tốt nhưng cần có size nhỏ hơn.','assets/img/productReview/tshirt_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(24,1,5,5,'Máy xay sinh tố rất mạnh mẽ, dễ sử dụng.','assets/img/productReview/blender_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(25,5,6,5,'Tai nghe tuyệt vời, âm thanh rất chất lượng.','assets/img/productReview/headphones_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(26,6,7,4,'Giày thể thao rất thoải mái, nhưng thiết kế chưa thật sự nổi bật.','assets/img/productReview/sneakers_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(27,7,8,5,'Đồng hồ rất đẹp và phù hợp với tất cả các trang phục.','assets/img/productReview/watch_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(28,8,9,4,'Áo khoác khá đẹp, nhưng màu sắc hơi tối.','assets/img/productReview/jacket_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(29,1,10,5,'Máy giặt hoạt động rất hiệu quả, tiết kiệm nước.','assets/img/productReview/washing_machine_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(30,1,11,5,'Điều hòa làm lạnh nhanh và tiết kiệm điện.','assets/img/productReview/air_conditioner_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(31,1,12,5,'Quần jeans rất thoải mái, chất liệu rất tốt.','assets/img/productReview/pants_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(32,1,13,4,'Tủ lạnh rất tiện lợi, nhưng hơi ồn.','assets/img/productReview/refrigerator_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(33,13,14,5,'Máy xay cà phê rất tốt, mùi cà phê thơm.','assets/img/productReview/coffee_maker_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(34,14,15,5,'Bàn làm việc rất chắc chắn, đẹp và bền.','assets/img/productReview/desk_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(35,1,16,5,'Tủ quần áo rất rộng rãi, chất liệu tốt.','assets/img/productReview/wardrobe_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(36,16,17,5,'Đèn ngủ sáng dịu, không gây khó chịu.','assets/img/productReview/night_light_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(37,17,18,4,'Máy chiếu đẹp nhưng không đủ sáng trong phòng sáng.','assets/img/productReview/projector_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(38,1,19,5,'Màn hình sắc nét, không bị mờ khi chơi game.','assets/img/productReview/monitor_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(39,19,20,5,'Đồ chơi rất an toàn, trẻ em rất thích.','assets/img/productReview/toy_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03'),(40,1,1,5,'Bàn phím cơ rất tốt, không có gì phải phàn nàn.','assets/img/productReview/keyboard_review_2.jpg','2025-02-18 23:22:03','2025-02-18 23:22:03');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Điện thoại','Mẫu mới nhất với hiệu suất cao',16999000,50,'2025-02-18 23:22:03','2025-02-18 23:22:03',1,1),(2,'Laptop','Laptop mạnh mẽ cho công việc và chơi game',29999000,30,'2025-02-18 23:22:03','2025-02-18 23:22:03',1,2),(3,'Áo thun','Áo thun cotton, có sẵn nhiều màu sắc',499000,100,'2025-02-18 23:22:03','2025-02-18 23:22:03',2,3),(4,'Máy xay sinh tố','Máy xay sinh tố tốc độ cao',2290000,20,'2025-02-18 23:22:03','2025-02-18 23:22:03',3,4),(5,'Tai nghe','Tai nghe chống ồn',4590000,70,'2025-02-18 23:22:03','2025-02-18 23:22:03',1,5),(6,'Giày thể thao','Giày chạy bộ thoải mái',1890000,150,'2025-02-18 23:22:03','2025-02-18 23:22:03',2,6),(7,'Đồng hồ','Đồng hồ đeo tay phong cách với dây da',3490000,80,'2025-02-18 23:22:03','2025-02-18 23:22:03',1,7),(8,'Áo khoác','Áo khoác mùa đông với lớp lót ấm',1890000,60,'2025-02-18 23:22:03','2025-02-18 23:22:03',2,8),(9,'Máy giặt','Máy giặt tiết kiệm năng lượng',11990000,10,'2025-02-18 23:22:03','2025-02-18 23:22:03',3,9),(10,'Điều hòa','Điều hòa làm mát mùa hè',7490000,15,'2025-02-18 23:22:03','2025-02-18 23:22:03',3,10),(11,'Quần jeans','Jeans thoải mái, có sẵn các size',899000,120,'2025-02-18 23:22:03','2025-02-18 23:22:03',2,11),(12,'Tủ lạnh','Tủ lạnh 2 cửa với ngăn đá',17990000,8,'2025-02-18 23:22:03','2025-02-18 23:22:03',3,12),(13,'Máy xay cà phê','Máy xay cà phê tự động',1490000,50,'2025-02-18 23:22:03','2025-02-18 23:22:03',1,13),(14,'Bàn làm việc','Bàn làm việc hiện đại',2790000,25,'2025-02-18 23:22:03','2025-02-18 23:22:03',2,14),(15,'Tủ quần áo','Tủ quần áo gỗ cao cấp',4990000,30,'2025-02-18 23:22:03','2025-02-18 23:22:03',3,15),(16,'Đèn ngủ','Đèn ngủ LED tiết kiệm năng lượng',499000,100,'2025-02-18 23:22:03','2025-02-18 23:22:03',1,16),(17,'Máy chiếu','Máy chiếu mini cho gia đình',1590000,40,'2025-02-18 23:22:03','2025-02-18 23:22:03',1,17),(18,'Màn hình máy tính','Màn hình LED Full HD',2490000,60,'2025-02-18 23:22:03','2025-02-18 23:22:03',2,18),(19,'Đồ chơi trẻ em','Đồ chơi giáo dục cho trẻ em',199000,200,'2025-02-18 23:22:03','2025-02-18 23:22:03',4,19),(20,'Bàn phím cơ','Bàn phím cơ chống nước',1200000,80,'2025-02-18 23:22:03','2025-02-18 23:22:03',1,20);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipper`
--

LOCK TABLES `shipper` WRITE;
/*!40000 ALTER TABLE `shipper` DISABLE KEYS */;
INSERT INTO `shipper` VALUES (1,'John Shipper','john.shipper@example.com','1234567890','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(2,'Jane Shipper','jane.shipper@example.com','0987654321','Busy','2025-02-18 23:22:03','2025-02-18 23:22:03'),(3,'David Shipper','david.shipper@example.com','1122334455','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(4,'Emily Shipper','emily.shipper@example.com','6677889900','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(5,'Chris Shipper','chris.shipper@example.com','2233445566','Busy','2025-02-18 23:22:03','2025-02-18 23:22:03'),(6,'Sarah Shipper','sarah.shipper@example.com','7788990011','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(7,'Michael Shipper','michael.shipper@example.com','4455667788','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(8,'Sophie Shipper','sophie.shipper@example.com','5566778899','Busy','2025-02-18 23:22:03','2025-02-18 23:22:03'),(9,'Daniel Shipper','daniel.shipper@example.com','6677889900','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(10,'Olivia Shipper','olivia.shipper@example.com','3344556677','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(11,'Lucas Shipper','lucas.shipper@example.com','8899001122','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(12,'Emma Shipper','emma.shipper@example.com','2233445566','Busy','2025-02-18 23:22:03','2025-02-18 23:22:03'),(13,'Ryan Shipper','ryan.shipper@example.com','1100112233','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(14,'Liam Shipper','liam.shipper@example.com','7766554433','Busy','2025-02-18 23:22:03','2025-02-18 23:22:03'),(15,'Sophia Shipper','sophia.shipper@example.com','9988776655','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(16,'Mason Shipper','mason.shipper@example.com','2233665500','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(17,'Ava Shipper','ava.shipper@example.com','3355778899','Busy','2025-02-18 23:22:03','2025-02-18 23:22:03'),(18,'Noah Shipper','noah.shipper@example.com','6677885544','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(19,'Noah Shipper22','noah.shipper@example.com','667788554422','Available','2025-02-18 23:22:03','2025-02-18 23:22:03'),(20,'Grace Shipper','grace.shipper@example.com','2233779988','Available','2025-02-18 23:22:03','2025-02-18 23:22:03');
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
INSERT INTO `shipperreviews` VALUES (1,1,1,1,5,'Dịch vụ tuyệt vời, giao hàng nhanh chóng và đúng giờ.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(2,2,2,2,4,'Dịch vụ tốt, nhưng thời gian giao hơi lâu.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(3,3,3,3,5,'Giao hàng nhanh, nhân viên rất thân thiện.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(4,4,4,4,3,'Giao hàng chậm, cần cải thiện tốc độ.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(5,5,5,5,4,'Nhận hàng đúng hạn, tuy nhiên không có thông báo trước.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(6,6,6,6,5,'Giao hàng rất nhanh, đúng hẹn, hài lòng với dịch vụ.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(7,7,7,7,4,'Dịch vụ giao hàng tốt, nhưng đóng gói chưa chắc chắn.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(8,8,8,8,5,'Giao hàng tuyệt vời, rất chuyên nghiệp.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(9,9,9,9,4,'Giao hàng ổn, nhưng chưa đáp ứng hoàn toàn yêu cầu của tôi.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(10,10,10,10,3,'Giao hàng lâu hơn so với dự kiến, nhưng vẫn hài lòng.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(11,11,11,11,5,'Dịch vụ giao hàng hoàn hảo, rất hài lòng.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(12,12,12,12,5,'Giao hàng rất nhanh chóng và đúng hẹn, dịch vụ tuyệt vời.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(13,13,13,13,4,'Giao hàng không đúng thời gian nhưng bù lại chất lượng tốt.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(14,14,14,14,3,'Giao hàng chậm, nhưng nhân viên giao hàng rất lịch sự.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(15,15,15,15,5,'Giao hàng nhanh chóng, sẽ tiếp tục ủng hộ dịch vụ này.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(16,16,16,16,4,'Dịch vụ ổn, nhưng có thể cải thiện thêm.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(17,17,17,17,5,'Giao hàng nhanh chóng, dịch vụ rất tốt.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(18,18,18,18,4,'Giao hàng đúng hẹn nhưng cần cải thiện đóng gói.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(19,19,19,19,5,'Dịch vụ giao hàng rất tuyệt vời.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(20,20,20,20,3,'Giao hàng chậm, cần cải thiện thời gian giao hàng.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(21,1,1,1,5,'Dịch vụ tuyệt vời, giao hàng nhanh chóng và đúng giờ.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(22,2,2,2,4,'Dịch vụ tốt, nhưng thời gian giao hơi lâu.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(23,3,3,3,5,'Giao hàng nhanh, nhân viên rất thân thiện.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(24,1,4,4,3,'Giao hàng chậm, cần cải thiện tốc độ.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(25,5,5,5,4,'Nhận hàng đúng hạn, tuy nhiên không có thông báo trước.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(26,6,6,6,5,'Giao hàng rất nhanh, đúng hẹn, hài lòng với dịch vụ.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(27,7,7,7,4,'Dịch vụ giao hàng tốt, nhưng đóng gói chưa chắc chắn.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(28,1,8,8,5,'Giao hàng tuyệt vời, rất chuyên nghiệp.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(29,9,9,9,4,'Giao hàng ổn, nhưng chưa đáp ứng hoàn toàn yêu cầu của tôi.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(30,1,10,10,3,'Giao hàng lâu hơn so với dự kiến, nhưng vẫn hài lòng.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(31,11,11,11,5,'Dịch vụ giao hàng hoàn hảo, rất hài lòng.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(32,1,12,12,5,'Giao hàng rất nhanh chóng và đúng hẹn, dịch vụ tuyệt vời.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(33,13,13,13,4,'Giao hàng không đúng thời gian nhưng bù lại chất lượng tốt.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(34,1,14,14,3,'Giao hàng chậm, nhưng nhân viên giao hàng rất lịch sự.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(35,1,15,15,5,'Giao hàng nhanh chóng, sẽ tiếp tục ủng hộ dịch vụ này.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(36,16,16,16,4,'Dịch vụ ổn, nhưng có thể cải thiện thêm.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(37,1,17,17,5,'Giao hàng nhanh chóng, dịch vụ rất tốt.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(38,18,18,18,4,'Giao hàng đúng hẹn nhưng cần cải thiện đóng gói.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(39,19,19,19,5,'Dịch vụ giao hàng rất tuyệt vời.','2025-02-18 23:22:03','2025-02-18 23:22:03'),(40,20,20,20,3,'Giao hàng chậm, cần cải thiện thời gian giao hàng.','2025-02-18 23:22:03','2025-02-18 23:22:03');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'Cửa hàng điện tử','assets/img/shop/product-3.jpg','123 Chợ điện tử, Thành phố','John Doe','2025-02-18 23:22:03','2025-02-20 18:56:43'),(2,'Thời trang nữ','assets/img/shop/product-4.jpg','456 Đại lộ Thời trang, Thành phố','Jane Smith','2025-02-18 23:22:03','2025-02-20 18:56:43'),(3,'Cửa hàng đồ thể thao','assets/img/shop/product-5.jpg','789 Khu thể thao, Thành phố','David Lee','2025-02-18 23:22:03','2025-02-20 18:56:43'),(4,'Mỹ phẩm đẹp','assets/img/shop/product-6.jpg','101 Phố làm đẹp, Thành phố','Mary Johnson','2025-02-18 23:22:03','2025-02-20 18:56:43'),(5,'Đồ gia dụng','assets/img/shop/product-7.jpg','102 Đại lộ Gia dụng, Thành phố','Michael Brown','2025-02-18 23:22:03','2025-02-20 18:56:43'),(6,'Cửa hàng đồ chơi','assets/img/shop/product-8.jpg','103 Quảng trường, Thành phố','Emily Davis','2025-02-18 23:22:03','2025-02-20 18:56:43'),(7,'Sách hay','assets/img/shop/product-9.jpg','104 Đường sách, Thành phố','James White','2025-02-18 23:22:03','2025-02-20 18:56:43'),(8,'Nhạc cụ','assets/img/shop/product-10.jpg','105 Phố nhạc, Thành phố','Olivia Brown','2025-02-18 23:22:03','2025-02-20 18:56:43'),(9,'Nội thất cao cấp','assets/img/shop/product-11.jpg','106 Đại lộ Nội thất, Thành phố','Liam Green','2025-02-18 23:22:03','2025-02-20 18:56:43'),(10,'Nội thất nhà','assets/img/shop/product-12.jpg','107 Đường nội thất, Thành phố','Sophia Wilson','2025-02-18 23:22:03','2025-02-20 18:56:43'),(11,'Cửa hàng điện tử 2','assets/img/shop/product-details-1.jpg','108 Đại lộ Công nghệ, Thành phố','James Brown','2025-02-18 23:22:03','2025-02-20 18:56:43'),(12,'Tạp hóa','assets/img/shop/product-details-2.jpg','109 Quảng trường Tạp hóa, Thành phố','Chris Martin','2025-02-18 23:22:03','2025-02-20 18:56:43'),(13,'Cửa hàng giày','assets/img/shop/product-details-3.jpg','110 Phố giày, Thành phố','Sarah Johnson','2025-02-18 23:22:03','2025-02-20 18:56:43'),(14,'Dụng cụ thể thao','assets/img/shop/product-details-4.jpg','111 Đường thể thao, Thành phố','William Green','2025-02-18 23:22:03','2025-02-20 18:56:43'),(15,'Thực phẩm','assets/img/shop/product-details-5.jpg','112 Chợ thực phẩm, Thành phố','Daniel Brown','2025-02-18 23:22:03','2025-02-20 18:56:43'),(16,'Nước hoa','assets/img/shop/product-details-6.jpg','113 Đường nước hoa, Thành phố','Jessica Taylor','2025-02-18 23:22:03','2025-02-20 18:56:43'),(17,'Đồ chơi trẻ em','assets/img/shop/shop_q_logo.png','114 Đường đồ chơi, Thành phố','Emily Wilson','2025-02-18 23:22:03','2025-02-18 23:22:03'),(18,'Bút vẽ','assets/img/shop/shop_r_logo.png','115 Đường vẽ, Thành phố','Andrew Scott','2025-02-18 23:22:03','2025-02-18 23:22:03'),(19,'CoCaa','assets/img/shop/shop_dat_logo.png','115 Đường vẽ, Thành phố','ABc Scott','2025-02-18 23:22:03','2025-02-18 23:22:03'),(20,'Mỹ phẩm cao cấp','assets/img/shop/shop_s_logo.png','116 Đại lộ Mỹ phẩm, Thành phố','Hannah Adams','2025-02-18 23:22:03','2025-02-18 23:22:03');
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

-- Dump completed on 2025-02-21  2:13:23
