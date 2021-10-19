CREATE TABLE users
(
    `id`              int auto_increment NOT NULL,
    `nick_name`       varchar(100),
    `password`        varchar(255),
    `email`           varchar(255),
    `sign_last_ip`    varchar(255),
    `sign_current_ip` varchar(255),
    `locked`          tinyint(1) default 0,
    `enabled`         tinyint(1) default 1,
    `created_time`    datetime,
    `updated_time`    datetime,
    `deleted_time`    datetime,
    primary key (`id`)
) COLLATE = 'utf8mb4_general_ci';