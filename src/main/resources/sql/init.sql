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
CREATE TABLE IF NOT EXISTS `button`
(
    `id`          varchar(255)                                                  NOT NULL,
    `template_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `position`    int(11)                                                       NOT NULL,
    `compType`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'button',
    `compIcon`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `showLabel`   int(11)                                                       NOT NULL,
    `ele`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `child`       varchar(255)                                                  NOT NULL,
    `size`        varchar(255)                                                  NOT NULL,
    `type`        varchar(255)                                                  NOT NULL,
    `compAlign`   varchar(255)                                                  NOT NULL,
    `plain`       int(11)                                                       NOT NULL,
    `round`       int(11)                                                       NOT NULL,
    `circle`      int(11)                                                       NOT NULL,
    `disabled`    int(11)                                                       NOT NULL,
    `icon`        varchar(255)                                                  NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for comp_value
-- ----------------------------
CREATE TABLE IF NOT EXISTS `comp_value`
(
    `form_id`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `component_id` varchar(255)                                                  NOT NULL,
    `value`        longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NOT NULL,
    `compType`     varchar(255)                                                  NOT NULL,
    PRIMARY KEY (`form_id`, `component_id`, `compType`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for divider
-- ----------------------------
CREATE TABLE IF NOT EXISTS `divider`
(
    `id`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `template_id`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `position`         int(11)                                                       NOT NULL,
    `compType`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'divider',
    `ele`              varchar(255)                                                  NOT NULL,
    `content_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `child`            varchar(255)                                                  NOT NULL,
    `showLabel`        int(11)                                                       NOT NULL,
    `compIcon`         varchar(255)                                                  NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for form
-- ----------------------------
CREATE TABLE IF NOT EXISTS `form`
(
    `id`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `template_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `submit_time` datetime                                                      NOT NULL,
    `detail`      varchar(255)                                                  NOT NULL,
    `name`        varchar(255)                                                  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for input
-- ----------------------------
CREATE TABLE IF NOT EXISTS `input`
(
    `id`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `template_id` varchar(255)                                                  NOT NULL,
    `position`    int(11)                                                       NOT NULL,
    `compType`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'input',
    `ele`         varchar(255)                                                  NOT NULL,
    `compIcon`    varchar(255)                                                  NOT NULL,
    `showLabel`   int(11)                                                       NOT NULL,
    `type`        varchar(255)                                                  NOT NULL,
    `title`       varchar(255)                                                  NOT NULL,
    `disabled`    int(11)                                                       NOT NULL,
    `placeholder` varchar(255)                                                  NOT NULL,
    `clearable`   int(11)                                                       NOT NULL,
    `required`    int(11)                                                       NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for selector
-- ----------------------------
CREATE TABLE IF NOT EXISTS `selector`
(
    `id`          varchar(255)                                                  NOT NULL,
    `template_id` varchar(255)                                                  NOT NULL,
    `position`    int(11)                                                       NOT NULL,
    `compType`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'select',
    `ele`         varchar(255)                                                  NOT NULL,
    `title`       varchar(255)                                                  NOT NULL,
    `compIcon`    varchar(255)                                                  NOT NULL,
    `showLabel`   int(11)                                                       NOT NULL,
    `multiple`    int(11)                                                       NOT NULL,
    `disabled`    int(11)                                                       NOT NULL,
    `size`        varchar(255)                                                  NOT NULL,
    `clearable`   int(11)                                                       NOT NULL,
    `placeholder` varchar(255)                                                  NOT NULL,
    `required`    int(11)                                                       NOT NULL,
    `child`       varchar(255)                                                  NOT NULL,
    `childIndex`  int(11)                                                       NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for slider
-- ----------------------------
CREATE TABLE IF NOT EXISTS `slider`
(
    `id`          varchar(255)                                                  NOT NULL,
    `template_id` varchar(255)                                                  NOT NULL,
    `position`    int(11)                                                       NOT NULL,
    `compType`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'slider',
    `ele`         varchar(255)                                                  NOT NULL,
    `title`       varchar(255)                                                  NOT NULL,
    `compIcon`    varchar(255)                                                  NOT NULL,
    `showLabel`   int(11)                                                       NOT NULL,
    `min`         int(11)                                                       NOT NULL,
    `max`         int(11)                                                       NOT NULL,
    `disabled`    int(11)                                                       NOT NULL,
    `step`        int(11)                                                       NOT NULL,
    `show_stop`   int(11)                                                       NOT NULL,
    `required`    int(11)                                                       NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for switch
-- ----------------------------
CREATE TABLE IF NOT EXISTS `switch`
(
    `id`             varchar(255)                                                  NOT NULL,
    `template_id`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `position`       int(11)                                                       NOT NULL,
    `compType`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'switch',
    `ele`            varchar(255)                                                  NOT NULL,
    `title`          varchar(255)                                                  NOT NULL,
    `disabled`       int(11)                                                       NOT NULL,
    `compIcon`       varchar(255)                                                  NOT NULL,
    `compAlign`      varchar(255)                                                  NOT NULL,
    `width`          varchar(255)                                                  NOT NULL,
    `active_text`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `inactive_text`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `active_color`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `inactive_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `showLabel`      int(11)                                                       NOT NULL,
    `required`       int(11)                                                       NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for template
-- ----------------------------
CREATE TABLE IF NOT EXISTS `template`
(
    `id`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `name`        varchar(255)                                                  NOT NULL,
    `update_time` datetime                                                      NOT NULL,
    `components`  varchar(255)                                                  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for textarea
-- ----------------------------
CREATE TABLE IF NOT EXISTS `textarea`
(
    `id`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `template_id` varchar(255)                                                  NOT NULL,
    `position`    int(11)                                                       NOT NULL,
    `compType`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'textarea',
    `ele`         varchar(255)                                                  NOT NULL,
    `compIcon`    varchar(255)                                                  NOT NULL,
    `type`        varchar(255)                                                  NOT NULL,
    `showLabel`   int(11)                                                       NOT NULL,
    `title`       varchar(255)                                                  NOT NULL,
    `row_s`       int(11)                                                       NOT NULL,
    `disabled`    int(11)                                                       NOT NULL,
    `required`    int(11)                                                       NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for date
-- ----------------------------
CREATE TABLE IF NOT EXISTS `date`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(255) NOT NULL,
    `position`    int(11)      NOT NULL,
    `compType`    varchar(255) NOT NULL DEFAULT 'date',
    `ele`         varchar(255) NOT NULL,
    `compIcon`    varchar(255) NOT NULL,
    `title`       varchar(255) NOT NULL,
    `showLabel`   int(11)      NOT NULL,
    `required`    int(11)      NOT NULL,
    `readonly`    int(11)      NOT NULL,
    `disabled`    int(11)      NOT NULL,
    `editable`    int(11)      NOT NULL,
    `clearable`   int(11)      NOT NULL,
    `size`        varchar(255) NOT NULL,
    `type`        varchar(255) NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for inputNumber
--

CREATE TABLE IF NOT EXISTS `input_number`
(
    `id`                varchar(255) NOT NULL,
    `template_id`       varchar(255) NOT NULL,
    `position`          int(11)      NOT NULL,
    `compType`          varchar(255) NOT NULL DEFAULT 'inputNumber',
    `compIcon`          varchar(255) NOT NULL,
    `ele`               varchar(255) NOT NULL,
    `title`             varchar(255) NOT NULL,
    `showLabel`         int(11)      NOT NULL,
    `min`               int(11)      NOT NULL,
    `max`               int(11)      NOT NULL,
    `step`              int(11)      NOT NULL,
    `size`              varchar(255) NOT NULL,
    `disabled`          int(11)      NOT NULL,
    `accuracy`          int(11)      NOT NULL,
    `controls`          int(11)      NOT NULL,
    `controls_position` varchar(255) NOT NULL,
    `required`          int(11)      NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for link
-- ----------------------------
CREATE TABLE IF NOT EXISTS `link`
(
    `id`          varchar(255)                                                  NOT NULL,
    `template_id` varchar(255)                                                  NOT NULL,
    `position`    int(11)                                                       NOT NULL,
    `compType`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'link',
    `compIcon`    varchar(255)                                                  NOT NULL,
    `compAlign`   varchar(255)                                                  NOT NULL,
    `ele`         varchar(255)                                                  NOT NULL,
    `showLabel`   int(11)                                                       NOT NULL,
    `child`       varchar(255)                                                  NOT NULL,
    `type`        varchar(255)                                                  NOT NULL,
    `underline`   int(11)                                                       NOT NULL,
    `disabled`    int(11)                                                       NOT NULL,
    `href`        varchar(255)                                                  NOT NULL,
    `icon`        varchar(255)                                                  NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for rate
-- ----------------------------
CREATE TABLE IF NOT EXISTS `rate`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(255) NOT NULL,
    `position`    int(11)      NOT NULL,
    `compType`    varchar(255) NOT NULL DEFAULT 'rate',
    `compIcon`    varchar(255) NOT NULL,
    `ele`         varchar(255) NOT NULL,
    `title`       varchar(255) NOT NULL,
    `showLabel`   int(11)      NOT NULL,
    `required`    int(11)      NOT NULL,
    `max`         int(11)      NOT NULL,
    `disabled`    int(11)      NOT NULL,
    `allow_half`  int(11)      NOT NULL,
    `show_score`  int(11)      NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for time
-- ----------------------------
CREATE TABLE IF NOT EXISTS `time`
(
    `id`                varchar(255)                                                  NOT NULL,
    `template_id`       varchar(255)                                                  NOT NULL,
    `compType`          varchar(255)                                                  NOT NULL DEFAULT 'time',
    `position`          int(11)                                                       NOT NULL,
    `compIcon`          varchar(255)                                                  NOT NULL,
    `ele`               varchar(255)                                                  NOT NULL,
    `title`             varchar(255)                                                  NOT NULL,
    `showLabel`         int(11)                                                       NOT NULL,
    `required`          int(11)                                                       NOT NULL,
    `readonly`          int(11)                                                       NOT NULL,
    `disabled`          int(11)                                                       NOT NULL,
    `editable`          int(11)                                                       NOT NULL,
    `is_range`          int(11)                                                       NOT NULL,
    `clearable`         int(11)                                                       NOT NULL,
    `range_separator`   varchar(255)                                                  NOT NULL,
    `start_placeholder` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `end_placeholder`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `placeholder`       varchar(255)                                                  NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for checkbox
-- ----------------------------
CREATE TABLE IF NOT EXISTS `checkbox`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(255) NOT NULL,
    `position`    int(11)      NOT NULL,
    `compType`    varchar(255) NOT NULL DEFAULT 'checkbox',
    `compIcon`    varchar(255) NOT NULL,
    `ele`         varchar(255) NOT NULL,
    `title`       varchar(255) NOT NULL,
    `showLabel`   int(11)      NOT NULL,
    `required`    int(11)      NOT NULL,
    `disabled`    int(11)      NOT NULL,
    `size`        varchar(255) NOT NULL,
    `min`         int(11)      NOT NULL,
    `max`         int(11)      NOT NULL,
    `text_color`  varchar(255) NOT NULL,
    `fill`        varchar(255) NOT NULL,
    `childIndex`  int(11)      NOT NULL,
    `child`       varchar(255) NOT NULL,
    `childAttr`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for radio
-- ----------------------------
CREATE TABLE IF NOT EXISTS `radio`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(255) NOT NULL,
    `position`    int(11)      NOT NULL,
    `compType`    varchar(255) NOT NULL DEFAULT 'radio',
    `compIcon`    varchar(255) NOT NULL,
    `ele`         varchar(255) NOT NULL,
    `title`       varchar(255) NOT NULL,
    `showLabel`   int(11)      NOT NULL,
    `required`    int(11)      NOT NULL,
    `disabled`    int(11)      NOT NULL,
    `size`        varchar(255) NOT NULL,
    `text_color`  varchar(255) NOT NULL,
    `fill`        varchar(255) NOT NULL,
    `child`       varchar(255) NOT NULL,
    `childIndex`  int(11)      NOT NULL,
    `childAttr`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for editor
-- ----------------------------
CREATE TABLE IF NOT EXISTS `editor`
(
    `id`               varchar(255) NOT NULL,
    `template_id`      varchar(255) NOT NULL,
    `position`         int(11)      NOT NULL,
    `compType`         varchar(255) NOT NULL DEFAULT 'editor',
    `ele`              varchar(255) NOT NULL,
    `title`            varchar(255) NOT NULL,
    `showLabel`        int(11)      NOT NULL,
    `required`         int(11)      NOT NULL,
    `inspect`          int(11)      NOT NULL,
    `compIcon`         varchar(255) NOT NULL,
    `defaultMaxLength` int(11)      NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for collapse
-- ----------------------------
CREATE TABLE IF NOT EXISTS `collapse`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(255) NOT NULL,
    `position`    int(11)      NOT NULL,
    `compType`    varchar(255) NOT NULL DEFAULT 'collapse',
    `compIcon`    varchar(255) NOT NULL,
    `ele`         varchar(255) NOT NULL,
    `showLabel`   int(11)      NOT NULL,
    `accordion`   int(11)      NOT NULL,
    `child`       longtext     NOT NULL,
    `childIndex`  int(11)      NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for color_picker
-- ----------------------------
CREATE TABLE IF NOT EXISTS `color_picker`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(255) NOT NULL,
    `position`    int(11)      NOT NULL,
    `compType`    varchar(255) NOT NULL DEFAULT 'colorpicker',
    `compIcon`    varchar(255) NOT NULL,
    `ele`         varchar(255) NOT NULL,
    `title`       varchar(255) NOT NULL,
    `showLabel`   int(11)      NOT NULL,
    `required`    int(11)      NOT NULL,
    `disabled`    int(11)      NOT NULL,
    `size`        varchar(255) NOT NULL,
    `show_alpha`  int(11)      NOT NULL,
    `predefine`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for qrcode
-- ----------------------------
CREATE TABLE IF NOT EXISTS `qrcode`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(255) NOT NULL,
    `position`    int(11)      NOT NULL,
    `compType`    varchar(255) NOT NULL DEFAULT 'qrcode',
    `compIcon`    varchar(255) NOT NULL,
    `compAlign`   varchar(255) NOT NULL,
    `ele`         varchar(255) NOT NULL,
    `title`       varchar(255) NOT NULL,
    `showLabel`   int(11)      NOT NULL,
    `text`        varchar(255) NOT NULL,
    `size`        int(11)      NOT NULL,
    `colorDark`   varchar(255) NOT NULL,
    `colorLight`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for barcode
-- ----------------------------
CREATE TABLE IF NOT EXISTS `barcode`
(
    `id`           varchar(255) NOT NULL,
    `template_id`  varchar(255) NOT NULL,
    `position`     int(11)      NOT NULL,
    `compType`     varchar(255) NOT NULL DEFAULT 'barcode',
    `compIcon`     varchar(255) NOT NULL,
    `compAlign`    varchar(255) NOT NULL,
    `ele`          varchar(255) NOT NULL,
    `showLabel`    int(11)      NOT NULL,
    `title`        varchar(255) NOT NULL,
    `lineColor`    varchar(255) NOT NULL,
    `background`   varchar(255) NOT NULL,
    `barWidth`     int(11)      NOT NULL,
    `barHeight`    int(11)      NOT NULL,
    `displayValue` int(11)      NOT NULL,
    `fontSize`     int(11)      NOT NULL,
    `text`         varchar(255) NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for text
-- ----------------------------
CREATE TABLE IF NOT EXISTS `text`
(
    `id`          varchar(255) NOT NULL,
    `template_id` varchar(255) NOT NULL,
    `position`    int(11)      NOT NULL,
    `compType`    varchar(255) NOT NULL DEFAULT 'text',
    `compIcon`    varchar(255) NOT NULL,
    `showLabel`   int(11)      NOT NULL,
    `ele`         varchar(255) NOT NULL,
    `align`       varchar(255) NOT NULL,
    `color`       varchar(255) NOT NULL,
    `size`        int(11)      NOT NULL,
    `bold`        int(11)      NOT NULL,
    `text`        longtext     NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for upload
-- ----------------------------
CREATE TABLE IF NOT EXISTS `upload`
(
    `id`           varchar(255) NOT NULL,
    `template_id`  varchar(255) NOT NULL,
    `position`     int(11)      NOT NULL,
    `compType`     varchar(255) NOT NULL DEFAULT 'upload',
    `compIcon`     varchar(255) NOT NULL,
    `ele`          varchar(255) NOT NULL,
    `disabled`     int(11)      NOT NULL,
    `restrictions` int(11)      NOT NULL,
    `showTips`     int(11)      NOT NULL,
    `tips`         varchar(255) NOT NULL,
    `listStyle`    varchar(255) NOT NULL,
    `acceptType`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`, `template_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;