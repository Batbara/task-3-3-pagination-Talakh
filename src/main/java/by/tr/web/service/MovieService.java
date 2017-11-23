package by.tr.web.service;

import by.tr.web.domain.MovieList;

import java.io.File;

public interface MovieService {
    MovieList parse(File source, int startPosition, int numberOfElements) throws MovieServiceException;
}
