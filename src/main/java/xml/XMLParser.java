package xml;

import dao.BooksDAO;
import dao.BooksDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import pojo.Books;
import pojo.UserData;
import java.io.File;
import java.sql.SQLException;
import javax.xml.bind.*;

/**
 * Created by Dmitriy Yurkin on 15.01.2018.
 */

public class XMLParser {
    /**
     * Извлекает всю информацию о пользователях из баз
     * UserPersonal и UserData и конвертирует в XML-файл
     */
    protected static void UsersParserToXML() throws SQLException, JAXBException {
        //XML Parser для всех пользователей
        UserDAO userDAO = new UserDAOImpl();
        File file1 = new File("all_users.xml");
        JAXBContext context1 = JAXBContext.newInstance(UserData.UsersWrapper.class);
        Marshaller marshaller1 = context1.createMarshaller();
        marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        UserData.UsersWrapper users = new UserData.UsersWrapper();
        for (UserData userData : userDAO.getAllUsers()) {
            users.add(userData);
            System.out.println(userData);
        }
        marshaller1.marshal(users, file1);
    }

    /**
     * Извлекает всю информацию о пользователях из баз
     * Books, Authors и Genres и конвертирует в XML-файл
     */
    protected static void BooksParserToXML() throws SQLException, JAXBException {
        BooksDAO booksDAO = new BooksDAOImpl();
        File file2 = new File("all_books.xml");
        JAXBContext context2 = JAXBContext.newInstance(Books.BooksWrapper.class);
        Marshaller marshaller2 = context2.createMarshaller();
        marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Books.BooksWrapper books = new Books.BooksWrapper();
        for (Books books2 : booksDAO.getAllBooks()) {
            books.add(books2);
        }
        marshaller2.marshal(books, file2);
    }

    public static void main(String[] args) throws SQLException, JAXBException {
        //From UserPersonal and UserData to XML
        //UsersParserToXML();
        //From Books, Authors and Genres to XML
        //BooksParserToXML();

    }
}