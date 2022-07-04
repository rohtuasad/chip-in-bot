create table if not exists chat
(
    tg_chat_id   VARCHAR(50)
        constraint chat_pk
            primary key,
    tg_chat_name varchar(255)
);
