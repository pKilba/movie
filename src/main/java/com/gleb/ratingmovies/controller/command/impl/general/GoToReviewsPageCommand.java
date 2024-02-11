package com.gleb.ratingmovies.controller.command.impl.general;

import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.service.CommentsPagesWithPagination;

public class GoToReviewsPageCommand implements Command {

    private static final String REVIEWS = "/jsp/pages/reviews.jsp";
    private static final CommentsPagesWithPagination commentsPagesWithPagination = CommentsPagesWithPagination.getInstance();


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        commentsPagesWithPagination.processCommandWithPagination(request);
        return CommandResponse.forward(REVIEWS);
    }
}
