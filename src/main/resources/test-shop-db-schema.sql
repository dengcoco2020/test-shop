-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 29, 2020 at 01:54 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `test-shop-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `base_price` double NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `prod_id` int(11) DEFAULT NULL,
  `prod_name` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `item_count` int(11) NOT NULL,
  `total_amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(50);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `prod_id` int(11) NOT NULL,
  `base_price` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `flag` int(11) NOT NULL,
  `prod_name` varchar(255) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`prod_id`, `base_price`, `description`, `flag`, `prod_name`, `brand_name`) VALUES
(1, 349, NULL, 1, 'Eco-Drive Blue Dial Titanium Men\'s Watch', 'CITIZEN'),
(2, 229, NULL, 1, 'PRS 516 Automatic Men\'s Watch', 'TISSOT'),
(3, 325, NULL, 1, 'Flight Chronograph Steel Black Dial Mens Watch', 'HAMILTON'),
(4, 301, NULL, 1, 'Khaki Field King Automatic Silver Dial Men\'s Watch', 'SEIKO'),
(5, 9995, NULL, 1, 'Submariner Automatic Black Dial Men\'s Watch', 'ROLEX'),
(6, 75, NULL, 1, 'PRS 516 Automatic Black Dial Men\'s Watch', 'MOVADO');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`prod_id`);
