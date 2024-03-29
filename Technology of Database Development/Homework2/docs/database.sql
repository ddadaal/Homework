-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: localhost    Database: carrier
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `basiccost`
--

DROP TABLE IF EXISTS `basiccost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basiccost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `callCost` decimal(11,3) DEFAULT NULL,
  `smsCost` decimal(11,3) DEFAULT NULL,
  `localDataCost` decimal(11,3) DEFAULT NULL,
  `domesticDataCost` decimal(11,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basiccost`
--

LOCK TABLES `basiccost` WRITE;
/*!40000 ALTER TABLE `basiccost` DISABLE KEYS */;
INSERT INTO `basiccost` VALUES (1,0.500,0.100,2.000,5.000);
/*!40000 ALTER TABLE `basiccost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `cost` decimal(11,3) DEFAULT NULL,
  `callMinutes` int(11) DEFAULT NULL,
  `sms` int(11) DEFAULT NULL,
  `localData` float DEFAULT NULL,
  `domesticData` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,'璇濊垂濂楅',20.000,100,0,0,0),(2,'鐭俊濂楅',10.000,0,200,0,0),(3,'鏈湴娴侀噺濂楅',20.000,0,0,2048,0),(4,'鍥藉唴娴侀噺濂楅',30.000,0,0,0,2048),(5,'闇哥帇濂楅',70.000,100,200,2048,2048);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usage`
--

DROP TABLE IF EXISTS `usage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usage` (
  `userId` int(11) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `serviceType` enum('CALL','SMS','LOCAL_DATA','DOMESTIC_DATA') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usage`
--

LOCK TABLES `usage` WRITE;
/*!40000 ALTER TABLE `usage` DISABLE KEYS */;
INSERT INTO `usage` VALUES (1,7,'2018-10-26 18:13:07','CALL'),(1,1,'2018-10-26 18:13:07','SMS'),(1,3072,'2018-10-26 18:13:07','LOCAL_DATA'),(1,1536,'2018-10-26 18:13:08','DOMESTIC_DATA');
/*!40000 ALTER TABLE `usage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `basicCostId` int(11) DEFAULT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'娴嬭瘯鐢ㄦ埛1',1,'12345667891'),(2,'娴嬭瘯鐢ㄦ埛2',1,'12345678892'),(3,'娴嬭瘯鐢ㄦ埛3',1,'12342356783');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userplansubscription`
--

DROP TABLE IF EXISTS `userplansubscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userplansubscription` (
  `transactionId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `planId` int(11) DEFAULT NULL,
  `activateTime` datetime DEFAULT NULL,
  `orderTime` datetime DEFAULT NULL,
  `deactivateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`transactionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userplansubscription`
--

LOCK TABLES `userplansubscription` WRITE;
/*!40000 ALTER TABLE `userplansubscription` DISABLE KEYS */;
INSERT INTO `userplansubscription` VALUES (1,1,2,'2018-11-01 00:00:00','2018-10-26 18:13:06',NULL),(2,1,3,'2018-10-26 18:13:06','2018-10-26 18:13:06',NULL),(3,1,4,'2018-10-26 18:13:06','2018-10-26 18:13:06',NULL),(4,2,1,'2018-10-26 18:13:06','2018-10-26 18:13:06','2018-10-26 18:13:07'),(5,2,5,'2018-10-26 18:13:06','2018-10-26 18:13:06',NULL);
/*!40000 ALTER TABLE `userplansubscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userplantransaction`
--

DROP TABLE IF EXISTS `userplantransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userplantransaction` (
  `transactionId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `planId` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `action` enum('ORDER_IMMEDIATELY','ORDER_NEXT_MONTH','CANCEL_IMMEDIATELY','CANCEL_NEXT_MONTH') DEFAULT NULL,
  PRIMARY KEY (`transactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userplantransaction`
--

LOCK TABLES `userplantransaction` WRITE;
/*!40000 ALTER TABLE `userplantransaction` DISABLE KEYS */;
INSERT INTO `userplantransaction` VALUES (1,1,2,'2018-10-26 18:13:06','ORDER_NEXT_MONTH'),(2,1,3,'2018-10-26 18:13:06','ORDER_IMMEDIATELY'),(3,1,4,'2018-10-26 18:13:06','ORDER_IMMEDIATELY'),(4,2,1,'2018-10-26 18:13:06','ORDER_IMMEDIATELY'),(5,2,5,'2018-10-26 18:13:06','ORDER_IMMEDIATELY'),(6,2,1,'2018-10-26 18:13:07','CANCEL_IMMEDIATELY');
/*!40000 ALTER TABLE `userplantransaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-26 18:15:10
