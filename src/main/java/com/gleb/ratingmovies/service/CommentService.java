package com.gleb.ratingmovies.service;

import com.gleb.ratingmovies.dao.entity.Comment;
import com.gleb.ratingmovies.dao.impl.CommentDaoImpl;
import com.gleb.ratingmovies.exception.DaoException;
import com.gleb.ratingmovies.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CommentService {

    private static final String FIND_COMMENTS_PROBLEM = "Exception find comments";
    private static final Logger logger = LogManager.getLogger();
    private static final CommentDaoImpl commentDao = CommentDaoImpl.getInstance();

    private static CommentService instance;

    private CommentService() {

    }

    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }


    public List<Comment> findAll() throws ServiceException {
        try {
            return commentDao.findAll();
        } catch (DaoException e) {
            logger.error(FIND_COMMENTS_PROBLEM + e);
            throw new ServiceException(FIND_COMMENTS_PROBLEM + e);
        }
    }

    public List<Comment> findByMovieId(long id) throws ServiceException {
        try {
            return commentDao.findCommentByIdMovies(id);
        } catch (DaoException e) {
            logger.error(FIND_COMMENTS_PROBLEM + e);
            throw new ServiceException(FIND_COMMENTS_PROBLEM + e);

        }
    }

    public int findCommentsAmountByMovieId(long id) throws ServiceException {
        try {
            return commentDao.findCommentsAmountByMovieId(id);
        } catch (DaoException e) {
            logger.error(FIND_COMMENTS_PROBLEM + e);
            throw new ServiceException(FIND_COMMENTS_PROBLEM + e);
        }
    }

    public List<Comment> findCommentRange(int offset, int size,int id) throws ServiceException {

        try {
            return commentDao.findCommentsRange(offset, size,id);
        } catch (DaoException e) {
            logger.error(FIND_COMMENTS_PROBLEM + e);
            throw new ServiceException(FIND_COMMENTS_PROBLEM + e);
        }
    }

    public Comment save(Comment comment) throws ServiceException {
        try {
            return commentDao.save(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    public int findCommentsAmount() throws ServiceException {
        try {
            return commentDao.findCommentsAmount();
        } catch (DaoException e) {
            logger.error(FIND_COMMENTS_PROBLEM + e);
            throw new ServiceException(FIND_COMMENTS_PROBLEM + e);
        }
    }

    public List<Comment> findCommentsRange(int amountQuery, int size,int id) throws ServiceException {

        try {
            return commentDao.findCommentsRange(amountQuery, size,id);
        } catch (DaoException e) {
            logger.error(FIND_COMMENTS_PROBLEM + e);
            throw new ServiceException(FIND_COMMENTS_PROBLEM + e);
        }
    }

}
