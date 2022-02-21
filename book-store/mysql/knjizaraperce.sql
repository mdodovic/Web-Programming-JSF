CREATE DATABASE  IF NOT EXISTS `knjizaraperce`;
USE `knjizaraperce`;
-- Host: 127.0.0.1    Database: knjizaraperce
--
-- Table structure for table `knjige`
--

DROP TABLE IF EXISTS `knjige`;
CREATE TABLE `knjige` (
  `idK` int(11) NOT NULL,
  `naslov` varchar(45) DEFAULT NULL,
  `slika` varchar(45) DEFAULT NULL,
  `autor` varchar(45) DEFAULT NULL,
  `brStr` int(11) DEFAULT NULL,
  `godina` int(11) DEFAULT NULL,
  `naStanju` int(11) DEFAULT NULL,
  PRIMARY KEY (`idK`)
) ENGINE=InnoDB;

--
-- Dumping data for table `knjige`
--


INSERT INTO `knjige` VALUES (1,'Andjeosko drvo','1.jpg','Lusinda Rajli',600,2017,20),(2,'Poslednja prilika','2.jpg','Fredrik Bakman',82,2018,2),(3,'Veliki pad','3.jpg','Peter Handke',216,2019,5);

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
CREATE TABLE `korisnici` (
  `idK` int(11) NOT NULL,
  `ime` varchar(45) DEFAULT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `korime` varchar(45) DEFAULT NULL,
  `lozinka` varchar(45) DEFAULT NULL,
  `tip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idK`)
) ENGINE=InnoDB;

--
-- Dumping data for table `korisnici`
--


INSERT INTO `korisnici` VALUES (1,'Marija','Tomic','maki','maki123','kupac'),(2,'Stefan','Nikolic','stefi','stefi123','kupac'),(3,'Jovana','Perkovic','joca','joca123','radnik');

--
-- Table structure for table `narudzbine`
--

DROP TABLE IF EXISTS `narudzbine`;
CREATE TABLE `narudzbine` (
  `idN` int(11) NOT NULL,
  `kupac` int(11) DEFAULT NULL,
  `knjiga` int(11) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idN`)
) ENGINE=InnoDB;

--
-- Dumping data for table `narudzbine`
--

INSERT INTO `narudzbine` VALUES (1,1,2,3,'naruceno'),(2,2,3,1,'prihvaceno');