package by.tr.web.controller.command.impl;

import by.tr.web.controller.CommandName;
import by.tr.web.controller.command.Command;
import by.tr.web.controller.util.ControllerParameter;
import by.tr.web.controller.util.XMLData;
import by.tr.web.controller.util.XMLParameter;
import by.tr.web.domain.MovieList;
import by.tr.web.exception.service.MovieServiceException;
import by.tr.web.service.MovieService;
import by.tr.web.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DOMParserCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        File source = (File) request.getServletContext().getAttribute(ControllerParameter.SOURCE);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MovieService movieService = serviceFactory.getDomServiceImpl();

        try {
            int page = 1;
            int recordsPerPage = ControllerParameter.RECORDS_PER_PAGE;
            int numOfRecords = XMLData.NUMBER_OF_ELEMENTS;

            if (request.getParameter(ControllerParameter.PAGE) != null) {
                page = Integer.parseInt(request.getParameter(ControllerParameter.PAGE));
            }
            int start = (page - 1) * recordsPerPage;

            MovieList movieList = movieService.parse(source, start, recordsPerPage);
            int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / recordsPerPage);

            request.setAttribute(XMLParameter.MOVIES, movieList.getMovies());
            request.setAttribute(ControllerParameter.NUM_OF_PAGES, numOfPages);
            request.setAttribute(ControllerParameter.CURRENT_PAGE, page);
            request.setAttribute(ControllerParameter.COMMAND, CommandName.DOM);

            request.getRequestDispatcher(ControllerParameter.MOVIES_URL).forward(request, response);
        } catch (MovieServiceException e) {
            request.setAttribute(ControllerParameter.ERROR_MESSAGE, e.getMessage());
            request.getRequestDispatcher(ControllerParameter.ERROR_PAGE_URL).forward(request, response);
        }
    }
}
