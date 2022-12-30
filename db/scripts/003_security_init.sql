CREATE TABLE if not exists authorities (
    id serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE if not exists users (
  id serial primary key,
  username VARCHAR(50) NOT NULL unique,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  authority_id int not null references authorities(id)
);

INSERT INTO authorities (authority) values ('ROLE_USER');
INSERT INTO authorities (authority) values ('ROLE_ADMIN');

INSERT INTO users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$8eG.HP.WlwTdIfjtyQTtTudQs1vSi9bvAE9vjxOYyPqEyCH/QEV6a',
        (select id from authorities where authority = 'ROLE_ADMIN'));

