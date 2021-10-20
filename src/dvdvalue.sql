/*
 Navicat Premium Data Transfer

 Source Server         : root8
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3308
 Source Schema         : dvd

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 20/10/2021 11:49:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dvd_dvd
-- ----------------------------
DROP TABLE IF EXISTS `dvd_dvd`;
CREATE TABLE `dvd_dvd`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dvdname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `len_count` int(0) NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `a` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `b` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dvd_dvd
-- ----------------------------
INSERT INTO `dvd_dvd` VALUES (1, '知识', 3, 3, 888, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (2, '对决', 2, 3, 12, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (3, '植物大战僵尸2', 3, 3, 123, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (4, '水浒传', 0, 0, 23, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (5, '圣斗士', 3, 0, 13, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (6, '植物大战僵尸', 3, 1, 88, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (7, '大理寺', 3, 0, 123, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (8, '电影', 3, 0, 111, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (9, '测试', 3, 0, 111, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (10, '大理寺', 3, 0, 123, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (11, '大理寺', 3, 0, 123, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (18, 'ceshi', 3, 0, 888, NULL, NULL);
INSERT INTO `dvd_dvd` VALUES (26, '电影2', 3, 0, 88, NULL, NULL);

-- ----------------------------
-- Table structure for dvd_lend
-- ----------------------------
DROP TABLE IF EXISTS `dvd_lend`;
CREATE TABLE `dvd_lend`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dvd_id` int(0) NULL DEFAULT NULL,
  `lend_time` bigint(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `a` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `b` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dvd_lend
-- ----------------------------
INSERT INTO `dvd_lend` VALUES (1, 1, 1634286758007, 1, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (2, 1, 1634265613846, 1, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (3, 1, 1634265620240, 0, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (4, 1, 1634265622331, 0, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (5, 1, 1634286758009, 0, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (6, 2, 1634467242640, 2, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (7, 2, 1634467349983, 2, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (8, 2, 1634468638093, 2, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (9, 1, 1634484451682, 2, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (10, 2, 1634484469167, 2, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (11, 1, 1634485001685, 3, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (12, 2, 1634485118371, 3, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (13, 1, 1634531187498, 2, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (14, 1, 1634531212492, 3, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (15, 1, 1634543560944, 2, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (16, 2, 1634543576544, 2, NULL, NULL);
INSERT INTO `dvd_lend` VALUES (17, 1, 1634543608703, 3, NULL, NULL);

-- ----------------------------
-- Table structure for dvd_log
-- ----------------------------
DROP TABLE IF EXISTS `dvd_log`;
CREATE TABLE `dvd_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `dvd_id` int(0) NULL DEFAULT NULL,
  `operate_time` bigint(0) NULL DEFAULT NULL,
  `operate_info` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `a` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `b` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dvd_log
-- ----------------------------
INSERT INTO `dvd_log` VALUES (1, 1, 0, 1634484400635, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (2, 1, 0, 1634484441584, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (3, 1, 0, 1634484445950, '查询未借出', NULL, NULL);
INSERT INTO `dvd_log` VALUES (4, 1, 1, 1634484451683, '借阅(已归还)', NULL, NULL);
INSERT INTO `dvd_log` VALUES (6, 1, 2, 1634484469168, '借阅(已归还)', NULL, NULL);
INSERT INTO `dvd_log` VALUES (8, 1, 0, 1634484475237, '查询已借出', NULL, NULL);
INSERT INTO `dvd_log` VALUES (9, 1, 0, 1634484477243, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (10, 1, 0, 1634484482509, '按借出数查询', NULL, NULL);
INSERT INTO `dvd_log` VALUES (11, 1, 0, 1634484488244, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (12, 1, 0, 1634484804016, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (13, 1, 18, 1634484819299, '添加', NULL, NULL);
INSERT INTO `dvd_log` VALUES (14, 1, 0, 1634484819305, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (15, 1, 18, 1634484843854, '修改', NULL, NULL);
INSERT INTO `dvd_log` VALUES (17, 1, 0, 1634484852932, '查询未借出', NULL, NULL);
INSERT INTO `dvd_log` VALUES (18, 1, 0, 1634484858288, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (19, 1, 1, 1634485001686, '归还', NULL, NULL);
INSERT INTO `dvd_log` VALUES (20, 1, 0, 1634485016939, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (21, 1, 0, 1634485020823, '查询已借出', NULL, NULL);
INSERT INTO `dvd_log` VALUES (22, 1, 0, 1634485025659, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (23, 1, 0, 1634485028081, '查询未借出', NULL, NULL);
INSERT INTO `dvd_log` VALUES (24, 1, 0, 1634485034126, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (25, 1, 2, 1634485118372, '归还', NULL, NULL);
INSERT INTO `dvd_log` VALUES (26, 1, 0, 1634485124304, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (27, 1, 0, 1634485126757, '查询未借出', NULL, NULL);
INSERT INTO `dvd_log` VALUES (28, 2, 0, 1634531176064, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (29, 2, 0, 1634531183021, '查询未借出', NULL, NULL);
INSERT INTO `dvd_log` VALUES (30, 2, 1, 1634531187499, '借阅(已归还)', NULL, NULL);
INSERT INTO `dvd_log` VALUES (32, 2, 1, 1634531212493, '归还', NULL, NULL);
INSERT INTO `dvd_log` VALUES (33, 2, 0, 1634543540116, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (34, 2, 1, 1634543560945, '借阅(已归还)', NULL, NULL);
INSERT INTO `dvd_log` VALUES (36, 2, 2, 1634543576544, '借阅', NULL, NULL);
INSERT INTO `dvd_log` VALUES (38, 2, 1, 1634543608703, '归还', NULL, NULL);
INSERT INTO `dvd_log` VALUES (39, 1, 0, 1634545776742, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (40, 1, 0, 1634545809425, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (41, 2, 0, 1634546681989, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (43, 1, 0, 1634546760675, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (45, 1, 0, 1634546937241, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (47, 1, 0, 1634547077522, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (49, 1, 0, 1634547226308, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (51, 1, 0, 1634547310044, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (53, 1, 0, 1634547363486, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (55, 1, 0, 1634547449374, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (56, 1, 26, 1634547458726, '添加', NULL, NULL);
INSERT INTO `dvd_log` VALUES (58, 1, 0, 1634547625341, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (59, 1, 0, 1634547633606, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (60, 1, 0, 1634548550320, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (61, 1, 0, 1634548555159, '按借出数查询', NULL, NULL);
INSERT INTO `dvd_log` VALUES (62, 1, 0, 1634548563544, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (63, 1, 0, 1634548580514, '按名查询', NULL, NULL);
INSERT INTO `dvd_log` VALUES (64, 1, 0, 1634548587540, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (65, 1, 0, 1634548597287, '查询已借出', NULL, NULL);
INSERT INTO `dvd_log` VALUES (66, 1, 0, 1634548599092, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (67, 1, 0, 1634548600976, '查询未借出', NULL, NULL);
INSERT INTO `dvd_log` VALUES (68, 1, 0, 1634548603819, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (69, 1, 0, 1634548606274, '查询已删除', NULL, NULL);
INSERT INTO `dvd_log` VALUES (70, 1, 0, 1634548607651, '查询所有', NULL, NULL);
INSERT INTO `dvd_log` VALUES (71, 1, 0, 1634549006779, '查询所有', NULL, NULL);

-- ----------------------------
-- Table structure for dvd_user
-- ----------------------------
DROP TABLE IF EXISTS `dvd_user`;
CREATE TABLE `dvd_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` bigint(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `a` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `b` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dvd_user
-- ----------------------------
INSERT INTO `dvd_user` VALUES (1, '张三', '111', 1634269251330, 1, NULL, NULL);
INSERT INTO `dvd_user` VALUES (2, '李四', '123', 1634269286983, 1, NULL, NULL);
INSERT INTO `dvd_user` VALUES (3, '王五', '123', 1634269286983, 1, NULL, NULL);
INSERT INTO `dvd_user` VALUES (4, '赵六', '123', 1634286297077, 1, NULL, NULL);
INSERT INTO `dvd_user` VALUES (6, 'qw', 'qw', 1634449487871, 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
