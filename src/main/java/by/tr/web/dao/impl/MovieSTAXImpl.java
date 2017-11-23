package by.tr.web.dao.impl;

import by.tr.web.controller.util.XMLParameter;
import by.tr.web.dao.MovieDAO;
import by.tr.web.domain.Director;
import by.tr.web.domain.Genre;
import by.tr.web.domain.Movie;
import by.tr.web.domain.MovieList;
import by.tr.web.exception.ExceptionMessage;
import by.tr.web.exception.dao.MovieDAOException;

import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MovieSTAXImpl implements MovieDAO {
    private int startElementID;
    private int endElementID;

    @Override
    public MovieList parse(File source, int startPosition, int numberOfElements) throws MovieDAOException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        MovieList movieList;
        startElementID = startPosition;
        endElementID = startElementID + numberOfElements;
        try {
            InputStream input = new FileInputStream(source);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
           //  reader = inputFactory.createFilteredReader(reader,filter);
            movieList = process(reader);
          //  movieList = wholeMovieList.getSublist(startPosition,numberOfElements);
         //   movieList = readDocument(reader);

        } catch (XMLStreamException e) {
            throw new MovieDAOException(ExceptionMessage.XML_STREAM_ERROR, e);
        } catch (FileNotFoundException e) {
            throw new MovieDAOException(ExceptionMessage.FILE_NOT_FOUND, e);
        }
        return movieList;
    }

    private MovieList process(XMLStreamReader reader) throws XMLStreamException {
        MovieList movieList = new MovieList();
        Movie movie = null;
        Director director = null;
        String elementName = null;
        boolean skip = false;
        int id = 1;
        while (reader.hasNext() && id<=endElementID) {
            int type = reader.next();
            switch (type) {

                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (elementName) {
                        case XMLParameter.MOVIE:

                            String idString = reader.getAttributeValue(null, XMLParameter.ID);
                            id = Integer.parseInt(idString);
                            if(id<=startElementID){

                                skip = true;
                                break;
                            }
                            skip = false;
                            movie = new Movie();
                            movie.setId(Integer.parseInt(idString));
                            break;
                        case XMLParameter.DIRECTOR:
                            director = new Director();
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if(skip){
                        break;
                    }
                    String textValue = reader.getText().trim();
                    if (textValue.isEmpty()) {
                        break;
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
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(skip){
                        break;
                    }
                    elementName = reader.getLocalName();
                    switch (elementName){
                        case XMLParameter.DIRECTOR:
                            movie.setDirector(director);
                            break;
                        case XMLParameter.MOVIE:
                            movieList.add(movie);
                            break;
                    }
                    break;

            }
        }
        return movieList;
    }
    private MovieList readDocument(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals(XMLParameter.MOVIES))
                        return readMovieList(reader);
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
        throw new XMLStreamException(ExceptionMessage.XML_STREAM_ERROR);
    }

    private MovieList readMovieList(XMLStreamReader reader) throws XMLStreamException {
        MovieList movies = new MovieList();

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals(XMLParameter.MOVIE)) {
                        Movie movie = readMovie(reader);
//                        if (movie != null) {
                            movies.add(movie);
//                        }
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return movies;
            }
        }
        throw new XMLStreamException(ExceptionMessage.XML_STREAM_ERROR);
    }

    private Movie readMovie(XMLStreamReader reader) throws XMLStreamException {
        Movie movie = new Movie();
        String attribute = reader.getAttributeValue(null, XMLParameter.ID);
        int movieID = Integer.parseInt(attribute);

        movie.setId(movieID);

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    switch (elementName) {
                        case XMLParameter.TITLE:
                            movie.setTitle(readCharacters(reader));
                            break;
                        case XMLParameter.GENRE:
                            movie.setGenre(readGenre(reader));
                            break;
                        case XMLParameter.YEAR:
                            movie.setYear(readInt(reader));
                            break;
                        case XMLParameter.DIRECTOR:
                            movie.setDirector(readDirector(reader));
                            break;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
//                    if (movieID < startElementID || movieID >= endElementID) {
//                        return null;
//                    } else
                    return movie;
            }
        }
        throw new XMLStreamException(ExceptionMessage.XML_STREAM_ERROR);
    }

    private String readCharacters(XMLStreamReader reader) throws XMLStreamException {
        StringBuilder result = new StringBuilder();
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamConstants.CHARACTERS:
                case XMLStreamReader.CDATA:
                    result.append(reader.getText());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return result.toString();
            }
        }
        throw new XMLStreamException(ExceptionMessage.XML_STREAM_ERROR);
    }

    private Genre readGenre(XMLStreamReader reader) throws XMLStreamException {
        StringBuilder result = new StringBuilder();
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.CHARACTERS:
                case XMLStreamReader.CDATA:
                    result.append(reader.getText());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return Genre.valueOf(result.toString());
            }
        }
        throw new XMLStreamException(ExceptionMessage.XML_STREAM_ERROR);
    }

    private int readInt(XMLStreamReader reader) throws XMLStreamException {
        String characters = readCharacters(reader);
        try {
            return Integer.valueOf(characters);
        } catch (NumberFormatException e) {
            throw new XMLStreamException(ExceptionMessage.INVALID_INTEGER + characters);
        }
    }

    private Director readDirector(XMLStreamReader reader) throws XMLStreamException {
        Director director = new Director();

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    switch (elementName) {
                        case XMLParameter.NAME:
                            director.setName(readCharacters(reader));
                            break;
                        case XMLParameter.SURNAME:
                            director.setSurname(readCharacters(reader));
                            break;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return director;
            }
        }
        throw new XMLStreamException(ExceptionMessage.XML_STREAM_ERROR);
    }
}
