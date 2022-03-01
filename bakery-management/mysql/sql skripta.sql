CREATE DATABASE  IF NOT EXISTS `pekaraklas` ;
USE `pekaraklas`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pekaraklas
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `korisnici` (
  `ime` varchar(45) DEFAULT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `korisnicko_ime` varchar(45) NOT NULL,
  `lozinka` varchar(45) DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  `tip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`korisnicko_ime`)
) ENGINE=InnoDB ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici`
--

/*!40000 ALTER TABLE `korisnici` DISABLE KEYS */;
INSERT INTO `korisnici` VALUES ('Jovana','Radic','jovana','jovana123','1997-04-12','kupac'),('Laza','Dukic','laza','laza123','1987-06-23','kupac'),('Maja','Milic','maja','maja123','1966-03-07','kupac'),('Mirko','Nikolic','mika','mika123','1954-12-22','kupac'),('Sofija','Jokic','sofija','sofija123','1982-08-25','radnik'),('Stefan','Misic','stefan','stefan123','1975-12-08','radnik');
/*!40000 ALTER TABLE `korisnici` ENABLE KEYS */;

--
-- Table structure for table `narudzbine`
--

DROP TABLE IF EXISTS `narudzbine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `narudzbine` (
  `id_nar` int(11) NOT NULL AUTO_INCREMENT,
  `kupac` varchar(45) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_nar`)
) ENGINE=InnoDB AUTO_INCREMENT=3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbine`
--

/*!40000 ALTER TABLE `narudzbine` DISABLE KEYS */;
INSERT INTO `narudzbine` VALUES (1,'jovana',3,'naruceno'),(2,'laza',1,'prihvaceno');
/*!40000 ALTER TABLE `narudzbine` ENABLE KEYS */;

--
-- Table structure for table `redcekanja`
--

DROP TABLE IF EXISTS `redcekanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `redcekanja` (
  `id_red` int(11) NOT NULL AUTO_INCREMENT,
  `kupac` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_red`)
) ENGINE=InnoDB AUTO_INCREMENT=3 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `redcekanja`
--

/*!40000 ALTER TABLE `redcekanja` DISABLE KEYS */;
INSERT INTO `redcekanja` VALUES (1,'maja'),(2,'sofija');
/*!40000 ALTER TABLE `redcekanja` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;