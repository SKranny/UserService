create sequence hibernate_sequence start 1 increment 1;
create table person (
id int4 not null,
 about varchar(2048),
 birth_day date,
 confirmation_code int4,
 town varchar(255),
 e_mail varchar(255),
 first_name varchar(255),
 is_approved boolean,
 is_blocked boolean,
 last_name varchar(255),
 last_online_time timestamp,
 messages_permission int4,
 password varchar(255),
 phone varchar(255),
 photo varchar(255),
 reg_date date,
 role int4,
 primary key (id)
 );