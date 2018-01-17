package pojo;

import javax.xml.bind.annotation.*;
import java.util.Set;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */
@XmlType(propOrder = { "id", "first_name", "last_name"}, name = "Authors")
@XmlRootElement
public class Authors {
    private int id;
    private String first_name;
    private String last_name;

    public Authors(String first_name) {
        this.first_name = first_name;
    }

    public Authors(int id, String last_name) {
        this.id = id;
        this.last_name = last_name;
    }

    public Authors(int id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Authors() {
    }

    public Authors(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }

    @XmlRootElement(name = "AllAuthors")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AuthorsWrapper {
        @XmlElement(name = "AuthorsList")
        private Set<Authors> authorsList;

        public Set<Authors> getusers() {
            return authorsList;
        }

        public void setUsers(Set<Authors> authors) {
            this.authorsList = authors;
        }

        public void add(Authors authors){
            authorsList.add(authors);
        }
    }
}
