-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 07, 2020 at 11:44 PM
-- Server version: 5.7.19
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jsf_laboratorija`
--
CREATE DATABASE IF NOT EXISTS `jsf_laboratorija` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `jsf_laboratorija`;

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE IF NOT EXISTS `korisnik` (
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `pol` varchar(50) NOT NULL,
  `korisnickoIme` varchar(50) NOT NULL,
  `lozinka` varchar(50) NOT NULL,
  `tip` varchar(50) NOT NULL,
  PRIMARY KEY (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`ime`, `prezime`, `pol`, `korisnickoIme`, `lozinka`, `tip`) VALUES
('Aleksandar', 'Isic', 'musko', 'aleksandar', 'aleksandar123', 'laborant'),
('Bozidar', 'Miladinovic', 'musko', 'bozidar', 'bozidar123', 'laborant'),
('Drazen', 'Draskovic', 'musko', 'drazen', 'drazen123', 'nastavnik'),
('Jelica', 'Cincovic', 'zensko', 'jelica', 'jelica123', 'nastavnik'),
('Luka', 'Milosevic', 'musko', 'luka', 'luka123', 'laborant'),
('Sanja', 'Delcev', 'zensko', 'sanja', 'sanja123', 'nastavnik');

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
CREATE TABLE IF NOT EXISTS `rezervacija` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `datumOd` datetime NOT NULL,
  `datumDo` datetime NOT NULL,
  `prostorije` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rezervacija`
--

INSERT INTO `rezervacija` (`id`, `naziv`, `datumOd`, `datumDo`, `prostorije`) VALUES
(1, 'PIA lab', '2021-05-12 09:00:00', '2021-05-12 11:15:00', 'P25,P26,70'),
(2, 'Zastita podataka - nadoknada lab3', '2021-05-19 10:00:00', '2021-05-19 12:30:00', 'P25,P26');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
