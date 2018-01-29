package db.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */
@XmlRootElement
public class UserPersonal {
    private int id;
    private String first_name;
    private String last_name;
    private String sex;
    private List<UserPersonal> users = new ArrayList<UserPersonal>();

    public UserPersonal(int id, String first_name, String last_name, String sex) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.sex = sex;
    }

    public UserPersonal(String first_name, String last_name, String sex){
        this.first_name = first_name;
        this.last_name = last_name;
        this.sex = sex;
    }

    public UserPersonal() {
    }

    public int getId() {
        return id;
    }
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    @XmlElement
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    @XmlElement
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSex() {
        return sex;
    }

    @XmlElement
    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<UserPersonal> getMembers() {
        return users;
    }

    @XmlElement
    public void setMembers(List<UserPersonal> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "User_Personal{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
