package by.tr.web.dao.impl;

import by.tr.web.controller.util.XMLParameter;
import by.tr.web.dao.MovieDAO;
import by.tr.web.domain.Director;
import by.tr.web.domain.Genre;
import by.tr.web.domain.Movie;
import by.tr.web.domain.MovieList;
import by.tr.web.dao.MovieDAOException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class MovieDOMParser implements MovieDAO {
    private DOMParser parser = new DOMParser();
    @Override
    public MovieList parse(File source, int startPosition, int numberOfElements) throws MovieDAOException {
        MovieList movieList = null;
        try {

            parser.parse(source.getPath());
            Document document = parser.getDocument();

            Element root = document.getDocumentElement();
            NodeList movies = root.getElementsByTagNameNS(XMLParameter.NAMESPACE, XMLParameter.MOVIE);

            movieList = new MovieList();
            Movie movie;
            for (int el = startPosition; el < startPosition + numberOfElements; el++) {
                movie = new Movie();
                Element movieElement = (Element) movies.item(el);

                String id = movieElement.getAttribute(XMLParameter.ID);
                movie.setId(Integer.parseInt(id));

                Element title = getSingleChild(movieElement, XMLParameter.TITLE);
                movie.setTitle(title.getTextContent().trim());

                Element genre = getSingleChild(movieElement, XMLParameter.GENRE);
                String genreString = genre.getTextContent().trim();
                movie.setGenre(Genre.valueOf(genreString));

                Element year = getSingleChild(movieElement, XMLParameter.YEAR);
                String yearString = year.getTextContent().trim();
                movie.setYear(Integer.parseInt(yearString));

                Director director = formDirectorEntity(movieElement);
                movie.setDirector(director);
                movieList.add(movie);
            }
        } catch (SAXException e) {
            throw new MovieDAOException("SAX parser error", e);
        } catch (IOException e) {
            throw new MovieDAOException("IO Stream error", e);
        }
        return movieList;
    }

    private Director formDirectorEntity(Element movie) {
        Element directorElement = getSingleChild(movie, XMLParameter.DIRECTOR);
        Director director = new Director();

        Element name = getSingleChild(directorElement, XMLParameter.NAME);
        director.setName(name.getTextContent().trim());

        Element surname = getSingleChild(directorElement, XMLParameter.SURNAME);
        director.setSurname(surname.getTextContent().trim());

        return director;

    }

    private Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagNameNS(XMLParameter.NAMESPACE, childName);
        Element child = (Element) nodeList.item(0);
        return child;
    }
}
