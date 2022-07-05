create table if not exists party
(
    party_id   uuid
        constraint party_pk
            primary key,
    is_active  boolean default true,
    chat_id    bigint
        constraint party_chat_fk
            references chat (tg_chat_id),
    party_name varchar(50)
);