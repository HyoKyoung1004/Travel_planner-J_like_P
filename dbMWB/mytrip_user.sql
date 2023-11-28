-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mytrip
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` bigint NOT NULL AUTO_INCREMENT,
  `userAccount` varchar(50) NOT NULL,
  `userEmail` varchar(50) NOT NULL,
  `nickName` varchar(50) NOT NULL,
  `userPassword` varchar(255) DEFAULT NULL,
  `userRole` varchar(10) DEFAULT NULL,
  `joinDate` date NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userAccount` (`userAccount`),
  UNIQUE KEY `userEmail` (`userEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'wodnr8462','wodnr8462@naver.com','woogie','1234','Admin','2023-05-01'),(2,'operation','ㄴㅇㅇㄴㄴㅇ','ㅇㄴㄴㅇㅇ','1111','Admin','2023-05-01'),(3,'woosugin','fggfg@naver.com','woogie','1234','Admin','2023-05-01'),(4,'sadasd','asdasd','asdasd','fgg',NULL,'2023-05-08'),(5,'sadaeresd','asdreerasd','asdererasd','ererfgg',NULL,'2023-05-08'),(6,'sada2323sd','asd223asd','asda2323sd','fgg',NULL,'2023-05-10'),(7,'sa43da2323sd','asd23423asd','asd34a2323sd','fgg',NULL,'2023-05-10'),(8,'sa43dsd','asd2','asd3','fgg',NULL,'2023-05-10'),(9,'sa43','as','a3','fgg',NULL,'2023-05-10'),(10,'sa4233','a3s','a33','fg3g',NULL,'2023-05-10'),(11,'operation11','dddddd','afdddf','1234','AMDIN','2023-05-23');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25 17:04:25
