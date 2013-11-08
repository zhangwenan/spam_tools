# MySQL-Front 5.1  (Build 4.13)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: spam_tools
# ------------------------------------------------------
# Server version 5.6.12

#
# Source for table mail_content
#

DROP TABLE IF EXISTS `mail_content`;
CREATE TABLE `mail_content` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `title` tinytext COMMENT '邮件主题',
  `content` tinytext COMMENT '邮件内容',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `del` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table mail_content
#

LOCK TABLES `mail_content` WRITE;
/*!40000 ALTER TABLE `mail_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_content` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table mail_send_from
#

DROP TABLE IF EXISTS `mail_send_from`;
CREATE TABLE `mail_send_from` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `user_name` varchar(255) DEFAULT NULL COMMENT '邮箱登录用户名',
  `pass` varchar(32) DEFAULT NULL COMMENT '邮箱登录密码',
  `host` varchar(50) DEFAULT NULL COMMENT 'smtp服务器地址',
  `success_count` int(10) DEFAULT NULL COMMENT '成功次数',
  `fail_count` int(10) DEFAULT NULL COMMENT '失败次数',
  `last_time` datetime DEFAULT NULL COMMENT '上次发送时间',
  `del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table mail_send_from
#

LOCK TABLES `mail_send_from` WRITE;
/*!40000 ALTER TABLE `mail_send_from` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_send_from` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table mail_send_history
#

DROP TABLE IF EXISTS `mail_send_history`;
CREATE TABLE `mail_send_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_from` varchar(255) DEFAULT NULL COMMENT '发送邮箱',
  `send_to` varchar(255) DEFAULT NULL COMMENT '接收邮箱',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '发送状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table mail_send_history
#

LOCK TABLES `mail_send_history` WRITE;
/*!40000 ALTER TABLE `mail_send_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_send_history` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table mail_send_to
#

DROP TABLE IF EXISTS `mail_send_to`;
CREATE TABLE `mail_send_to` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `success_count` int(10) DEFAULT NULL COMMENT '成功次数',
  `fail_count` int(10) DEFAULT NULL COMMENT '失败次数',
  `last_time` datetime DEFAULT NULL COMMENT '上次接收时间',
  `del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table mail_send_to
#

LOCK TABLES `mail_send_to` WRITE;
/*!40000 ALTER TABLE `mail_send_to` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_send_to` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
