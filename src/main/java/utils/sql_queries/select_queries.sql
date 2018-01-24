SELECT * FROM authors;

SELECT
  books.id as books_id,
  books.book_ref as books_ref,
  books.title as books_title,
  a.first_name as authors_first_name,
  a.last_name as authors_last_name,
  g.name as genre_name
FROM books
  FULL JOIN authors a
    ON books.author_id = a.id
  FULL JOIN genres g
    ON books.genre_id = g.id;

SELECT
  user_personal.first_name AS user_personal_first_name,
  user_personal.last_name AS user_personal_last_name,
  user_personal.sex AS user_personal_sex,
  user_data.login as user_data_login,
  user_data.password as user_data_password
FROM user_personal FULL JOIN user_data ON user_personal.id = user_data.id_personal;


EXPLAIN SELECT user_data.login, user_data.password
FROM user_data
WHERE id_personal = (
  SELECT id
  FROM user_personal
  WHERE user_personal.first_name = 'Артур'
  AND user_personal.last_name = 'Галиулин');


EXPLAIN SELECT user_data.login, user_data.password
FROM user_data JOIN user_personal
    ON user_data.id_personal = user_personal.id
WHERE user_personal.first_name = 'Артур'
AND user_personal.last_name = 'Галиулин';

--Все данные  книгах из БД
SELECT *
FROM books AS b LEFT JOIN authors a
    ON b.author_id = a.id JOIN genres as g on b.genre_id = g.id;

--поиск нужной книги
SELECT *
FROM books
WHERE title = 'Бессознательное';

--удаление нужной книги
DELETE FROM books AS b
WHERE b.title = 'Бессознательное';

--удаление пользователя из БД
DELETE FROM user_data
WHERE login = 'witch';
DELETE FROM user_personal
WHERE last_name = 'Делевинь';

--Получение всех данных о книгах
SELECT * FROM books AS b LEFT JOIN authors a ON b.author_id = a.id JOIN genres AS g ON b.genre_id = g.id;

INSERT INTO user_data(id_personal, login, password) VALUES
  ((SELECT id FROM user_personal WHERE user_personal.last_name = 'Делевинь'), 'dimasta', '12345');

SELECT * FROM books;