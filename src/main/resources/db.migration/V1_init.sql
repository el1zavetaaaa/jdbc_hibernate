DROP SCHEMA IF EXISTS public cascade ;

CREATE SCHEMA public;
-- SET search_path TO myschema,demo;

create table fruits(
    id serial primary key ,
    name varchar not null unique

);

insert into fruits (id,name) values (default,'apple'),(default,'grape');
