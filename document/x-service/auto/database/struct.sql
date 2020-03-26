-- ----------------------------
--  Table structure for `auto_database`
-- ----------------------------
DROP TABLE IF EXISTS `auto_database`;
CREATE TABLE `auto_database` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100),
  `desc` varchar(200),
  `status` varchar(2),
  `create_user` varchar(32),
  `update_user` varchar(32),
  `create_time` datetime,
  `update_time` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auto_table`
-- ----------------------------
DROP TABLE IF EXISTS `auto_table`;
CREATE TABLE `auto_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100),
  `desc` varchar(200),
  `status` varchar(2),
  `database_id` int,
  `database_name` varchar(100),
  `create_user` varchar(32),
  `update_user` varchar(32),
  `create_time` datetime,
  `update_time` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auto_column`
-- ----------------------------
DROP TABLE IF EXISTS `auto_column`;
CREATE TABLE `auto_column` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100),
  `desc` varchar(200),
  `status` varchar(2),
  `table_id` int,
  `table_name` varchar(100),
  `type` varchar(20),
  `length` int,
  `decimals` int,
  `required` varchar(1),
  `key` varchar(1),
  `auto_increase` varchar(1),
  `create_user` varchar(32),
  `update_user` varchar(32),
  `create_time` datetime,
  `update_time` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;