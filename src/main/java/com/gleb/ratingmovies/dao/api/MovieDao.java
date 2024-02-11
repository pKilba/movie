package com.gleb.ratingmovies.dao.api;

import com.gleb.ratingmovies.dao.entity.Movie;
import com.gleb.ratingmovies.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MovieDao extends DAO<Movie, Long> {

    /**
     * @param offset (current movie let's start the countdown)
     * @param amount amount movies
     * @return list movies by range
     * @throws DaoException if database errors occurs
     */
    List<Movie> findMoviesRange(int offset, int amount) throws DaoException;

    /**
     * @return count all film
     * @throws DaoException if database errors occurs
     */
    int findMoviesAmount() throws DaoException;

    /**
     * @param idMovie movie id
     * @return optional movie by id
     * @throws DaoException if database errors occurs
     */
    Optional<Movie> findById(Long idMovie) throws DaoException;
}
