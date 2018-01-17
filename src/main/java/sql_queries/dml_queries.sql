
/**
 * Created by Dmitriy Yurkin on 12.01.2018.
 */
INSERT INTO genres(name) VALUES
  ('Классика'), ('Приключения и история'),
  ('Психология'), ('Фантастика'), ('Юмор');

SELECT * From genres;

INSERT INTO user_personal(first_name, last_name, sex) VALUES
  ('Дмитрий', 'Юркин', 'м'), ('Артур', 'Галиулин', 'м'),
  ('Динар', 'Балягеев', 'м'),('Александр', 'Кретов', 'м'),
  ('Григорий', 'Жуганов', 'м'),('Ренат', 'Хабиров', 'м'),
  ('Скарлет', 'Йохансон', 'ж'),('Марго', 'Робби', 'ж'),
  ('Джессика', 'Альба', 'ж'),('Николь', 'Кидман', 'ж');

SELECT * From user_personal;

INSERT INTO user_data(id_personal, login, password) VALUES
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Юркин'), 'dimasta', '12345'),
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Галиулин'), 'king_arthur', 'archi'),
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Балягеев'), 'din', '12345'),
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Кретов'), 'alex', 'fitness'),
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Жуганов'), 'grigor', 'zenit'),
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Хабиров'), 'gifka', 'memchik'),
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Йохансон'), 'black_widow', 'marvel'),
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Робби'), 'harly_quinn', 'dc'),
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Альба'), 'alba', 'qwerty'),
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Кидман'), 'nicky', 'actor');

SELECT login,password FROM user_data;

INSERT INTO books(title, author_id, genre_id, book_ref) VALUES
  ('Так жить нельзя', '34', '5', 'C:\books\Юмор\Жванецкий Михаил\Так жить нельзя.txt'),
  ('Утечка мозгов', '34', '5', 'C:\books\Юмор\Жванецкий Михаил\Утечка мозгов.txt'),
  ('Великая сила', '35', '5', 'C:\books\Юмор\Задорнов Михаил\Великая сила.txt'),
  ('Эссе', '35', '5', 'C:\books\Юмор\Задорнов Михаил\Эссе.txt'),
  ('Избранное', '36', '5', 'C:\books\Юмор\Зощенко Михаил\Избранное.txt'),
  ('На краешке стола', '36', '5', 'C:\books\Юмор\Зощенко Михаил\На краешке стола.txt'),
  ('Избранное от автора', '37', '5', 'C:\books\Юмор\Коклюшкин Виктор\Избранное от автора.txt')
;

SELECT b.title FROM  books b LEFT JOIN authors a ON b.author_id = a.id
WHERE a.last_name = 'Гоголь';

-- создание таблицы authors
CREATE TABLE authors
(
  id         SERIAL      NOT NULL
    CONSTRAINT authors_pkey
    PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name  VARCHAR(50) NOT NULL
);

CREATE UNIQUE INDEX authors_id_uindex
  ON authors (id);


-- создание таблицы books
-- auto-generated definition
CREATE TABLE books
(
  id        SERIAL       NOT NULL
    CONSTRAINT books_pkey
    PRIMARY KEY,
  title     VARCHAR(50)  NOT NULL,
  author_id INTEGER      NOT NULL
    CONSTRAINT author_id
    REFERENCES authors
    ON UPDATE CASCADE,
  genre_id  INTEGER      NOT NULL
    CONSTRAINT genre_id
    REFERENCES genres
    ON UPDATE CASCADE,
  book_ref  VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX books_id_uindex
  ON books (id);

CREATE UNIQUE INDEX books_title_uindex
  ON books (title);

CREATE UNIQUE INDEX books_book_ref_uindex
  ON books (book_ref);

-- создание таблицы
create table genres
(
  id serial not null
    constraint genres_pkey
    primary key,
  name varchar(50) not null
)
;

create unique index genres_id_uindex
  on genres (id)
;
-- создание таблицы
create table user_data
(
  id serial not null
    constraint user_data_pkey
    primary key,
  id_personal integer not null
    constraint id_pesonal_key
    references user_personal
    on update cascade on delete restrict,
  login varchar(50) not null,
  password varchar(50) not null);

-- удаление таблицы
-- DROP TABLE user_data;
-- создание таблицы
create table user_personal
(
  id serial not null
    constraint user_personal_pkey
    primary key,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  sex varchar(4) not null
)
;

select * FROM books;

