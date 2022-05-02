CREATE TABLE top_posts
(
  `id` int auto_increment not null,
  `post_id` int,
  `sort` int,
  `is_hidden` tinyint(2) default 0,
  `created_at` datetime,
  `updated_at` datetime,
  `deleted_at` datetime,
  primary key (`id`)
) COLLATE = 'utf8mb4_general_ci';