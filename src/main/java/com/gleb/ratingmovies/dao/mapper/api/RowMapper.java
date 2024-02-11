package com.gleb.ratingmovies.dao.mapper.api;

import com.gleb.ratingmovies.dao.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends AbstractEntity> {
    /**
     * @param resultSet get date about entity
     * @return build entity
     * @throws SQLException if sql errors occurs.
     */
    T map(ResultSet resultSet) throws SQLException;
}
