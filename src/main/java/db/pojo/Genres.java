package db.pojo;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */
public class Genres {
    private int id;
    private String name;

    public Genres(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genres() {
    }

    public Genres(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genres genres = (Genres) o;

        return getId() == genres.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Genres{" +

                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
