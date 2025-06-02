create sequence if not exists workout_seq;

create table if not exists workout(
    workout_id bigint primary key not null,
    user_id bigint not null,
    workout_date date not null,
    duration_minutes int not null
);

