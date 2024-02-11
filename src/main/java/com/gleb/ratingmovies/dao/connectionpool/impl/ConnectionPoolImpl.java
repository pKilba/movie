package com.gleb.ratingmovies.dao.connectionpool.impl;

import com.gleb.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.gleb.ratingmovies.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPoolImpl implements ConnectionPool {
    private static final String PROPERTIES_FILE = "db.properties";
    private static final String DB_URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASS = "db.password";
    private static final String DRIVER = "db.driver";

    private static final int INITIAL_POOL_SIZE = 3;
    private static final AtomicBoolean IS_POOL_CREATED = new AtomicBoolean(false);
    private static final ReentrantLock INSTANCE_LOCKER = new ReentrantLock();

    private final LinkedList<Connection> availableConnections;
    private final LinkedList<Connection> giveAwayConnections;

    private static final Logger logger = LogManager.getLogger(ConnectionPoolImpl.class);

    private static ConnectionPoolImpl instance;

    private ConnectionPoolImpl() {
        availableConnections = new LinkedList<>();
        giveAwayConnections = new LinkedList<>();
        init();
    }

    public static ConnectionPool getInstance() {
        if (!IS_POOL_CREATED.get()) {
            INSTANCE_LOCKER.lock();
            if (!IS_POOL_CREATED.get()) {
                instance = new ConnectionPoolImpl();
                IS_POOL_CREATED.set(true);
            }
            INSTANCE_LOCKER.unlock();
        }
        return instance;
    }

    @Override
    public boolean init() {
        if (!IS_POOL_CREATED.get()) {
            try {
                Properties properties = loadProperties();
                String driver = properties.getProperty(DRIVER);
                Class.forName(driver);
            } catch (ClassNotFoundException | IOException e) {
                logger.throwing(Level.WARN, e);
                throw new ConnectionPoolException(e);
            }

            initializeConnections(INITIAL_POOL_SIZE);
            logger.traceExit("pool initialized by amount:" + availableConnections.size());
            return true;
        }
        return false;
    }

    private Properties loadProperties() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        }
    }

    @Override
    public boolean shutDown() {
        if (IS_POOL_CREATED.get()) {
            closeConnections();
            IS_POOL_CREATED.set(false);
            return true;
        }
        return false;
    }

    private void closeConnections() {
        closeConnections(this.availableConnections);
        closeConnections(this.giveAwayConnections);
    }

    private void closeConnections(LinkedList<Connection> connections) {
        for (Connection connection : connections) {
            closeConnection(connection);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);
            throw new ConnectionPoolException(e);
        }
    }

    @Override
    public synchronized Connection takeConnection() {
        while (availableConnections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.throwing(Level.WARN, e);
                throw new ConnectionPoolException(e);
            }
        }
        Connection connection = availableConnections.poll();
        giveAwayConnections.add(connection);
        return connection;
    }

    @Override
    public synchronized void returnConnection(Connection connection) {
        if (giveAwayConnections.remove(connection)) {
            availableConnections.add(connection);
            notifyAll();
        } else {
            logger.warn("Failed to get the connection back");
        }
    }

    private boolean initializeConnections(int amount) {
        try {
            Properties properties = loadProperties();
            String url = properties.getProperty(DB_URL);
            String user = properties.getProperty(USER);
            String password = properties.getProperty(PASS);

            for (int i = 0; i < amount; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                availableConnections.add(connection);
            }
            return true;
        } catch (SQLException | IOException e) {
            logger.throwing(Level.WARN, e);
            throw new ConnectionPoolException(e);
        }
    }
}
