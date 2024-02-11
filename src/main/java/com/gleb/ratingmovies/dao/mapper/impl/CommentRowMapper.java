package com.gleb.ratingmovies.dao.mapper.impl;

import com.gleb.ratingmovies.dao.entity.Comment;
import com.gleb.ratingmovies.dao.mapper.api.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gleb.ratingmovies.dao.entity.ColumnName.COMMENT_CREATE_TIME;
import static com.gleb.ratingmovies.dao.entity.ColumnName.COMMENT_ID;
import static com.gleb.ratingmovies.dao.entity.ColumnName.COMMENT_MESSAGE;
import static com.gleb.ratingmovies.dao.entity.ColumnName.MOVIE_ID;
import static com.gleb.ratingmovies.dao.entity.ColumnName.USER_ID;

public class CommentRowMapper implements RowMapper<Comment> {

    private static CommentRowMapper instance;

    public static CommentRowMapper getInstance() {
        if (instance == null) {
            instance = new CommentRowMapper();
        }
        return instance;
    }

    @Override
    public Comment map(ResultSet resultSet) throws SQLException {
        return Comment.builder()
                .setCommendId(resultSet.getInt(COMMENT_ID))
                .setMessage(resultSet.getString(COMMENT_MESSAGE))
                .setMovie(resultSet.getInt(MOVIE_ID))
                .setUser(resultSet.getInt(USER_ID))
                .setCreateTime(resultSet.getTimestamp(COMMENT_CREATE_TIME))
                .build();
    }
}
