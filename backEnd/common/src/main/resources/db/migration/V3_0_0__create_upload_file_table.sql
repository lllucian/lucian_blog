CREATE TABLE `upload_files` (
                                `id` varchar(255) NOT NULL,
                                `user_id` varchar(255) DEFAULT NULL,
                                `controller_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                                `file_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                                `ext_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                                `bucket_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                                `created_at` datetime DEFAULT NULL,
                                `updated_at` datetime DEFAULT NULL,
                                `deleted_at` datetime DEFAULT NULL,
                                `real_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                index user_id (`user_id`),
                                index deleted_at (`deleted_at`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;