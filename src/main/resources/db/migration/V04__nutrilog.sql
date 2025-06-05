create sequence if not exists nutrition_seq;

create table if not exists nutrition_log (
    nutrition_id bigint primary key not null,
    user_id bigint not null,
    nutrition_type varchar(10) not null ,
    log_date date not null,
    food varchar (50) not null,
    calories double precision not null,
    protein double precision,
    carbohydrate double precision,
    fat double precision
);