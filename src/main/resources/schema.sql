DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id        BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    username  VARCHAR(30)  NULL DEFAULT NULL COMMENT '姓名',
    email     VARCHAR(50)  NULL DEFAULT NULL COMMENT '邮箱',
    password  varchar(100) NOT NULL COMMENT '密码',
    locked    tinyint(1)        default 0 comment '用户是否锁定',
    create_at timestamp    not null comment '用户创建时间',
    update_at timestamp null DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    PRIMARY KEY (id)
);


drop table if exists account;
create table account
(
    password    varchar(50)  not null comment '登陆密码',
    user_i_d    BIGINT(20)   not null comment '账号id',
    user_name   varchar(20)  not null comment '登陆账号',
    nick_name   varchar(20)  not null comment '账号昵称',
    login_key   varchar(105) not null comment '再次登陆的key',
    session_i_d varchar(105) not null comment '再次登陆的key',
    creditkey   varchar(135) not null,
    secure_key  varchar(40)  not null,
    create_at   timestamp    not null comment '用户创建时间',
    update_at   timestamp null DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    PRIMARY KEY (user_i_d)
);

drop table if exists user_account;
CREATE TABLE user_account
(
    user_id   BIGINT(20) NOT NULL COMMENT '用户id',
    user_i_d  BIGINT(20) NOT NULL COMMENT '账户的用户id',
    create_at timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
    update_at timestamp null ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (user_id, user_i_d)
);

drop table if exists account_data;
create table account_data
(
    user_i_d     BIGINT(20) not null comment '账号id',
    mine_info    json       not null comment '账号信息',
    device_info  json       not null comment '账号信息',
    balance_info json       not null comment '账号信息',
    produce_stat json       not null comment '账号信息',
    privilege json       not null comment '账号信息',
    update_at    timestamp  null DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    PRIMARY KEY (user_i_d)
);

drop table if exists account_history;
create table account_history
(
    user_i_d     BIGINT(20) not null comment '账号id',
    day          date       not null comment '日期',
    pdc          int(6)     not null default 0 comment '水晶产量',
    box_pdc      int(6)     not null default 0 comment '宝箱',
    last_speed   int(6)     not null default 0 comment '速度',
    deploy_speed int(6)     not null default 0 comment '速度',
    balance      int(6)     not null default 0 comment '',
    income       int(6)     not null default 0 comment '',
    refreshes    int(6)     not null default 0 comment '',
    speed_stat   json       not null comment '',
    pdc_detail   json       not null comment '',
    produce_stat json       not null comment '',
    update_at    timestamp  null     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    primary key (user_i_d, day)
)