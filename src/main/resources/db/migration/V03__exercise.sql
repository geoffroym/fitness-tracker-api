create sequence if not exists exercise_seq;

create table if not exists exercise(
    exercise_id bigint primary key not null,
    workout_id bigint not null,
    exercise_name varchar(100) not null,
    sets int not null,
    reps int,
    weight_kg double precision,
    type varchar(100) not null
);