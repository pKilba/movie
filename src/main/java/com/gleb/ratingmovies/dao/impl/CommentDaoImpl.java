package com.gleb.ratingmovies.dao.impl;

import com.gleb.ratingmovies.dao.api.CommentDao;
import com.gleb.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.gleb.ratingmovies.dao.connectionpool.impl.ConnectionPoolImpl;
import com.gleb.ratingmovies.dao.entity.Comment;
import com.gleb.ratingmovies.dao.mapper.api.RowMapper;
import com.gleb.ratingmovies.dao.mapper.impl.CommentRowMapper;
import com.gleb.ratingmovies.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {


    private final RowMapper<Comment> mapper = new CommentRowMapper();
    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private static final String SQL_SAVE_COMMENT = "INSERT INTO comments(message,movie_id," + "user_id,create_time)" + " values (?,?,?,?)";
    private static final String SQL_FIND_ALL_COMMENTS = "SELECT comment_id,message,movie_id,user_id,create_time FROM comments ORDER BY m.movie_release_date ASC";

    private static final String SQL_FIND_BY_ID_MOVIES =
            "SELECT c.comment_id, c.message, c.movie_id, c.user_id, c.create_time " +
                    "FROM comments c " +
                    "JOIN movies m ON c.movie_id = m.movie_id " +
                    "WHERE c.movie_id = ? " +
                    "ORDER BY m.movie_release_date ASC";


    private static final String SQL_FIND_COMMENTS_RANGE =
            "SELECT comment_id, message, movie_id, user_id, create_time " +
                    "FROM comments " +
                    "WHERE movie_id = ? " +
                    "ORDER BY create_time DESC LIMIT ?,?";

    private static CommentDaoImpl instance;

    private CommentDaoImpl() {

    }

    public static CommentDaoImpl getInstance() {
        if (instance == null) {
            instance = new CommentDaoImpl();
        }
        return instance;
    }


    @Override
    public Comment save(Comment comment) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_COMMENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, comment.getMessage());
            preparedStatement.setLong(2, comment.getMovieId());
            preparedStatement.setLong(3, comment.getUserId());
            preparedStatement.setTimestamp(4, comment.getCreateTimeComment());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comment.setId(generatedKeys.getLong(1));
                } else {
                    throw new DaoException("Creating user failed, no ID obtained.");
                }

            } finally {
                preparedStatement.close();
                connectionPool.returnConnection(connection);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comment;
    }

    @Override
    public List<Comment> findCommentsRange(int offset, int amount,int id) throws DaoException {


        Connection connection = connectionPool.takeConnection();
        List<Comment> result = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_COMMENTS_RANGE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, amount);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = mapper.map(resultSet);
                result.add(comment);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return result;
    }


    @Override
    public int findCommentsAmountByMovieId(long id) throws DaoException {
        ResultSet resultSet = null;
        int result = 0;
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID_MOVIES)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                result++;
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
        return result;
    }

    @Override
    public int findCommentsAmount() throws DaoException {
        Connection connection = connectionPool.takeConnection();
        int counter = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_COMMENTS);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                counter++;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return counter;
    }

    @Override
    public List<Comment> findCommentByIdMovies(long id) throws DaoException {
        ResultSet resultSet = null;
        List<Comment> result = new ArrayList();
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement =
                        connection.prepareStatement(SQL_FIND_BY_ID_MOVIES)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Comment comment = mapper.map(resultSet);

                result.add(comment);
            }
            resultSet.close();

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
        return result;
    }

    @Override
    public List<Comment> findAll() throws DaoException {
        Connection connection = connectionPool.takeConnection();
        List<Comment> result = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_COMMENTS);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Comment comment = mapper.map(resultSet);
                result.add(comment);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return result;
    }

}
