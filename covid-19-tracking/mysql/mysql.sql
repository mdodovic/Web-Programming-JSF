-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 21, 2020 at 01:43 PM
-- Server version: 5.7.19
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

SET @@global.time_zone='+00:00';
SET @@session.time_zone='+00:00';

--
-- Database: `covid19pia`
--
DROP DATABASE IF EXISTS `covid19pia`;
CREATE DATABASE IF NOT EXISTS `covid19pia` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `covid19pia`;

-- --------------------------------------------------------

--
-- Table structure for table `bolnica`
--

DROP TABLE IF EXISTS `bolnica`;
CREATE TABLE IF NOT EXISTS `bolnica` (
  `bID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `lokacija` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `ukupno_kreveta` int(11) NOT NULL,
  `broj_slobodnih_kreveta` int(11) NOT NULL,
  `broj_test_PCR` int(11) NOT NULL,
  `broj_test_antitela` int(11) NOT NULL,
  PRIMARY KEY (`bID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bolnica`
--

INSERT INTO `bolnica` (`bID`, `naziv`, `lokacija`, `ukupno_kreveta`, `broj_slobodnih_kreveta`, `broj_test_PCR`, `broj_test_antitela`) VALUES
(1, 'Dragisa Misovic', 'Beograd', 10, 9, 2, 3),
(2, 'Bezanijska kosa', 'Novi Beograd', 6, 6, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE IF NOT EXISTS `korisnik` (
  `username` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ime` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `eposta` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`username`, `password`, `ime`, `prezime`, `eposta`) VALUES
('doktor1', 'sifra123', 'Nestor', 'Nestorovic', 'nestor@doktor.rs'),
('doktor2', 'sifra123', 'Peca', 'Konic', 'peca@doktor.rs'),
('doktor3', 'sifra123', 'Ivan', 'Ivanovic', 'ivan@doktor.rs'),
('doktor4', 'sifra123', 'Desa', 'Desankic', 'desa@doktor.rs'),
('pacijent1', 'sifra123', 'Petar', 'Petrovic', 'petar@etf.bg.ac.rs'),
('pacijent2', 'sifra123', 'Jovan', 'Jovanovic', 'jova@etf.bg.ac.rs'),
('pacijent3', 'sifra123', 'Milan', 'Milanovic', 'mica@etf.bg.ac.rs'),
('pacijent4', 'sifra123', 'Dejan', 'Dejanovic', 'deki@etf.bg.ac.rs'),
('pacijent5', 'sifra123', 'Branka', 'Brankic', 'branka@etf.bg.ac.rs');

-- --------------------------------------------------------

--
-- Table structure for table `lekar`
--

DROP TABLE IF EXISTS `lekar`;
CREATE TABLE IF NOT EXISTS `lekar` (
  `username` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `specijalizacija` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `idBolnice` int(11) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `idBolnice` (`idBolnice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lekar`
--

INSERT INTO `lekar` (`username`, `specijalizacija`, `idBolnice`) VALUES
('doktor1', 'pulmolog', 1),
('doktor2', 'virusolog', 1),
('doktor3', 'imunolog', 1),
('doktor4', 'anesteziolog', 2);

-- --------------------------------------------------------

--
-- Table structure for table `pacijent`
--

DROP TABLE IF EXISTS `pacijent`;
CREATE TABLE IF NOT EXISTS `pacijent` (
  `username` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `hospitalizovan` tinyint(1) NOT NULL,
  `status` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `datum_test` date DEFAULT NULL,
  `vrsta_test` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rezultat_test_positiv` tinyint(1) DEFAULT NULL,
  `datum_hosp` date DEFAULT NULL,
  `datum_otpust` date DEFAULT NULL,
  `idBolnice` int(11) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `idBolnice` (`idBolnice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `pacijent`
--

INSERT INTO `pacijent` (`username`, `hospitalizovan`, `status`, `datum_test`, `vrsta_test`, `rezultat_test_positiv`, `datum_hosp`, `datum_otpust`, `idBolnice`) VALUES
('pacijent1', 1, 'O', '2020-08-22', 'PCR', 1, '2020-08-20', NULL, 1),
('pacijent2', 1, 'P', '2020-08-22', 'ANT', 0, '2020-08-19', '2020-08-23', 1),
('pacijent3', 0, 'N', NULL, NULL, NULL, NULL, NULL, 1),
('pacijent4', 1, 'O', '2020-08-22', 'PCR', 1, '2020-08-19', NULL, 2),
('pacijent5', 1, 'U', '2020-08-21', 'PCR', 1, '2020-07-01', '2020-08-23', 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `lekar`
--
ALTER TABLE `lekar`
  ADD CONSTRAINT `lekar_ibfk_1` FOREIGN KEY (`username`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lekar_ibfk_2` FOREIGN KEY (`idBolnice`) REFERENCES `bolnica` (`bID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pacijent`
--
ALTER TABLE `pacijent`
  ADD CONSTRAINT `pacijent_ibfk_1` FOREIGN KEY (`username`) REFERENCES `korisnik` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pacijent_ibfk_2` FOREIGN KEY (`idBolnice`) REFERENCES `bolnica` (`bID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*/!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*/!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*/!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;