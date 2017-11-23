package by.tr.web.dao.factory;

import by.tr.web.dao.MovieDAO;
import by.tr.web.dao.impl.MovieDOMImpl;
import by.tr.web.dao.impl.MovieSAXImpl;
import by.tr.web.dao.impl.MovieSTAXImpl;

public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();
    private MovieDAO movieDOMImpl = new MovieDOMImpl();
    private MovieDAO movieSAXImpl = new MovieSAXImpl();
    private MovieDAO movieSTAXImpl = new MovieSTAXImpl();
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
