package by.tr.web.service.impl;

import by.tr.web.dao.MovieDAO;
import by.tr.web.dao.factory.DAOFactory;
import by.tr.web.domain.MovieList;
import by.tr.web.dao.MovieDAOException;
import by.tr.web.service.MovieServiceException;
import by.tr.web.service.MovieService;

import java.io.File;

public class MovieSTAXServiceImpl implements MovieService {
    @Override
    public MovieList parse(File source, int startPosition, int numberOfElements) throws MovieServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MovieDAO movieDAO = daoFactory.getMovieSTAXImpl();
        MovieList movieList;
        try {
            movieList = movieDAO.parse(source, startPosition, numberOfElements);
        } catch (MovieDAOException e) {

            throw new MovieServiceException(e);
        }
        return movieList;
    }
}
