package com.gleb.ratingmovies.controller.command.impl.general;

import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.request.RequestContext;

public class GoToLoginPageCommand implements Command {
    private static final String LOGIN = "/jsp/pages/login.jsp";

    @Override
    public CommandResponse execute(RequestContext request) {
        return CommandResponse.forward(LOGIN);
    }
}
