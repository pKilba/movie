package com.gleb.ratingmovies.controller.command.impl.user;

import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.util.Attribute;


public class LogOutCommand implements Command {

    private static final String MOVIES = "/jsp/pages/login.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        request.addSession(Attribute.INVALIDATE_ATTRIBUTE, true);
        return CommandResponse.redirect(MOVIES);
    }
}
