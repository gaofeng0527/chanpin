--
-- 数据库: `toolkit`
--
# 数据库
CREATE DATABASE IF NOT EXISTS `toolkit`;
USE toolkit;
-- --------------------------------------------------------

--
-- 表的结构 `t_chapter`
--
DROP TABLE IF EXISTS t_chapter;
CREATE TABLE IF NOT EXISTS `t_chapter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ccode` varchar(100) DEFAULT NULL,
  `cctitle` varchar(100) DEFAULT NULL,
  `video` varchar(300) DEFAULT NULL,
  `subject_id` bigint(20) NOT NULL,
  `title` varchar(200) NOT NULL,
  `orderL` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 表的结构 `t_know`
--
DROP TABLE IF EXISTS t_know;
CREATE TABLE IF NOT EXISTS `t_know` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `times` bigint(20) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `kcode` varchar(100) DEFAULT NULL,
  `chapter_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


--
-- 表的结构 `t_question`
--
DROP TABLE IF EXISTS t_question
CREATE TABLE IF NOT EXISTS `t_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qcode` varchar(50) NOT NULL,
  `q_desc` varchar(500) DEFAULT NULL,
  `q_type` varchar(100) NOT NULL,
  `title` varchar(500) NOT NULL,
  `optiona` varchar(800) DEFAULT NULL,
  `optionb` varchar(800) DEFAULT NULL,
  `optionc` varchar(800) DEFAULT NULL,
  `optiond` varchar(800) DEFAULT NULL,
  `optione` varchar(800) DEFAULT NULL,
  `optionf` varchar(800) DEFAULT NULL,
  `optiong` varchar(800) DEFAULT NULL,
  `answer` varchar(800) NOT NULL,
  `score` double NOT NULL,
  `know_id` bigint(20) NOT NULL,
  `analysis` varchar(1000) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 表的结构 `t_subject`
--
DROP TABLE IF EXISTS t_subject
CREATE TABLE IF NOT EXISTS `t_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `module` varchar(11) DEFAULT NULL,
  `code` varchar(100) NOT NULL,
  `definition` varchar(100) DEFAULT NULL,
  `requireRTE` int(11) DEFAULT '1',
  `requireAQ` int(11) DEFAULT '1',
  `add_time` datetime DEFAULT NULL,
  `last_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

