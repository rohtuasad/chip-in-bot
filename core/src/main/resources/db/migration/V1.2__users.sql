create table if not exists users
(
    user_id    uuid
        constraint user_pk
            primary key,
    user_tg_id bigint
        constraint user_tg_id_uk
            unique,
    nick_name  varchar(50),
    user_name  varchar(50)
);