package com.gleb.ratingmovies.controller.command.impl.general;

import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.dao.entity.Movie;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.MovieService;
import com.gleb.ratingmovies.util.Attribute;

public class GoToMoviePageCommand implements Command {
    private static final String MOVIE = "/jsp/pages/movie.jsp";
    private static final MovieService movieService = MovieService.getInstance();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        Movie movie = movieService.findMovieById(id);
        request.addAttribute(Attribute.MOVIE, movie);
        request.addAttribute(Attribute.ID, id);
        return CommandResponse.forward(MOVIE);
    }
}
