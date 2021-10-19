CREATE TABLE roles
(
    `id` int not null auto_increment,
    `name` varchar(255),
    `zh_name` varchar(255),
    `locked` tinyint(1),
    `created_at` datetime,
    `updated_at` datetime,
    `deleted_at` datetime,
    primary key (`id`)
) COLLATE = 'utf8mb4_general_ci';

CREATE TABLE user_roles
(
    `id` int not null auto_increment,
    `user_id` int,
    `role_id` int,
    `created_at` datetime,
    `updated_at` datetime,
    `deleted_at` datetime,
    primary key (`id`)
) COLLATE = 'utf8mb4_general_ci';