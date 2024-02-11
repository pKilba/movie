package com.gleb.ratingmovies.controller.command.impl.user;

import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.util.Attribute;
import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.util.Parameter;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.service.AccountInfoChangeService;
import com.gleb.ratingmovies.service.UserService;

public class ChangeGeneralInfoCommand implements Command {

    private static final String INVALID_DATA_KEY = "invalid.data";
    private static final String VALID_DATA_KEY = "success";
    private static final UserService userService = UserService.getInstance();
    private static final String USER_ACCOUNT_SETTINGS = "/jsp/pages/account-settings.jsp";
    private static final AccountInfoChangeService accountInfoChangeService = AccountInfoChangeService.getInstance();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        long id = ParameterTaker.takeId(request);
        String name = ParameterTaker.takeString(Parameter.NAME, request);
        String email = ParameterTaker.takeString(Parameter.EMAIL, request);
        String telegram = ParameterTaker.takeString(Parameter.TELEGRAM, request);
        User user = userService.findUserById(id);
        if (accountInfoChangeService.isValidInfoAccount(email, telegram, name)) {
            userService.updateNameEmailTelegramById(name, email, telegram, id);
            request.addAttribute(Attribute.SUCCESS_MESSAGE, VALID_DATA_KEY);
            return CommandResponse.forward(USER_ACCOUNT_SETTINGS);
        }
        request.addAttribute(Attribute.ERROR_MESSAGE, INVALID_DATA_KEY);
        request.addAttribute(Attribute.USER, user);
        return CommandResponse.forward(USER_ACCOUNT_SETTINGS);
    }
}
