package by.tr.web.dao.factory;

import by.tr.web.dao.MovieDAO;
import by.tr.web.dao.impl.MovieDOMParser;
import by.tr.web.dao.impl.MovieSAXParser;
import by.tr.web.dao.impl.MovieSTAXParser;

public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();
    private MovieDAO movieDOMImpl = new MovieDOMParser();
    private MovieDAO movieSAXImpl = new MovieSAXParser();
    private MovieDAO movieSTAXImpl = new MovieSTAXParser();
    public static DAOFactory getInstance(){
        return instance;
    }
    private DAOFactory(){}

    public MovieDAO getMovieDOMImpl() {
        return movieDOMImpl;
    }

    public MovieDAO getMovieSAXImpl() {
        return movieSAXImpl;
    }

    public MovieDAO getMovieSTAXImpl() {
        return movieSTAXImpl;
    }

}
