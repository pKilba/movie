package com.gleb.ratingmovies.dao.connectionpool.api;

import com.gleb.ratingmovies.exception.ConnectionPoolException;

import java.sql.Connection;

public interface ConnectionPool {
    /**
     * @return true or false, resul initialize connection pool
     * @throws ConnectionPoolException if connection pool errors occurs.
     */
    boolean init() throws ConnectionPoolException;

    /**
     * @return true or false, result shut down connection pool
     */
    boolean shutDown();


    /**
     * @return connection by connection pool
     */
    Connection takeConnection();

    /**
     * @param connection connection what return in connection pool
     */
    void returnConnection(Connection connection);

}
