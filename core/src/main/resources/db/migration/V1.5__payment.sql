create table if not exists payment
(
    payment_id   uuid
    constraint payment_pk
    primary key,
    party_id        uuid
    constraint payment_party_fk
    references party (party_id),
    user_id        bigint
    constraint payment_user_fk
    references tg_user (user_tg_id),
    amount real,
    description  varchar(500)
);