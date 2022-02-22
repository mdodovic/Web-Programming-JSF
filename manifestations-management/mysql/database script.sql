-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 25, 2020 at 02:41 PM
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
-- Database: `jsf_lab_2020`
--
CREATE DATABASE IF NOT EXISTS `jsf_lab_2020` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `jsf_lab_2020`;

-- --------------------------------------------------------

--
-- Table structure for table `manifestations`
--

DROP TABLE IF EXISTS `manifestations`;
CREATE TABLE IF NOT EXISTS `manifestations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL,
  `type` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `organizer` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `organizer` (`organizer`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `manifestations`
--

INSERT INTO `manifestations` (`id`, `name`, `date_from`, `date_to`, `type`, `status`, `organizer`) VALUES
(1, 'Fest 2021', '2021-03-10', '2021-03-15', 'festival', 'nova', 'joca'),
(2, 'Jazz Festival', '2020-11-22', '2020-11-29', 'festival', 'odobrena', 'joca'),
(3, 'Univerzijada 2021', '2021-07-15', '2021-07-29', 'sport', 'odbijena', 'dule'),
(4, 'Zdravko Colic', '2020-12-03', '2020-12-03', 'koncert', 'nova', 'dule');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `first_name`, `last_name`, `is_admin`) VALUES
('dule', 'Dule123!', 'Dusan', 'Dud', 0),
('joca', 'Joca123!', 'Jovana', 'Jovic', 0),
('pera', 'Pera123!', 'Petar', 'Peric', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `manifestations`
--
ALTER TABLE `manifestations`
  ADD CONSTRAINT `fk_user_manifestation` FOREIGN KEY (`organizer`) REFERENCES `users` (`username`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
