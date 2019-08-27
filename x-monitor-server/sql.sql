-- ----------------------------
--  Table structure for `METRIC`
-- ----------------------------
DROP TABLE IF EXISTS `METRIC`;
CREATE TABLE `METRIC` (
  `MAIN_KEY` varchar(32) NOT NULL,
  `SUB_KEY` varchar(32) DEFAULT NULL,
  `VALUE` varchar(1024) DEFAULT NULL,
  `OBJ_TYPE` varchar(4) DEFAULT NULL,
  `OBJ_NO` varchar(12) DEFAULT NULL,
  `MAIN_TYPE` varchar(8) DEFAULT NULL,
  `MAIN_TYPE_EXT` varchar(8) DEFAULT NULL,
  `SUB_TYPE` varchar(8) DEFAULT NULL,
  `VALUE_TYPE` varchar(4) DEFAULT NULL,
  `COLLECT_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
