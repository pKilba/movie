package com.gleb.ratingmovies.dao.api;

import com.gleb.ratingmovies.dao.entity.AbstractEntity;
import com.gleb.ratingmovies.exception.DaoException;

import java.util.List;

public interface DAO<T extends AbstractEntity<K>, K> {

    /**
     * @param entity
     * @return save entity
     * @throws DaoException if database errors occurs
     */
    T save(T entity) throws DaoException;


    /**
     * @return list all entity
     * @throws DaoException if database errors occurs
     */
    List<T> findAll() throws DaoException;


}
