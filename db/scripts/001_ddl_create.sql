CREATE TABLE if not exists types (
                          id serial primary key,
                          name varchar(255)
);

CREATE TABLE if not exists rule (
                      id serial primary key,
                      name varchar(255)
);

CREATE TABLE if not exists accident (
                        id serial primary key,
                        type_id int references types(id),
                        name varchar(255),
                        text varchar(2000),
                        address varchar(255)
);

CREATE TABLE if not exists accident_rule (
                               accident_id int references accident(id),
                               rule_id int references rule(id)
);