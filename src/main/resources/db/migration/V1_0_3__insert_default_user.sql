insert into users(nick_name, email, password, created_at, updated_at)
values ('lucian', '1024462231@qq.com', '{bcrypt}$2a$10$YC0K4xD7Ca/EjN49m5Lj1.gn.bse69VDh7w8y939gwbUT8DwjlJuS', now(),
        now());
insert into roles(name, zh_name, created_at, updated_at)
values ('ROLE_admin', '管理员', now(), now());
insert into user_roles(user_id, role_id, created_at, updated_at)
values (1, 1, now(), now());