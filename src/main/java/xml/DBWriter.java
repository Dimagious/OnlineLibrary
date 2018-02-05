package xml;

import db.dao.BooksDAO;
import db.dao.BooksDAOImpl;
import db.dao.UserDAO;
import db.dao.UserDAOImpl;
import db.exceptions.DAOException;
import org.apache.log4j.Logger;
import db.pojo.Books;
import db.pojo.UserData;
import db.pojo.UserPersonal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 12.01.2018.
 */
public class DBWriter {
    private static final Logger logger = Logger.getLogger(DBWriter.class);
    /**
     * Извлекает всю информацию о пользователях из XML-файла
     * и в нужном порядке добавляет её в базы UserPersonal и UserData
     */
    private static void UsersWriterToDB() throws SQLException, DAOException {
        try {
            File file = new File("all_users.xml");
            JAXBContext context1 = JAXBContext.newInstance(UserData.UsersWrapper.class);
            Unmarshaller unmarshaller1 = context1.createUnmarshaller();
            UserData.UsersWrapper userData = (UserData.UsersWrapper) unmarshaller1.unmarshal(file);
            UserDAO userDAO = new UserDAOImpl();
            for (UserData userList : userData.getusers()) {
                UserPersonal person = new UserPersonal(
                        userList.getUserPersonal().getFirst_name(),
                        userList.getUserPersonal().getLast_name(),
                        userList.getUserPersonal().getSex());
                UserData data = new UserData(
                        userList.getLogin(),
                        userList.getPassword());
                userDAO.saveUser(person,data);
            }
        } catch (JAXBException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * Извлекает всю информацию о пользователях из XML-файла
     * и в нужном порядке добавляет её в базы UserPersonal и UserData
     */
    private static void BooksWriterToDB() throws SQLException, DAOException {
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
}