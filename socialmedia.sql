-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 24, 2017 at 12:51 AM
-- Server version: 5.7.19-0ubuntu0.16.04.1
-- PHP Version: 7.0.22-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `socialmedia`
--

-- --------------------------------------------------------

--
-- Table structure for table `social_media`
--

CREATE TABLE `social_media` (
  `id` int(11) NOT NULL,
  `fb_username` varchar(11) NOT NULL,
  `fb_email` varchar(110) NOT NULL,
  `fb_birthday` varchar(110) NOT NULL,
  `gg_email` varchar(110) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `social_media`
--

INSERT INTO `social_media` (`id`, `fb_username`, `fb_email`, `fb_birthday`, `gg_email`, `user_id`) VALUES
(2, 'Ola Yakout', 'ola_yakout@yahoo.com', '02/22/1993', 'ola.yakout@gmail.com', 18);

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `name` varchar(111) NOT NULL,
  `fb_access_token` text NOT NULL,
  `tw_access_token` varchar(111) NOT NULL,
  `gg_access_token` text NOT NULL,
  `last_login` varchar(110) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`id`, `name`, `fb_access_token`, `tw_access_token`, `gg_access_token`, `last_login`) VALUES
(18, 'ola', 'EAAbpziPuZA9IBABqWtBzXIgpCPTbLpTbP7v611H1Pkdy4ZCDgXZAEE22GdgO7qyhT9ZBa93p9nt9mZCx6lqCZCfrghK8bhU4JEkjeasx8JJu2OP3OCQdl2ZBLbET7dcL2nBL3Kt6MVCZCZB3v4ZCUxWSFXZCOTsjtKvt6AZD', '424494168-OgHyvWSxq8Zy8SKBDQqlLDSiGPDEfxXupu6eqKPz', 'ya29.GlvtBKTLZEpnNJ810wQhH5S2X8NIaYbddb0tX-oDBH3Sp8abPYHpCi7qv7mc3c6wANzke1CN6BNhO0WrZzHV0YbFMlMju6IvilPQxEU5Xoa8h--MUwnSRqpQnRTo', '2017-10-23');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `social_media`
--
ALTER TABLE `social_media`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_User` (`user_id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `social_media`
--
ALTER TABLE `social_media`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `social_media`
--
ALTER TABLE `social_media`
  ADD CONSTRAINT `FK_User` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
