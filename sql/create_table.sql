create database small;

use small;

create table small.data
(
    time     varchar(128) null,
    mileage  varchar(128) null,
    type     varchar(128) null,
    id       int null,
    dataId   int null,
    username varchar(128) null,
    location varchar(128) null,
    bytes    varchar(128) null,
    password varchar(128) null
);

create table small.friend
(
    fromId   int null,
    toId     int null,
    username varchar(128) null,
    message  varchar(128) null,
    sex      varchar(51) null
);

create table small.img
(
    id          varchar(256) null comment 'id',
    imgLocation varchar(512) null comment '图片'
) comment '图片' collate = utf8mb4_unicode_ci;

create table small.post
(
    id         bigint auto_increment comment 'id'
        primary key,
    title      varchar(512) null comment '标题',
    content    text null comment '内容',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '帖子' collate = utf8mb4_unicode_ci;

create index idx_userId
    on small.post (userId);

create table small.post_comment
(
    id         bigint auto_increment comment 'id'
        primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    content    text null comment '评论内容',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '帖子评论' collate = utf8mb4_unicode_ci;

create index idx_userId
    on small.post_comment (id);

create table small.post_favour
(
    id         bigint auto_increment comment 'id'
        primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '帖子收藏';

create index idx_postId
    on small.post_favour (postId);

create index idx_userId
    on small.post_favour (userId);

create table small.post_thumb
(
    id         bigint auto_increment comment 'id'
        primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '帖子点赞';

create index idx_postId
    on small.post_thumb (postId);

create index idx_userId
    on small.post_thumb (userId);

create table small.user
(
    id       bigint auto_increment comment 'id'
        primary key,
    username varchar(256) null comment '用户昵称',
    phone   varchar(12) null comment '手机号',
    emergencyNumber  varchar(12) null comment '紧急联系人',
    password varchar(512) not null comment '密码',
    sex      varchar(12) null comment '性别',
    bytes    varchar(512) null comment '人脸信息'
) comment '用户' collate = utf8mb4_unicode_ci;

