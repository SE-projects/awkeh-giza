-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2020 at 09:01 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.1.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `supermarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `productId` int(10) NOT NULL,
  `productName` varchar(30) NOT NULL,
  `productType` varchar(30) NOT NULL,
  `price` int(10) NOT NULL,
  `amount` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`productId`, `productName`, `productType`, `price`, `amount`) VALUES
(1, 'car', 'vehicle', 210000, 1),
(2, 'villa', 'house', 2000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productId` int(10) NOT NULL,
  `productName` varchar(30) NOT NULL,
  `type` varchar(10) NOT NULL,
  `productBrand` varchar(30) NOT NULL,
  `quantity` int(10) NOT NULL,
  `price` int(10) NOT NULL,
  `information` varchar(30) NOT NULL,
  `location` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productId`, `productName`, `type`, `productBrand`, `quantity`, `price`, `information`, `location`) VALUES
(12, 'bread', 'food', 'shewabread', 200, 2, 'hot', '2T/right'),
(13, 'defo', 'food', 'shewabread', 100, 100, '1 day', 'DF/center'),
(14, 'loseQebe', 'packedFood', 'nek', 12, 37, 'long-last', 'Rt34/center');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `reportId` int(10) NOT NULL,
  `productName` varchar(40) NOT NULL,
  `productBrand` varchar(60) NOT NULL,
  `reportReason` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`reportId`, `productName`, `productBrand`, `reportReason`) VALUES
(1, 'bread', 'shewa', 'very costy'),
(5, 'honey', 'gojjam', 'too testy'),
(6, 'coffe', 'jimma', 'expensive');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `id` int(10) NOT NULL,
  `productName` varchar(30) NOT NULL,
  `productType` varchar(30) NOT NULL,
  `productBrand` varchar(30) NOT NULL,
  `numProducts` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `requestforstock`
--

CREATE TABLE `requestforstock` (
  `productId` int(10) NOT NULL,
  `productName` varchar(30) NOT NULL,
  `productBrand` varchar(30) NOT NULL,
  `amount` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `requestforstock`
--

INSERT INTO `requestforstock` (`productId`, `productName`, `productBrand`, `amount`, `status`) VALUES
(1, 'honey', 'local', '100', 'urgent'),
(13, 'bread', 'defo', '300', 'urgent');

-- --------------------------------------------------------

--
-- Table structure for table `stockprovided`
--

CREATE TABLE `stockprovided` (
  `productId` int(10) NOT NULL,
  `productName` varchar(30) NOT NULL,
  `productType` varchar(30) NOT NULL,
  `productBrand` varchar(30) NOT NULL,
  `quantity` int(30) NOT NULL,
  `singlePrice` int(30) NOT NULL,
  `information` varchar(30) NOT NULL,
  `source` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stockprovided`
--

INSERT INTO `stockprovided` (`productId`, `productName`, `productType`, `productBrand`, `quantity`, `singlePrice`, `information`, `source`, `status`) VALUES
(1, 'bread', 'food', 'shewabread', 200, 2, 'hot', '4 kilo', 'DF-()8/center'),
(2, 'defo', 'food', 'shewabread', 100, 100, '1 day', 'piasa', 'DF/center'),
(3, 'loseQebe', 'packedFood', 'nek', 12, 37, 'long-last', 'abadir', 'Rt34/center'),
(4, 'tuna', 'food', 'haw', 10, 36, 'new', 'abadir', 'WE12/2nd-block');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`productId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productId`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`reportId`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `requestforstock`
--
ALTER TABLE `requestforstock`
  ADD PRIMARY KEY (`productId`);

--
-- Indexes for table `stockprovided`
--
ALTER TABLE `stockprovided`
  ADD PRIMARY KEY (`productId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `reportId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `requestforstock`
--
ALTER TABLE `requestforstock`
  MODIFY `productId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `stockprovided`
--
ALTER TABLE `stockprovided`
  MODIFY `productId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
