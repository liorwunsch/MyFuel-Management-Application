CREATE DATABASE  IF NOT EXISTS `myfuel` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `myfuel`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: myfuel
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `activityID` int NOT NULL AUTO_INCREMENT,
  `FK_employeeID` int NOT NULL,
  `time` timestamp NOT NULL,
  `action` varchar(500) NOT NULL,
  PRIMARY KEY (`activityID`),
  KEY `activity_ibfk_1` (`FK_employeeID`),
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`FK_employeeID`) REFERENCES `employee` (`employeeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,4,'2020-01-27 20:00:09','added person customer with ID = 111111111'),(2,4,'2020-01-28 08:01:09','added company customer with ID = 222222222'),(3,4,'2020-01-27 20:02:09','added car with registration plate = 9959599'),(4,4,'2020-01-28 08:03:09','added car with registration plate = 9958599'),(5,4,'2020-01-27 20:04:09','added car with registration plate = 9957599'),(6,4,'2020-01-28 08:05:09','added car with registration plate = 9956599'),(7,4,'2020-01-27 20:06:09','added car with registration plate = 9955599'),(8,4,'2020-01-28 08:07:09','added car with registration plate = 9954599'),(9,4,'2020-01-27 20:08:09','added car with registration plate = 9953599'),(10,4,'2020-01-28 08:09:09','added car with registration plate = 9951599'),(11,1,'2020-02-29 20:00:09','generated quarterly report for 2020 quarter 2'),(12,2,'2020-02-29 20:00:09','generated quarterly report for 2020 quarter 2'),(13,9,'2020-02-29 20:00:09','generated quarterly report for 2020 quarter 2'),(14,3,'2020-01-07 17:30:09','initiated sale with salespatternid 1'),(15,3,'2020-01-07 11:00:09','initiated sale with salespatternid 1'),(16,5,'2020-01-13 23:00:09','declined rates update request 2'),(17,5,'2020-01-05 14:00:09','approved rates update request 3'),(18,1,'2020-01-01 08:00:09','updated minimum storage threshold'),(19,2,'2020-01-01 08:00:09','updated minimum storage threshold'),(20,9,'2020-01-01 08:00:09','updated minimum storage threshold'),(21,6,'2020-01-26 20:00:09','applied supplyment of fuelstationorder 1'),(22,6,'2020-01-27 08:01:09','applied supplyment of fuelstationorder 2'),(23,6,'2020-01-26 20:02:09','applied supplyment of fuelstationorder 3'),(24,7,'2020-02-06 08:00:09','applied supplyment of fuelstationorder 4'),(25,7,'2020-02-05 20:01:09','applied supplyment of fuelstationorder 5'),(26,7,'2020-02-06 08:02:09','applied supplyment of fuelstationorder 6'),(27,8,'2020-02-19 20:00:09','applied supplyment of fuelstationorder 7'),(28,8,'2020-02-20 08:01:09','applied supplyment of fuelstationorder 8'),(29,8,'2020-02-19 20:02:09','applied supplyment of fuelstationorder 9'),(30,3,'2020-06-20 07:15:40','logged in '),(31,3,'2020-06-20 07:15:46','Initialzing Sale '),(32,3,'2020-06-20 07:15:56','Initialzing Sale '),(33,3,'2020-06-20 07:16:37','Create A New Pricing Model Request '),(34,3,'2020-06-20 07:16:40','Create A New Pricing Model Request '),(35,3,'2020-06-20 07:16:46','Create A New Pricing Model Request '),(36,1,'2020-06-20 07:40:24','logged in '),(37,1,'2020-06-20 07:41:26','Updated Threshold '),(38,1,'2020-06-20 07:45:47','logged in '),(39,1,'2020-06-20 07:46:08','Updated Threshold '),(40,1,'2020-06-20 07:49:20','logged in '),(41,1,'2020-06-20 07:49:54','Confirmed unassessed order No.13 '),(42,1,'2020-06-20 07:53:13','Confirmed unassessed order No.13 '),(43,1,'2020-06-20 07:54:12','Genarated generated quarter report Year: 2020 Quarter: 1. '),(44,1,'2020-06-20 07:54:39','Notification Dismissed '),(45,2,'2020-06-20 07:54:50','logged in '),(47,3,'2020-06-20 07:55:31','logged in '),(48,3,'2020-06-20 07:56:41','Generated New Periodic Report With From Date = 2020-06-04 , To Date = 2020-06-17 '),(49,3,'2020-06-20 07:57:24','logged in '),(50,3,'2020-06-20 08:29:19','logged in '),(51,3,'2020-06-20 08:30:04','Created A Sale Pattern With ID = 3 '),(52,7,'2020-06-20 08:44:55','logged in '),(53,2,'2020-06-20 08:45:05','logged in '),(54,6,'2020-06-20 08:45:20','logged in '),(55,12,'2020-06-20 08:45:42','logged in '),(56,12,'2020-06-20 08:45:50','Confirmed unassessed order No.19 '),(57,7,'2020-06-20 08:46:01','logged in '),(58,6,'2020-06-20 08:46:11','logged in '),(59,5,'2020-06-20 08:46:34','logged in '),(60,5,'2020-06-20 08:46:53','approved pricing model update request 5 '),(62,3,'2020-06-20 09:50:30','logged in '),(63,3,'2020-06-20 09:54:45','logged in '),(64,3,'2020-06-20 09:54:57','Created A Sale Pattern With ID = 4 '),(65,3,'2020-06-20 09:55:02','Created A Sale Pattern With ID = 5 '),(66,2,'2020-06-20 10:00:25','logged in '),(67,2,'2020-06-20 10:00:31','Updated Threshold '),(68,3,'2020-06-20 11:55:10','logged in '),(69,3,'2020-06-20 11:55:57','Initialzing Sale '),(70,3,'2020-06-20 11:56:01','Initialzing Sale '),(71,3,'2020-06-20 11:56:06','Initialzing Sale '),(72,3,'2020-06-20 11:56:20','Created A Sale Pattern With ID = 6 '),(73,3,'2020-06-20 11:56:30','Initialzing Sale '),(74,3,'2020-06-20 11:56:55','Initialzing Sale '),(75,3,'2020-06-20 11:57:05','Generated New Common Report For Sale = 3 '),(76,3,'2020-06-20 11:57:12','Generated New Periodic Report With From Date = 2020-06-20 , To Date = 2020-06-20 '),(77,3,'2020-06-20 11:57:48','Create A New Pricing Model Request '),(78,3,'2020-06-20 11:57:53','Create A New Pricing Model Request '),(79,5,'2020-06-20 11:58:07','logged in '),(80,1,'2020-06-20 11:58:22','logged in '),(81,4,'2020-06-20 11:58:47','logged in '),(82,4,'2020-06-20 11:59:57','saved customer \'234234234\' '),(83,4,'2020-06-20 12:00:11','saved car \'12341234\' for customer \'234234234\' '),(84,4,'2020-06-20 12:00:20','saved car \'3213212\' for customer \'234234234\' '),(85,4,'2020-06-20 12:00:30','saved car \'3213215\' for customer \'234234234\' '),(86,4,'2020-06-20 12:00:46','set purchasing program \'Premium\' for customer \'234234234\' '),(87,4,'2020-06-20 12:00:50','set pricing model for customer \'234234234\' '),(88,4,'2020-06-20 12:01:31','set pricing model for customer \'234234234\' '),(89,4,'2020-06-20 12:01:38','set pricing model for customer \'234234234\' '),(90,4,'2020-06-20 12:02:18','saved customer \'123456789\' '),(91,4,'2020-06-20 12:02:30','saved car \'55566644\' for customer \'123456789\' '),(92,4,'2020-06-20 12:02:37','saved car \'55566642\' for customer \'123456789\' '),(93,4,'2020-06-20 12:02:43','set purchasing program \'Standard\' for customer \'123456789\' '),(94,4,'2020-06-20 12:02:48','set pricing model for customer \'123456789\' '),(95,4,'2020-06-20 12:03:35','saved customer \'212212212\' '),(96,4,'2020-06-20 12:03:48','saved car \'12332112\' for customer \'212212212\' '),(97,4,'2020-06-20 12:03:51','set purchasing program \'Standard\' for customer \'212212212\' '),(98,4,'2020-06-20 12:03:53','set pricing model for customer \'212212212\' '),(99,4,'2020-06-20 12:04:45','logged in '),(100,4,'2020-06-20 12:05:15','logged in '),(101,10,'2020-06-20 12:15:47','logged in '),(102,10,'2020-06-20 12:16:03','Confirmed unassessed order No.24 '),(103,11,'2020-06-20 12:16:14','logged in '),(104,11,'2020-06-20 12:16:20','Confirmed unassessed order No.17 '),(105,12,'2020-06-20 12:16:28','logged in '),(106,12,'2020-06-20 12:16:31','Confirmed unassessed order No.26 '),(107,6,'2020-06-20 12:16:39','logged in '),(108,6,'2020-06-20 12:16:50','approved supply of fuel station order '),(109,5,'2020-06-20 12:17:09','logged in '),(110,5,'2020-06-20 12:17:16','approved pricing model update request 3 '),(111,8,'2020-06-20 12:17:56','logged in '),(112,9,'2020-06-20 12:18:14','logged in ');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `registrationPlate` varchar(50) NOT NULL,
  `FK_customerID` varchar(50) NOT NULL,
  `FK_productName` varchar(50) NOT NULL,
  `ownerName` varchar(500) NOT NULL,
  `deleted` varchar(1) NOT NULL,
  PRIMARY KEY (`registrationPlate`),
  KEY `car_ibfk_1` (`FK_productName`),
  KEY `car_ibfk_2` (`FK_customerID`),
  CONSTRAINT `car_ibfk_1` FOREIGN KEY (`FK_productName`) REFERENCES `product` (`productname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `car_ibfk_2` FOREIGN KEY (`FK_customerID`) REFERENCES `customer` (`customerid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES ('12332112','212212212','Diesel','Sidius','0'),('12341234','234234234','Motorbike Fuel','ObiWan','0'),('3213212','234234234','Diesel','ObiWan','0'),('3213215','234234234','Gasoline','ObiWan','0'),('55566642','123456789','Gasoline','Anikan','0'),('55566644','123456789','Diesel','Anikan','0'),('9951599','222222222','Motorbike Fuel','Israel Ltd','0'),('9953599','222222222','Motorbike Fuel','Israel Ltd','0'),('9954599','222222222','Gasoline','Israel Ltd','0'),('9955599','222222222','Diesel','Israel Ltd','0'),('9956599','222222222','Gasoline','Israel Ltd','0'),('9957599','222222222','Diesel','Israel Ltd','0'),('9958599','222222222','Gasoline','Israel Ltd','0'),('9959599','111111111','Gasoline','Israel A','0');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customerID` varchar(50) NOT NULL,
  `FK_username` varchar(50) NOT NULL,
  `creditCard` varchar(20) NOT NULL,
  `customerType` varchar(50) NOT NULL,
  `deleted` varchar(1) NOT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `FK_username` (`FK_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('111111111','IsraelThePersonCustomer','1111222233334444','Person','0'),('123456789','123456789','3333444455556666','Person','0'),('212212212','212212212','1234123412341234','Company','0'),('222222222','IsraelTheCompanyCustomer','4444333322221111','Company','0'),('234234234','234234234','1111111122222222','Person','0');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_bought_from_company`
--

DROP TABLE IF EXISTS `customer_bought_from_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_bought_from_company` (
  `FK_customerID` varchar(50) NOT NULL,
  `FK_fuelCompanyName` varchar(50) NOT NULL,
  `dateOfPurchase` timestamp NOT NULL,
  `amountBoughtFromCompany` double(32,2) NOT NULL,
  `amountPaidCompany` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_customerID`,`FK_fuelCompanyName`,`dateOfPurchase`),
  KEY `CustomerBoughtFromCompany_ibfk_1` (`FK_customerID`),
  KEY `CustomerBoughtFromCompany_ibfk_2` (`FK_fuelCompanyName`),
  CONSTRAINT `CustomerBoughtFromCompany_ibfk_1` FOREIGN KEY (`FK_customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CustomerBoughtFromCompany_ibfk_2` FOREIGN KEY (`FK_fuelCompanyName`) REFERENCES `fuel_company` (`fuelcompanyname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_bought_from_company`
--

LOCK TABLES `customer_bought_from_company` WRITE;
/*!40000 ALTER TABLE `customer_bought_from_company` DISABLE KEYS */;
INSERT INTO `customer_bought_from_company` VALUES ('111111111','Paz','2020-01-06 11:15:09',3791.67,875.00),('111111111','Paz','2020-06-20 07:13:46',5.00,20.75),('222222222','Delek','2020-01-06 11:15:09',4823.00,1113.00),('222222222','Delek','2020-06-20 07:12:25',255.00,1096.50),('222222222','Paz','2020-06-20 07:12:09',506.00,2831.55),('222222222','Sonol','2020-01-06 11:15:09',1971.67,455.00),('222222222','Sonol','2020-06-20 07:11:59',438.00,1904.90),('234234234','Delek','2020-06-20 12:07:41',200.00,860.00);
/*!40000 ALTER TABLE `customer_bought_from_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_bought_in_sale`
--

DROP TABLE IF EXISTS `customer_bought_in_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_bought_in_sale` (
  `FK_saleID` int NOT NULL,
  `FK_customerID` varchar(50) NOT NULL,
  `amountPaid` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_saleID`,`FK_customerID`),
  KEY `customer_bought_in_sale_ibfk_1` (`FK_saleID`),
  KEY `customer_bought_in_sale_ibfk_2` (`FK_customerID`),
  CONSTRAINT `customer_bought_in_sale_ibfk_1` FOREIGN KEY (`FK_saleID`) REFERENCES `sale` (`saleid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `customer_bought_in_sale_ibfk_2` FOREIGN KEY (`FK_customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_bought_in_sale`
--

LOCK TABLES `customer_bought_in_sale` WRITE;
/*!40000 ALTER TABLE `customer_bought_in_sale` DISABLE KEYS */;
INSERT INTO `customer_bought_in_sale` VALUES (1,'111111111',250.00),(1,'222222222',130.00),(2,'222222222',188.00),(3,'222222222',688.00);
/*!40000 ALTER TABLE `customer_bought_in_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employeeID` int NOT NULL AUTO_INCREMENT,
  `FK_userName` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  `affiliation` varchar(50) NOT NULL,
  PRIMARY KEY (`employeeID`),
  UNIQUE KEY `FK_userName` (`FK_userName`),
  KEY `employee_ibfk_1` (`FK_userName`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`FK_userName`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'IsraelTheSonolFuelStationManager','FuelStationManager','Fuel Station'),(2,'IsraelThePazFuelStationManager','FuelStationManager','Fuel Station'),(3,'IsraelTheMarketingManager','MarketingManager','Marketing'),(4,'IsraelTheMarketingRepresentative','MarketingRepresentative','Marketing'),(5,'IsraelTheNetworkManager','NetworkManager','Management'),(6,'IsraelTheSonolSupplier','Supplier','Supplier'),(7,'IsraelThePazSupplier','Supplier','Supplier'),(8,'IsraelTheDelekSupplier','Supplier','Supplier'),(9,'IsraelTheDelekFuelStationManager','FuelStationManager','Fuel Station'),(10,'IsraelTheDelekFuelStationManager2','FuelStationManager','Fuel Station'),(11,'IsraelThePazFuelStationManager2','FuelStationManager','Fuel Station'),(12,'IsraelTheSonolFuelStationManager2','FuelStationManager','Fuel Station');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fast_fuel`
--

DROP TABLE IF EXISTS `fast_fuel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fast_fuel` (
  `fastFuelID` int NOT NULL AUTO_INCREMENT,
  `FK_registrationPlate` varchar(50) NOT NULL,
  `FK_customerID` varchar(50) NOT NULL,
  `FK_productInStationID` int NOT NULL,
  `fastFuelTime` timestamp NOT NULL,
  `amountBought` double(32,2) NOT NULL,
  `finalPrice` double(32,2) NOT NULL,
  PRIMARY KEY (`fastFuelID`),
  KEY `fast_fuel_ibfk_1` (`FK_productInStationID`),
  KEY `fast_fuel_ibfk_2` (`FK_customerID`),
  KEY `fast_fuel_ibfk_3` (`FK_registrationPlate`),
  CONSTRAINT `fast_fuel_ibfk_1` FOREIGN KEY (`FK_productInStationID`) REFERENCES `product_in_station` (`productinstationid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fast_fuel_ibfk_2` FOREIGN KEY (`FK_customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fast_fuel_ibfk_3` FOREIGN KEY (`FK_registrationPlate`) REFERENCES `car` (`registrationPlate`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fast_fuel`
--

LOCK TABLES `fast_fuel` WRITE;
/*!40000 ALTER TABLE `fast_fuel` DISABLE KEYS */;
INSERT INTO `fast_fuel` VALUES (1,'9959599','111111111',4,'2020-01-06 23:30:09',43.14,151.00),(2,'9957599','222222222',5,'2020-01-06 23:30:09',50.00,151.00),(3,'9951599','222222222',6,'2020-01-06 23:30:09',23.23,151.00),(4,'9958599','222222222',1,'2020-01-06 23:30:09',83.14,291.00),(5,'9955599','222222222',2,'2020-01-06 23:30:09',97.00,291.00),(6,'9953599','222222222',3,'2020-01-06 23:30:09',44.77,291.00),(7,'9954599','222222222',7,'2020-01-06 23:30:09',106.00,371.00),(8,'9957599','222222222',8,'2020-01-07 11:20:09',123.00,371.00),(9,'9953599','222222222',9,'2020-01-07 11:20:09',57.08,371.00),(10,'9956599','222222222',1,'2020-06-20 07:11:59',12.00,51.60),(11,'9956599','222222222',4,'2020-06-20 07:12:09',1.00,4.30),(12,'9956599','222222222',7,'2020-06-20 07:12:25',232.00,997.60),(13,'9956599','222222222',10,'2020-06-20 07:12:35',23.00,98.90),(14,'9956599','222222222',13,'2020-06-20 07:13:14',200.00,860.00),(15,'9956599','222222222',16,'2020-06-20 07:13:26',1.00,4.30),(16,'9959599','111111111',4,'2020-06-20 07:13:46',2.00,8.30),(17,'9959599','111111111',13,'2020-06-20 07:13:53',3.00,12.45),(18,'9955599','222222222',17,'2020-06-20 07:18:18',200.00,1032.00),(19,'9956599','222222222',16,'2020-06-20 07:19:17',200.00,688.00),(20,'3213215','234234234',10,'2020-06-20 12:07:41',200.00,860.00),(21,'9951599','222222222',15,'2020-06-20 12:09:57',5.00,32.25),(22,'9951599','222222222',15,'2020-06-20 12:10:26',100.00,645.00),(23,'9951599','222222222',15,'2020-06-20 12:14:18',200.00,1290.00),(24,'9955599','222222222',17,'2020-06-20 12:14:45',25.00,129.00);
/*!40000 ALTER TABLE `fast_fuel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel_company`
--

DROP TABLE IF EXISTS `fuel_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fuel_company` (
  `fuelCompanyName` varchar(50) NOT NULL,
  `FK_employeeID` int NOT NULL,
  PRIMARY KEY (`fuelCompanyName`),
  KEY `fuel_company_ibfk_1` (`FK_employeeID`),
  CONSTRAINT `fuel_company_ibfk_1` FOREIGN KEY (`FK_employeeID`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_company`
--

LOCK TABLES `fuel_company` WRITE;
/*!40000 ALTER TABLE `fuel_company` DISABLE KEYS */;
INSERT INTO `fuel_company` VALUES ('Sonol',6),('Paz',7),('Delek',8);
/*!40000 ALTER TABLE `fuel_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel_station`
--

DROP TABLE IF EXISTS `fuel_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fuel_station` (
  `fuelStationID` int NOT NULL AUTO_INCREMENT,
  `FK_fuelCompanyName` varchar(50) NOT NULL,
  `FK_employeeID` int NOT NULL,
  `stationName` varchar(500) NOT NULL,
  `address` varchar(500) NOT NULL,
  PRIMARY KEY (`fuelStationID`),
  UNIQUE KEY `FK_employeeID` (`FK_employeeID`),
  UNIQUE KEY `stationName` (`stationName`),
  KEY `fuel_station_ibfk_1` (`FK_fuelCompanyName`),
  KEY `fuel_station_ibfk_2` (`FK_employeeID`),
  CONSTRAINT `fuel_station_ibfk_1` FOREIGN KEY (`FK_fuelCompanyName`) REFERENCES `fuel_company` (`fuelCompanyName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fuel_station_ibfk_2` FOREIGN KEY (`FK_employeeID`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_station`
--

LOCK TABLES `fuel_station` WRITE;
/*!40000 ALTER TABLE `fuel_station` DISABLE KEYS */;
INSERT INTO `fuel_station` VALUES (1,'Sonol',1,'Sonol Neighborhood','Peretz St, Kiryat Ata'),(2,'Paz',2,'Paz Gas Ltd','Road 6'),(3,'Delek',9,'Delek Dror','Dror St, Karmiel'),(4,'Delek',10,'Delek Delek','Delek St, Haifa'),(5,'Paz',11,'Paz Paz','Road 4'),(6,'Sonol',12,'Sonol Sonol','Hameginim, Kiryat Bialik');
/*!40000 ALTER TABLE `fuel_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel_station_manager`
--

DROP TABLE IF EXISTS `fuel_station_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fuel_station_manager` (
  `FK_employeeID` int NOT NULL,
  `phoneNo` varchar(50) NOT NULL,
  PRIMARY KEY (`FK_employeeID`),
  KEY `fuel_station_manager_ibfk_1` (`FK_employeeID`),
  CONSTRAINT `fuel_station_manager_ibfk_1` FOREIGN KEY (`FK_employeeID`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_station_manager`
--

LOCK TABLES `fuel_station_manager` WRITE;
/*!40000 ALTER TABLE `fuel_station_manager` DISABLE KEYS */;
INSERT INTO `fuel_station_manager` VALUES (1,'0501111111'),(2,'0502222222'),(9,'0509999999'),(10,'0503333333'),(11,'0504444444'),(12,'0505555555');
/*!40000 ALTER TABLE `fuel_station_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel_station_order`
--

DROP TABLE IF EXISTS `fuel_station_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fuel_station_order` (
  `FK_ordersID` int NOT NULL,
  `FK_productInStationID` int NOT NULL,
  `assessed` varchar(1) NOT NULL,
  `approved` varchar(1) DEFAULT NULL,
  `reasonDismissal` varchar(500) DEFAULT NULL,
  `supplied` varchar(1) NOT NULL,
  `timeSupplied` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`FK_ordersID`),
  KEY `fuel_station_order_ibfk_1` (`FK_productInStationID`),
  KEY `fuel_station_order_ibfk_2` (`FK_ordersID`),
  CONSTRAINT `fuel_station_order_ibfk_1` FOREIGN KEY (`FK_productInStationID`) REFERENCES `product_in_station` (`productinstationid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fuel_station_order_ibfk_2` FOREIGN KEY (`FK_ordersID`) REFERENCES `orders` (`ordersid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_station_order`
--

LOCK TABLES `fuel_station_order` WRITE;
/*!40000 ALTER TABLE `fuel_station_order` DISABLE KEYS */;
INSERT INTO `fuel_station_order` VALUES (3,1,'1','1','Nan','1','2020-01-25 23:30:09'),(4,2,'1','1','Nan','1','2020-01-25 23:30:09'),(5,3,'1','1','Nan','1','2020-01-25 23:30:09'),(6,4,'1','1','Nan','1','2020-02-05 11:30:09'),(7,5,'1','1','Nan','1','2020-02-05 11:30:09'),(8,6,'1','1','Nan','1','2020-02-05 11:30:09'),(9,7,'1','1','Nan','1','2020-02-18 23:30:09'),(10,8,'1','1','Nan','1','2020-02-18 23:30:09'),(11,9,'1','1','Nan','1','2020-02-18 23:30:09'),(12,1,'1','0','supply already on route','0',NULL),(13,2,'1','1','Nan ','0',NULL),(17,13,'1','1','Nan ','0',NULL),(18,13,'0',NULL,NULL,'0',NULL),(19,17,'1','1','Nan ','0',NULL),(20,16,'0',NULL,NULL,'0',NULL),(24,10,'1','1','Nan ','0',NULL),(25,15,'0',NULL,NULL,'0',NULL),(26,17,'1','1','Nan ','1','2020-06-20 12:16:49');
/*!40000 ALTER TABLE `fuel_station_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `home_fuel_order`
--

DROP TABLE IF EXISTS `home_fuel_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `home_fuel_order` (
  `FK_ordersID` int NOT NULL,
  `FK_customerID` varchar(50) NOT NULL,
  `FK_product_Name` varchar(50) NOT NULL,
  `FK_shipmentType` varchar(50) NOT NULL,
  `duetime` timestamp NOT NULL,
  `finalPrice` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_ordersID`),
  KEY `home_fuel_order_ibfk_1` (`FK_ordersID`),
  KEY `home_fuel_order_ibfk_2` (`FK_customerID`),
  KEY `home_fuel_order_ibfk_3` (`FK_product_Name`),
  KEY `home_fuel_order_ibfk_4` (`FK_shipmentType`),
  CONSTRAINT `home_fuel_order_ibfk_1` FOREIGN KEY (`FK_ordersID`) REFERENCES `orders` (`ordersid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `home_fuel_order_ibfk_2` FOREIGN KEY (`FK_customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `home_fuel_order_ibfk_3` FOREIGN KEY (`FK_product_Name`) REFERENCES `product` (`productname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `home_fuel_order_ibfk_4` FOREIGN KEY (`FK_shipmentType`) REFERENCES `shipment_method` (`shipmenttype`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `home_fuel_order`
--

LOCK TABLES `home_fuel_order` WRITE;
/*!40000 ALTER TABLE `home_fuel_order` DISABLE KEYS */;
INSERT INTO `home_fuel_order` VALUES (1,'111111111','Home Fuel','Regular','2020-01-13 23:30:09',1169.34),(2,'111111111','Home Fuel','Urgent','2020-02-24 17:30:09',821.50),(14,'222222222','Home Fuel','Urgent','2020-06-20 13:10:39',29.98),(15,'222222222','Home Fuel','Urgent','2020-06-20 13:11:06',29.98),(16,'222222222','Home Fuel','Regular','2020-06-30 07:11:32',89.50),(21,'123456789','Home Fuel','Urgent','2020-06-20 18:06:05',52.42),(22,'123456789','Home Fuel','Regular','2020-06-30 12:06:18',49.50),(23,'234234234','Home Fuel','Regular','2020-06-30 12:06:37',73.50);
/*!40000 ALTER TABLE `home_fuel_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income_report`
--

DROP TABLE IF EXISTS `income_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `income_report` (
  `FK_repQuarter` int NOT NULL,
  `FK_repYear` varchar(50) NOT NULL,
  `FK_fuelStationID` int NOT NULL,
  `totalIncome` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_repQuarter`,`FK_repYear`,`FK_fuelStationID`),
  KEY `income_report_ibfk_1` (`FK_repQuarter`,`FK_repYear`,`FK_fuelStationID`),
  CONSTRAINT `income_report_ibfk_1` FOREIGN KEY (`FK_repQuarter`, `FK_repYear`, `FK_fuelStationID`) REFERENCES `quarterly_report` (`repquarter`, `repyear`, `fk_fuelstationid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income_report`
--

LOCK TABLES `income_report` WRITE;
/*!40000 ALTER TABLE `income_report` DISABLE KEYS */;
INSERT INTO `income_report` VALUES (1,'2020',1,453.00),(1,'2020',2,873.00),(1,'2020',3,1113.00);
/*!40000 ALTER TABLE `income_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_report`
--

DROP TABLE IF EXISTS `inventory_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_report` (
  `FK_repQuarter` int NOT NULL,
  `FK_repYear` varchar(50) NOT NULL,
  `FK_fuelStationID` int NOT NULL,
  `totalAmountSold` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_repQuarter`,`FK_repYear`,`FK_fuelStationID`),
  KEY `inventory_report_ibfk_1` (`FK_repQuarter`,`FK_repYear`,`FK_fuelStationID`),
  CONSTRAINT `inventory_report_ibfk_1` FOREIGN KEY (`FK_repQuarter`, `FK_repYear`, `FK_fuelStationID`) REFERENCES `quarterly_report` (`repquarter`, `repyear`, `fk_fuelstationid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_report`
--

LOCK TABLES `inventory_report` WRITE;
/*!40000 ALTER TABLE `inventory_report` DISABLE KEYS */;
INSERT INTO `inventory_report` VALUES (1,'2020',1,116.37),(1,'2020',2,224.91),(1,'2020',3,286.08);
/*!40000 ALTER TABLE `inventory_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notificationID` int NOT NULL AUTO_INCREMENT,
  `FK_employeeID` int NOT NULL,
  `message` varchar(500) NOT NULL,
  `dismissed` varchar(1) NOT NULL,
  `dateCreated` timestamp NOT NULL,
  PRIMARY KEY (`notificationID`),
  KEY `notification_ibfk_1` (`FK_employeeID`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`FK_employeeID`) REFERENCES `fuel_station_manager` (`FK_employeeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,1,'a station order is ready to be assessed','1','2020-01-24 11:15:09'),(2,2,'a station order is ready to be assessed','1','2020-02-03 11:15:09'),(3,9,'a station order is ready to be assessed','1','2020-02-17 11:15:09'),(4,1,'a station order is ready to be assessed','1','2020-01-25 11:15:09'),(5,11,'station order 17 is ready to be assessed','0','2020-06-20 07:13:14'),(6,11,'station order 18 is ready to be assessed','0','2020-06-20 07:13:53'),(7,12,'station order 19 is ready to be assessed','0','2020-06-20 07:18:18'),(8,12,'station order 20 is ready to be assessed','0','2020-06-20 07:19:17'),(9,12,'a quarterly report for the year 2020 and the quarter 1 has yet to be created','0','2020-06-20 11:15:42'),(10,10,'station order 24 is ready to be assessed','0','2020-06-20 12:07:41'),(11,11,'station order 25 is ready to be assessed','0','2020-06-20 12:14:18'),(12,12,'station order 26 is ready to be assessed','0','2020-06-20 12:14:45'),(13,10,'a quarterly report for the year 2020 and the quarter 1 has yet to be created','0','2020-06-20 14:45:47'),(14,11,'a quarterly report for the year 2020 and the quarter 1 has yet to be created','0','2020-06-20 14:46:14'),(15,12,'a quarterly report for the year 2020 and the quarter 1 has yet to be created','0','2020-06-20 14:46:28'),(16,12,'fuelstation order 26 is supplied.','0','2020-06-20 12:16:50');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `ordersID` int NOT NULL AUTO_INCREMENT,
  `orderTime` timestamp NOT NULL,
  `amountBought` double(32,2) NOT NULL,
  `address` varchar(500) NOT NULL,
  PRIMARY KEY (`ordersID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2020-01-03 23:30:09',600.00,'4th Hertzel St, Haifa'),(2,'2020-02-24 11:30:09',400.00,'4th Hertzel St, Haifa'),(3,'2020-01-24 23:30:09',5130.00,'Peretz St, Kiryat Ata'),(4,'2020-01-24 23:30:09',4275.00,'Peretz St, Kiryat Ata'),(5,'2020-01-24 23:30:09',3420.00,'Peretz St, Kiryat Ata'),(6,'2020-02-04 11:30:09',17100.00,'Road 6'),(7,'2020-02-04 11:30:09',14250.00,'Road 6'),(8,'2020-02-04 11:30:09',11400.00,'Road 6'),(9,'2020-02-17 23:30:09',3420.00,'Dror St, Karmiel'),(10,'2020-02-17 23:30:09',2850.00,'Dror St, Karmiel'),(11,'2020-02-17 23:30:09',2280.00,'Dror St, Karmiel'),(12,'2020-01-26 11:30:09',5130.00,'Peretz St, Kiryat Ata'),(13,'2020-03-01 23:30:09',4275.00,'Peretz St, Kiryat Ata'),(14,'2020-06-20 07:10:39',12.00,'Lev Haari 3, Haifa'),(15,'2020-06-20 07:11:06',12.00,'Lev Haari 3, Haifa'),(16,'2020-06-20 07:11:32',42.00,'Mazi Pinat Mapo, Haifa'),(17,'2020-06-20 07:13:14',100.00,'Road 4'),(18,'2020-06-20 07:13:53',106.00,'Road 4'),(19,'2020-06-20 07:18:18',100.00,'Hameginim, Kiryat Bialik'),(20,'2020-06-20 07:19:17',102.00,'Hameginim, Kiryat Bialik'),(21,'2020-06-20 12:06:05',23.00,'Tatoine'),(22,'2020-06-20 12:06:18',22.00,'Kamino'),(23,'2020-06-20 12:06:37',34.00,'Yavin-4'),(24,'2020-06-20 12:07:41',146.00,'Delek St, Haifa'),(25,'2020-06-20 12:14:18',310.00,'Road 4'),(26,'2020-06-20 12:14:45',150.00,'Hameginim, Kiryat Bialik');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outcome_report`
--

DROP TABLE IF EXISTS `outcome_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outcome_report` (
  `FK_repQuarter` int NOT NULL,
  `FK_repYear` varchar(50) NOT NULL,
  `FK_fuelStationID` int NOT NULL,
  `totalAmountBoughtFromSupplier` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_repQuarter`,`FK_repYear`,`FK_fuelStationID`),
  KEY `outcome_report_ibfk_1` (`FK_repQuarter`,`FK_repYear`,`FK_fuelStationID`),
  CONSTRAINT `outcome_report_ibfk_1` FOREIGN KEY (`FK_repQuarter`, `FK_repYear`, `FK_fuelStationID`) REFERENCES `quarterly_report` (`repquarter`, `repyear`, `fk_fuelstationid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outcome_report`
--

LOCK TABLES `outcome_report` WRITE;
/*!40000 ALTER TABLE `outcome_report` DISABLE KEYS */;
INSERT INTO `outcome_report` VALUES (1,'2020',1,116.37),(1,'2020',2,224.91),(1,'2020',3,286.08);
/*!40000 ALTER TABLE `outcome_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodic_customers_report`
--

DROP TABLE IF EXISTS `periodic_customers_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periodic_customers_report` (
  `dateFrom` timestamp NOT NULL,
  `dateTo` timestamp NOT NULL,
  `dateCreated` timestamp NOT NULL,
  PRIMARY KEY (`dateFrom`,`dateTo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodic_customers_report`
--

LOCK TABLES `periodic_customers_report` WRITE;
/*!40000 ALTER TABLE `periodic_customers_report` DISABLE KEYS */;
INSERT INTO `periodic_customers_report` VALUES ('2019-12-31 11:15:09','2020-01-27 11:15:09','2020-01-27 11:15:09'),('2020-06-04 10:26:41','2020-06-17 10:26:41','2020-06-20 10:26:41'),('2020-06-20 14:27:11','2020-06-20 14:27:11','2020-06-20 14:27:11');
/*!40000 ALTER TABLE `periodic_customers_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricing_model`
--

DROP TABLE IF EXISTS `pricing_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricing_model` (
  `FK_customerID` varchar(50) NOT NULL,
  `FK_pricingModelName` varchar(50) NOT NULL,
  `currentDiscount` double(32,2) NOT NULL,
  `lastMonthUtillization` double(32,2) DEFAULT NULL,
  PRIMARY KEY (`FK_customerID`),
  KEY `pricing_model_ibfk_1` (`FK_customerID`),
  KEY `pricing_model_ibfk_2` (`FK_pricingModelName`),
  CONSTRAINT `pricing_model_ibfk_1` FOREIGN KEY (`FK_customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pricing_model_ibfk_2` FOREIGN KEY (`FK_pricingModelName`) REFERENCES `pricing_model_type` (`pricingmodelname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricing_model`
--

LOCK TABLES `pricing_model` WRITE;
/*!40000 ALTER TABLE `pricing_model` DISABLE KEYS */;
INSERT INTO `pricing_model` VALUES ('111111111','Full Program Single Car',0.15,1.33),('123456789','Pay In Place',0.00,NULL),('212212212','Full Program Single Car',0.15,0.00),('222222222','Monthly Program Multiple Cars',0.14,NULL),('234234234','Monthly Program Multiple Cars',0.14,NULL);
/*!40000 ALTER TABLE `pricing_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricing_model_type`
--

DROP TABLE IF EXISTS `pricing_model_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricing_model_type` (
  `pricingModelName` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `defaultDiscount` double(32,2) NOT NULL,
  PRIMARY KEY (`pricingModelName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricing_model_type`
--

LOCK TABLES `pricing_model_type` WRITE;
/*!40000 ALTER TABLE `pricing_model_type` DISABLE KEYS */;
INSERT INTO `pricing_model_type` VALUES ('Full Program Single Car','Payment every month for the amount bought in practice in the previous month,\nby price like \'Monthly Program Multiple Cars\'\n+\nextra discount of determined discount (per liter)',0.15),('Monthly Program Multiple Cars','Like \'Monthly Program Single Car\'\n+\ngeneral discount of determined discount (per liter)',0.14),('Monthly Program Single Car','Determined Discount from max price (per liter)',0.02),('Pay In Place','Max price (per liter)',0.00);
/*!40000 ALTER TABLE `pricing_model_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricing_model_update_request`
--

DROP TABLE IF EXISTS `pricing_model_update_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricing_model_update_request` (
  `requestID` int NOT NULL AUTO_INCREMENT,
  `FK_pricingModelName` varchar(50) DEFAULT NULL,
  `requestDate` timestamp NOT NULL,
  `requestedDiscount` double(32,2) NOT NULL,
  `assessed` varchar(1) NOT NULL,
  `approved` varchar(1) DEFAULT NULL,
  `reasonDismissal` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`requestID`),
  KEY `pricing_model_ibfk_5` (`FK_pricingModelName`),
  CONSTRAINT `pricing_model_ibfk_5` FOREIGN KEY (`FK_pricingModelName`) REFERENCES `pricing_model_type` (`pricingModelName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricing_model_update_request`
--

LOCK TABLES `pricing_model_update_request` WRITE;
/*!40000 ALTER TABLE `pricing_model_update_request` DISABLE KEYS */;
INSERT INTO `pricing_model_update_request` VALUES (1,'Monthly Program Single Car','2020-01-15 11:15:09',10.00,'0',NULL,NULL),(2,'Full Program Single Car','2020-01-12 11:15:09',2.00,'1','0','No Bueno'),(3,'Monthly Program Single Car','2020-06-20 07:16:37',2.00,'1','1',NULL),(4,'Monthly Program Multiple Cars','2020-06-20 07:16:40',5.00,'0',NULL,NULL),(5,'Full Program Single Car','2020-06-20 07:16:45',15.00,'1','1',NULL),(6,'Full Program Single Car','2020-06-20 11:57:48',23.20,'0',NULL,NULL),(7,'Monthly Program Multiple Cars','2020-06-20 11:57:52',4.87,'0',NULL,NULL);
/*!40000 ALTER TABLE `pricing_model_update_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productName` varchar(50) NOT NULL,
  `maxPrice` double(32,2) NOT NULL,
  `currentPrice` double(32,2) NOT NULL,
  PRIMARY KEY (`productName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('Diesel',10.00,6.00),('Gasoline',10.00,5.00),('Home Fuel',5.00,2.00),('Motorbike Fuel',15.00,7.50);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_in_income_report`
--

DROP TABLE IF EXISTS `product_in_income_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_in_income_report` (
  `FK_productInStationID` int NOT NULL,
  `FK_repQuarter_IncomeReport` int NOT NULL,
  `FK_repYear_IncomeReport` varchar(50) NOT NULL,
  `incomePerProduct` double(32,2) NOT NULL,
  `avgPrice` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_productInStationID`,`FK_repQuarter_IncomeReport`,`FK_repYear_IncomeReport`),
  KEY `product_in_income_report_ibfk_1` (`FK_productInStationID`),
  KEY `product_in_income_report_ibfk_2` (`FK_repQuarter_IncomeReport`,`FK_repYear_IncomeReport`),
  CONSTRAINT `product_in_income_report_ibfk_1` FOREIGN KEY (`FK_productInStationID`) REFERENCES `product_in_station` (`productinstationid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_in_income_report_ibfk_2` FOREIGN KEY (`FK_repQuarter_IncomeReport`, `FK_repYear_IncomeReport`) REFERENCES `income_report` (`FK_repQuarter`, `FK_repYear`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_in_income_report`
--

LOCK TABLES `product_in_income_report` WRITE;
/*!40000 ALTER TABLE `product_in_income_report` DISABLE KEYS */;
INSERT INTO `product_in_income_report` VALUES (1,1,'2020',151.00,3.50),(2,1,'2020',151.00,3.00),(3,1,'2020',151.00,6.50),(4,1,'2020',291.00,3.50),(5,1,'2020',291.00,3.00),(6,1,'2020',291.00,6.50),(7,1,'2020',371.00,3.50),(8,1,'2020',371.00,3.00),(9,1,'2020',371.00,6.50);
/*!40000 ALTER TABLE `product_in_income_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_in_inventory_report`
--

DROP TABLE IF EXISTS `product_in_inventory_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_in_inventory_report` (
  `FK_productInStationID` int NOT NULL,
  `FK_repQuarter_inventoryReport` int NOT NULL,
  `FK_repYear_inventoryReport` varchar(50) NOT NULL,
  `amountSold` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_productInStationID`,`FK_repQuarter_inventoryReport`,`FK_repYear_inventoryReport`),
  KEY `product_in_inventory_report_ibfk_1` (`FK_productInStationID`),
  KEY `product_in_inventory_report_ibfk_2` (`FK_repQuarter_inventoryReport`,`FK_repYear_inventoryReport`),
  CONSTRAINT `product_in_inventory_report_ibfk_1` FOREIGN KEY (`FK_productInStationID`) REFERENCES `product_in_station` (`productinstationid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_in_inventory_report_ibfk_2` FOREIGN KEY (`FK_repQuarter_inventoryReport`, `FK_repYear_inventoryReport`) REFERENCES `inventory_report` (`FK_repQuarter`, `FK_repYear`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_in_inventory_report`
--

LOCK TABLES `product_in_inventory_report` WRITE;
/*!40000 ALTER TABLE `product_in_inventory_report` DISABLE KEYS */;
INSERT INTO `product_in_inventory_report` VALUES (1,1,'2020',43.14),(2,1,'2020',50.00),(3,1,'2020',23.23),(4,1,'2020',83.14),(5,1,'2020',97.00),(6,1,'2020',44.77),(7,1,'2020',106.00),(8,1,'2020',123.00),(9,1,'2020',57.08);
/*!40000 ALTER TABLE `product_in_inventory_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_in_outcome_report`
--

DROP TABLE IF EXISTS `product_in_outcome_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_in_outcome_report` (
  `FK_productInStationID` int NOT NULL,
  `FK_repQuarter_outcomeReport` int NOT NULL,
  `FK_repYear_outcomeReport` varchar(50) NOT NULL,
  `amountBoughtFromSupplier` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_productInStationID`,`FK_repQuarter_outcomeReport`,`FK_repYear_outcomeReport`),
  KEY `product_in_outcome_report_ibfk_1` (`FK_productInStationID`),
  KEY `product_in_outcome_report_ibfk_2` (`FK_repQuarter_outcomeReport`,`FK_repYear_outcomeReport`),
  CONSTRAINT `product_in_outcome_report_ibfk_1` FOREIGN KEY (`FK_productInStationID`) REFERENCES `product_in_station` (`productinstationid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_in_outcome_report_ibfk_2` FOREIGN KEY (`FK_repQuarter_outcomeReport`, `FK_repYear_outcomeReport`) REFERENCES `outcome_report` (`FK_repQuarter`, `FK_repYear`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_in_outcome_report`
--

LOCK TABLES `product_in_outcome_report` WRITE;
/*!40000 ALTER TABLE `product_in_outcome_report` DISABLE KEYS */;
INSERT INTO `product_in_outcome_report` VALUES (1,1,'2020',43.14),(2,1,'2020',50.00),(3,1,'2020',23.23),(4,1,'2020',83.14),(5,1,'2020',97.00),(6,1,'2020',44.77),(7,1,'2020',106.00),(8,1,'2020',123.00),(9,1,'2020',57.08);
/*!40000 ALTER TABLE `product_in_outcome_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_in_sales_pattern`
--

DROP TABLE IF EXISTS `product_in_sales_pattern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_in_sales_pattern` (
  `FK_salesPatternID` int NOT NULL,
  `FK_productName` varchar(50) NOT NULL,
  `salesDiscount` double(32,2) NOT NULL,
  PRIMARY KEY (`FK_productName`,`FK_salesPatternID`),
  KEY `product_in_sales_pattern_ibfk_1` (`FK_salesPatternID`),
  KEY `product_in_sales_pattern_ibfk_2` (`FK_productName`),
  CONSTRAINT `product_in_sales_pattern_ibfk_1` FOREIGN KEY (`FK_salesPatternID`) REFERENCES `sales_pattern` (`salespatternid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_in_sales_pattern_ibfk_2` FOREIGN KEY (`FK_productName`) REFERENCES `product` (`productName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_in_sales_pattern`
--

LOCK TABLES `product_in_sales_pattern` WRITE;
/*!40000 ALTER TABLE `product_in_sales_pattern` DISABLE KEYS */;
INSERT INTO `product_in_sales_pattern` VALUES (1,'Diesel',1.50),(3,'Diesel',5.00),(6,'Diesel',2.70),(2,'Gasoline',1.00),(3,'Gasoline',4.00),(4,'Gasoline',1.50),(1,'Motorbike Fuel',3.00),(3,'Motorbike Fuel',6.50),(5,'Motorbike Fuel',2.50),(6,'Motorbike Fuel',3.14);
/*!40000 ALTER TABLE `product_in_sales_pattern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_in_station`
--

DROP TABLE IF EXISTS `product_in_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_in_station` (
  `productInStationID` int NOT NULL AUTO_INCREMENT,
  `FK_productName` varchar(50) NOT NULL,
  `FK_fuelStationID` int NOT NULL,
  `capacity` double(32,2) NOT NULL,
  `threshold` double(32,2) NOT NULL,
  PRIMARY KEY (`productInStationID`),
  KEY `product_in_station_ibfk_1` (`FK_productName`),
  KEY `product_in_station_ibfk_2` (`FK_fuelStationID`),
  CONSTRAINT `product_in_station_ibfk_1` FOREIGN KEY (`FK_productName`) REFERENCES `product` (`productName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_in_station_ibfk_2` FOREIGN KEY (`FK_fuelStationID`) REFERENCES `fuel_station` (`fuelStationID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_in_station`
--

LOCK TABLES `product_in_station` WRITE;
/*!40000 ALTER TABLE `product_in_station` DISABLE KEYS */;
INSERT INTO `product_in_station` VALUES (1,'Gasoline',1,4008.00,2500.00),(2,'Diesel',1,2685.00,2200.00),(3,'Motorbike Fuel',1,1500.00,1300.00),(4,'Gasoline',2,13397.00,1.00),(5,'Diesel',2,8950.00,1.00),(6,'Motorbike Fuel',2,5000.00,1.00),(7,'Gasoline',3,2448.00,1500.00),(8,'Diesel',3,1790.00,800.00),(9,'Motorbike Fuel',3,1000.00,300.00),(10,'Gasoline',4,277.00,350.00),(11,'Diesel',4,500.00,350.00),(12,'Motorbike Fuel',4,500.00,350.00),(13,'Gasoline',5,197.00,250.00),(14,'Diesel',5,400.00,250.00),(15,'Motorbike Fuel',5,95.00,250.00),(16,'Gasoline',6,99.00,150.00),(17,'Diesel',6,225.00,150.00),(18,'Motorbike Fuel',6,300.00,150.00);
/*!40000 ALTER TABLE `product_in_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchasing_program`
--

DROP TABLE IF EXISTS `purchasing_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchasing_program` (
  `FK_customerID` varchar(50) NOT NULL,
  `FK_purchasingProgramName` varchar(50) NOT NULL,
  `FK_fuelCompanyName1` varchar(50) NOT NULL,
  `FK_fuelCompanyName2` varchar(50) DEFAULT NULL,
  `FK_fuelCompanyName3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`FK_customerID`),
  KEY `purchasing_program_ibfk_1` (`FK_customerID`),
  KEY `purchasing_program_ibfk_2` (`FK_purchasingProgramName`),
  KEY `purchasing_program_ibfk_3` (`FK_fuelCompanyName1`),
  KEY `purchasing_program_ibfk_4` (`FK_fuelCompanyName2`),
  KEY `purchasing_program_ibfk_5` (`FK_fuelCompanyName3`),
  CONSTRAINT `purchasing_program_ibfk_1` FOREIGN KEY (`FK_customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `purchasing_program_ibfk_2` FOREIGN KEY (`FK_purchasingProgramName`) REFERENCES `purchasing_program_type` (`purchasingprogramname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `purchasing_program_ibfk_3` FOREIGN KEY (`FK_fuelCompanyName1`) REFERENCES `fuel_company` (`fuelCompanyName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `purchasing_program_ibfk_4` FOREIGN KEY (`FK_fuelCompanyName2`) REFERENCES `fuel_company` (`fuelCompanyName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `purchasing_program_ibfk_5` FOREIGN KEY (`FK_fuelCompanyName3`) REFERENCES `fuel_company` (`fuelCompanyName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchasing_program`
--

LOCK TABLES `purchasing_program` WRITE;
/*!40000 ALTER TABLE `purchasing_program` DISABLE KEYS */;
INSERT INTO `purchasing_program` VALUES ('111111111','Standard','Paz',NULL,NULL),('123456789','Standard','Sonol',NULL,NULL),('212212212','Standard','Sonol',NULL,NULL),('222222222','Premium','Sonol','Paz','Delek'),('234234234','Premium','Sonol','Delek',NULL);
/*!40000 ALTER TABLE `purchasing_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchasing_program_type`
--

DROP TABLE IF EXISTS `purchasing_program_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchasing_program_type` (
  `purchasingProgramName` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `monthlyPrice` double(32,2) NOT NULL,
  PRIMARY KEY (`purchasingProgramName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchasing_program_type`
--

LOCK TABLES `purchasing_program_type` WRITE;
/*!40000 ALTER TABLE `purchasing_program_type` DISABLE KEYS */;
INSERT INTO `purchasing_program_type` VALUES ('Premium','Fast fueling in fuel stations of 2-3 fuel companies',20.00),('Standard','Fast fueling in fuel stations of only 1 fuel company',10.00);
/*!40000 ALTER TABLE `purchasing_program_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quarterly_report`
--

DROP TABLE IF EXISTS `quarterly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quarterly_report` (
  `repQuarter` int NOT NULL,
  `repYear` varchar(50) NOT NULL,
  `FK_fuelStationID` int NOT NULL,
  `dateCreated` timestamp NOT NULL,
  PRIMARY KEY (`repQuarter`,`repYear`,`FK_fuelStationID`),
  KEY `quarterly_report_ibfk_1` (`FK_fuelStationID`),
  CONSTRAINT `quarterly_report_ibfk_1` FOREIGN KEY (`FK_fuelStationID`) REFERENCES `fuel_station` (`fuelStationID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quarterly_report`
--

LOCK TABLES `quarterly_report` WRITE;
/*!40000 ALTER TABLE `quarterly_report` DISABLE KEYS */;
INSERT INTO `quarterly_report` VALUES (1,'2020',1,'2020-02-29 11:15:09'),(1,'2020',2,'2020-02-29 11:15:09'),(1,'2020',3,'2020-02-29 11:15:09');
/*!40000 ALTER TABLE `quarterly_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranking_sheet`
--

DROP TABLE IF EXISTS `ranking_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ranking_sheet` (
  `FK_customerID` varchar(50) NOT NULL,
  `customerTypeRank` double(32,2) NOT NULL,
  `fuelingHoursRank` double(32,2) NOT NULL,
  `fuelTypesRank` double(32,2) NOT NULL,
  `updatedForDate` timestamp NOT NULL,
  PRIMARY KEY (`FK_customerID`),
  KEY `ranking_sheet_ibfk_1` (`FK_customerID`),
  CONSTRAINT `ranking_sheet_ibfk_1` FOREIGN KEY (`FK_customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranking_sheet`
--

LOCK TABLES `ranking_sheet` WRITE;
/*!40000 ALTER TABLE `ranking_sheet` DISABLE KEYS */;
INSERT INTO `ranking_sheet` VALUES ('111111111',8.00,6.00,5.00,'2020-05-14 09:15:09'),('222222222',10.00,10.00,9.00,'2020-05-14 09:15:09');
/*!40000 ALTER TABLE `ranking_sheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `saleID` int NOT NULL AUTO_INCREMENT,
  `FK_salesPatternID` int NOT NULL,
  `startTime` timestamp NOT NULL,
  `endTime` timestamp NOT NULL,
  PRIMARY KEY (`saleID`),
  KEY `sale_ibfk_1` (`FK_salesPatternID`),
  CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`FK_salesPatternID`) REFERENCES `sales_pattern` (`salespatternid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (1,1,'2020-01-07 05:30:09','2020-01-07 06:00:09'),(2,2,'2020-01-06 23:00:09','2020-01-14 00:00:09'),(3,2,'2020-06-20 07:15:46','2020-06-20 08:15:46'),(4,1,'2020-06-21 07:15:56','2020-06-21 07:45:56'),(5,2,'2020-06-24 10:55:57','2020-06-24 11:55:57'),(6,3,'2020-06-25 10:55:01','2020-06-25 11:05:01'),(7,5,'2020-06-28 10:55:06','2020-06-28 10:57:06'),(8,6,'2020-07-15 12:56:30','2020-07-15 14:56:30'),(9,6,'2020-07-17 20:56:55','2020-07-17 10:56:55');
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_comments_report`
--

DROP TABLE IF EXISTS `sale_comments_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_comments_report` (
  `FK_saleID` int NOT NULL,
  `numberOfCustomersBought` int NOT NULL,
  `sumOfPurchases` double(32,2) NOT NULL,
  `dateCreated` timestamp NOT NULL,
  PRIMARY KEY (`FK_saleID`),
  KEY `sale_comments_report_ibfk_1` (`FK_saleID`),
  CONSTRAINT `sale_comments_report_ibfk_1` FOREIGN KEY (`FK_saleID`) REFERENCES `sale` (`saleID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_comments_report`
--

LOCK TABLES `sale_comments_report` WRITE;
/*!40000 ALTER TABLE `sale_comments_report` DISABLE KEYS */;
INSERT INTO `sale_comments_report` VALUES (1,2,380.00,'2020-01-27 11:15:09'),(2,1,188.00,'2020-01-27 11:15:09'),(3,1,688.00,'2020-06-20 11:57:05');
/*!40000 ALTER TABLE `sale_comments_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_pattern`
--

DROP TABLE IF EXISTS `sales_pattern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_pattern` (
  `salesPatternID` int NOT NULL AUTO_INCREMENT,
  `durationInMinutes` int NOT NULL,
  PRIMARY KEY (`salesPatternID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_pattern`
--

LOCK TABLES `sales_pattern` WRITE;
/*!40000 ALTER TABLE `sales_pattern` DISABLE KEYS */;
INSERT INTO `sales_pattern` VALUES (1,30),(2,60),(3,10),(4,2),(5,2),(6,120);
/*!40000 ALTER TABLE `sales_pattern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment_method`
--

DROP TABLE IF EXISTS `shipment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment_method` (
  `shipmentType` varchar(50) NOT NULL,
  `shipmentPrice` double(32,2) NOT NULL,
  `shipmentMultiplier` double(32,2) NOT NULL,
  `deliveryTime` varchar(500) NOT NULL,
  PRIMARY KEY (`shipmentType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment_method`
--

LOCK TABLES `shipment_method` WRITE;
/*!40000 ALTER TABLE `shipment_method` DISABLE KEYS */;
INSERT INTO `shipment_method` VALUES ('Regular',5.50,1.00,'5-10 Days'),('Urgent',5.50,1.02,'6 Hours');
/*!40000 ALTER TABLE `shipment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `connected` varchar(1) NOT NULL,
  `email` varchar(64) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('123456789','1234','0','Ani@JediAcademy.com','Anikan','SkyWalker'),('212212212','1234','0','sidius@SithLord.com','Sidius','Palpatine'),('234234234','1234','0','cloneWars@JediAcademi.com','ObiWan','Kenobi'),('IsraelTheCompanyCustomer','1234','0','IsraelTheCompanyCustomer@gmail.com','Israel','TheCompanyCustomer'),('IsraelTheDelekFuelStationManager','1234','0','IsraelTheDelekFuelStationManager@gmail.com','Israel','TheDelekFuelStationManager'),('IsraelTheDelekFuelStationManager2','1234','0','IsraelTheDelekFuelStationManager2@gmail.com','Israel','TheDelekFuelStationManager2'),('IsraelTheDelekSupplier','1234','0','IsraelTheDelekSupplier@gmail.com','Israel','TheDelekSupplier'),('IsraelTheMarketingManager','1234','0','IsraelTheMarketingManager@gmail.com','Israel','TheMarketingManager'),('IsraelTheMarketingRepresentative','1234','0','IsraelTheMarketingRepresentative@gmail.com','Israel','TheMarketingRepresentative'),('IsraelTheNetworkManager','1234','0','IsraelTheNetworkManager@gmail.com','Israel','TheNetworkManager'),('IsraelThePazFuelStationManager','1234','0','IsraelThePazFuelStationManager@gmail.com','Israel','ThePazFuelStationManager'),('IsraelThePazFuelStationManager2','1234','0','IsraelThePazFuelStationManager2@gmail.com','Israel','ThePazFuelStationManager2'),('IsraelThePazSupplier','1234','0','IsraelThePazSupplier@gmail.com','Israel','ThePazSupplier'),('IsraelThePersonCustomer','1234','0','IsraelThePersonCustomer@gmail.com','Israel','ThePersonCustomer'),('IsraelTheSonolFuelStationManager','1234','0','IsraelTheSonolFuelStationManager@gmail.com','Israel','TheSonolFuelStationManager'),('IsraelTheSonolFuelStationManager2','1234','0','IsraelTheSonolFuelStationManager2@gmail.com','Israel','TheSonolFuelStationManager2'),('IsraelTheSonolSupplier','1234','0','IsraelTheSonolSupplier@gmail.com','Israel','TheSonolSupplier');
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

-- Dump completed on 2020-06-20 15:19:30
