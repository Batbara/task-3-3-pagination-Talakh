package by.tr.web.controller.listeners;

import by.tr.web.controller.util.ControllerParameter;
import by.tr.web.controller.util.XMLData;
import by.tr.web.controller.util.XMLParameter;
import by.tr.web.domain.Director;
import by.tr.web.domain.Movie;
import by.tr.web.domain.MovieList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class FrontControllerListener implements ServletContextListener {

    public FrontControllerListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            JAXBContext context = JAXBContext.newInstance(MovieList.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, XMLParameter.SCHEMA_NAMESPACE);

            MovieList movieList = createMovies();

            File source = new File(getSourcePath());

            if (!source.exists()) {
                source.createNewFile();
            }
            m.marshal(movieList, source);
            m.marshal(movieList, System.out); // копия на консоль

            sce.getServletContext().setAttribute(ControllerParameter.SOURCE, source);
        } catch (JAXBException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


    private MovieList createMovies() {
        MovieList movieList = new MovieList();

        for (int movieNum = 0; movieNum < XMLData.NUMBER_OF_ELEMENTS; movieNum++) {
            Movie movie = new Movie();
            movie.setId(movieNum + 1);
            movie.setTitle(XMLData.TITLE_LIST[movieNum]);
            movie.setYear(XMLData.YEAR_LIST[movieNum]);
            movie.setDirector(new Director(XMLData.NAME_LIST[movieNum], XMLData.SURNAME_LIST[movieNum]));
            movie.setGenre(XMLData.GENRE_LIST[movieNum]);
            movieList.add(movie);
        }
        return movieList;
    }

    private String getSourcePath() {

        ClassLoader classLoader = getClass().getClassLoader();
        URL sourceURL = classLoader.getResource(XMLParameter.XML_SOURCE_PATH);
        assert sourceURL != null;
        String rawPath = sourceURL.getPath();

        String incorrectDriveLetterRegEx = "^/(.:/)";
        String capturedGroup = "$1";
        String correctPath = rawPath.replaceFirst(incorrectDriveLetterRegEx, capturedGroup);

        return correctPath;

    }
}
