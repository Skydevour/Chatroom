-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- 主机: 127.0.0.1:3306
-- 生成日期: 2019 年 12 月 20 日 02:55
-- 服务器版本: 5.5.20
-- PHP 版本: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `chatroom`
--

-- --------------------------------------------------------

--
-- 表的结构 `chatinfor`
--

CREATE TABLE IF NOT EXISTS `chatinfor` (
  `MNumber` varchar(11) NOT NULL,
  `DInfo` varchar(200) NOT NULL,
  `DTime` datetime NOT NULL,
  `DRecive` varchar(11) NOT NULL,
  PRIMARY KEY (`DRecive`),
  KEY `MNumber` (`MNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `friend`
--

CREATE TABLE IF NOT EXISTS `friend` (
  `MNumber` varchar(11) NOT NULL,
  `DRecive` varchar(11) NOT NULL,
  KEY `MNumber` (`MNumber`),
  KEY `DRecive` (`DRecive`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `personinfor`
--

CREATE TABLE IF NOT EXISTS `personinfor` (
  `MNumber` varchar(11) NOT NULL,
  `MSecret` varchar(20) NOT NULL,
  `MName` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `MSex` char(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `MIntro` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Name` char(20) CHARACTER SET utf8 DEFAULT NULL,
  `Telephone` char(11) DEFAULT NULL,
  `Occupation` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `personinfor`
--

INSERT INTO `personinfor` (`MNumber`, `MSecret`, `MName`, `MSex`, `birthday`, `MIntro`, `Name`, `Telephone`, `Occupation`) VALUES
('1111111', '123456', '随意', '1', '1999-09-25', NULL, '随意', '12345678912', '学生'),
('41808251', '123456789', '张滢', '0', '2000-09-30', NULL, '张滢', '15268773789', '学生'),
('41812146', '123456', '陈浩宇', '1', '1999-08-12', NULL, '陈浩宇', '12345678912', '学生'),
('41812147', '1234567', '邹圣俊', '1', '1999-09-25', NULL, '邹圣俊', '15179071987', '学生');

--
-- 限制导出的表
--

--
-- 限制表 `chatinfor`
--
ALTER TABLE `chatinfor`
  ADD CONSTRAINT `chatinfor_ibfk_1` FOREIGN KEY (`MNumber`) REFERENCES `personinfor` (`MNumber`);

--
-- 限制表 `friend`
--
ALTER TABLE `friend`
  ADD CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`MNumber`) REFERENCES `personinfor` (`MNumber`),
  ADD CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`DRecive`) REFERENCES `chatinfor` (`DRecive`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
