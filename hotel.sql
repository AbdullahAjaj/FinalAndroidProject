-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 19, 2022 at 12:48 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `userName` varchar(50) NOT NULL,
  `fullName` varchar(60) NOT NULL,
  `phoneNum` int(11) NOT NULL,
  `roomID` int(11) DEFAULT NULL,
  `totalPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`userName`, `fullName`, `phoneNum`, `roomID`, `totalPrice`) VALUES
('ajaj23', 'ajaj', 568008432, 1, 0),
('hajali', 'mohamad', 594594999, 5, 1800),
('Tayseer', 'mohamad', 4689, 4, 0);

-- --------------------------------------------------------

--
-- Table structure for table `receptionist`
--

CREATE TABLE `receptionist` (
  `receptionistname` varchar(100) NOT NULL,
  `receptionistpass` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receptionist`
--

INSERT INTO `receptionist` (`receptionistname`, `receptionistpass`) VALUES
('ajaj', '1234'),
('mohamad', '123');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomID` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `priceByDay` int(11) NOT NULL,
  `image` text NOT NULL,
  `isReserved` tinyint(1) NOT NULL,
  `userName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomID`, `capacity`, `priceByDay`, `image`, `isReserved`, `userName`) VALUES
(1, 3, 150, 'http://192.168.1.115:80/mobileProject/images/room1.jpg', 0, ' '),
(2, 2, 200, 'http://192.168.1.115:80/mobileProject/images/room2.jpg', 0, ''),
(3, 1, 100, 'http://192.168.1.115:80/mobileProject/images/room3.jpg', 0, ' '),
(4, 2, 300, 'http://192.168.1.115:80/mobileProject/images/room4.jpg', 1, ' Tayseer'),
(5, 2, 450, 'http://192.168.1.115:80/mobileProject/images/room5.jpg', 1, ' hajali'),
(6, 3, 600, 'http://192.168.1.115:80/mobileProject/images/room6.jpg', 0, ''),
(7, 3, 250, 'http://192.168.1.115:80/mobileProject/images/room7.jpg', 0, ''),
(8, 2, 250, 'http://192.168.1.115:80/mobileProject/images/room8.jpg', 0, ' '),
(9, 2, 400, 'http://192.168.1.115:80/mobileProject/images/room9.jpg', 0, ''),
(10, 2, 700, 'http://192.168.1.115:80/mobileProject/images/room10.jpg', 0, ''),
(11, 2, 300, 'http://192.168.1.115:80/mobileProject/images/room11.jpg', 0, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`userName`),
  ADD KEY `roomID` (`roomID`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
