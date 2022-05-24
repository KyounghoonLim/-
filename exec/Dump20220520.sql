-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: k6e105.p.ssafy.io    Database: daki
-- ------------------------------------------------------
-- Server version	8.0.29-0ubuntu0.20.04.3

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_no` bigint NOT NULL AUTO_INCREMENT,
  `board_content` varchar(255) DEFAULT NULL,
  `board_date` datetime DEFAULT NULL,
  `board_head_line` varchar(255) DEFAULT NULL,
  `board_title` varchar(255) DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`board_no`),
  KEY `FK7twrkrjpdiofmi6vvy8mbtjqe` (`user_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diary`
--

DROP TABLE IF EXISTS `diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diary` (
  `diary_no` bigint NOT NULL AUTO_INCREMENT,
  `diary_content` longtext,
  `diary_date` datetime DEFAULT NULL,
  `diary_title` varchar(255) DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`diary_no`),
  KEY `FKo2jff3tm6ptsb73ghi0243q22` (`user_no`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary`
--

LOCK TABLES `diary` WRITE;
/*!40000 ALTER TABLE `diary` DISABLE KEYS */;
INSERT INTO `diary` VALUES (1,'[{\"type\":\"image\",\"top\":26.761744966442976,\"left\":56.5290178571428,\"width\":18.638392857142858,\"height\":28.02013422818792,\"imgUrl\":\"https://diarypj.s3.ap-northeast-2.amazonaws.com/tndk4997%40naver.com/logo.png\",\"content\":null,\"fontStyle\":{\"size\":16,\"family\":\"HallymMjo-Regular\",\"weight\":\"normal\",\"align\":\"left\"}},{\"type\":\"text\",\"top\":4.530201342281877,\"left\":30.58035714285716,\"width\":45.78124999999999,\"height\":29.798657718120825,\"imgUrl\":null,\"content\":\"일기를 마음껏 꾸며요\",\"fontStyle\":{\"size\":63,\"family\":\"HallymMjo-Regular\",\"weight\":\"bold\",\"align\":\"left\"}},{\"type\":\"sticker\",\"top\":6.711409395973153,\"left\":-10.044642857142854,\"width\":55.80357142857143,\"height\":83.89261744966443,\"imgUrl\":\"https://img.stipop.io/2022/5/17/1652714206457_6.gif\",\"content\":null,\"fontStyle\":{\"size\":16,\"family\":\"HallymMjo-Regular\",\"weight\":\"normal\",\"align\":\"left\"}}]','2022-05-19 17:12:02','오늘의 일기',10),(2,'[{\"type\":\"text\",\"top\":9.19674039580908,\"left\":15.91304347826087,\"width\":20,\"height\":10,\"imgUrl\":null,\"content\":\"화이팅\",\"fontStyle\":{\"size\":16,\"family\":\"HallymMjo-Regular\",\"weight\":\"normal\",\"align\":\"left\"}}]','2022-05-19 17:27:12','최종발표!',10);
/*!40000 ALTER TABLE `diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doll`
--

DROP TABLE IF EXISTS `doll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doll` (
  `doll_no` bigint NOT NULL AUTO_INCREMENT,
  `doll_likeable` int DEFAULT NULL,
  `doll_type` int DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`doll_no`),
  KEY `FKg7cjcrjljd2r93ysgrla6s1x2` (`user_no`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doll`
--

LOCK TABLES `doll` WRITE;
/*!40000 ALTER TABLE `doll` DISABLE KEYS */;
INSERT INTO `doll` VALUES (1,0,2,1),(2,0,2,2),(3,0,2,3),(4,0,1,4),(5,0,2,5),(6,0,2,6),(7,0,1,7),(8,0,2,8),(9,0,1,9),(10,0,1,10),(11,0,1,11),(12,0,0,12),(13,0,0,13),(14,0,1,14),(15,0,1,15),(16,0,1,16),(17,0,2,17);
/*!40000 ALTER TABLE `doll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_no` bigint NOT NULL AUTO_INCREMENT,
  `item_categories` int DEFAULT NULL,
  `item_image` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `item_price` int DEFAULT NULL,
  PRIMARY KEY (`item_no`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,0,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/cloth1.png','유아용 턱받이',300),(2,0,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/cloth2.png','갈색 자켓',400),(3,0,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/cloth3.png','슈퍼맨',500),(4,0,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/cloth4.png','힙스터',600),(5,1,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/hair1.png','바가지 머리',300),(6,1,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/hair2.png','선글라스',400),(7,1,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/hair3.png','고양이 귀',500),(8,1,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/hair4.png','야구모자',600),(9,1,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/hair5.png','십자가 장식',300),(10,2,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/background1.png','바다 배경',400),(11,2,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/background2.png','보라빛 밤 배경',500),(12,2,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/background3.png','밤 시내 배경',600),(13,3,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/deco1.png','뚫어뻥',500),(14,3,'https://diarypj.s3.ap-northeast-2.amazonaws.com/basic/deco2.png','햄버거',600);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `token_key` varchar(255) NOT NULL,
  `token_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`token_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES ('test@test.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM1Nzg1Mzl9.piDwmmoUtUqGMQQ-mgu8augjWugOXCJY1PxVqOuuW46qvy22BQmWHvQFQHNfLesPBkbVG_dD_ZRsiOG8vvsI6g'),('abc@a.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM1ODA3MjN9.yF-2xxjSFQWUTVhW4rTiQZBGhrClbGKnCrfF2ravPwTgLFggcbkJCumZd0lpxd8SZYVpC4povq9gV3d6-wXV6Q'),('b@a.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM1ODI3OTF9.hmoVuOSrRXvKcwc5vhfRXyOUYxeqvcnuyde6dpSJACjVq5zsTRwArD7VlpoBwxF1bDvcXfqo4b9_J_RhVUOOYg'),('aa@a.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM2MTA1MDl9.PusmswxguvQ5-R-p9VYAi4stgsl240ywOjrXDgWUhs_-v21OBZ6naP6goNC1bmCUWuUPqHJGIZfcl9IUd3ISgQ'),('aaa@a.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM1ODM0NDZ9.phQZ4gcD-wkAjwZ0uJpft-7-XKD7moDlRwGpAdz5QS9GtqTb7E4jq59so8HJWNTjDdkB91czlyaTLsmLmrJYtQ'),('tndk4997@naver.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM2MTA2MDh9.ZVQXmrI01IrQmA96VlFlAOcliFXGwEavZSpWMXjdKZk_1sXT1rGOVfYlMxnPS6TXz0jY-uuXmtUp4w_h8ZuAFA'),('acc@a.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM1ODU2OTV9.b_jOGG3wubz4tKeKQBoUCMWalxwd0rb-9Om0LWT6-ig4iF9n3tl23UWoxVBg1fC-ELiwHXhXiPr9-DWmNQFSXA'),('mogies@naver.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM1ODYyMzF9.Re8G1rrpgOdzvLwfiqdbOmYOyvQzp3yCzafi8hGPB6FR8mbAwRDeqM-kAbVFD2AcGtOrllgLGETxZyxIzmEIjg'),('mogy2667@gmail.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM1ODY0MDd9.jgJeXwMimAo6ucvSVWoIe4C2sZatr88BhauO1WF8cMbIma_HPAytIEdGy2B7FMvf7qk3sKge2P7aGNgu1R83rw'),('df@naver.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM2MDk1MDF9.ZHzKvJbpTB1egVqZNqIGF-M7JN5oIp_bAUTRAy-l6-GuWJ7GCrGHnn-OD-EBUdUU0Tuy6n-BDHf7n0tqfT7IhQ'),('kht@naver.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM2MTIxODh9.Cdp4cPvlJq7Z5u6D5YLbIDLHyjtzytnfT-IiNrf4HbOIefuaVp2aE9tg_qdB0FNrwPIsptnTm2IKpW0DOCx2Zg'),('daki@test.com','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM2MTIxMDh9.Hk1z2F3vLIn3bG3X6Ym3RneDLRQx2XFgqceQaxbNzBkR9PcELQT8FlWbt1rKTGn3bGsohI1tkOGDRQ007_C5Vg');
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply` (
  `reply_no` bigint NOT NULL AUTO_INCREMENT,
  `reply_content` varchar(255) DEFAULT NULL,
  `reply_date` datetime DEFAULT NULL,
  `board_no` bigint DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`reply_no`),
  KEY `FKb58q1nmk6q4y93f5cejg56scr` (`board_no`),
  KEY `FK94mfeggh4qx14vmoby6tr49vl` (`user_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_no` bigint NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `user_birth` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_gender` varchar(255) DEFAULT NULL,
  `user_nickname` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_point` int DEFAULT NULL,
  PRIMARY KEY (`user_no`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ROLE_USER','2022-05-11','asdf@naver.com','F','asdfasdf','$2a$10$zlF8ztt82VU8wVDZEILw7Ov4Gs50n1qgKn.F2.Y0QmxE8PetAx7Fy',0),(3,'ROLE_USER','2022-05-05','a@a.com','M','aa','$2a$10$Z8vq.rnc6Mq4T6bQizIyR.cJpKoNVc.Tt5zP5NXbH2tfv8/BhDVm6',0),(4,'ROLE_USER','2022-05-02','test@test.com','M','test2','$2a$10$4e/4GjvLFPzUvNGnC2xD8eq/NVLwOam5Mtn1GNXcBL0wJZkBQzTiK',0),(5,'ROLE_USER','2022-05-06','abc@a.com','M','as','$2a$10$KaUCEDI/T4J6lSkF/kIItuWC2PXJAQxwOoaI/Ie6Pc3FSa7Fv6oLa',0),(6,'ROLE_USER','2022-05-18','b@a.com','M','bb','$2a$10$kRhfI2Qs2Wr/5WDeaDf.Pus2MPYRVTq3Neb6BVQg9LrgZcrMMHkda',0),(7,'ROLE_USER','2022-05-02','aa@a.com','M','aaa','$2a$10$WisX34Tib8wVYIaLUpuwGO/tU9d/CuKaNj0H6FSTlaFhtYnpKJ02.',0),(8,'ROLE_USER','1','1','M','1','$2a$10$mYE/sqTYbYPl2ZyElTArRuCJJtB7YvzoG8rwWAlFte8ZgUThIa9Mm',0),(9,'ROLE_USER','2022-05-04','aaa@a.com','M','aaaa','$2a$10$QzBL1FH82pLSIXCqaQWTo.dua0rglU/HiS1ymnraRz0uuq144SMf6',0),(10,'ROLE_USER','2022-05-03','tndk4997@naver.com','F','수아수아','$2a$10$IqrHrS5tAR5s6K1aP2b65.wEX5TAezqxbW/r1S80/5jOfvBnmZl8a',0),(11,'ROLE_USER','2022-05-03','acc@a.com','M','aa11','$2a$10$0KjUhpFX35HtxP9lxvx5O.AjX2vay8Mc6iwoV.uoMx2SMuJEMmHTW',0),(12,'ROLE_USER',NULL,'mogies@naver.com',NULL,'경훈}','$2a$10$S8xRe9lK8eqommdsgHgJzuI4VUCc90hxb6zFezpbiK1QdW0geeoT6',0),(13,'ROLE_USER',NULL,'mogy2667@gmail.com',NULL,'제리','$2a$10$g2NlVLgaMzbMVD6MbGbbMe.Mszydcn9tgF6Vnv9UF88gvIbximLtS',0),(14,'ROLE_USER','2000-09-28','ssafy1@naver.com','F','다키','$2a$10$k1SU7LnF2.vzouxu.xrKKuFSmbuM6dVn3hiWqQL/QzGIUMvjFZV2y',0),(15,'ROLE_USER','2022-05-17','df@naver.com','M','아이야이양','$2a$10$0N8YtrUXt9jj5Ys1rAE8/uzdFmvDrVYT/vHHLV/PCmqhBJaKYAcam',0),(16,'ROLE_USER','1997-01-22','kht@naver.com','M','kht','$2a$10$KHwIlSXcXj4Vp3rqEcD7vegzKN24Wnf5lRIozyyTNei9eQDjXpEZu',0),(17,'ROLE_USER','2022-01-14','daki@test.com','F','daki','$2a$10$3PcV0s6L8HoP4a0vKiGWTu12rVRbGE1ph0UN8y3K9.vZshiK42z7O',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_item`
--

DROP TABLE IF EXISTS `user_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_item` (
  `user_item_no` bigint NOT NULL AUTO_INCREMENT,
  `wear_flag` int DEFAULT NULL,
  `doll_no` bigint DEFAULT NULL,
  `item_no` bigint DEFAULT NULL,
  PRIMARY KEY (`user_item_no`),
  KEY `FK7rc0bquf03f9mlo4j0y2gah7d` (`doll_no`),
  KEY `FKjjkdshu1hrl8fgm4uvt3lgvel` (`item_no`)
) ENGINE=MyISAM AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_item`
--

LOCK TABLES `user_item` WRITE;
/*!40000 ALTER TABLE `user_item` DISABLE KEYS */;
INSERT INTO `user_item` VALUES (1,1,1,1),(2,1,2,1),(3,1,3,1),(4,1,6,1),(5,0,6,2),(6,0,6,3),(7,0,6,4),(8,0,6,5),(9,0,6,6),(10,0,6,7),(11,0,6,8),(12,0,6,9),(13,0,6,10),(14,0,6,11),(15,0,6,12),(16,0,6,13),(17,0,6,14),(18,1,7,1),(19,0,7,2),(20,0,7,3),(21,0,7,4),(22,0,7,5),(23,0,7,6),(24,0,7,7),(25,0,7,8),(26,0,7,9),(27,0,7,10),(28,0,7,11),(29,0,7,12),(30,0,7,13),(31,0,7,14),(32,1,8,1),(33,0,8,2),(34,0,8,3),(35,0,8,4),(36,0,8,5),(37,0,8,6),(38,0,8,7),(39,0,8,8),(40,0,8,9),(41,0,8,10),(42,0,8,11),(43,0,8,12),(44,0,8,13),(45,0,8,14),(46,1,9,1),(47,0,9,2),(48,0,9,3),(49,0,9,4),(50,0,9,5),(51,0,9,6),(52,0,9,7),(53,0,9,8),(54,0,9,9),(55,0,9,10),(56,0,9,11),(57,0,9,12),(58,0,9,13),(59,0,9,14),(60,1,10,1),(61,0,10,2),(62,0,10,3),(63,0,10,4),(64,0,10,5),(65,0,10,6),(66,0,10,7),(67,0,10,8),(68,0,10,9),(69,0,10,10),(70,0,10,11),(71,0,10,12),(72,0,10,13),(73,0,10,14),(74,1,11,1),(75,1,11,2),(76,1,11,3),(77,1,11,4),(78,1,11,5),(79,1,11,6),(80,1,11,7),(81,1,11,8),(82,1,11,9),(83,1,11,10),(84,1,11,11),(85,1,11,12),(86,1,11,13),(87,1,11,14),(88,1,12,1),(89,0,12,2),(90,0,12,3),(91,0,12,4),(92,0,12,5),(93,0,12,6),(94,0,12,7),(95,0,12,8),(96,0,12,9),(97,0,12,10),(98,0,12,11),(99,0,12,12),(100,0,12,13),(101,0,12,14),(102,1,13,1),(103,0,13,2),(104,0,13,3),(105,0,13,4),(106,0,13,5),(107,0,13,6),(108,0,13,7),(109,0,13,8),(110,0,13,9),(111,0,13,10),(112,0,13,11),(113,0,13,12),(114,0,13,13),(115,0,13,14),(116,1,14,1),(117,0,14,2),(118,0,14,3),(119,0,14,4),(120,0,14,5),(121,0,14,6),(122,0,14,7),(123,0,14,8),(124,0,14,9),(125,0,14,10),(126,0,14,11),(127,0,14,12),(128,0,14,13),(129,0,14,14),(130,1,15,1),(131,0,15,2),(132,0,15,3),(133,0,15,4),(134,0,15,5),(135,0,15,6),(136,0,15,7),(137,0,15,8),(138,0,15,9),(139,0,15,10),(140,0,15,11),(141,0,15,12),(142,0,15,13),(143,0,15,14),(144,1,16,1),(145,1,16,2),(146,0,16,3),(147,0,16,4),(148,0,16,5),(149,0,16,6),(150,0,16,7),(151,1,16,8),(152,0,16,9),(153,0,16,10),(154,0,16,11),(155,1,16,12),(156,0,16,13),(157,0,16,14),(158,1,17,1),(159,0,17,2),(160,1,17,3),(161,0,17,4),(162,0,17,5),(163,0,17,6),(164,1,17,7),(165,0,17,8),(166,0,17,9),(167,0,17,10),(168,0,17,11),(169,0,17,12),(170,0,17,13),(171,1,17,14);
/*!40000 ALTER TABLE `user_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 10:00:32
