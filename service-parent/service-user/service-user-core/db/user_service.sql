/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.19 : Database - user_service
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`user_service` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `user_service`;

/*Table structure for table `t_client` */

DROP TABLE IF EXISTS `t_client`;

CREATE TABLE `t_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(200) DEFAULT NULL,
  `client_desc` varchar(2000) DEFAULT NULL,
  `client_id` varchar(100) DEFAULT NULL,
  `client_secret` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_id` bigint(20) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_client_id` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_client` */

insert  into `t_client`(`id`,`client_name`,`client_desc`,`client_id`,`client_secret`,`created_time`,`created_id`,`updated_time`,`updated_id`) values (1,'client1','this is client1','111','111',NULL,NULL,NULL,NULL);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `real_name` varchar(40) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  `latest_login` datetime DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_id` bigint(20) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_id` bigint(20) DEFAULT NULL,
  `open_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_open_id` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`login`,`password`,`real_name`,`email`,`phone`,`active`,`latest_login`,`created_time`,`created_id`,`updated_time`,`updated_id`,`open_id`) values (1,'user1','1',NULL,'111@111.com',NULL,'',NULL,NULL,NULL,NULL,NULL,'111');

/*Table structure for table `t_user_client` */

DROP TABLE IF EXISTS `t_user_client`;

CREATE TABLE `t_user_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_client` (`user_id`,`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_client` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
