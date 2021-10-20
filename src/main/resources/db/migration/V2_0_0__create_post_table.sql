create table posts
(
    `id`          int not null auto_increment,
    `title`       varchar(255),
    `description` varchar(255),
    `content`     text,
    `view_count`  int,
    `likes`       int,
    `sort`        int,
    `user_id`     int,
    `created_at`  datetime,
    `updated_at`  datetime,
    `deleted_at`  datetime,
    primary key (`id`)
) collate = "utf8mb4_general_ci";

create table categories
(
    `id`          int not null auto_increment,
    `name`        varchar(100),
    `slug`        varchar(20),
    `description` text,
    `parent_id`   int,
    `created_at`  datetime,
    `updated_at`  datetime,
    `deleted_at`  datetime,
    primary key (`id`)
) collate = "utf8mb4_general_ci";

create table tags
(
    `id`          int not null auto_increment,
    `name`        varchar(20),
    `slug`        varchar(20),
    `description` text,
    `created_at`  datetime,
    `updated_at`  datetime,
    `deleted_at`  datetime,
    primary key (`id`)
) collate = "utf8mb4_general_ci";

create table post_categories
(
    `id`          int not null auto_increment,
    `post_id`     int,
    `category_id` int,
    `sort`        int,
    `created_at`  datetime,
    `updated_at`  datetime,
    `deleted_at`  datetime,
    primary key (`id`)
) collate = "utf8mb4_general_ci";

create table post_tags
(
    `id`         int not null auto_increment,
    `post_id`    int,
    `tag_id`     int,
    `sort`       int,
    `created_at` datetime,
    `updated_at` datetime,
    `deleted_at` datetime,
    primary key (`id`)
) collate = "utf8mb4_general_ci";
