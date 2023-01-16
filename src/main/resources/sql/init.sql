/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : formdesigner

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2023-01-16 20:03:25
*/

-- ----------------------------
-- Table structure for button
-- ----------------------------
CREATE TABLE IF NOT EXISTS `button` (
  `id` varchar(255) NOT NULL,
  `template_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `position` int(11) NOT NULL,
  `compType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'button',
  `compIcon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `showLabel` int(11) NOT NULL,
  `ele` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `child` varchar(255) NOT NULL,
  `size` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `plain` int(11) NOT NULL,
  `round` int(11) NOT NULL,
  `circle` int(11) NOT NULL,
  `disabled` int(11) NOT NULL,
  `icon` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for comp_value
-- ----------------------------
CREATE TABLE IF NOT EXISTS `comp_value` (
  `form_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `component_id` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `compType` varchar(255) NOT NULL,
  PRIMARY KEY (`form_id`,`component_id`,`compType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for divider
-- ----------------------------
CREATE TABLE IF NOT EXISTS `divider` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `template_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `position` int(11) NOT NULL,
  `compType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'divider',
  `ele` varchar(255) NOT NULL,
  `content_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `child` varchar(255) NOT NULL,
  `showLabel` int(11) NOT NULL,
  `compIcon` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for form
-- ----------------------------
CREATE TABLE IF NOT EXISTS `form` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `template_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `submit_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for input
-- ----------------------------
CREATE TABLE IF NOT EXISTS `input` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `template_id` varchar(255) NOT NULL,
  `position` int(11) NOT NULL,
  `compType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'input',
  `ele` varchar(255) NOT NULL,
  `compIcon` varchar(255) NOT NULL,
  `showLabel` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `disabled` int(11) NOT NULL,
  `placeholder` varchar(255) NOT NULL,
  `clearable` int(11) NOT NULL,
  `required` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for selector
-- ----------------------------
CREATE TABLE IF NOT EXISTS `selector` (
  `id` varchar(255) NOT NULL,
  `template_id` varchar(255) NOT NULL,
  `position` int(11) NOT NULL,
  `compType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'select',
  `ele` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `compIcon` varchar(255) NOT NULL,
  `showLabel` int(11) NOT NULL,
  `multiple` int(11) NOT NULL,
  `disabled` int(11) NOT NULL,
  `size` varchar(255) NOT NULL,
  `clearable` int(11) NOT NULL,
  `placeholder` varchar(255) NOT NULL,
  `required` int(11) NOT NULL,
  `child` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for slider
-- ----------------------------
CREATE TABLE IF NOT EXISTS `slider` (
  `id` varchar(255) NOT NULL,
  `template_id` varchar(255) NOT NULL,
  `position` int(11) NOT NULL,
  `compType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'slider',
  `ele` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `compIcon` varchar(255) NOT NULL,
  `showLabel` int(11) NOT NULL,
  `min` int(11) NOT NULL,
  `max` int(11) NOT NULL,
  `disabled` int(11) NOT NULL,
  `step` int(11) NOT NULL,
  `show_stop` int(11) NOT NULL,
  `required` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for switch
-- ----------------------------
CREATE TABLE IF NOT EXISTS `switch` (
  `id` varchar(255) NOT NULL,
  `template_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `position` int(11) NOT NULL,
  `compType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'switch',
  `ele` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `disabled` int(11) NOT NULL,
  `compIcon` varchar(255) NOT NULL,
  `width` varchar(255) NOT NULL,
  `active_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `inactive_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `active_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `inactive_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `showLabel` int(11) NOT NULL,
  `required` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for template
-- ----------------------------
CREATE TABLE IF NOT EXISTS `template` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) NOT NULL,
  `update_time` datetime NOT NULL,
  `components` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for textarea
-- ----------------------------
CREATE TABLE IF NOT EXISTS `textarea` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `template_id` varchar(255) NOT NULL,
  `position` int(11) NOT NULL,
  `compType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'textarea',
  `ele` varchar(255) NOT NULL,
  `compIcon` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `showLabel` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `row_s` int(11) NOT NULL,
  `disabled` int(11) NOT NULL,
  `required` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
