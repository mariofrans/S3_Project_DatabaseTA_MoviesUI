-- MySQL dump 10.13  Distrib 8.0.22, for osx10.15 (x86_64)
--
-- Host: localhost    Database: Movies
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movieTitle` varchar(64) NOT NULL,
  `description` varchar(200) NOT NULL,
  `rating` decimal(2,1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (100,'Run','I like running',5.4),(101,'Fatman','I Eat',6.2),(102,'Home Alone','I do math',7.8),(103,'Baby Boss','Baby in a suit',5.0),(104,'Mosul','IDK',4.4);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `username` varchar(25) NOT NULL,
  `rate` decimal(3,1) DEFAULT NULL,
  `review` varchar(200) DEFAULT NULL,
  `movieID` int NOT NULL,
  PRIMARY KEY (`username`,`movieID`),
  KEY `movieID` (`movieID`),
  CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `rating_ibfk_2` FOREIGN KEY (`movieID`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES ('Asher',2.0,'Bad',100),('Asher',4.0,'',101),('Asher',8.0,'',102),('Asher',3.0,'',103),('Asher',4.0,'',104),('Corin',8.0,'I kinda like it',100),('Corin',2.0,'',101),('Corin',9.0,'',102),('Corin',3.0,'',103),('Corin',6.0,'',104),('Gunner',4.0,'I changed my mind',100),('Gunner',10.0,'I liked it a lot OMG I wish i can tell how much I liked it',101),('Gunner',10.0,'I love it :) xd EPIC',102),('Gunner',9.0,'I like baby boss',103),('Gunner',5.0,'Meeeeeeh ',104),('Jaylor',4.0,'',100),('Jaylor',7.0,'',101),('Jaylor',8.0,'',102),('Jaylor',3.0,'',103),('Jaylor',4.0,'',104),('Lackay',5.0,'Not my forte',100),('Lackay',8.0,'Its really good',101),('Lackay',5.0,'I guess its ok',102),('Lackay',6.0,'Hmm its fine',103),('Lackay',5.0,'Its boring',104),('Lequeen',7.0,'Is meh',100),('Lequeen',6.0,'',101),('Lequeen',7.0,'',102),('Lequeen',6.0,'',103),('Lequeen',4.0,'',104),('Louisdoesstuff',8.0,'Its meh but I enjoyed it',100),('Louisdoesstuff',3.0,'Ok i want to edit this it is really bad',104);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(25) NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `lastName` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Asher','Harry','Fisher','Sushi'),('Corin','Colin','Baker','Bread'),('Gunner','Gunker','Bunker','Raisins'),('Jaylor','John','Taylor','Cookies'),('Lackay','Lillian','Mackey','Powder'),('Lequeen','Liam','Quinn','Biscuit'),('Louisdoesstuff','Louis','Clay','Bababoey');
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

-- Dump completed on 2021-01-03  8:10:08
