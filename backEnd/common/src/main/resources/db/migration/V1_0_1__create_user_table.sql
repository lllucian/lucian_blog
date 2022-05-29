CREATE TABLE users
(
    `id`              varchar(255) NOT NULL,
    `username`        varchar(100),
    `password`        varchar(255),
    `email`           varchar(255),
    `sign_last_ip`    varchar(255),
    `sign_current_ip` varchar(255),
    `locked`          tinyint(1) default 0,
    `enabled`         tinyint(1) default 1,
    `created_at`      datetime,
    `updated_at`      datetime,
    `deleted_at`      datetime,
    primary key (`id`),
    index deleted_at(`deleted_at`)
) COLLATE = 'utf8mb4_general_ci';