insert into users(id, username, email, password, created_at, updated_at)
values ('e78da465-cad4-4c56-98e8-2f026158429c', 'lucian', '1024462231@qq.com',
        '{bcrypt}$2a$10$YC0K4xD7Ca/EjN49m5Lj1.gn.bse69VDh7w8y939gwbUT8DwjlJuS', now(),
        now());
insert into roles(id, name, zh_name, created_at, updated_at)
values ('34b78bfc-617e-4e64-bad4-4b51316054a7', 'ROLE_admin', '管理员', now(), now());
insert into user_roles(id, user_id, role_id, created_at, updated_at)
values ('b94435c9-1a6f-4660-903d-f19a835ec313', 'e78da465-cad4-4c56-98e8-2f026158429c',
        '34b78bfc-617e-4e64-bad4-4b51316054a7', now(), now());