package by.tr.web.domain;

import by.tr.web.controller.util.XMLParameter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = XMLParameter.MOVIES, namespace = XMLParameter.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieList {
    @XmlElement(name = XMLParameter.MOVIE, namespace = XMLParameter.NAMESPACE)

    private List<Movie> movies = new ArrayList<>();

    public MovieList() {
    }

    public List<Movie> getMovies() {
        return movies;
    }
    public MovieList getSublist(int start, int num){
        MovieList mList = new MovieList();
        mList.setMovies(movies.subList(start,start+num));
        return mList;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public boolean add(Movie movie) {
        return movies.add(movie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieList movieList1 = (MovieList) o;

        return movies != null ? movies.equals(movieList1.movies) : movieList1.movies == null;
    }

    @Override
    public int hashCode() {
        return movies != null ? movies.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "movies=" + movies +
                '}';
    }
}
