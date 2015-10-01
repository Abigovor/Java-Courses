-- table role for users
CREATE TABLE role(
	id serial PRIMARY KEY,
	name_of_role VARCHAR(200) NOT NULL UNIQUE,
	permission VARCHAR(200)
);

-- create table client
CREATE TABLE client (
	uid SERIAL PRIMARY KEY,
	first_name VARCHAR(200) NOT NULL,
	second_name VARCHAR(200) NOT NULL,
	password VARCHAR(200) NOT NULL,
	sex CHAR (1) NOT NULL,
	email VARCHAR (200) UNIQUE NOT NULL,
	birthday DATE,
	role INT NOT NULL REFERENCES role(id)
);

-- create table pet
CREATE TABLE pet (
	uid serial PRIMARY KEY,
	nick VARCHAR (200) NOT NULL,
	sex CHAR (1) NOT NULL,
	birthday DATE,
	type_of_pet varchar(50) NOT NULL,
	client_id INT NOT NULL REFERENCES client(uid)
);

INSERT INTO role(name_of_role, permission) VALUES('admin', 'all');


	-- add column in the table
-- ALTER TABLE pet
-- ADD COLUMN type_of_pet varchar(50) NOT NULL DEFAULT 'default';

-- delete default value
-- ALTER TABLE pet
-- ALTER COLUMN type_of_pet DROP DEFAULT;

--ALTER TABLE client ALTER COLUMN email SET NOT NULL;
--ALTER TABLE client ADD UNIQUE (email);
-- то же самое
--ALTER TABLE client ADD UNIQUE (email), ALTER COLUMN email SET NOT NULL;