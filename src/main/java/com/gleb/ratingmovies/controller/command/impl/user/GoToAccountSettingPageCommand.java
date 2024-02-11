package com.gleb.ratingmovies.controller.command.impl.user;

import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.UserService;
import com.gleb.ratingmovies.util.Attribute;

public class GoToAccountSettingPageCommand implements Command {


    private static final String SETTING = "/jsp/pages/account-settings.jsp";
    private static final UserService userService = UserService.getInstance();

    @Override
    public CommandResponse execute(RequestContext requestContext) throws ServiceException {

        long id = ParameterTaker.takeId(requestContext);
        User user = userService.findUserById(id);
        requestContext.addAttribute(Attribute.USER, user);
        return CommandResponse.forward(SETTING);
    }
}
