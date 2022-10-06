create table if not exists transfer
(
    transfer_id   uuid
        constraint transfer_pk
            primary key,
    party_id        uuid
        constraint transfer_party_fk
            references party (party_id),
    sender_id        bigint
        constraint transfer_sender_fk
            references tg_user (user_tg_id),
    receiver_id        bigint
        constraint transfer_receiver_fk
            references tg_user (user_tg_id),
    amount real
);
