
insert into filter_currency (id, name,name_decoding) values ( 1,'USD','Доллар США');
insert into filter_currency (id, name,name_decoding) values ( 2,'EUR','Евро');
insert into filter_currency (id, name,name_decoding) values ( 3,'JPY','Японских иен');
insert into filter_currency (id, name,name_decoding) values ( 4,'GBP','Фунт стерлингов Соединенного королевства');


insert into CURRENCY (currency_date,currency_type,currency_value) values
            ( PARSEDATETIME('26.10.2022, 05:15:58 AM','dd.MM.yyyy, hh:mm:ss a','en'),'GBP',59.13);

