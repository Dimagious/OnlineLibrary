package db.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */

@XmlType(propOrder = { "id", "id_personal","userPersonal", "login", "password"}, name = "Users Information")
@XmlRootElement(name = "Users")
public class UserData {
    private int id;
    private int id_personal;
    private UserPersonal userPersonal;
    private String login;
    private String password;
    private String role;
    private ArrayList<UserData> users = new ArrayList<>();

    public UserData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserData(int id, int id_personal, UserPersonal userPersonal, String login, String password) {
        this.id = id;

        this.id_personal = id_personal;
        this.userPersonal = userPersonal;
        this.login = login;
        this.password = password;
    }

    public UserData(int id, int id_personal, String login, String password) {
        this.id = id;
        this.id_personal = id_personal;
        this.login = login;
        this.password = password;
    }


    public UserData(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserData(int id, UserPersonal userPersonal, String login, String password, String role) {
        this.id = id;
        this.userPersonal = userPersonal;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserData() {
    }

    public UserData(int id, UserPersonal userPersonal, String login, String password) {
        this.id = id;
        this.userPersonal = userPersonal;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", id_personal=" + id_personal +
                ", userPersonal=" + userPersonal +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public int getId_personal() {
        return id_personal;
    }

    @XmlElement
    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public UserPersonal getUserPersonal() {
        return userPersonal;
    }

    @XmlElement
    public void setUserPersonal(UserPersonal userPersonal) {
        this.userPersonal = userPersonal;
    }

    public String getLogin() {
        return login;
    }

    @XmlElement
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    @XmlElement
    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<UserData> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserData> users) {
        this.users = users;
    }

    public ArrayList<UserData> getUserData() {
        return users;
    }

    public void add(UserData userData) {
        users.add(userData);
    }

    @XmlRootElement
    public static class UsersWrapper {
        @XmlElement
        private Set<UserData> users = new HashSet<>();

        public Set<UserData> getusers() {
            return users;
        }

        public void setUsers(Set<UserData> users) {
            this.users = users;
        }

        public void add(UserData userData){
            users.add(userData);
        }
    }
}