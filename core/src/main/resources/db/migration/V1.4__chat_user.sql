create table if not exists chat_user
(
    chat_id        uuid
        constraint chat_user_chat_fk
            references chat (chat_id),
    user_id        uuid
        constraint chat_user_user_fk
            references users (user_id)
);