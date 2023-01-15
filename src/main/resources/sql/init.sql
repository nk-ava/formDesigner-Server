/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : form

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2023-01-14 21:06:57
*/

-- ----------------------------
-- Table structure for button
-- ----------------------------
CREATE TABLE IF NOT EXISTS `button`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(20)  NOT NULL,
    `position`    int(11)      NOT NULL,
    `type`        varchar(255) NOT NULL DEFAULT 'button',
    `width`       varchar(255) NOT NULL,
    `label`       varchar(255) NOT NULL,
    `text`        varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for comp_value
-- ----------------------------
CREATE TABLE IF NOT EXISTS `comp_value`
(
    `form_id`      varchar(20)  NOT NULL,
    `component_id` varchar(255) NOT NULL,
    `value`        varchar(255) NOT NULL,
    `compType`     varchar(255) NOT NULL,
    PRIMARY KEY (`form_id`, `component_id`, `compType`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for form
-- ----------------------------
CREATE TABLE IF NOT EXISTS `form`
(
    `id`          varchar(20) NOT NULL,
    `template_id` varchar(20) NOT NULL,
    `submit_time` datetime    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for switch
-- ----------------------------
CREATE TABLE IF NOT EXISTS `switch`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(20)  NOT NULL,
    `position`    int(11)      NOT NULL,
    `type`        varchar(255) NOT NULL DEFAULT 'switch',
    `width`       varchar(255) NOT NULL,
    `height`      varchar(255) NOT NULL,
    `label`       varchar(255) NOT NULL,
    `color`       varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for template
-- ----------------------------
CREATE TABLE IF NOT EXISTS `template`
(
    `id`          varchar(20)  NOT NULL,
    `name`        varchar(255) NOT NULL,
    `update_time` datetime     NOT NULL,
    `components`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
