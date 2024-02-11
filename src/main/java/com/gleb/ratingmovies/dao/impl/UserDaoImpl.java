package com.gleb.ratingmovies.dao.impl;

import com.gleb.ratingmovies.dao.api.UserDAO;
import com.gleb.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.gleb.ratingmovies.dao.connectionpool.impl.ConnectionPoolImpl;
import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.dao.mapper.api.RowMapper;
import com.gleb.ratingmovies.dao.mapper.impl.UserRowMapper;
import com.gleb.ratingmovies.exception.DaoException;
import com.gleb.ratingmovies.dao.entity.ColumnName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDaoImpl implements UserDAO {

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();


    static private UserDaoImpl instance;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }


    private final RowMapper<User> mapper = new UserRowMapper();
    private static final String SQL_SAVE_USER = "INSERT INTO users( login, password," +
            " role_id, name, mail, account_telegram, status_id, create_time," +
            " profile_picture)" +
            " values (?,?,?,?,?,?,?,?,?)";


    private static final String SQL_FIND_ALL_USERS = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE login = ?";
    private static final String SQL_FIND_ID_BY_LOGIN = "SELECT user_id FROM users WHERE login = ?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE user_id = ?";
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE login = ? and password = ?";
    private static final String SQL_FIND_USER_BY_ACCOUNT_TELEGRAM = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE account_telegram = ?";


    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";

    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET login = ?, password = ?, role_id = ? ,name =  ?, mail = ? , account_telegram = ? , status_id = ? , create_time = ? , profile_picture = ?  WHERE user_id = ?";
    private static final String SQL_UPDATE_PHOTO_BY_ID = "UPDATE users SET profile_picture  = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_PASSWORD_BY_ID = "UPDATE users SET password  = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_STATUS_BY_ID = "UPDATE users SET status_id  = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_NAME_EMAIL_TELEGRAM_BY_ID = "UPDATE users SET name = ?, mail = ?, account_telegram = ? WHERE user_id = ?";
    private static final String SQL_FIND_USER_BY_MAIL = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE mail = ?";

    private static final String SQL_FIND_USERS_RANGE =
            "SELECT user_id, login, password,role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users ORDER BY " +
                    "create_time DESC LIMIT ?,?";

    public void updateNameEmailTelegramById(String name, String email, String telegram, long id) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_NAME_EMAIL_TELEGRAM_BY_ID);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, telegram);
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);

        }

    }

    @Override
    public User save(User user) throws DaoException {

        Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserRole().ordinal());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getTelegramAccount());
            preparedStatement.setInt(7, user.getUserStatus().getId());
            preparedStatement.setTimestamp(8, user.getDate());
            preparedStatement.setString(9, user.getProfilePicture());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new DaoException("Creating user failed, no ID obtained.");
                }

            }

        } catch (SQLException e) {

            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);

        }
        return user;
    }

    @Override
    public long findIdByLogin(String login) throws DaoException {
        ResultSet resultSet = null;
        long id = 0;
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_ID_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                id = resultSet.getInt(ColumnName.USER_ID);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

        }
        return id;
    }

    @Override
    public void delete(Long id) throws DaoException {

        Connection connection = connectionPool.takeConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            Boolean result = Objects.equals(preparedStatement.executeUpdate(), 1);
            preparedStatement.close();
            System.out.println(result);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);

        }
    }

    @Override
    public Optional<User> findUserById(long id) throws DaoException {
        ResultSet resultSet = null;
        Optional<User> userOptional = Optional.empty();
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connectionPool.returnConnection(connection);

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

        }
        return userOptional;

    }


    @Override
    public List<User> findAll() throws DaoException {
        Connection connection = connectionPool.takeConnection();
        List<User> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = mapper.map(resultSet);
                result.add(user);
            }
            preparedStatement.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);

        }
        return result;
    }

    @Override
    public boolean blockById(Long id) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS_BY_ID);
            preparedStatement.setInt(1, 2);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connectionPool.returnConnection(connection);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        connectionPool.returnConnection(connection);

        return true;
    }

    @Override
    public boolean isUnblockedById(long id) throws DaoException {
        User user = findUserById(id).get();
        return user.getUserStatus().getId() == 1;
    }

    @Override
    public boolean isBlockedById(long id) throws DaoException {
        User user = findUserById(id).get();
        return user.getUserStatus().getId() == 2;
    }

    @Override
    public boolean unblockById(Long id) throws DaoException {

        Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS_BY_ID);
            preparedStatement.setInt(1, 1);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {

            connectionPool.returnConnection(connection);
        }
        return true;
    }


    @Override
    public List<User> findUsersRange(int offset, int amount) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        List<User> result = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USERS_RANGE)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, amount);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = mapper.map(resultSet);
                result.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            connectionPool.returnConnection(connection);
        }
        return result;
    }

    @Override
    public int findUsersAmount() throws DaoException {
        Connection connection = connectionPool.takeConnection();
        int counter = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                counter++;
            }
            preparedStatement.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return counter;
    }

    @Override
    public Optional<User> findUserByLoginPassword(String login, String password) throws DaoException {

        ResultSet resultSet = null;
        Optional<User> userOptional = Optional.empty();
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userOptional = Optional.of(mapper.map(resultSet));
            }

        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            connectionPool.returnConnection(connection);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

        }
        return userOptional;
    }


    @Override
    public Optional<User> findUserByTelegram(String telegram) throws DaoException {
        ResultSet resultSet = null;
        Optional<User> userOptional = Optional.empty();
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ACCOUNT_TELEGRAM)) {
            statement.setString(1, telegram);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

        }
        return userOptional;
    }


    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        ResultSet resultSet = null;
        Optional<User> userOptional = Optional.empty();
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }


        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

        }
        return userOptional;
    }

    @Override
    public boolean findUserByLogin(User user) throws DaoException {
        ResultSet resultSet = null;
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            statement.setString(1, user.getLogin());
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {

        } finally {
            connectionPool.returnConnection(connection);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return false;
    }

    @Override
    public boolean findUserByTelegram(User user) throws DaoException {
        ResultSet resultSet = null;
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ACCOUNT_TELEGRAM)) {
            statement.setString(1, user.getTelegramAccount());
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            connectionPool.returnConnection(connection);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {

                throw new DaoException(e);
            }
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {

        ResultSet resultSet = null;
        Optional<User> userOptional = Optional.empty();
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_MAIL)) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }

        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            connectionPool.returnConnection(connection);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {

                e.printStackTrace();
                throw new DaoException(e);
            }

        }
        return userOptional;


    }

    @Override
    public boolean findUserByEmail(User user) throws DaoException {
        ResultSet resultSet = null;
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_MAIL)) {
            statement.setString(1, user.getEmail());
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            connectionPool.returnConnection(connection);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return false;
    }

    @Override
    public void updatePhotoByUserId(long id, String photo) throws DaoException {

        Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PHOTO_BY_ID);
            preparedStatement.setString(1, photo);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public boolean updatePasswordByUserId(long id, String password) throws DaoException {
        Connection connection = connectionPool.takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PASSWORD_BY_ID);
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }

        return true;
    }


    @Override
    public User update(User user) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserRole().ordinal());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getTelegramAccount());
            preparedStatement.setInt(7, user.getUserStatus().getId());
            preparedStatement.setTimestamp(8, user.getDate());
            preparedStatement.setString(9, user.getProfilePicture());
            preparedStatement.setLong(10, user.getId());
            Objects.equals(preparedStatement.executeUpdate(), 1);
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);

        }
        return user;


    }

}
