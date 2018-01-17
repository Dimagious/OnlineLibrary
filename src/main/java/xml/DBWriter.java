package xml;

import dao.BooksDAO;
import dao.BooksDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import pojo.Books;
import pojo.UserData;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 12.01.2018.
 */
public class DBWriter {
    /**
     * Извлекает всю информацию о пользователях из XML-файла
     * и в нужном порядке добавляет её в базы UserPersonal и UserData
     */
    private static void UsersWriterToDB() throws SQLException {
        try {
            File file = new File("all_users.xml");
            JAXBContext context1 = JAXBContext.newInstance(UserData.UsersWrapper.class);
            Unmarshaller unmarshaller1 = context1.createUnmarshaller();
            UserData.UsersWrapper userData = (UserData.UsersWrapper) unmarshaller1.unmarshal(file);
            UserDAO userDAO = new UserDAOImpl();
            for (UserData userList : userData.getusers()) {
                System.out.println(userList);
                userDAO.registerUser(
                        userList.getUserPersonal().getFirst_name(),
                        userList.getUserPersonal().getLast_name(),
                        userList.getUserPersonal().getSex(),
                        userList.getLogin(),
                        userList.getPassword()
                );
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Извлекает всю информацию о пользователях из XML-файла
     * и в нужном порядке добавляет её в базы UserPersonal и UserData
     */
    private static void BooksWriterToDB() throws SQLException {
        try {
            File file = new File("all_books.xml");
            JAXBContext context2 = JAXBContext.newInstance(Books.BooksWrapper.class);
            Unmarshaller unmarshaller2 = context2.createUnmarshaller();
            Books.BooksWrapper books = (Books.BooksWrapper) unmarshaller2.unmarshal(file);
            BooksDAO booksDAO = new BooksDAOImpl();
            for (Books booksList : books.getBooks()) {
                booksDAO.saveBook(
                        booksList.getTitle(),
                        booksList.getAuthors().getFirst_name(),
                        booksList.getAuthors().getLast_name(),
                        booksList.getGenres().getName(),
                        booksList.getBook_ref()
                );
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        //From XML to UserPersonal and UserData tables
        UsersWriterToDB();
        //From XML to Books, Authors and Genres tables
        //BooksWriterToDB();
    }
}