package by.tr.web.domain;

import by.tr.web.controller.util.XMLParameter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XMLParameter.MOVIE_TYPE, propOrder = {
        XMLParameter.TITLE,
        XMLParameter.GENRE,
        XMLParameter.YEAR,
        XMLParameter.DIRECTOR
})
public class Movie implements Serializable {

    @XmlAttribute(name = XMLParameter.ID, required = true)
    private int id;
    @XmlElement(namespace = XMLParameter.NAMESPACE)
    private String title;
    @XmlElement(namespace = XMLParameter.NAMESPACE)
    private int year;
    @XmlElement(namespace = XMLParameter.NAMESPACE)
    private Genre genre;
    @XmlElement(namespace = XMLParameter.NAMESPACE)
    private Director director;

    public Movie() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Director getDirector() {
        return director;
    }

    public String getDirectorName() {
        return director.toString();
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (year != movie.year) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (genre != movie.genre) return false;
        return director != null ? director.equals(movie.director) : movie.director == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        return result;
    }
}
