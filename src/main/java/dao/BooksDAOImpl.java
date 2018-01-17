package dao;

import for_db.Connector;
import pojo.Authors;
import pojo.Books;
import pojo.Genres;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 11.01.2018.
 */
public class BooksDAOImpl implements BooksDAO {
    /**
     * Возвращает список всех книг с их данными
     * Метод доступен для администратора
     *
     * @return list список всех книг
     */
    @Override
    public List<Books> getAllBooks() throws SQLException {
        List<Books> list = Connector.executeQuery(con -> {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT b.id, b.title, b.author_id, genre_id, " +
                            "b.book_ref, a.first_name, a.last_name, g.name " +
                            "FROM books AS b LEFT JOIN authors a ON b.author_id = a.id " +
                            "LEFT JOIN genres AS g ON b.genre_id = g.id ORDER BY b.id"
            );
            List<Books> AllBooks = new ArrayList<>();
            while (resultSet.next()) {
                Books books = getFieldsFromBooks(resultSet);
                Authors authors = getFieldsFromAuthors(resultSet);
                Genres genres = getFieldsFromGenres(resultSet);
                books.setAuthors(authors);
                books.setGenres(genres);
                System.out.println(books);
                AllBooks.add(books);
            }
            return AllBooks;
        });
        return list;
    }

    /**
     * Достаёт из БД список книг указанного автора
     *
     * @param authorslastname фамилия автора книги
     */
    @Override
    public List<Books> getBooksByAuthorsLastname(String authorslastname) throws SQLException {
        List<Books> list = Connector.executeQuery(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT b.id, b.title, b.author_id, b.genre_id, b.book_ref," +
                            "a.id, a.first_name, a.last_name " +
                            "FROM books b LEFT JOIN authors a ON b.author_id = a.id " +
                            "WHERE a.last_name = ?;"
            );
            statement.setString(1, authorslastname);
            List<Books> authors_books = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Authors authors = getFieldsFromAuthors(resultSet);
                Books books = getFieldsFromBooks(resultSet);
                books.setAuthor_id(authors.getId());
                authors_books.add(books);
            }
            return authors_books;
        });
        return list;
    }

    /**
     * Достаёт из БД список книг выбранного жанра
     *
     * @param genre жанр книги
     */
    @Override
    public List<Books> getBooksByGenre(String genre) throws SQLException {
        List<Books> list = Connector.executeQuery(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT b.id, b.title, b.author_id, b.genre_id, b.book_ref," +
                            "g.id, g.name " +
                            "FROM books b LEFT JOIN genres g ON b.genre_id = g.id " +
                            "WHERE g.name = ?;"
            );
            statement.setString(1, genre);
            List<Books> authors_books = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Books books = getFieldsFromBooks(resultSet);
                books.setAuthor_id(books.getGenre_id());
                authors_books.add(books);
            }
            return authors_books;
        });
        return list;
    }

    /**
     * Удаляет из БД указанную книгу
     * Метод доступен для администратора
     *
     * @param title название книги
     */
    @Override
    public void deleteBookByTitle(String title) throws SQLException {
        BooksDAO booksDAO = new BooksDAOImpl();
        Books books = booksDAO.findBookByTitle(title);
        if (books != null) {
            Connector.executeQuery(con -> {
                PreparedStatement statement = con.prepareStatement(
                        "DELETE FROM books WHERE title = ?;"
                );
                statement.setString(1, title);
                statement.executeUpdate();
                System.out.println("Указанная книга удалена из библиотеки");
                return true;
            });
        } else {
            System.out.println("Указанной книги в библиотеке нет");
        }
    }

    /**
     * Проверяет наличие указанной книги в БД
     *
     * @param title название книги
     * @return данные о книге из БД
     * NULL если книги нет в БД
     */
    @Override
    public Books findBookByTitle(String title) {
        Books book = null;
        try {
            book = Connector.executeQuery(con -> {
                PreparedStatement statement = con.prepareStatement(
                        "SELECT * FROM books WHERE title = ?"
                );
                statement.setString(1, title);
                ResultSet resultSet = statement.executeQuery();
                Books books = null;
                if (resultSet.next()) {
                    books = getFieldsFromBooks(resultSet);
                }
                return books;
            });
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return book;
    }

    /**
     * Сохраняет в базе данных информацию об авторе.
     * Обновляет id в полученном объекте
     *
     * @param first_name имя автора
     * @param last_name фамилия автора
     */
    @Override
    public Authors saveAuthor(String first_name, String last_name) throws SQLException {
        Authors authors = null;
        try {
            getAuthorId(first_name, last_name);
        } catch (SQLException e) {
            authors = Connector.executeQuery(con -> {
                PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO authors (first_name, last_name)" +
                                "VALUES (?, ?) RETURNING id, first_name, last_name");
                statement.setString(1, first_name);
                statement.setString(2, last_name);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int idfromDB = resultSet.getInt("id");
                String firstNameFromDB = resultSet.getString("first_name");
                String lastNameFromDB = resultSet.getString("last_name");
                Authors newAuthor = new Authors();
                newAuthor.setId(idfromDB);
                newAuthor.setFirst_name(firstNameFromDB);
                newAuthor.setLast_name(lastNameFromDB);
                System.out.println(newAuthor);
                return newAuthor;
            });
        }
        return authors;
    }

    /**
     * Находит в базе данных информацию об авторе.
     *
     * @param first_name имя автора
     * @param last_name фамилия автора
     */
    @Override
    public int getAuthorId(String first_name, String last_name) throws SQLException {
        return Connector.executeQuery(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT id FROM authors WHERE first_name = ? AND last_name = ?");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int idfromDB = resultSet.getInt("id");
            Authors newAuthor = new Authors();
            newAuthor.setId(idfromDB);
            return idfromDB;
        });
    }

    /**
     * Сохраняет в базе данных информацию о жанре.
     * Обновляет id в полученном объекте
     *
     * @param name название жанра
     */
    @Override
    public Genres saveGenre(String name) throws SQLException {
        Genres genres = null;
        try {
            getGenreId(name);
        } catch (SQLException e) {
            genres = Connector.executeQuery(con -> {
                PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO genres (name) VALUES (?) RETURNING id, name");
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int idfromDB = resultSet.getInt("id");
                String nameFromDB = resultSet.getString("name");
                Genres newGenre = new Genres();
                newGenre.setId(idfromDB);
                newGenre.setName(nameFromDB);
                System.out.println(newGenre);
                return newGenre;
            });
        }
        return genres;
    }

    /**
     * Находит в базе данных информацию об авторе.
     *
     * @param name название жанра
     */
    @Override
    public int getGenreId(String name) throws SQLException {
        return Connector.executeQuery(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT id FROM genres WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int idfromDB = resultSet.getInt("id");
            Genres newGenre = new Genres();
            newGenre.setId(idfromDB);
            return idfromDB;
        });
    }

    /**
     * Сохраняет в базе данных информацию о новой книге.
     * Обновляет id в полученном объекте
     *
     * @param title название книги
     * @param first_name имя автора
     * @param last_name фамилия автора
     * @param name название жанра
     * @param book_ref ссылка на книгу
     */
    @Override
    public Books saveBook(String title, String first_name, String last_name,
                          String name, String book_ref) throws SQLException {
        Authors newAuthor = saveAuthor(first_name, last_name);
        Genres newGenre = saveGenre(name);
        Books books = Connector.executeQuery(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO books (title, author_id, genre_id, book_ref)" +
                            "VALUES (?, ?, ?, ?) RETURNING id, title, author_id, genre_id, book_ref");
            statement.setString(1, title);
            statement.setInt(2, getAuthorId(first_name, last_name));
            statement.setInt(3, getGenreId(name));
            statement.setString(4, book_ref);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String bookRefFromDB = resultSet.getString("book_ref");
            Books newBook = new Books();
            newBook.setId(resultSet.getInt("id"));
            newBook.setTitle(resultSet.getString("title"));
            newBook.setAuthor_id(resultSet.getInt("author_id"));
            newBook.setAuthors(newAuthor);
            newBook.setGenre_id(resultSet.getInt("genre_id"));
            newBook.setGenres(newGenre);
            newBook.setBook_ref(bookRefFromDB);
            System.out.println(newBook);
            return newBook;
        });
        return books;
    }

    /**
     * Достаёт из БД информацию из таблицы Books
     */
    private Books getFieldsFromBooks(ResultSet resultSet) throws SQLException {
        Books books = new Books(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getInt("author_id"),
                resultSet.getInt("genre_id"),
                resultSet.getString("book_ref"));
        return books;
    }

    /**
     * Достаёт из БД информацию из таблицы Authors
     */
    private Authors getFieldsFromAuthors(ResultSet resultSet) throws SQLException {
        Authors authors = new Authors(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"));
        return authors;
    }

    /**
     * Достаёт из БД информацию из таблицы Genres
     */
    private Genres getFieldsFromGenres(ResultSet resultSet) throws SQLException {
        Genres genres = new Genres(
                resultSet.getInt("id"),
                resultSet.getString("name"));
        return genres;
    }
}