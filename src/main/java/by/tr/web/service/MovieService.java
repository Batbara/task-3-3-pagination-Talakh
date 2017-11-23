package by.tr.web.service;

import by.tr.web.domain.MovieList;
import by.tr.web.exception.service.MovieServiceException;

import java.io.File;

public interface MovieService {
    MovieList parse(File source, int startPosition, int numberOfElements) throws MovieServiceException;
}
