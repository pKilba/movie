package com.gleb.ratingmovies.controller.command.impl.general;

import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.MoviesPagesWithPagination;

public class GoToMoviesPageCommand implements Command {

    private static final String MOVIES = "/jsp/pages/movies.jsp";
    private static final MoviesPagesWithPagination moviesPagesWithPagination = MoviesPagesWithPagination.getInstance();


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        moviesPagesWithPagination.processCommandWithPagination(request);
        return CommandResponse.forward(MOVIES);
    }
}
