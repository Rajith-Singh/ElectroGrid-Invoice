-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2022 at 01:20 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `cus_nic` varchar(12) NOT NULL,
  `month` varchar(12) NOT NULL,
  `unit_calculation` double(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`id`, `cus_nic`, `month`, `unit_calculation`) VALUES
(2, '757593564V', 'November', 314.00),
(3, '757593564V', 'December', 738.10),
(4, '757593564V', 'January', 2531.85),
(12, '775565879V', 'July', 1394.10);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `nic` varchar(12) NOT NULL,
  `address` varchar(60) NOT NULL,
  `telNo` varchar(10) NOT NULL,
  `accNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `name`, `nic`, `address`, `telNo`, `accNo`) VALUES
(2, 'Joel', '99999', 'Polonnaruwa', '777777', 88888),
(4, 'Panadura', '99999', 'Polonnaruwa', '777777', 88888),
(5, 'Dulaksha', '2001002040', 'Panagoda', '0711232233', 123),
(6, 'Dulsara', '9834244', 'Balangoda', '0711222233', 344),
(9, 'Weerasingha', '233242', 'Kaduwela', '234234', 3243423),
(11, 'Amal', '0099887766', 'Kottawa', '0983234234', 713432433),
(13, 'Sudesh', '222298877', 'Homagama', '2223332', 111331211),
(14, 'Sadamini', '222298877', 'Balangoda', '2223332', 111331211),
(17, 'Upula Ariyasena', '757593564V', 'No.546/D, Weligepola, Balangoda', '0754565321', 64564),
(18, 'Kamal Wanigarathne', '775565879V', 'Mudungoda, Yakkala', '0754565321', 64564);

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

CREATE TABLE `unit` (
  `unit_id` varchar(30) NOT NULL,
  `no_of_units` int(11) NOT NULL,
  `unit_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `unit`
--

INSERT INTO `unit` (`unit_id`, `no_of_units`, `unit_price`) VALUES
('unit_132', 33, 27.75),
('unit_198', 66, 32),
('unit_225', 225, 45),
('unit_66', 66, 7.85),
('unit_99', 33, 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`unit_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
