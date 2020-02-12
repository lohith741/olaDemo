-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: ola_db
-- ------------------------------------------------------
-- Server version	5.6.29-log

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
-- Table structure for table `request_db`
--

DROP TABLE IF EXISTS `request_db`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_db` (
  `request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accpeted_time` datetime DEFAULT NULL,
  `customer_id` bigint(20) NOT NULL,
  `driver_id` bigint(20) DEFAULT NULL,
  `request_status` varchar(255) DEFAULT NULL,
  `request_time` datetime NOT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_db`
--

LOCK TABLES `request_db` WRITE;
/*!40000 ALTER TABLE `request_db` DISABLE KEYS */;
INSERT INTO `request_db` VALUES (11,'2020-02-12 21:43:48',1,1,'completed','2020-02-12 21:43:07'),(12,'2020-02-12 21:44:18',2,3,'completed','2020-02-12 21:43:11'),(13,'2020-02-12 22:03:11',3,1,'completed','2020-02-12 21:43:15'),(14,'2020-02-12 21:44:04',4,2,'completed','2020-02-12 21:43:18'),(15,'2020-02-12 22:03:39',4,4,'completed','2020-02-12 21:43:21'),(16,NULL,22,NULL,'waiting','2020-02-12 22:25:15'),(17,NULL,25,NULL,'waiting','2020-02-12 22:40:48');
/*!40000 ALTER TABLE `request_db` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-13  4:19:10
