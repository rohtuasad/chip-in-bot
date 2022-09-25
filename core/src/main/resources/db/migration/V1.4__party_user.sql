create table if not exists party_user
(
    party_id uuid
        constraint party_user_party_fk
            references party (party_id),
    user_id  bigint
        constraint party_user_user_fk
            references users (user_tg_id)
);