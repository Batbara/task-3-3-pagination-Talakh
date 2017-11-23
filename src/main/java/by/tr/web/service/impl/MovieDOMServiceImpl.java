package by.tr.web.service.impl;

import by.tr.web.controller.util.XMLParameter;
import by.tr.web.dao.MovieDAO;
import by.tr.web.dao.factory.DAOFactory;
import by.tr.web.domain.Director;
import by.tr.web.domain.Genre;
import by.tr.web.domain.Movie;
import by.tr.web.domain.MovieList;
import by.tr.web.exception.ExceptionMessage;
import by.tr.web.exception.dao.MovieDAOException;
import by.tr.web.exception.service.MovieServiceException;
import by.tr.web.service.MovieService;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class MovieDOMServiceImpl implements MovieService {
    @Override
    public MovieList parse(File source, int startPosition, int numberOfElements) throws MovieServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO movieDAO = daoFactory.getMovieDOMImpl();
        MovieList movieList;
        try {
            movieList = movieDAO.parse(source, startPosition, numberOfElements);
        } catch (MovieDAOException e) {

            throw new MovieServiceException(e);
        }
        return movieList;

    }

}
