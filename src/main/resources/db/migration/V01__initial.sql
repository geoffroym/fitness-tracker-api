create sequence if not exists user_seq;

create table if not exists users(
    user_id bigint primary key,
    name varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(100) not null,
    date_of_birth date not null,
    height double precision not null,
    weight double precision not null,
    goal varchar(50) not null
);
