insert into types (name) values ('Парковка'), ('Столкновение'), ('Наезд');

insert into rule (name) values ('Статья1'), ('Статья2'), ('Статья3');

insert into accident (type_id, name, text, address) values (1, 'Перегорожен проезд', 'Прегорожен проезд из-за неправильной парковки', 'ул. Солдата Воскобойникова, 16');

insert into accident_rule (accident_id, rule_id) values (1, 1), (1, 2);