-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: myyoga
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `phone` bigint(10) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `height` varchar(5) DEFAULT NULL,
  `weight` int(3) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Koushik','abc@abc.com','abc123','2010-10-10',1234567890,'iuifndsfnkjdsf','6',60,'Male'),(2,'abc','abc@123.com','abc123','2020-10-10',1234567890,'dfsjndsjfkn','6',66,'male'),(3,'abcqwer','abc@abcd123.com','qwer1234','2010-10-10',1234567890,'dndsjndkjnvk','6',60,'male'),(4,'qwerty','qwerty@abcd123.com','qwer1234','2010-10-10',1234567890,'dndsjndkjnvk','6',60,'male'),(5,'qwerty','qwer@qwer.com.com','qwer1234','2010-10-10',1234567890,'dndsjndkjnvk','6',60,'male'),(6,'qwerty','qwery@qwery.com.com','qwer1234','2010-10-10',1234567890,'dndsjndkjnvk','6',60,'male'),(7,'qwerty','qwery@abcd.com','qwer1234','2010-10-10',1234567890,'dndsjndkjnvk','6',60,'male'),(8,'Koushik','kthai@stevens.edu','qwer1234','2020-10-10',1234567890,'asdsdfsfsdfsfsdafsfsdfsafa','6',70,'MALE'),(9,'newuser','nwe@nusew.com','abc123','1987-04-08',4092179925,'newuser','4',44,'MALE'),(10,'user1','user1@user1.com','abc123','1993-04-16',4092179925,'user1 address','6',78,'MALE'),(11,'user2','user2@gmail.com','abc123','2017-04-04',9123456789,'user2 address','5',67,'FEMALE'),(12,'alpha','alp@bet.com','abc1234','2006-04-06',4092189823,'alpha address','5',60,'FEMALE'),(13,'betes','betes@gmail.com','abc1234','2017-04-12',5092345432,'betes address','5',56,'FEMALE'),(14,'Aishwarya Dakshinamurthy','rdakshina@gmail.com','abc1234','2013-04-11',1234567890,'70 Lincoln Street','6',120,'FEMALE'),(15,'aaa','aaa@aaa.com','aaa1234','2017-04-05',1234567890,'aaa address','43',12,'MALE'),(16,'bbb','bbb@bbb.com','bbb1234','2017-04-11',1234567890,'bbb address','9',45,'FEMALE'),(17,'abcv','abcv@abcv.com','abc123','2017-04-12',1234567890,'abcv address','3',56,'MALE'),(18,'abcf@gmail.com','nmv@gmail.com','abc123','2017-04-15',1234567890,'abdc jdnfsfnin','10.3',54,'MALE'),(19,'ren','ren@123.com','qwerty','2017-04-29',1234567890,'141','5.3',100,'FEMALE'),(20,'Aishwarya Umachandran','aish@gmail.com','abc123','1991-12-19',2019255508,'141 Booraem Avenue','5.3',57,'FEMALE');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-01 19:34:34
