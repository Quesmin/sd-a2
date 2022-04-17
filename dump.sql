CREATE DATABASE  IF NOT EXISTS `sd-a2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sd-a2`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: sd-a2
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `id` varchar(255) NOT NULL,
  `category` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `restaurant_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm9xrxt95wwp1r2s7andom1l1c` (`restaurant_id`),
  CONSTRAINT `FKm9xrxt95wwp1r2s7andom1l1c` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES ('39a6a345-60c8-465e-93ca-7411a7b95923',1,'test','test',12,'09975678-0062-461f-9868-8c6cbcc6f7f5'),('4896d5a8-558b-4fad-845a-ad320b365c48',2,'asd','asd',12,'09975678-0062-461f-9868-8c6cbcc6f7f5'),('6eff3e09-2b60-4e48-926d-f43a225a0148',0,'123','asd',12,'09975678-0062-461f-9868-8c6cbcc6f7f5'),('8b890bbe-aab4-4653-a43f-4586fbc3a696',1,'dough, cheese, tomato sauce, mushrooms','Pizza Capricioasa',23,'30c4f69d-2dd9-4c8e-8c00-9e2de16bc452'),('a3f98515-db9f-4e2c-834e-68329f06dcf5',0,'dsa','asd',123,'09975678-0062-461f-9868-8c6cbcc6f7f5'),('c04ed82c-6479-4ca8-8ebf-3848d8f8a735',0,'123','test123',12,'09975678-0062-461f-9868-8c6cbcc6f7f5'),('e64ec7c8-5f1c-47e4-9acb-1a6e286fe100',0,'dfgh','fgjd',23,'09975678-0062-461f-9868-8c6cbcc6f7f5');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordered_food`
--

DROP TABLE IF EXISTS `ordered_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordered_food` (
  `id` varchar(255) NOT NULL,
  `quantity` int DEFAULT NULL,
  `food_id` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbmd6kulwkrenlll0s6tblji6j` (`food_id`),
  KEY `FK4o5ymem1aghd4echyvrr7e2m3` (`order_id`),
  CONSTRAINT `FK4o5ymem1aghd4echyvrr7e2m3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKbmd6kulwkrenlll0s6tblji6j` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered_food`
--

LOCK TABLES `ordered_food` WRITE;
/*!40000 ALTER TABLE `ordered_food` DISABLE KEYS */;
INSERT INTO `ordered_food` VALUES ('76859276-b454-43a9-a862-bdb99ad603c1',21,'8b890bbe-aab4-4653-a43f-4586fbc3a696','9cdabd50-fae2-4c74-ba87-a6300616792f'),('ad25fe86-adc0-4119-844b-e80ec99181c3',2,'8b890bbe-aab4-4653-a43f-4586fbc3a696','c27f6aa5-5f8c-461f-86f3-47c95a8e06fc');
/*!40000 ALTER TABLE `ordered_food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL,
  `order_status` int DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `restaurant_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsjfs85qf6vmcurlx43cnc16gy` (`customer_id`),
  KEY `FKi7hgjxhw21nei3xgpe4nnpenh` (`restaurant_id`),
  CONSTRAINT `FKi7hgjxhw21nei3xgpe4nnpenh` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FKsjfs85qf6vmcurlx43cnc16gy` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('9cdabd50-fae2-4c74-ba87-a6300616792f',4,'8b15c7c7-3b8e-4342-92bd-69b8f30e9b4a','30c4f69d-2dd9-4c8e-8c00-9e2de16bc452'),('b3f6f0dd-b682-4ad2-b156-6884faec74b5',0,'8b15c7c7-3b8e-4342-92bd-69b8f30e9b4a','30c4f69d-2dd9-4c8e-8c00-9e2de16bc452'),('c27f6aa5-5f8c-461f-86f3-47c95a8e06fc',3,'8b15c7c7-3b8e-4342-92bd-69b8f30e9b4a','30c4f69d-2dd9-4c8e-8c00-9e2de16bc452');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `id` varchar(255) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `admin_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrk767nt9h14telxuh6tn5xff7` (`admin_id`),
  CONSTRAINT `FKrk767nt9h14telxuh6tn5xff7` FOREIGN KEY (`admin_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES ('09975678-0062-461f-9868-8c6cbcc6f7f5','str asd1','R1','1663d0a9-c635-4e93-b294-db540a8da5de'),('30c4f69d-2dd9-4c8e-8c00-9e2de16bc452','Str. Ceahlau','20Pizza','1663d0a9-c635-4e93-b294-db540a8da5de'),('84da75db-9ce4-4e36-ac03-b045c4dbaeaa','Str Florilor','Samsara','1663d0a9-c635-4e93-b294-db540a8da5de');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_foods`
--

DROP TABLE IF EXISTS `restaurant_foods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant_foods` (
  `restaurant_id` varchar(255) NOT NULL,
  `foods_id` varchar(255) NOT NULL,
  UNIQUE KEY `UK_jcui1ncguod9vfe1qbpksy46j` (`foods_id`),
  KEY `FK4dbvvswitmtp26c2n425c9d12` (`restaurant_id`),
  CONSTRAINT `FK4dbvvswitmtp26c2n425c9d12` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FK546hh7nv85x4dl0aefekixbm6` FOREIGN KEY (`foods_id`) REFERENCES `food` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_foods`
--

LOCK TABLES `restaurant_foods` WRITE;
/*!40000 ALTER TABLE `restaurant_foods` DISABLE KEYS */;
INSERT INTO `restaurant_foods` VALUES ('09975678-0062-461f-9868-8c6cbcc6f7f5','39a6a345-60c8-465e-93ca-7411a7b95923'),('09975678-0062-461f-9868-8c6cbcc6f7f5','4896d5a8-558b-4fad-845a-ad320b365c48'),('09975678-0062-461f-9868-8c6cbcc6f7f5','6eff3e09-2b60-4e48-926d-f43a225a0148'),('09975678-0062-461f-9868-8c6cbcc6f7f5','a3f98515-db9f-4e2c-834e-68329f06dcf5'),('09975678-0062-461f-9868-8c6cbcc6f7f5','c04ed82c-6479-4ca8-8ebf-3848d8f8a735'),('09975678-0062-461f-9868-8c6cbcc6f7f5','e64ec7c8-5f1c-47e4-9acb-1a6e286fe100'),('30c4f69d-2dd9-4c8e-8c00-9e2de16bc452','8b890bbe-aab4-4653-a43f-4586fbc3a696');
/*!40000 ALTER TABLE `restaurant_foods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_orders`
--

DROP TABLE IF EXISTS `restaurant_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant_orders` (
  `restaurant_id` varchar(255) NOT NULL,
  `orders_id` varchar(255) NOT NULL,
  UNIQUE KEY `UK_d1dqp0i886vmn4ue9eggj0d8j` (`orders_id`),
  KEY `FK21jwpdt4b37ymv5nkjjte0seg` (`restaurant_id`),
  CONSTRAINT `FK21jwpdt4b37ymv5nkjjte0seg` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FK8swg1vejobtj0ryb6fiwg264k` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_orders`
--

LOCK TABLES `restaurant_orders` WRITE;
/*!40000 ALTER TABLE `restaurant_orders` DISABLE KEYS */;
INSERT INTO `restaurant_orders` VALUES ('30c4f69d-2dd9-4c8e-8c00-9e2de16bc452','9cdabd50-fae2-4c74-ba87-a6300616792f'),('30c4f69d-2dd9-4c8e-8c00-9e2de16bc452','b3f6f0dd-b682-4ad2-b156-6884faec74b5'),('30c4f69d-2dd9-4c8e-8c00-9e2de16bc452','c27f6aa5-5f8c-461f-86f3-47c95a8e06fc');
/*!40000 ALTER TABLE `restaurant_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `dtype` varchar(31) NOT NULL,
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Admin','1663d0a9-c635-4e93-b294-db540a8da5de','admin1@email.com','$2a$10$wA9r8aP0h0xJ3A2Kahn.hupCmHoyFFeCVvQ6N7IY6UR8mSsk94pI6'),('Customer','8b15c7c7-3b8e-4342-92bd-69b8f30e9b4a','customer1@email.com','$2a$10$Eovn5iq9XI2uEJAB8AHkOOcWnXiTArdEGDcpDvQxS0MXqBUaT7hiG');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_orders`
--

DROP TABLE IF EXISTS `users_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_orders` (
  `customer_id` varchar(255) NOT NULL,
  `orders_id` varchar(255) NOT NULL,
  UNIQUE KEY `UK_1njdfitph68mh7p7c6f3qc736` (`orders_id`),
  KEY `FK535gbkx5lnqoh2tinilnquojy` (`customer_id`),
  CONSTRAINT `FK2lnf5jw8p8q0ytkr8dp0mlx6` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK535gbkx5lnqoh2tinilnquojy` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_orders`
--

LOCK TABLES `users_orders` WRITE;
/*!40000 ALTER TABLE `users_orders` DISABLE KEYS */;
INSERT INTO `users_orders` VALUES ('8b15c7c7-3b8e-4342-92bd-69b8f30e9b4a','9cdabd50-fae2-4c74-ba87-a6300616792f'),('8b15c7c7-3b8e-4342-92bd-69b8f30e9b4a','b3f6f0dd-b682-4ad2-b156-6884faec74b5'),('8b15c7c7-3b8e-4342-92bd-69b8f30e9b4a','c27f6aa5-5f8c-461f-86f3-47c95a8e06fc');
/*!40000 ALTER TABLE `users_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_restaurants`
--

DROP TABLE IF EXISTS `users_restaurants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_restaurants` (
  `admin_id` varchar(255) NOT NULL,
  `restaurants_id` varchar(255) NOT NULL,
  UNIQUE KEY `UK_8h27sv3t26a2rsk4op3x43la7` (`restaurants_id`),
  KEY `FKnxioxhqspamncuuigg98hksny` (`admin_id`),
  CONSTRAINT `FK8pqqnjtabbpyn66pbvtbp8q37` FOREIGN KEY (`restaurants_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FKnxioxhqspamncuuigg98hksny` FOREIGN KEY (`admin_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_restaurants`
--

LOCK TABLES `users_restaurants` WRITE;
/*!40000 ALTER TABLE `users_restaurants` DISABLE KEYS */;
INSERT INTO `users_restaurants` VALUES ('1663d0a9-c635-4e93-b294-db540a8da5de','09975678-0062-461f-9868-8c6cbcc6f7f5'),('1663d0a9-c635-4e93-b294-db540a8da5de','30c4f69d-2dd9-4c8e-8c00-9e2de16bc452'),('1663d0a9-c635-4e93-b294-db540a8da5de','84da75db-9ce4-4e36-ac03-b045c4dbaeaa');
/*!40000 ALTER TABLE `users_restaurants` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-18  2:34:27
