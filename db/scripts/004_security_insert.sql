INSERT INTO authorities (authority) values ('ROLE_USER');
INSERT INTO authorities (authority) values ('ROLE_ADMIN');

INSERT INTO users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$8eG.HP.WlwTdIfjtyQTtTudQs1vSi9bvAE9vjxOYyPqEyCH/QEV6a',
        (select id from authorities where authority = 'ROLE_ADMIN'));

