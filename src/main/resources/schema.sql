DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id        BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    username  VARCHAR(30)  NULL DEFAULT NULL COMMENT '姓名',
    email     VARCHAR(50)  NULL DEFAULT NULL COMMENT '邮箱',
    password  varchar(100) NOT NULL COMMENT '密码',
    locked    tinyint(1)   not null comment '用户是否锁定',
    create_at timestamp    not null comment '用户创建时间',
    update_at timestamp comment '更新时间',
    PRIMARY KEY (id)
);


drop table if exists account;
create table account
(
    id         bigint(20)   not null auto_increment comment '账号主键',
    username   varchar(20)  not null comment '登陆账号',
    password   varchar(50)  not null comment '登陆密码',
    user_i_d    varchar(20)  not null comment '账号id',
    nick_name  varchar(20)  not null comment '账号昵称',
    login_key  varchar(105) not null comment '再次登陆的key',
    session_i_d varchar(105) not null comment '再次登陆的key',
    creditkey  varchar(135) not null,
    secure_key varchar(40)  not null,
    create_at  timestamp    not null comment '用户创建时间',
    update_at  timestamp comment '更新时间',
    PRIMARY KEY (id)
);

drop table if exists user_account;
CREATE TABLE `user_account` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账号主键',
    `user_id` bigint(20) NOT NULL COMMENT '用户id',
    `account_id` bigint(20) NOT NULL COMMENT '用户id',
    `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户创建时间',
    `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
    PRIMARY KEY (`id`)
);

drop table if exists account_data;
create table account_data
(
    id         bigint(20)   not null auto_increment comment '账号主键',
    mine_info   json  not null comment '账号信息',
    device_info   json  not null comment '账号信息',
    balance_info   json  not null comment '账号信息',
    produce_stat   json  not null comment '账号信息',
    update_at  timestamp comment '更新时间',
    PRIMARY KEY (id)
);