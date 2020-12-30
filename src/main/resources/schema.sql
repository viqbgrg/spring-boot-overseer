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
    id      bigint(20) not null auto_increment comment '账号主键',
    xunlei_id  bigint(20) not null comment '用户id',
    create_at timestamp    not null comment '用户创建时间',
    update_at timestamp comment '更新时间',
    PRIMARY KEY (id)
);

drop table if exists user_account;
create table user_account
(
    id      bigint(20) not null auto_increment comment '账号主键',
    user_id bigint(20) not null comment '用户id',
    xunlei_id  bigint(20) not null comment '用户id',
    create_at timestamp    not null comment '用户创建时间',
    update_at timestamp comment '更新时间',
    PRIMARY KEY (id)
)