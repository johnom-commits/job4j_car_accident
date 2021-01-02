CREATE TABLE accidents (
  id serial primary key,
  name varchar(2000) NOT NULL,
  text varchar(2000),
  address varchar(200),
  type_id int references accident_type(id)
);

CREATE TABLE accident_type (
 id serial primary key,
 name varchar(500) UNIQUE NOT NULL
);

CREATE TABLE rules (
 id serial primary key,
 name varchar(500) UNIQUE NOT NULL
);

CREATE TABLE accidents_rules (
acc_id int4 NOT NULL,
rule_id int4 NOT NULL,
PRIMARY KEY (acc_id, rule_id)
);

INSERT INTO accident_type (name) VALUES ('Две машины');
INSERT INTO accident_type (name) VALUES ('Машина и человек');
INSERT INTO accident_type (name) VALUES ('Машина и велосипед');

INSERT INTO rules (name) VALUES ('Статья. 1');
INSERT INTO rules (name) VALUES ('Статья. 2');
INSERT INTO rules (name) VALUES ('Статья. 3');



