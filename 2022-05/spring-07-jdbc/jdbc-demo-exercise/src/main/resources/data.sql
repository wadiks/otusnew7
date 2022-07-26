
insert into genre (id, name)
values (1, 'Ужас');
insert into genre (id, name)
values (2, 'Детектив');
insert into genre (id, name)
values (3, 'Фантастика');
insert into genre (id, name)
values (4, 'Проза');
insert into genre (id, name)
values (5, 'Роман');


insert into authors (id, name, surname)
values (1, 'Джордж', 'Элиот');
insert into authors (id, name, surname)
values (2, 'Вирджиния', 'Вулф');
insert into authors (id, name, surname)
values (3, 'Чарльз', 'Бронте');
insert into authors (id, name, surname)
values (4, 'Шарлотта', 'Бронте');
insert into authors (id, name, surname)
values (5, 'Эмили', 'Бронте');
insert into authors (id, name, surname)
values (6, 'Мэри', 'Шелли');
insert into authors (id, name, surname)
values (7, 'Уильям', 'Теккерей');
insert into authors (id, name, surname)
values (8, 'Джейн', 'Остин');
insert into authors (id, name, surname)
values (9, 'Лев', 'Толстой');


insert into books (id, name, genre_id, authors_id)
values (NEXT VALUE FOR SEQ_ID, 'Мидлмарч', 2, 1);
insert into books (id, name, genre_id, authors_id)
values (NEXT VALUE FOR SEQ_ID, 'На маяк', 4, 2);
insert into books (id, name, genre_id, authors_id)
values (NEXT VALUE FOR SEQ_ID, 'Большие надежды', 4, 3);
insert into books (id, name, genre_id, authors_id)
values (NEXT VALUE FOR SEQ_ID, 'Джейн Эйр', 2, 4);
insert into books (id, name, genre_id, authors_id)
values (NEXT VALUE FOR SEQ_ID, 'Грозовой перевал', 3, 5);
insert into books (id, name, genre_id, authors_id)
values (NEXT VALUE FOR SEQ_ID, 'Франкенштейн', 1, 6);
insert into books (id, name, genre_id, authors_id)
values (NEXT VALUE FOR SEQ_ID, 'Ярмарка Тщеславия', 3, 7);
insert into books (id, name, genre_id, authors_id)
values (NEXT VALUE FOR SEQ_ID, 'Гордость и предубеждение', 2, 8);