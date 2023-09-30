-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 04, 2023 at 09:08 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `infits`
--

-- --------------------------------------------------------

--
-- Table structure for table `referral_table`
--

CREATE TABLE `referral_table` (
  `clientID` varchar(25) NOT NULL,
  `referralCode` varchar(8) NOT NULL,
  `activeUsers` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `referral_table`
--

INSERT INTO `referral_table` (`clientID`, `referralCode`, `activeUsers`) VALUES
('test', 'tes31395', 'Azarudeen, Azarudeen, Azarudeen'),
('Azarudeen', 'Aza12345', 'none'),
('test1', 'tes77154', 'none'),
('atul', 'atu87247', 'none'),
('dev', 'dev04184', 'none'),
('dev', 'dev92753', 'none'),
('dev', 'dev44953', 'none'),
('dev', 'dev91384', 'none'),
('dev', 'dev28604', 'none'),
('dev', 'dev83120', 'none'),
('dev', 'dev03390', 'none'),
('dev', 'dev04880', 'none'),
('dev', 'dev41533', 'none'),
('dev', 'dev67678', 'none'),
('dev', 'dev41157', 'none'),
('dev', 'dev76256', 'none'),
('dev', 'dev08126', 'none'),
('dev', 'dev23221', 'none'),
('dev', 'dev73002', 'none'),
('dev', 'dev36524', 'none'),
('dev', 'dev90912', 'none'),
('dev', 'dev04147', 'none'),
('dev', 'dev31003', 'none'),
('dev', 'dev71962', 'none'),
('dev', 'dev34651', 'none'),
('dev', 'dev27674', 'none'),
('dev', 'dev12575', 'none'),
('dev', 'dev74370', 'none'),
('dev', 'dev38433', 'none'),
('dev', 'dev03498', 'none'),
('dev', 'dev98763', 'none'),
('dev', 'dev15887', 'none'),
('dev', 'dev07884', 'none'),
('dev', 'dev41555', 'none'),
('dev', 'dev06875', 'none'),
('dev', 'dev10181', 'none'),
('dev', 'dev05120', 'none'),
('dev', 'dev34520', 'none'),
('dev', 'dev53251', 'none'),
('dev', 'dev58697', 'none'),
('dev', 'dev69321', 'none'),
('dev', 'dev43212', 'none'),
('dev', 'dev78967', 'none'),
('dev', 'dev37664', 'none'),
('dev', 'dev63378', 'none'),
('dev', 'dev11672', 'none'),
('dev', 'dev19032', 'none'),
('dev', 'dev68407', 'none'),
('dev', 'dev24083', 'none'),
('dev', 'dev14089', 'none'),
('dev', 'dev79311', 'none'),
('dev', 'dev43837', 'none'),
('dev', 'dev31616', 'none'),
('dev', 'dev49267', 'none'),
('dev', 'dev40394', 'none'),
('hello', 'hel49445', 'none'),
('hello', 'hel40971', 'none'),
('dev', 'dev02445', 'none'),
('dev', 'dev05533', 'none'),
('dev', 'dev96696', 'none'),
('dev', 'dev26716', 'none'),
('deva', 'dev69560', 'none'),
('dev', 'dev70858', 'none'),
('test', 'tes32651', 'none'),
('test', 'tes97687', 'none'),
('test', 'tes71539', 'none'),
('test', 'tes31017', 'none');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
