create table sys_logs
(
    id             int auto_increment not null,
    username       varchar(255),
    operation      varchar(255),
    time           bigint,
    method         varchar(255),
    request_params  text,
    response_result text,
    ip             varchar(255),
    address        varchar(255),
    browser        varchar(255),
    os             varchar(255),
    created_at     datetime,
    updated_at     datetime,
    deleted_at     datetime,
    primary key (id)
) collate utf8mb4_bin;