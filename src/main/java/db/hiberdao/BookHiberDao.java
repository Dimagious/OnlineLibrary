package db.hiberdao;

import db.entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

/**
 * Created by Dmitriy Yurkin on 03.02.2018.
 */

@Repository
public class BookHiberDao {
    @Autowired
    private SessionFactory factory;

    /**
     * Возвращает {@link Book} по её id
     *
     * @return Book
     */
    public Book getBookById(long id) {
        return getFromDB(session ->
                session.get(Book.class, id));
    }

    /**
     * Возвращает {@link Book} по её названию
     *
     * @param title название книги
     * @return Book
     */
    public Book getBookByTitle(String title) {
        return getFromDB(session ->
        session.get(Book.class, title));
    }

    /**
     * Вспомогательный метод для работы с {@link Session}
     * используя {@link Function}
     */
    private <T> T getFromDB(Function<Session, T> function) {
        T result;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            result = function.apply(session);
            session.getTransaction().commit();
        }
        return result;
    }
}