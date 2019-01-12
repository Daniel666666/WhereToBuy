/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/1/2 16:59:32                            */
/*==============================================================*/


drop table if exists admin;

drop table if exists china;

drop table if exists collection;

drop table if exists comment;

drop table if exists goods;

drop table if exists goods_type;

drop table if exists seller;

drop table if exists user;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   id                   int not null comment '管理员id',
   name                 varchar(20) comment '管理员名称',
   nick_name            varchar(20) comment '管理员别名',
   password             varchar(50) comment '管理员密码',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: china                                                 */
/*==============================================================*/
create table china
(
   id                   int comment '地区id',
   name                 varchar(20) comment '地区名称',
   parent_id            int comment '父级id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: collection                                            */
/*==============================================================*/
create table collection
(
   id                   int not null comment '收藏id',
   user_id              int comment '用户id',
   type                 varchar(1) comment '收藏类型(1:商家 2:商品)',
   seller_goods_id      int comment '商家或商品id',
   create_time          datetime comment '收藏时间',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   id                   int not null comment '评论id',
   goods_id             varchar(200) comment '评论内容',
   user_id              int comment '评论用户id',
   score                float comment '评论商品评分',
   time                 datetime comment '评论时间',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   id                   int not null comment '商品id',
   name                 varchar(50) comment '商品名称',
   seller_id            int comment '商家id',
   price                float comment '商品价格',
   introduction         varchar(200) comment '商品描述',
   images               varchar(500) comment '商品图片',
   type_1_id            int comment '商品类型一级id',
   type_2_id            int comment '商品类型二级id',
   create_time          datetime comment '商品创建时间',
   update_time          datetime comment '商品修改时间',
   status               varchar(1) comment '商品状态(0:未审核 1:已审核 2:审核未通过 3:已上架 4:已下架)',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: goods_type                                            */
/*==============================================================*/
create table goods_type
(
   id                   int not null comment '类型id',
   name                 varchar(20) comment '类型名称',
   parent_id            int comment '父分类id',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: seller                                                */
/*==============================================================*/
create table seller
(
   id                   int not null comment '商家id',
   name                 varchar(20) comment '商家名称',
   nick_name            varchar(20) comment '商家别名',
   password             varchar(50) comment '商家密码',
   phone                varchar(11) comment '商家手机号',
   email                varchar(50) comment '商家邮箱',
   address              varchar(80) comment '商家地址',
   latitude             varchar(20) comment '商家位置纬度',
   longitude            varchar(20) comment '商家位置经度',
   china_3              int comment '商家县级id',
   china_2              int comment '商家市级id',
   china_1              int comment '商家省级id',
   images               varchar(3000) comment '商家图片',
   status               varchar(1) comment '商家状态(0:未审核 1:已审核 2:审核未通过)',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null comment '用户id',
   name                 varchar(20) comment '用户名称',
   nick_name            varchar(20) comment '用户别名',
   password             varchar(50) comment '用户密码',
   phone                varchar(11) comment '用户手机号',
   email                varchar(50) comment '用户邮箱',
   avatar               varchar(200) comment '用户头像',
   sex                  varchar(1) comment '用户性别(0: 女1:男)',
   status               varchar(1) comment '用户状态(0:不可用 1:可用)',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

