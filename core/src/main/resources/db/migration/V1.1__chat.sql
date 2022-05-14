create table if not exists chat
(
    chat_id      uuid
        constraint chat_pk
            primary key,
    tg_chat_id   VARCHAR(50),
    ping_list    text,
    tg_chat_name varchar(255)
);
