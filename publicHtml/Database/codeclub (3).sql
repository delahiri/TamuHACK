-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 10, 2015 at 11:17 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `codeclub`
--

-- --------------------------------------------------------

--
-- Table structure for table `problems`
--

CREATE TABLE IF NOT EXISTS `problems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `statement` varchar(1000) NOT NULL,
  `testCases` varchar(1000) NOT NULL,
  `problem_name` varchar(100) NOT NULL,
  `problem_type` int(11) NOT NULL,
  `solution_template` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `problem_type` (`problem_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `problems`
--

INSERT INTO `problems` (`id`, `statement`, `testCases`, `problem_name`, `problem_type`, `solution_template`) VALUES
(1, 'Problem Statement: You are given 2 numbers a and b. You need to give the sum of these numbers as output. Consider the numbers as Integers.\r\n\r\nFor example:\r\n1 4\r\n\r\noutput:\r\n5', '[{a=1,b=2},{a=3,b=7}]', 'Hello World', 1, 'public class Solution\r\n{\r\n   public int giveSum(int a, int b)\r\n   {\r\n       //your code goes here\r\n   }\r\n}'),
(2, 'Problem: Find the fibonacci number for of a value of n. \r\n\r\nFor e.g if n is 2 then fibonacci number is 1 and if n is 1 then fibonacci number is 2.', '', 'Fibonacci Problems', 2, 'public class Solution\r\n{\r\n   public int giveFibo(int n)\r\n   {\r\n     //your code goes here\r\n   }\r\n}'),
(3, 'Some problem which requires you to use Greedy. I am too tired at this moment to add a problem.', '', 'Some unique name', 3, 'public class Solution\r\n{\r\n   public int sol(int a)\r\n   {\r\n      //its not a bug. its a feature.\r\n   }\r\n}');

-- --------------------------------------------------------

--
-- Table structure for table `problem_type`
--

CREATE TABLE IF NOT EXISTS `problem_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `problem_type`
--

INSERT INTO `problem_type` (`id`, `name`) VALUES
(1, 'Arrays'),
(2, 'Dynamic Programming'),
(3, 'Greedy Programming');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `current_prob` int(11) DEFAULT NULL,
  `password` varchar(256) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `name`, `email`, `current_prob`, `password`) VALUES
(1, 'Mark', 'mark@facebook.com', 3, 'password'),
(2, 'Jack', 'jack@twitter.com', 1, 'password'),
(3, 'Elon Musk', 'musk@tesla.com', 99, 'password'),
(4, 'Mario', 'mario@game.com', 3, 'password');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
