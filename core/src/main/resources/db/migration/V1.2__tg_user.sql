create table if not exists tg_user
(
    user_tg_id bigint
        constraint user_pk
            primary key,
    nick_name  varchar(50),
    user_name  varchar(50)
);
