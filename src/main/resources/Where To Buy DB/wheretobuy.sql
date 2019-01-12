/*
Navicat MySQL Data Transfer

Source Server         : wheretobuy_remote
Source Server Version : 50721
Source Host           : 39.108.70.119:33306
Source Database       : wheretobuy

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-01-12 15:58:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `name` varchar(20) DEFAULT NULL COMMENT '管理员名称',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '管理员别名',
  `password` varchar(50) DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'daniel', 'daniel', 'daniel');
INSERT INTO `admin` VALUES ('2', 'zhangyiming', 'zhangyiming', 'zhangyiming');
INSERT INTO `admin` VALUES ('3', 'zhoukefan', 'zhoukefan', 'zhoukefan');
INSERT INTO `admin` VALUES ('4', 'lufengjiao', 'lufengjiao', 'lufengjiao');
INSERT INTO `admin` VALUES ('5', 'hexiaolian', 'hexiaolian', 'hexiaolian');
INSERT INTO `admin` VALUES ('6', 'linshuixin', 'linshuixin', 'linshuixin');
INSERT INTO `admin` VALUES ('7', 'admin', 'admin', 'admin');

-- ----------------------------
-- Table structure for china
-- ----------------------------
DROP TABLE IF EXISTS `china`;
CREATE TABLE `china` (
  `id` int(11) DEFAULT NULL COMMENT '地区id',
  `name` varchar(20) DEFAULT NULL COMMENT '地区名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='中国省市县表';

-- ----------------------------
-- Records of china
-- ----------------------------

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `type` varchar(1) DEFAULT NULL COMMENT '收藏类型(1:商家 2:商品)',
  `seller_goods_id` int(11) DEFAULT NULL COMMENT '商家或商品id',
  `create_time` datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏';

-- ----------------------------
-- Records of collection
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` varchar(200) DEFAULT NULL COMMENT '评论内容',
  `goods_id` varchar(200) DEFAULT NULL COMMENT '评论商品id',
  `user_id` int(11) DEFAULT NULL COMMENT '评论用户id',
  `score` float DEFAULT NULL COMMENT '评论商品评分',
  `time` datetime DEFAULT NULL COMMENT '评论时间',
  `parent_id` int(11) DEFAULT NULL COMMENT '评论父评论id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='评论';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '非常好，值得去买哦！', '1', '1', '10', '1970-01-01 08:00:00', '0');
INSERT INTO `comment` VALUES ('2', '非常好吃，下次再来！', '1', '12', '8', '2019-01-11 15:14:14', '0');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `seller_id` int(11) DEFAULT NULL COMMENT '商家id',
  `price` float DEFAULT NULL COMMENT '商品价格',
  `introduction` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `images` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `type_1_id` int(11) DEFAULT NULL COMMENT '商品类型一级id',
  `type_2_id` int(11) DEFAULT NULL COMMENT '商品类型二级id',
  `create_time` datetime DEFAULT NULL COMMENT '商品创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '商品修改时间',
  `status` varchar(1) DEFAULT NULL COMMENT '商品状态(0:未审核 1:已审核 2:审核未通过 3:已上架 4:已下架)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '商品1', '19', '1', '1', 'http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg', '17', '22', '2019-01-04 19:00:16', '2019-01-04 19:08:16', '3');
INSERT INTO `goods` VALUES ('2', '商品2', '19', '2', '2', 'http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg', '18', '20', '2019-01-04 19:00:29', '2019-01-04 23:23:39', '3');
INSERT INTO `goods` VALUES ('3', '商品3', '22', '3', '3', 'http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg', '19', '21', '2019-01-04 19:00:44', null, '3');
INSERT INTO `goods` VALUES ('4', '商品4', '23', '4', '4', 'http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg', '18', '21', '2019-01-04 19:00:54', null, '3');
INSERT INTO `goods` VALUES ('5', '商品5', '23', '5', '5', 'http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpghttp://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg;http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg', '18', '21', '2019-01-04 19:01:05', null, '3');
INSERT INTO `goods` VALUES ('14', '1234', '28', '1234', '2134', null, null, null, '2019-01-11 07:14:36', null, null);
INSERT INTO `goods` VALUES ('15', '1234', '28', '1234', '1234', 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4QuOANiJfAAAC5wJlX4Y220.png', null, null, '2019-01-11 07:16:52', null, null);
INSERT INTO `goods` VALUES ('16', 'goods12', '19', null, null, null, null, null, '2019-01-11 07:48:14', null, '0');
INSERT INTO `goods` VALUES ('18', '1234', '28', '1234', '1234', 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4TW-AfAv2AABIBI7HgY0193.png', null, null, '2019-01-11 08:01:52', null, '0');
INSERT INTO `goods` VALUES ('19', '1234', '28', '1234', '2134', 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4TauAa1OUAABIBI7HgY0940.png', null, null, '2019-01-11 08:02:52', null, '0');
INSERT INTO `goods` VALUES ('20', '43312', '28', '1234', '1234', 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4Te2AaZJGAABIBI7HgY0992.png', null, null, '2019-01-11 08:03:58', null, '0');
INSERT INTO `goods` VALUES ('21', null, '19', null, null, null, null, null, null, null, null);
INSERT INTO `goods` VALUES ('22', null, '19', null, null, null, null, null, null, null, null);
INSERT INTO `goods` VALUES ('23', null, '19', null, null, null, null, null, null, null, null);
INSERT INTO `goods` VALUES ('24', null, '19', null, null, null, null, null, null, null, null);
INSERT INTO `goods` VALUES ('25', null, '19', null, null, null, null, null, null, null, null);
INSERT INTO `goods` VALUES ('26', null, '19', null, null, null, null, null, null, null, null);
INSERT INTO `goods` VALUES ('27', null, '19', null, null, null, null, null, null, null, null);
INSERT INTO `goods` VALUES ('28', '小米10', '19', '1299', '很好的手机，值得购买', 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4U3mAEeBCAABN-KN051432.jfif', null, null, '2019-01-11 08:27:38', null, '1');
INSERT INTO `goods` VALUES ('29', '小米11', '19', '1399', '小米手机', 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4U7KAPZtGAABN-KN051421.jfif', null, null, '2019-01-11 08:28:35', null, '1');
INSERT INTO `goods` VALUES ('30', null, '29', null, null, 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4VAOAJ9EnAAFawv0MA18762.jpg', null, null, '2019-01-11 08:29:56', null, '0');

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `name` varchar(20) DEFAULT NULL COMMENT '类型名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8 COMMENT='商品类型';

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES ('17', '女装', '0');
INSERT INTO `goods_type` VALUES ('18', '上装', '17');
INSERT INTO `goods_type` VALUES ('19', '裤装', '17');
INSERT INTO `goods_type` VALUES ('20', '男装', '0');
INSERT INTO `goods_type` VALUES ('21', '卫衣', '20');
INSERT INTO `goods_type` VALUES ('22', '休闲裤', '20');
INSERT INTO `goods_type` VALUES ('23', '男鞋', '0');
INSERT INTO `goods_type` VALUES ('24', '女鞋', '0');
INSERT INTO `goods_type` VALUES ('25', '箱包手袋', '0');
INSERT INTO `goods_type` VALUES ('26', '美妆护肤', '0');
INSERT INTO `goods_type` VALUES ('27', '个护清洁', '0');
INSERT INTO `goods_type` VALUES ('28', '钟表珠宝', '0');
INSERT INTO `goods_type` VALUES ('29', '手机数码', '0');
INSERT INTO `goods_type` VALUES ('30', '电脑办公', '0');
INSERT INTO `goods_type` VALUES ('31', '家用电器', '0');
INSERT INTO `goods_type` VALUES ('32', '食品生鲜', '0');
INSERT INTO `goods_type` VALUES ('33', '酒水饮料', '0');
INSERT INTO `goods_type` VALUES ('34', '母婴童装', '0');
INSERT INTO `goods_type` VALUES ('35', '玩具乐器', '0');
INSERT INTO `goods_type` VALUES ('36', '医药保健', '0');
INSERT INTO `goods_type` VALUES ('37', '家居厨具', '0');
INSERT INTO `goods_type` VALUES ('38', '外套', '17');
INSERT INTO `goods_type` VALUES ('39', '裙装', '17');
INSERT INTO `goods_type` VALUES ('40', '童装', '17');
INSERT INTO `goods_type` VALUES ('41', '毛衣', '17');
INSERT INTO `goods_type` VALUES ('42', '男士外套', '20');
INSERT INTO `goods_type` VALUES ('43', '衬衫', '20');
INSERT INTO `goods_type` VALUES ('44', '牛仔裤', '20');
INSERT INTO `goods_type` VALUES ('45', 'T恤', '20');
INSERT INTO `goods_type` VALUES ('46', '休闲皮鞋', '23');
INSERT INTO `goods_type` VALUES ('47', '商务休闲鞋', '23');
INSERT INTO `goods_type` VALUES ('48', '板鞋', '23');
INSERT INTO `goods_type` VALUES ('49', '帆布鞋', '23');
INSERT INTO `goods_type` VALUES ('50', '运动休闲鞋', '23');
INSERT INTO `goods_type` VALUES ('51', '豆豆鞋', '23');
INSERT INTO `goods_type` VALUES ('52', '凉鞋', '23');
INSERT INTO `goods_type` VALUES ('53', '拖鞋', '23');
INSERT INTO `goods_type` VALUES ('54', '正装鞋', '23');
INSERT INTO `goods_type` VALUES ('55', '单鞋', '24');
INSERT INTO `goods_type` VALUES ('56', '休闲鞋', '24');
INSERT INTO `goods_type` VALUES ('57', '高跟鞋', '24');
INSERT INTO `goods_type` VALUES ('58', '帆布鞋', '24');
INSERT INTO `goods_type` VALUES ('59', '凉鞋', '24');
INSERT INTO `goods_type` VALUES ('60', '皮鞋', '24');
INSERT INTO `goods_type` VALUES ('61', '板鞋', '24');
INSERT INTO `goods_type` VALUES ('62', '小白鞋', '24');
INSERT INTO `goods_type` VALUES ('63', '豆豆鞋', '24');
INSERT INTO `goods_type` VALUES ('64', '拉杆箱', '25');
INSERT INTO `goods_type` VALUES ('65', '单肩包', '25');
INSERT INTO `goods_type` VALUES ('66', '电脑包', '25');
INSERT INTO `goods_type` VALUES ('67', '双肩包', '25');
INSERT INTO `goods_type` VALUES ('68', '男双肩包', '25');
INSERT INTO `goods_type` VALUES ('69', '单间斜挎包', '25');
INSERT INTO `goods_type` VALUES ('70', '男士钱包', '25');
INSERT INTO `goods_type` VALUES ('71', '书包', '25');
INSERT INTO `goods_type` VALUES ('72', '手提包', '25');
INSERT INTO `goods_type` VALUES ('73', '胸包腰包', '25');
INSERT INTO `goods_type` VALUES ('74', '护肤礼盒', '26');
INSERT INTO `goods_type` VALUES ('75', '深层清洁', '26');
INSERT INTO `goods_type` VALUES ('76', '敏感肌', '26');
INSERT INTO `goods_type` VALUES ('77', '卸妆', '26');
INSERT INTO `goods_type` VALUES ('78', '洁面', '26');
INSERT INTO `goods_type` VALUES ('79', '爽肤水', '26');
INSERT INTO `goods_type` VALUES ('80', '乳液/面霜', '26');
INSERT INTO `goods_type` VALUES ('81', '精华/肌底液', '26');
INSERT INTO `goods_type` VALUES ('82', '眼霜', '26');
INSERT INTO `goods_type` VALUES ('83', '睡眠面膜', '26');
INSERT INTO `goods_type` VALUES ('84', '面膜', '26');
INSERT INTO `goods_type` VALUES ('85', '唇膜', '26');
INSERT INTO `goods_type` VALUES ('86', '喷雾', '26');
INSERT INTO `goods_type` VALUES ('87', '眼膜', '26');
INSERT INTO `goods_type` VALUES ('88', '护手霜', '27');
INSERT INTO `goods_type` VALUES ('89', '驱蚊用品', '27');
INSERT INTO `goods_type` VALUES ('90', '走珠/止汗露', '27');
INSERT INTO `goods_type` VALUES ('91', '免洗洗手液', '27');
INSERT INTO `goods_type` VALUES ('92', '漱口水', '27');
INSERT INTO `goods_type` VALUES ('93', '皮具护理', '27');
INSERT INTO `goods_type` VALUES ('94', '卫生棉条', '27');
INSERT INTO `goods_type` VALUES ('95', '湿厕纸', '27');
INSERT INTO `goods_type` VALUES ('96', '瑞士表', '28');
INSERT INTO `goods_type` VALUES ('97', '智能手表', '28');
INSERT INTO `goods_type` VALUES ('98', '闹钟', '28');
INSERT INTO `goods_type` VALUES ('99', '挂钟', '28');
INSERT INTO `goods_type` VALUES ('100', '座钟', '28');
INSERT INTO `goods_type` VALUES ('101', '德表', '28');
INSERT INTO `goods_type` VALUES ('102', '项链', '28');
INSERT INTO `goods_type` VALUES ('103', '手链/脚链', '28');
INSERT INTO `goods_type` VALUES ('104', '耳饰', '28');
INSERT INTO `goods_type` VALUES ('105', '胸针', '28');
INSERT INTO `goods_type` VALUES ('106', '发饰', '28');
INSERT INTO `goods_type` VALUES ('107', '苹果', '29');
INSERT INTO `goods_type` VALUES ('108', '华为', '29');
INSERT INTO `goods_type` VALUES ('109', '小米', '29');
INSERT INTO `goods_type` VALUES ('110', '一加手机', '29');
INSERT INTO `goods_type` VALUES ('111', 'vivo', '29');
INSERT INTO `goods_type` VALUES ('112', 'oppo', '29');
INSERT INTO `goods_type` VALUES ('113', '魅族手机', '29');
INSERT INTO `goods_type` VALUES ('114', '努比亚手机', '29');
INSERT INTO `goods_type` VALUES ('115', '三星', '29');
INSERT INTO `goods_type` VALUES ('116', '诺基亚', '29');
INSERT INTO `goods_type` VALUES ('117', '手机壳', '29');
INSERT INTO `goods_type` VALUES ('118', '数据线', '29');
INSERT INTO `goods_type` VALUES ('119', '手机贴膜', '29');
INSERT INTO `goods_type` VALUES ('120', '充电宝', '29');
INSERT INTO `goods_type` VALUES ('121', '充电器', '29');
INSERT INTO `goods_type` VALUES ('122', '耳机', '29');
INSERT INTO `goods_type` VALUES ('123', '手机存储卡', '29');
INSERT INTO `goods_type` VALUES ('124', '游戏本', '30');
INSERT INTO `goods_type` VALUES ('125', '轻薄本', '30');
INSERT INTO `goods_type` VALUES ('126', '游戏台式机', '30');
INSERT INTO `goods_type` VALUES ('127', '机械键盘', '30');
INSERT INTO `goods_type` VALUES ('128', '移动硬盘', '30');
INSERT INTO `goods_type` VALUES ('129', '曲面显示器', '30');
INSERT INTO `goods_type` VALUES ('130', '组装电脑', '30');
INSERT INTO `goods_type` VALUES ('131', '666', '17');

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `name` varchar(20) DEFAULT NULL COMMENT '商家名称',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '商家别名',
  `password` varchar(50) DEFAULT NULL COMMENT '商家密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '商家手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '商家邮箱',
  `address` varchar(80) DEFAULT NULL COMMENT '商家地址',
  `latitude` varchar(20) DEFAULT NULL COMMENT '商家位置纬度',
  `longitude` varchar(20) DEFAULT NULL COMMENT '商家位置经度',
  `china_3` int(11) DEFAULT NULL COMMENT '商家县级id',
  `china_2` int(11) DEFAULT NULL COMMENT '商家市级id',
  `china_1` int(11) DEFAULT NULL COMMENT '商家省级id',
  `images` varchar(3000) DEFAULT NULL COMMENT '商家图片',
  `status` varchar(1) DEFAULT NULL COMMENT '商家状态(0:未审核 1:已审核 2:审核未通过)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='商家';

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES ('19', 'daniel', 'CDTU旗舰店', 'aa47f8215c6f30a0dcdb2a36a9f4168e', null, '228845326@qq.com', '四川省成都市', null, null, null, null, null, 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4UDeAZTw8AAFawv0MA18496.jpg', '2');
INSERT INTO `seller` VALUES ('22', 'daniel520', null, '1c1243543d5ca83c68a0e2ccb1effd45', null, 'hxy_daniel520@163.com', null, null, null, null, null, null, null, '3');
INSERT INTO `seller` VALUES ('23', 'daniel521', null, 'ec9bbdb21829c594fd5082ee26f73b71', null, 'hxy_daniel520@163.com', null, null, null, null, null, null, null, '0');
INSERT INTO `seller` VALUES ('25', 'daniel5210', null, '74317052eae2d2f35173a4ea374e304e', null, 'hxy_daniel520@163.com', null, null, null, null, null, null, null, '0');
INSERT INTO `seller` VALUES ('28', 'ByZYM', 'ByZYM', 'aa47f8215c6f30a0dcdb2a36a9f4168e', '12312310', '838826997@qq.com', '1234100', null, null, null, null, null, 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4Vi2AYl-dAADoDiacWn0388.jpg', '2');
INSERT INTO `seller` VALUES ('29', 'aaa', 'aaa', '47bce5c74f589f4867dbd57e9ca9f808', 'aaa', '228845326@qq.com', 'aaa', null, null, null, null, null, 'http://39.108.70.119:8888/group1/M00/00/00/rBAPO1w4TLGALHLlAAAGFY9_z-o144.png', '2');
INSERT INTO `seller` VALUES ('30', '菜狗子', '菜逼', 'e10adc3949ba59abbe56e057f20f883e', '11111111111', '111111@qq.com', 'dizi', '132', '123', '132', '123', '213', '123', '0');
INSERT INTO `seller` VALUES ('31', '妞妞精品店', '妞妞', '9fab6755cd2e8817d3e73b0978ca54a6', '12584562874', '65165465@qq.com', '成都工业学院门口', '165', '785', '313', '6165', '35165', '', '0');
INSERT INTO `seller` VALUES ('32', '漫步者官方旗舰店', '漫步者', 'adc887a3a9ed325eee06e7fe45c4464f', '15627546535', 'shuixin1016@foxmail.com', '湖南长沙市河滨街九号', null, null, null, null, null, null, '0');
INSERT INTO `seller` VALUES ('33', '唐师官方旗舰店', '唐师', 'b7f04bb1d2eb76359e6f79aab8e075f2', '19865623455', 'shuixin1016@foxmail.com', '甘肃兰州西南街九号', null, null, null, null, null, null, '0');
INSERT INTO `seller` VALUES ('34', 'MG小象欧美街拍时尚女装', 'MG小象', '86cd90a5a9b3166fb18cd8e1ae8bd34e', '16583421656', 'shuixin1016@foxmail.com', '江苏宜兴九鼎街六号', null, null, null, null, null, null, '0');
INSERT INTO `seller` VALUES ('36', '18483657690', '何晓莲', '3c4124c5d29331cabfee2252b694856d', '18438657690', '809303894@qq.com', '何晓莲', null, null, null, null, null, null, '1');
INSERT INTO `seller` VALUES ('37', '18483657690', '何晓莲', '3c4124c5d29331cabfee2252b694856d', '18438657690', '809303894@qq.com', '何晓莲', null, null, null, null, null, null, '2');
INSERT INTO `seller` VALUES ('38', 'sonia', '123456', '96e79218965eb72c92a549dd5a330112', '18483607927', '156454@qq.com', '成都市工业学院', null, null, null, null, null, null, '0');
INSERT INTO `seller` VALUES ('39', 'helen', 'lanzi', '94f115823941e46f3492a6e491954e2a', '15881093100', 'shuixin1016@foxmail.com', '成都工业学院', null, null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '用户别名',
  `password` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `sex` varchar(1) DEFAULT NULL COMMENT '用户性别(0: 女1:男)',
  `status` varchar(1) DEFAULT NULL COMMENT '用户状态(0:不可用 1:可用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'daniel', 'daniel', 'aa47f8215c6f30a0dcdb2a36a9f4168e', '123', '123', 'image', '1', '1');
INSERT INTO `user` VALUES ('12', 'admin', null, '21232f297a57a5a743894a0e4a801fc3', null, '1054089446@qq.com', null, null, '0');
INSERT INTO `user` VALUES ('16', 'asd', null, '7815696ecbf1c96e6894b779456d330e', null, '1332488532@qq.com', null, null, '0');
INSERT INTO `user` VALUES ('17', 'aaa', null, '47bce5c74f589f4867dbd57e9ca9f808', null, '1054089446@qq.com', null, null, '1');
INSERT INTO `user` VALUES ('18', 'qaz', null, '4eae18cf9e54a0f62b44176d074cbe2f', null, '1332488532@qq.com', null, null, '1');
INSERT INTO `user` VALUES ('20', 'sonia', null, 'e10adc3949ba59abbe56e057f20f883e', null, '474135456@qq.com', null, null, '1');
INSERT INTO `user` VALUES ('21', '809303894', null, '59271584532fb49107b341a3281ab686', null, '809303894@qq.com', null, null, '1');
INSERT INTO `user` VALUES ('22', 'helen', null, '3fe4c917f62e537e040eb6424ef3d304', null, 'shuixin1016@foxmail.com', null, null, '0');
INSERT INTO `user` VALUES ('23', 'helloworld', null, 'fc5e038d38a57032085441e7fe7010b0', null, '853645715@qq.com', null, null, '1');
INSERT INTO `user` VALUES ('24', 'helloworld1', null, 'f0d6457cd9472e82d912302d7fc29bbd', null, '853645715@qq.com', null, null, '1');
