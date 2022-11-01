insert into FILTER_CURRENCY (id, name,name_decoding) values ( 1,'USD','Доллар США');
insert into FILTER_CURRENCY (id, name,name_decoding) values ( 2,'EUR','Евро');
insert into FILTER_CURRENCY (id, name,name_decoding) values ( 3,'JPY','Японских иен');
insert into FILTER_CURRENCY (id, name,name_decoding) values ( 4,'GBP','Фунт стерлингов Соединенного королевства');

insert into CURRENCY (currency_type, currency_date,currency_value) values ('USD', PARSEDATETIME('29.10.2022, 00:00:00 AM','dd.MM.yyyy, hh:mm:ss a','en'),61.53);
insert into CURRENCY (currency_type, currency_date,currency_value) values ('EUR', PARSEDATETIME('29.10.2022, 00:00:00 AM','dd.MM.yyyy, hh:mm:ss a','en'),61.13);
insert into CURRENCY (currency_type, currency_date,currency_value) values ('JPY', PARSEDATETIME('29.10.2022, 00:00:00 AM','dd.MM.yyyy, hh:mm:ss a','en'),41.97);
insert into CURRENCY (currency_type, currency_date,currency_value) values ('GBP', PARSEDATETIME('29.10.2022, 00:00:00 AM','dd.MM.yyyy, hh:mm:ss a','en'),71.26);
insert into CURRENCY (currency_type, currency_date,currency_value) values ('USD', PARSEDATETIME('01.11.2022, 00:00:00 AM','dd.MM.yyyy, hh:mm:ss a','en'),61.62);
insert into CURRENCY (currency_type, currency_date,currency_value) values ('EUR', PARSEDATETIME('01.11.2022, 00:00:00 AM','dd.MM.yyyy, hh:mm:ss a','en'),61.11);
insert into CURRENCY (currency_type, currency_date,currency_value) values ('JPY', PARSEDATETIME('01.11.2022, 00:00:00 AM','dd.MM.yyyy, hh:mm:ss a','en'),41.56);
insert into CURRENCY (currency_type, currency_date,currency_value) values ('GBP', PARSEDATETIME('01.11.2022, 00:00:00 AM','dd.MM.yyyy, hh:mm:ss a','en'),71.43);

