DROP SCHEMA IF EXISTS public cascade ;

CREATE SCHEMA public;
-- SET search_path TO myschema,demo;

create table customers(
    customer_id serial primary key ,
    customer_name varchar not null unique

);

insert into customers (customer_id,customer_name) values (default,'customer 1'),(default,'customer 2 ');

create table contacts (

    contact_id serial primary key,
    contact_name varchar not null unique ,
    customer_id integer references customers(customer_id)

);

insert into contacts (contact_id,contact_name) values (default,'contact 1'), (default,'contact 2')
