package by.tr.web.dao.impl;

import by.tr.web.dao.MovieDAO;
import by.tr.web.domain.MovieList;
import by.tr.web.dao.MovieDAOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;

public class MovieSAXParser implements MovieDAO {

    @Override
    public MovieList parse(File source, int startPosition, int numberOfElements) throws MovieDAOException {
        MovieList movieList = null;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            MovieSAXHandler handler = new MovieSAXHandler();

            reader.setContentHandler(handler);
            reader.parse(source.getPath());

            MovieList wholeList = handler.getMovieList();
            movieList = wholeList.getSublist(startPosition, numberOfElements);
        } catch (SAXException e) {

            throw new MovieDAOException("SAX parser error", e);

        } catch (IOException e) {
            throw new MovieDAOException("IO Stream error", e);
        }
        return movieList;
    }
}
