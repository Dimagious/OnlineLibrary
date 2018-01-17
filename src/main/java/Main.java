import dao.BooksDAO;
import dao.BooksDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import pojo.Authors;
import pojo.Books;
import pojo.UserData;

import java.sql.SQLException;
/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */
public class Main {
    public static void main(String[] args) {
        BooksDAO booksDAO = new BooksDAOImpl();
        UserDAO userDAO = new UserDAOImpl();

        //Получить данные обо всех пользователях системы
//        for (UserData user : userDAO.getAllUsers()) {
//            System.out.println(user.toString());
//        }

        //Зарегистрировать нового пользователя
//        userDAO.registerUser("Натали", "Портман", "ж", "black_swan", "portmone");

        //Авторизовать зарегистрированного пользователя
//        userDAO.authorizeUser("alex", "fitness");

        //Получит данные обо всех книгах в системе
//        for (Books book : booksDAO.getAllBooks()) {
//            System.out.println(book.toString());
//        }

        //Получить список книг указанного автора
//        for (Books books : booksDAO.getBooksByAuthorsLastname("Гоголь")) {
//            System.out.println(books.getTitle());
//        }

        //Получить список книг указанного жанра
//        for (Books books : booksDAO.getBooksByGenre("Классика")) {
//            System.out.println(books.getTitle());
//        }
//
        //Удалить из БД указанную книгу
//        String title = "Бессознательное";
//        booksDAO.deleteBookByTitle(title);
    }
}
