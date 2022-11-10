insert into user_security (id, user_security,password_security,role_security ) values ( 1,'admin','admin','ROLE_ADMIN');
insert into user_security (id, user_security,password_security,role_security ) values ( 2,'staff','staff','ROLE_STAFF');
insert into user_security (id, user_security,password_security,role_security ) values ( 3,'user1','user1','ROLE_USER');
insert into user_security (id, user_security,password_security,role_security ) values ( 4,'user2','user2','ROLE_USER');

insert into user_deposit (user_id, deposit) values ( 1, 100);
insert into user_deposit (user_id, deposit) values ( 2, 5800.30);
insert into user_deposit (user_id, deposit) values ( 3, 58000.10);
insert into user_deposit (user_id, deposit) values ( 4, 100000);

insert into currency_exchange (id, user_deposit,currency_type,currency_deposit) values ( 1, 2,'USD',500.10);
insert into currency_exchange (id, user_deposit,currency_type,currency_deposit) values ( 2, 2,'GPB',30.20);
insert into currency_exchange (id, user_deposit,currency_type,currency_deposit) values ( 3, 3,'EUR',50.20);
insert into currency_exchange (id, user_deposit,currency_type,currency_deposit) values ( 4, 3,'USD',1000);