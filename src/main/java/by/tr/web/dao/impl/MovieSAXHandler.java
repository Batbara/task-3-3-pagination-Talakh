package by.tr.web.dao.impl;

import by.tr.web.controller.util.XMLParameter;
import by.tr.web.domain.Director;
import by.tr.web.domain.Genre;
import by.tr.web.domain.Movie;
import by.tr.web.domain.MovieList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MovieSAXHandler extends DefaultHandler {
    private MovieList movieList = new MovieList();
    private Movie movie;
    private Director director;
    private StringBuilder text;

    public MovieSAXHandler() {
        super();
    }

    public MovieList getMovieList() {
        return movieList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        text = new StringBuilder();

        if (localName.equals(XMLParameter.MOVIE)) {
            int movieID = Integer.parseInt(attributes.getValue(XMLParameter.ID));

            movie = new Movie();
            movie.setId(movieID);

        }
        if (localName.equals(XMLParameter.DIRECTOR)) {
            director = new Director();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        switch (localName) {
            case XMLParameter.NAME:
                director.setName(text.toString());
                break;
            case XMLParameter.SURNAME:
                director.setSurname(text.toString());
                break;
            case XMLParameter.GENRE:
                movie.setGenre(formGenre());
                break;
            case XMLParameter.YEAR:
                movie.setYear(formNumber());
                break;
            case XMLParameter.TITLE:
                movie.setTitle(text.toString());
                break;
            case XMLParameter.DIRECTOR:
                movie.setDirector(director);
                director = null;
                break;
            case XMLParameter.MOVIE:
                movieList.add(movie);
                movie = null;
                break;
        }

    }
    private Genre formGenre(){
        return Genre.valueOf(text.toString());
    }
    private int formNumber(){
        return Integer.parseInt(text.toString());
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }

}
