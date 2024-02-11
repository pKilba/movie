package com.gleb.ratingmovies.dao.mapper.impl;

import com.gleb.ratingmovies.dao.entity.Genre;
import com.gleb.ratingmovies.dao.entity.Movie;
import com.gleb.ratingmovies.dao.mapper.api.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gleb.ratingmovies.dao.entity.ColumnName.*;

public class MovieRowMapper implements RowMapper<Movie> {
    private static MovieRowMapper instance;

    public static MovieRowMapper getInstance() {
        if (instance == null) {
            instance = new MovieRowMapper();
        }
        return instance;
    }

    @Override
    public Movie map(ResultSet resultSet) throws SQLException {
        return  Movie.builder()
                .setMovieId(resultSet.getInt(MOVIE_ID))
                .setPoster(resultSet.getString(MOVIE_POSTER))
                .setAbout(resultSet.getString(MOVIE_ABOUT))
                .setReleaseTime(resultSet.getTimestamp(MOVIE_RELEASE_DATE))
                .setRating(resultSet.getDouble(MOVIE_RATING))
                .setMovieGenre(Genre.getById(resultSet.getInt(MOVIE_GENRE_ID)))
                .setMovieName(resultSet.getString(MOVIE_NAME))
                .setMovieProducer(resultSet.getString(MOVIE_PRODUCER))
                .setMovieDuration(resultSet.getInt(MOVIE_DURATION))
                .setMovieBackground(resultSet.getString(MOVIE_BACKGROUND))
                .setMovieLink(resultSet.getString(MOVIE_LINK))
                .build();
    }
}
