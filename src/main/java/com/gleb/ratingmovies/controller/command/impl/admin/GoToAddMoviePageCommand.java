package com.gleb.ratingmovies.controller.command.impl.admin;

import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.exception.ServiceException;


public class GoToAddMoviePageCommand implements Command {

    private static final String CREATE_MOVIE = "/jsp/pages/createMovie.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        return CommandResponse.forward(CREATE_MOVIE);
    }
}
