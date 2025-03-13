-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: purchase-order
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `price` int NOT NULL,
  `cost` int NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Updated Item','This is an updated item',1200,900,'admin','SYSTEM','2025-03-13 13:01:45','2025-03-13 19:54:16'),(2,'Sample Item 2','This is a sample item',1000,800,'admin','admin','2025-03-13 13:22:17','2025-03-13 12:00:00'),(3,'Sample Item 2','This is a sample item',1000,800,'admin','admin','2025-03-13 13:47:58','2025-03-13 12:00:00'),(5,'Sample Item 2','This is a sample item',1000,800,NULL,NULL,'2025-03-13 13:58:27',NULL),(6,'Sample Item 3','This is a sample item',1000,800,NULL,NULL,'2025-03-13 15:42:07',NULL);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `po_d`
--

DROP TABLE IF EXISTS `po_d`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `po_d` (
  `id` int NOT NULL AUTO_INCREMENT,
  `poh_id` int NOT NULL,
  `item_id` int NOT NULL,
  `item_qty` int NOT NULL,
  `item_cost` int NOT NULL,
  `item_price` int NOT NULL,
  `created_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `poh_id` (`poh_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `po_d_ibfk_1` FOREIGN KEY (`poh_id`) REFERENCES `po_h` (`id`),
  CONSTRAINT `po_d_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `po_d`
--

LOCK TABLES `po_d` WRITE;
/*!40000 ALTER TABLE `po_d` DISABLE KEYS */;
INSERT INTO `po_d` VALUES (1,2,1,5,10,15,'2025-03-13 13:22:39','2025-03-13 13:22:39'),(2,2,1,5,10,15,'2025-03-13 13:22:39','2025-03-13 13:22:39'),(3,2,1,5,10,15,'2025-03-13 13:22:39','2025-03-13 13:22:39'),(4,2,2,3,20,30,'2025-03-13 13:22:39','2025-03-13 13:22:39'),(7,4,1,5,10,15,'2025-03-13 15:55:58','2025-03-13 15:55:58'),(8,4,2,3,20,30,'2025-03-13 15:55:58','2025-03-13 15:55:58'),(9,5,1,5,10,15,'2025-03-13 15:58:09','2025-03-13 15:58:09'),(10,5,2,3,20,30,'2025-03-13 15:58:09','2025-03-13 15:58:09');
/*!40000 ALTER TABLE `po_d` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `po_h`
--

DROP TABLE IF EXISTS `po_h`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `po_h` (
  `id` int NOT NULL AUTO_INCREMENT,
  `datetime` datetime NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `total_price` int NOT NULL,
  `total_cost` int NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `po_h`
--

LOCK TABLES `po_h` WRITE;
/*!40000 ALTER TABLE `po_h` DISABLE KEYS */;
INSERT INTO `po_h` VALUES (1,'2025-03-13 10:30:00','Office supplies order',165,110,'john.doe',NULL,'2025-03-13 13:22:40','2025-03-13 20:35:00'),(2,'2025-03-13 10:30:00','Office supplies order',165,110,'john.doe',NULL,'2025-03-13 13:22:40',NULL),(4,'2025-03-13 10:30:00','Office supplies order',165,110,'SYSTEM',NULL,'2025-03-13 15:55:59',NULL),(5,'2025-03-13 10:30:00','Office supplies order',165,110,'SYSTEM',NULL,'2025-03-13 15:58:09',NULL);
/*!40000 ALTER TABLE `po_h` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(500) DEFAULT NULL,
  `last_name` varchar(500) DEFAULT NULL,
  `email` varchar(500) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'John','Doe','john.doe@example.com','555-123-4567',NULL,NULL,'2025-03-12 23:18:41',NULL),(2,'John','Doe','john.doe2@example.com','555-123-4567',NULL,NULL,'2025-03-12 23:18:41','2025-03-13 20:35:23'),(3,'John','Doe','john.doe10@example.com','555-123-4567',NULL,NULL,'2025-03-12 23:18:41','2025-03-13 20:35:23'),(4,'John 15','Doe','john.doe3@example.com','555-123-4444',NULL,'SYSTEM','2025-03-13 13:58:36','2025-03-13 20:10:49'),(6,'John 4','Doe','john.doe4@example.com','555-123-4567',NULL,NULL,'2025-03-13 13:59:04','2025-03-13 13:59:04'),(7,'John 5','Doe','john.doe5@example.com','555-123-4567','SYSTEM','SYSTEM','2025-03-13 14:01:50','2025-03-13 14:01:50'),(9,'John 6','Doe','john.doe6@example.com','555-123-4567','SYSTEM','SYSTEM','2025-03-13 14:03:26','2025-03-13 14:03:26'),(10,'John 7','Doe','john.doe7@example.com','555-123-4567','SYSTEM','SYSTEM','2025-03-13 14:04:43','2025-03-13 14:04:43'),(11,'John 8','Doe','john.doe8@example.com','555-123-4567','SYSTEM',NULL,'2025-03-13 14:05:20',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'purchase-order'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-13 20:35:39
