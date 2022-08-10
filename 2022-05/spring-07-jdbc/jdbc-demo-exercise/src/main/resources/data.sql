insert into books (id, name)
values (NEXT VALUE FOR BOOKS_SEQ_ID, 'Мидлмарч');
insert into books (id, name)
values (NEXT VALUE FOR BOOKS_SEQ_ID, 'На маяк');
insert into books (id, name)
values (NEXT VALUE FOR BOOKS_SEQ_ID, 'Большие надежды');
insert into books (id, name)
values (NEXT VALUE FOR BOOKS_SEQ_ID, 'Джейн Эйр');
insert into books (id, name)
values (NEXT VALUE FOR BOOKS_SEQ_ID, 'Грозовой перевал');
insert into books (id, name)
values (NEXT VALUE FOR BOOKS_SEQ_ID, 'Франкенштейн');
insert into books (id, name)
values (NEXT VALUE FOR BOOKS_SEQ_ID, 'Ярмарка Тщеславия');
insert into books (id, name)
values (NEXT VALUE FOR BOOKS_SEQ_ID, 'Гордость и предубеждение');

insert into genre (id, name)
values (1, 'Ужас');
insert into genre (id, name)
values (2, 'Детектив');
insert into genre (id, name)
values (3, 'Фантастика');
insert into genre (id, name)
values (4, 'Проза');

insert into books_genre (books_id, genre_id)
values (1, 3),
       (2, 2),(2, 3),
       (3, 4),(3, 2),
       (4, 3),
       (5, 2),
       (6, 1),
       (7, 3),
       (7, 2);

insert into authors (id, name, surname, book_id)
values (1, 'Джордж', 'Элиот', 1);
insert into authors (id, name, surname, book_id)
values (2, 'Вирджиния', 'Вулф', 2);
insert into authors (id, name, surname, book_id)
values (3, 'Чарльз', 'Бронте', 3);
insert into authors (id, name, surname, book_id)
values (4, 'Шарлотта', 'Бронте', 4);
insert into authors (id, name, surname, book_id)
values (5, 'Эмили', 'Бронте', 5);
insert into authors (id, name, surname, book_id)
values (6, 'Мэри', 'Шелли', 6);
insert into authors (id, name, surname, book_id)
values (7, 'Уильям', 'Теккерей', 7);
insert into authors (id, name, surname, book_id)
values (8, 'Джейн', 'Остин', 8);

insert into comment (id, ktext, book_id)
values (NEXT VALUE FOR COMMENT_SEQ_ID, 'Книга ничего так страшновать.', 6);
insert into comment (id, ktext, book_id)
values (NEXT VALUE FOR COMMENT_SEQ_ID, 'А то это же франкенштейн.', 6);
insert into comment (id, ktext, book_id)
values (NEXT VALUE FOR COMMENT_SEQ_ID, 'Я читал и получше.', 6);
insert into comment (id, ktext, book_id)
values (NEXT VALUE FOR COMMENT_SEQ_ID, 'Я бы по спорил.', 6);
insert into comment (id, ktext, book_id)
values (NEXT VALUE FOR COMMENT_SEQ_ID, 'Приведи пример.', 6);
insert into comment (id, ktext, book_id)
values (NEXT VALUE FOR COMMENT_SEQ_ID, 'Ну вот напримео меня больше вдохнавила серия книг Гарри Поттер', 6);
insert into comment (id, ktext, book_id)
values (NEXT VALUE FOR COMMENT_SEQ_ID, 'Ну ты сравнил.', 6);
insert into comment (id, ktext, book_id)
values (NEXT VALUE FOR COMMENT_SEQ_ID, 'Что скажете про эту книгу?', 7);