package by.tr.web.dao;

import by.tr.web.exception.dao.MovieDAOException;
import by.tr.web.domain.MovieList;

import java.io.File;

public interface MovieDAO {
    MovieList parse(File source, int startPosition, int numberOfElements) throws MovieDAOException;
}
