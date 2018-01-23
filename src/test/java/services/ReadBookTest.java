package services;

import dao.BooksDAO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Books;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Dmitriy Yurkin on 23.01.2018.
 */
class ReadBookTest {
    private static final Logger logger = Logger.getLogger(ReadBookTest.class);

    @BeforeAll
    static void setUp() {
        try {
            BooksDAO mock = mock(BooksDAO.class);
            Books book = new Books("Каштанка", 1, 2, "C:\\books\\Классика\\Чехов Антон Павлович\\Каштанка.txt");
            when(mock.findBookByTitle("Каштанка")).thenReturn(book);

            Field field = ReadBook.class.getDeclaredField("booksDAO");
            field.setAccessible(true);
            field.set(ReadBook.class, mock);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readBook() {
        assertEquals("Дурное поведение", (ReadBook.readBook("Каштанка")).substring(111, 127));
    }
}