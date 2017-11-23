package by.tr.web.service.factory;

import by.tr.web.service.MovieService;
import by.tr.web.service.impl.MovieDOMServiceImpl;
import by.tr.web.service.impl.MovieSAXServiceImpl;
import by.tr.web.service.impl.MovieSTAXServiceImpl;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    private MovieService domServiceImpl = new MovieDOMServiceImpl();
    private MovieService staxServiceImpl = new MovieSTAXServiceImpl();
    private MovieService saxServiceImpl = new MovieSAXServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {
    }

    public MovieService getDomServiceImpl() {
        return domServiceImpl;
    }

    public MovieService getStaxServiceImpl() {
        return staxServiceImpl;
    }

    public MovieService getSaxServiceImpl() {
        return saxServiceImpl;
    }

}
