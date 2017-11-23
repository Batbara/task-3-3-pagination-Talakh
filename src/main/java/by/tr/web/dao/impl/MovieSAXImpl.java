package by.tr.web.dao.impl;

import by.tr.web.dao.MovieDAO;
import by.tr.web.exception.ExceptionMessage;
import by.tr.web.exception.dao.MovieDAOException;
import by.tr.web.domain.MovieList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;

public class MovieSAXImpl implements MovieDAO {
    @Override
    public MovieList parse(File source, int startPosition, int numberOfElements) throws MovieDAOException {
        MovieList movieList = null;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            MovieSAXHandler handler = new  MovieSAXHandler();
            reader.setContentHandler(handler);
            reader.parse(source.getPath());
            MovieList wholeList = handler.getMovieList();
            movieList = wholeList.getSublist(startPosition,numberOfElements);
        } catch (SAXException e) {

                throw new MovieDAOException(ExceptionMessage.SAX_PARSER_ERROR,e);

        } catch (IOException e) {
            throw new MovieDAOException(ExceptionMessage.IO_STREAM_ERROR,e);
        }
        return movieList;
    }
}
