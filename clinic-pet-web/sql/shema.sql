-- table role for users
create table roles(
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
	client_id int not null references client(uid)
);


-- insert role
INSERT INTO role (name_of_role,permission) VALUES('user', 'permission');

-- insert client
INSERT INTO client (first_name,second_name,password,sex,role) VALUES('user_name', 'second_name', 'password', 'm',1);

-- insert pet
INSERT INTO pet (nick,sex,client_id) VALUES('pet_nick', 'f',1);

-- select clients with pet
SELECT * FROM client AS cl
LEFT JOIN pet AS pet ON pet.client_id = cl.uid;

-- select client by name
select * from client as cl
	left join pet as pet on pet.client_id = cl.uid
	where  cl.first_name ilike 'alan';

-- select client with pet
SELECT * FROM client AS cl
LEFT JOIN pet AS pet ON pet.client_id = cl.uid
WHERE cl.uid = '70';


-- delete all pets of client
DELETE FROM pet WHERE client_id = 70;

-- delete client
DELETE FROM client WHERE uid = 1;


	-- add column in the table
ALTER TABLE pet
ADD COLUMN type_of_pet varchar(50) NOT NULL DEFAULT 'default';

-- delete default value
ALTER TABLE pet
ALTER COLUMN type_of_pet DROP DEFAULT;