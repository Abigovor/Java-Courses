-- table role for users
create table role(
	id serial primary key,
	name_of_role varchar(200) not null UNIQUE,
	permission varchar(200)
);

-- create table client
create table client (
	uid serial primary key,
	first_name varchar(200) not null,
	second_name varchar(200) not null,
	password varchar(200) not null,
	sex char (1) not null,
	email varchar(200),
	birthday date,
	role int not null references role(id)
);

-- create table pet
create table pet (
	uid serial primary key,
	nick varchar(200) not null,
	sex char (1) not null,
	birthday date,
	type_of_pet varchar(50) NOT NULL,
	client_id int not null references client(uid)
);

INSERT INTO role(name_of_role, permission) VALUES('admin', 'all');