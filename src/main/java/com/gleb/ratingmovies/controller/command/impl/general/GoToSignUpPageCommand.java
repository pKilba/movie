package com.gleb.ratingmovies.controller.command.impl.general;

import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.request.RequestContext;

public class GoToSignUpPageCommand implements Command {

    private static final String SIGN_UP = "/jsp/pages/sign-up.jsp";

    @Override
    public CommandResponse execute(RequestContext request) {

        return CommandResponse.forward(SIGN_UP);
    }
}
