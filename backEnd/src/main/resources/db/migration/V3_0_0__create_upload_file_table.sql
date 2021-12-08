create table `upload_files`
(
    `id`              int auto_increment not null,
    `user_id`         int,
    `controller_name` varchar(255),
    `file_name`       varchar(255),
    `ext_name`        varchar(255),
    `fdfs_group_name` varchar(255),
    `fdfs_file_path`  varchar(255),
    `created_at`      datetime,
    `updated_at`      datetime,
    `deleted_at`      datetime,
    primary key (`id`)
) collate utf8mb4_bin;