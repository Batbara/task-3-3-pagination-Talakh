package by.tr.web.dao.impl;

import by.tr.web.controller.util.XMLParameter;
import by.tr.web.domain.Director;
import by.tr.web.domain.Genre;
import by.tr.web.domain.Movie;
import by.tr.web.domain.MovieList;
import by.tr.web.exception.ExceptionMessage;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

public class MovieSAXHandler extends DefaultHandler {
    private MovieList movieList = new MovieList();
    private Movie movie;
    private Director director;
    private StringBuilder text;
    private int startElement;
    private int numberOfElements;

    public MovieSAXHandler() {
        super();
    }

    public MovieSAXHandler(int startElement, int numberOfElements) {
        this.startElement = startElement;
        this.numberOfElements = numberOfElements;
    }

    public MovieList getMovieList() {
        return movieList;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
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
                movie.setGenre(Genre.valueOf(text.toString()));
                break;
            case XMLParameter.YEAR:
                movie.setYear(Integer.parseInt(text.toString()));
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

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        super.processingInstruction(target, data);
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        super.skippedEntity(name);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        super.warning(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
    }
}
