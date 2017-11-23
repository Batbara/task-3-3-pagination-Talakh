package by.tr.web.dao.impl;

import by.tr.web.controller.util.XMLParameter;
import by.tr.web.dao.MovieDAO;
import by.tr.web.domain.Director;
import by.tr.web.domain.Genre;
import by.tr.web.domain.Movie;
import by.tr.web.domain.MovieList;
import by.tr.web.dao.MovieDAOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MovieSTAXParser implements MovieDAO {
    private int startElementID;
    private int endElementID;
    private XMLStreamReader reader;

    private MovieList movieList = null;
    private Movie movie = null;
    private Director director = null;
    private boolean skip = false;
    private int currentID = 1;

    @Override
    public MovieList parse(File source, int startPosition, int numberOfElements) throws MovieDAOException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        startElementID = startPosition;
        endElementID = startElementID + numberOfElements;
        nullFields();

        try {
            InputStream input = new FileInputStream(source);

            reader = inputFactory.createXMLStreamReader(input);
            movieList = new MovieList();
            parse();

        } catch (XMLStreamException e) {
            throw new MovieDAOException("STAX parsers XML stream error", e);
        } catch (FileNotFoundException e) {
            throw new MovieDAOException("File not found", e);
        }
        return movieList;
    }

    private void nullFields() {
        movieList = null;
        movie = null;
        director = null;
        skip = false;
        currentID = 1;
    }

    private void parse() throws XMLStreamException {

        String elementName = null;
        while (reader.hasNext() && currentID <= endElementID) {
            int type = reader.next();
            switch (type) {

                case XMLStreamConstants.START_ELEMENT:

                    elementName = reader.getLocalName();
                    processStartElement(elementName);
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String textValue = reader.getText().trim();
                    setUpElementValue(elementName, textValue);
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elementName = reader.getLocalName();
                    processEndElement(elementName);
                    break;
            }
        }
    }

    private boolean isDirector(String elementName) {
        return elementName.equals(XMLParameter.DIRECTOR);
    }

    private boolean isMovie(String elementName) {
        return elementName.equals(XMLParameter.MOVIE);
    }

    private void processStartElement(String elementName) throws XMLStreamException {

        if (isMovie(elementName)) {
            String idString = reader.getAttributeValue(null, XMLParameter.ID);
            currentID = Integer.parseInt(idString);
            if (currentID <= startElementID) {

                skip = true;
                return;
            }
            skip = false;
            movie = new Movie();
            movie.setId(Integer.parseInt(idString));
            return;
        }
        if (isDirector(elementName)) {
            director = new Director();
        }
    }

    private void setUpElementValue(String elementName, String textValue) throws XMLStreamException {
        if (skip) {
            return;
        }
        if (textValue.isEmpty()) {
            return;
        }
        switch (elementName) {
            case XMLParameter.TITLE:
                movie.setTitle(textValue);
                break;
            case XMLParameter.GENRE:
                movie.setGenre(Genre.valueOf(textValue));
                break;
            case XMLParameter.YEAR:
                movie.setYear(Integer.parseInt(textValue));
                break;
            case XMLParameter.NAME:
                director.setName(textValue);
                break;
            case XMLParameter.SURNAME:
                director.setSurname(textValue);
                break;
        }
    }

    private void processEndElement(String elementName) throws XMLStreamException {
        if (skip) {
            return;
        }
        if (isDirector(elementName)) {
            movie.setDirector(director);
            return;
        }
        if (isMovie(elementName)) {
            movieList.add(movie);
        }
    }

}
