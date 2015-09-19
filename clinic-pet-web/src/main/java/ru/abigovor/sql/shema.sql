table role - описывает роли пользователей
create table roles(
	id serial primary key,
	name_of_role varchar(200) not null UNIQUE,
	permission varchar(200)
);


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

create table pet (
	uid serial primary key,
	nick varchar(200) not null,
	sex char (1) not null,
	birthday date,
	client_id int not null references client(uid)
);


insert into role (name_of_role,permission) values('user', 'do');

insert into client (first_name,second_name,password,sex,role) values('user_name', 'second_name', 'qwe', 'm',1);

insert into pet (nick,sex,client_id) values('pet_nick', 'f',1);

select distinct role.name_of_role as role,
	client.first_name as name,
	client.second_name as surname,
	client.sex as sex,
	pet.sex
	from role
inner join client as client on client.sex = 'f'
inner join pet as pet on pet.sex = 'm';