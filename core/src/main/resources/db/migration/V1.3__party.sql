create table if not exists party
(
    party_id    uuid
        constraint party_pk
            primary key,
    chat_id        uuid
        constraint party_chat_fk
            references chat (tg_chat_id),
    party_name  varchar(50)
);