-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 04, 2020 at 12:41 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `awkeh_giza`
--

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `branch` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`branch`) VALUES
('EAST'),
('WEST'),
('NORTH'),
('SOUTH');

-- --------------------------------------------------------

--
-- Table structure for table `departmentmanagerlogin`
--

CREATE TABLE `departmentmanagerlogin` (
  `Name` varchar(30) NOT NULL,
  `Branch` varchar(10) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departmentmanagerlogin`
--

INSERT INTO `departmentmanagerlogin` (`Name`, `Branch`, `Password`) VALUES
('kal', 'WEST', '4321');

-- --------------------------------------------------------

--
-- Table structure for table `generalmanager`
--

CREATE TABLE `generalmanager` (
  `Name` varchar(50) NOT NULL,
  `Branch` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `generalmanager`
--

INSERT INTO `generalmanager` (`Name`, `Branch`, `Password`) VALUES
('kuku', 'east', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `sent from` varchar(30) NOT NULL,
  `report` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `departmentmanagerlogin`
--
ALTER TABLE `departmentmanagerlogin`
  ADD UNIQUE KEY `Password` (`Password`);

--
-- Indexes for table `generalmanager`
--
ALTER TABLE `generalmanager`
  ADD UNIQUE KEY `Password` (`Password`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD UNIQUE KEY `sent from` (`sent from`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
