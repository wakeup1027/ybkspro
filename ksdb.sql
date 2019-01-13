/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.1.62-community : Database - kusjxlkjalsdmqwnx_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kusjxlkjalsdmqwnx_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kusjxlkjalsdmqwnx_db`;

/*Table structure for table `opennumber_log` */

DROP TABLE IF EXISTS `opennumber_log`;

CREATE TABLE `opennumber_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_qihao` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开奖期号',
  `create_open` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开奖号码',
  `create_time` datetime DEFAULT NULL COMMENT '开奖时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `opennumber_log` */

insert  into `opennumber_log`(`id`,`create_qihao`,`create_open`,`create_time`) values (2,'000001','2,3,4','2019-01-13 01:20:00'),(3,'000002','2,3,5','2019-01-13 01:30:00'),(4,'000003','1,1,1','2019-01-13 01:40:00'),(5,'000004','1,3,6','2019-01-13 01:50:00'),(6,'000005','2,6,6','2019-01-13 02:00:00'),(7,'000006','2,3,4','2019-01-13 14:30:00'),(8,'000007','3,3,5','2019-01-13 14:40:00');

/*Table structure for table `taskconfig` */

DROP TABLE IF EXISTS `taskconfig`;

CREATE TABLE `taskconfig` (
  `id` int(11) NOT NULL,
  `status` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态：NORMAL(正常) PAUSED(暂停)',
  `nowNum` int(11) DEFAULT NULL COMMENT '现在开到几期了的标志',
  `second` int(11) DEFAULT NULL COMMENT '倒计时（同步前后端）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `taskconfig` */

insert  into `taskconfig`(`id`,`status`,`nowNum`,`second`) values (1,'NORMAL',8,207);

/*Table structure for table `yushenumber` */

DROP TABLE IF EXISTS `yushenumber`;

CREATE TABLE `yushenumber` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qihao` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `openumber` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creatime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `yushenumber` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
