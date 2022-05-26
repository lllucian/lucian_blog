create table posts
(
    `id`          varchar(255) not null,
    `title`       varchar(255),
    `description` varchar(255),
    `content`     text,
    `view_count`  int,
    `likes`       int,
    `sort`        int,
    `user_id`     varchar(255),
    `upload_file_id` varchar(255),
    `created_at`  datetime,
    `updated_at`  datetime,
    `deleted_at`  datetime,
    primary key (`id`)
) collate = "utf8mb4_general_ci";

create table categories
(
    `id`          varchar(255) not null,
    `sort`        int,
    `name`        varchar(100),
    `slug`        varchar(20),
    `description` text,
    `parent_id`   varchar(255),
    `created_at`  datetime,
    `updated_at`  datetime,
    `deleted_at`  datetime,
    primary key (`id`)
) collate = "utf8mb4_general_ci";

create table tags
(
    `id`          varchar(255) not null,
    `sort`        int,
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
    `id`          varchar(255) not null,
    `post_id`     varchar(255),
    `category_id` varchar(255),
    `sort`        int,
    `created_at`  datetime,
    `updated_at`  datetime,
    `deleted_at`  datetime,
    primary key (`id`)
) collate = "utf8mb4_general_ci";

create table post_tags
(
    `id`         varchar(255) not null,
    `post_id`    varchar(255),
    `tag_id`     varchar(255),
    `sort`       int,
    `created_at` datetime,
    `updated_at` datetime,
    `deleted_at` datetime,
    primary key (`id`)
) collate = "utf8mb4_general_ci";
