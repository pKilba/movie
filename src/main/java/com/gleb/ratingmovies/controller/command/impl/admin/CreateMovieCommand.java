package com.gleb.ratingmovies.controller.command.impl.admin;

import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.controller.command.util.Parameter;
import com.gleb.ratingmovies.dao.entity.Genre;
import com.gleb.ratingmovies.dao.entity.Movie;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.MovieService;
import com.gleb.ratingmovies.util.Attribute;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class CreateMovieCommand implements Command {

    private static final String INVALID_DATA_KEY = "invalid.data";
    private static final String VALID_DATA_KEY = "success";
    private static final MovieService movieService = MovieService.getInstance();
    private static final String MOVIE = "/jsp/pages/createMovie.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        String about = ParameterTaker.takeString(Parameter.ABOUT, request);
        String image = ParameterTaker.takeString(Parameter.IMAGE_FILM, request);
        String str = ParameterTaker.takeString(Parameter.DATA, request);
        LocalDateTime dateTime = LocalDate.parse(str).atStartOfDay();
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        double rating =  ParameterTaker.takeBinaryNumber(Parameter.RATING, request);
        String name = ParameterTaker.takeString(Parameter.NAME, request);
        String producer = ParameterTaker.takeString(Parameter.PRODUCER, request);
        int duration = (int) ParameterTaker.takeNumber(Parameter.DURATION, request);
        int idGenre = (int) ParameterTaker.takeNumber(Parameter.GENRE, request);
        String imageBack = ParameterTaker.takeString(Parameter.IMAGE_FILM_BACK, request);
        String linkMovie = ParameterTaker.takeString(Parameter.LINK_MOVIE, request);

        if (movieService.isValid(name, duration, producer, about)) {
            request.addAttribute(Attribute.SUCCESS_MESSAGE, VALID_DATA_KEY);
            Movie movie = Movie.builder()
                    .setAbout(about).
                    setMovieGenre(Genre.getById(idGenre)).
                    setPoster(image).
                    setRating(rating).
                    setReleaseTime(timestamp).
                    setMovieDuration(duration).
                    setMovieProducer(producer).
                    setMovieName(name).
                    setMovieBackground(imageBack).
                    setMovieLink(linkMovie).
                    build();
            movieService.save(movie);
        } else {
            request.addAttribute(Attribute.ERROR_MESSAGE, INVALID_DATA_KEY);
        }

        return CommandResponse.forward(MOVIE);
    }
}
