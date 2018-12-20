SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for amuse_book_info
-- ----------------------------
DROP TABLE IF EXISTS `amuse_book_info`;
CREATE TABLE `amuse_book_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'excel主文件名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for amuse_column_info
-- ----------------------------
DROP TABLE IF EXISTS `amuse_column_info`;
CREATE TABLE `amuse_column_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `col_name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '字段名称',
  `col_comment` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '字段中文注释',
  `col_type` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '字段类型',
  `col_order` int(11) NOT NULL COMMENT '字段序号，影响在excel中的先后顺序',
  `sheet_id` int(64) NOT NULL COMMENT '字段所属sheet的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for amuse_sheet_info
-- ----------------------------
DROP TABLE IF EXISTS `amuse_sheet_info`;
CREATE TABLE `amuse_sheet_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sheet_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sheet名称',
  `sheet_order` int(11) DEFAULT NULL COMMENT 'sheet序号',
  `book_id` int(11) DEFAULT NULL COMMENT '所属excel book id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;

-- ---------------------------------------------
alter table amuse_sheet_info add data_table varchar(64) comment '数据来源';
alter table amuse_column_info add col_size int(11) comment '字段长度';
alter table amuse_column_info add null_tag int(11) not null COMMENT '是否为空，0：不能为空，1：可以为空';