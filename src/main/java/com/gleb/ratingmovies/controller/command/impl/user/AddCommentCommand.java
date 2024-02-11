package com.gleb.ratingmovies.controller.command.impl.user;

import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.CommandName;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.dao.entity.Comment;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.CommentService;
import com.gleb.ratingmovies.util.Attribute;

import java.sql.Timestamp;

public class AddCommentCommand implements Command {

    private static final String RATING_MOVIES_COMMAND = "ratingMovies?command=" + CommandName.MOVIE_PAGE + "&id=";
    private static final CommentService COMMENT_SERVICE = CommentService.getInstance();
    private static final String LEAVE_COMMENT = "leaveComment";


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        long idMovie = ParameterTaker.takeIdNow(request);
        Comment newComment = Comment.builder().
                setMessage(request.getRequestParameter(LEAVE_COMMENT))
                .setMovie(idMovie).
                setUser(id).
                setCreateTime(new Timestamp(System.currentTimeMillis())).build();
        COMMENT_SERVICE.save(newComment);
        request.addAttribute(Attribute.ID, idMovie);
        return CommandResponse.redirect(RATING_MOVIES_COMMAND + idMovie);
    }
}
