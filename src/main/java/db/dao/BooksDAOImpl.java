package db.dao;

import db.connections.ConnectionManager;
import db.connections.CustomConnectionManager;
import db.exceptions.DAOException;
import db.pojo.Authors;
import db.pojo.Books;
import db.pojo.Genres;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 11.01.2018.
 */

@Repository
public class BooksDAOImpl implements BooksDAO {
    private static final Logger logger = Logger.getLogger(BooksDAOImpl.class);
    private static ConnectionManager connectionManager;

    /**
     * Возвращает список всех книг с их данными
     * Метод доступен для администратора
     *
     * @return list список всех книг
     */
    @Override
    public List<Books> getAllBooks() throws DAOException {
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT b.id, b.title, b.author_id, genre_id, " +
                            "b.book_ref, a.first_name, a.last_name, g.name " +
                            "FROM books AS b LEFT JOIN authors a ON b.author_id = a.id " +
                            "LEFT JOIN genres AS g ON b.genre_id = g.id ORDER BY b.id");
            List<Books> allBooks = new ArrayList<>();
            while (resultSet.next()) {
                Books books = getFieldsFromBooks(resultSet);
                Authors authors = getFieldsFromAuthors(resultSet);
                Genres genres = getFieldsFromGenres(resultSet);
                books.setAuthors(authors);
                books.setGenres(genres);
                allBooks.add(books);
            }
            return allBooks;
        } catch (SQLException e1) {
            throw new DAOException("getAllBooks()" + e1);
        }
    }


    /**
     * Достаёт из БД список книг указанного автора
     *
     * @param authorslastname фамилия автора книги
     */
    @Override
    public List<Books> getBooksByAuthorsLastname(String authorslastname) throws DAOException {
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
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
        } catch (SQLException e) {
            throw new DAOException("getBooksByAuthorsLastname()", e);
        }
    }

    /**
     * Достаёт из БД список книг выбранного жанра
     *
     * @param genre жанр книги
     */
    @Override
    public List<Books> getBooksByGenre(String genre) throws DAOException {
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
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
        } catch (SQLException e) {
            throw new DAOException("getBooksByGenre()", e);
        }
    }

    /**
     * Удаляет из БД указанную книгу
     * Метод доступен для администратора
     *
     * @param title название книги
     */
    @Override
    public void deleteBookByTitle(String title) throws DAOException {
        BooksDAO booksDAO = new BooksDAOImpl();
        Books books = booksDAO.findBookByTitle(title);
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        if (books != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM books WHERE title = ?;"
                );
                statement.setString(1, title);
                statement.executeUpdate();
                logger.debug("Указанная книга удалена из библиотеки");
            } catch (SQLException e) {
                throw new DAOException("deleteBookByTitle()", e);
            }
        } else {
            logger.debug("Указанной книги в библиотеке нет");
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
    public Books findBookByTitle(String title) throws DAOException {
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        try {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM books WHERE title = ?"
            );
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            Books books = null;
            if (resultSet.next()) {
                books = getFieldsFromBooks(resultSet);
            }
            return books;

        } catch (SQLException e) {
            throw new DAOException("findBookByTitle()", e);
        }
    }

    /**
     * Сохраняет в базе данных информацию об авторе.
     * Обновляет id в полученном объекте
     *
     * @param first_name имя автора
     * @param last_name  фамилия автора
     */
    @Override
    public Authors saveAuthor(String first_name, String last_name) throws DAOException {
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        try {
            getAuthorId(first_name, last_name);
            PreparedStatement statement = connection.prepareStatement(
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
        } catch (SQLException e) {
            throw new DAOException("saveAuthor()", e);
        }
    }

    /**
     * Находит в базе данных информацию об авторе.
     *
     * @param first_name имя автора
     * @param last_name  фамилия автора
     */
    @Override
    public int getAuthorId(String first_name, String last_name) throws DAOException {
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id FROM authors WHERE first_name = ? AND last_name = ?");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int idfromDB = resultSet.getInt("id");
            Authors newAuthor = new Authors();
            newAuthor.setId(idfromDB);
            return idfromDB;
        } catch (SQLException e) {
            throw new DAOException("getAuthorId()", e);
        }
    }

    /**
     * Сохраняет в базе данных информацию о жанре.
     * Обновляет id в полученном объекте
     *
     * @param name название жанра
     */
    @Override
    public Genres saveGenre(String name) throws DAOException {
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        try {
            getGenreId(name);
            PreparedStatement statement = connection.prepareStatement(
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
        } catch (SQLException e) {
            throw new DAOException("saveGenre()", e);
        }
    }

    /**
     * Находит в базе данных информацию об авторе.
     *
     * @param name название жанра
     */
    @Override
    public int getGenreId(String name) throws DAOException {
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id FROM genres WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int idfromDB = resultSet.getInt("id");
            Genres newGenre = new Genres();
            newGenre.setId(idfromDB);
            return idfromDB;
        } catch (SQLException e) {
            throw new DAOException("getGenreId()", e);
        }
    }

    /**
     * Сохраняет в базе данных информацию о новой книге.
     * Обновляет id в полученном объекте
     *
     * @param title      название книги
     * @param first_name имя автора
     * @param last_name  фамилия автора
     * @param name       название жанра
     * @param book_ref   ссылка на книгу
     */
    @Override
    public Books saveBook(String title, String first_name, String last_name,
                          String name, String book_ref) throws DAOException {
        Authors newAuthor = saveAuthor(first_name, last_name);
        Genres newGenre = saveGenre(name);
        Books books = null;
        connectionManager = CustomConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
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
        } catch (SQLException e) {
            throw new DAOException("saveBook()", e);
        }
    }


    /**
     * Достаёт из БД информацию из таблицы Books
     */
    private Books getFieldsFromBooks(ResultSet resultSet) throws DAOException {
        Books books = null;
        try {
            books = new Books(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("author_id"),
                    resultSet.getInt("genre_id"),
                    resultSet.getString("book_ref"));
        } catch (SQLException e) {
            throw new DAOException("getFieldsFromBooks()", e);
        }
        return books;
    }

    /**
     * Достаёт из БД информацию из таблицы Authors
     */
    private Authors getFieldsFromAuthors(ResultSet resultSet) throws DAOException {
        Authors authors = null;
        try {
            authors = new Authors(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));
        } catch (SQLException e) {
            throw new DAOException("getFieldsFromAuthors()", e);
        }
        return authors;
    }

    /**
     * Достаёт из БД информацию из таблицы Genres
     */
    private Genres getFieldsFromGenres(ResultSet resultSet) throws DAOException {
        Genres genres = null;
        try {
            genres = new Genres(
                    resultSet.getInt("id"),
                    resultSet.getString("name"));
        } catch (SQLException e) {
            throw new DAOException("getFieldsFromGenres()", e);
        }
        return genres;
    }
}