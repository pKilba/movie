package com.gleb.ratingmovies.service;

import com.gleb.ratingmovies.dao.entity.Movie;
import com.gleb.ratingmovies.dao.impl.MovieDaoImpl;
import com.gleb.ratingmovies.exception.DaoException;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.validator.impl.MovieValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MovieService {

    private static final Logger logger = LogManager.getLogger();
    private static final String FIND_MOVIE_PROBLEM = "Exception find movie  ";
    private static final String SAVE_MOVIE_PROBLEM = "Exception save movie  ";
    private static final MovieDaoImpl movieDao = MovieDaoImpl.getInstance();
    private static final MovieValidator movieValidator = MovieValidator.getInstance();


    private static MovieService instance;

    private MovieService() {

    }

    public static MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }
        return instance;
    }


    public boolean isValid(String name, int duration, String producer, String about) {
        return movieValidator.isValid(name, duration, producer, about);
    }


    public int findMoviesAmount() throws ServiceException {
        try {
            return movieDao.findMoviesAmount();
        } catch (DaoException e) {
            logger.error(FIND_MOVIE_PROBLEM + e);
            throw new ServiceException(FIND_MOVIE_PROBLEM + e);
        }
    }

    public Movie findMovieById(long id) throws ServiceException {
        Optional<Movie> movie;
        try {
            movie = movieDao.findById(id);
        } catch (DaoException e) {
            logger.error(FIND_MOVIE_PROBLEM + e);
            throw new ServiceException(FIND_MOVIE_PROBLEM + e);
        }
        if (movie.isPresent()) {
            return movie.get();
        } else {
            logger.error(FIND_MOVIE_PROBLEM);
            throw new ServiceException(FIND_MOVIE_PROBLEM);
        }
    }


    public void save(Movie movie) throws ServiceException {
        try {
            movieDao.save(movie);
        } catch (DaoException e) {
            logger.error(SAVE_MOVIE_PROBLEM + e);
            throw new ServiceException(SAVE_MOVIE_PROBLEM + e);

        }
    }

    public List findMovies() throws ServiceException {
        try {
            return movieDao.findAll();
        } catch (DaoException e) {
            logger.error(SAVE_MOVIE_PROBLEM + e);
            throw new ServiceException(SAVE_MOVIE_PROBLEM + e);
        }
    }

    public List<Movie> findMoviesRange(int offset, int size) throws ServiceException {
        try {
            return movieDao.findMoviesRange(offset, size);
        } catch (DaoException e) {
            logger.error(FIND_MOVIE_PROBLEM + e);
            throw new ServiceException(FIND_MOVIE_PROBLEM);
        }

    }
}
