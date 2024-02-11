package com.gleb.ratingmovies.controller.command.impl.user;

import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.controller.command.util.Parameter;
import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.AccountChangePassword;
import com.gleb.ratingmovies.service.UserService;
import com.gleb.ratingmovies.util.Attribute;
import com.gleb.ratingmovies.util.LineHasher;

public class ChangePasswordCommand implements Command {
    private static final String INVALID_DATA_KEY = "invalid.pass";
    private static final String VALID_DATA_KEY = "success.pass";
    private static final UserService userService = UserService.getInstance();
    private static final AccountChangePassword accountChangePassword = AccountChangePassword.getInstance();
    private static final String SETTINGS = "/jsp/pages/account-settings.jsp";
    private static final LineHasher lineHasher = new LineHasher();

    @Override
    public CommandResponse execute(RequestContext requestContext)
            throws ServiceException {
        long id = ParameterTaker.takeId(requestContext);
        User user = userService.findUserById(id);
        requestContext.addAttribute(Attribute.USER, user);
        String currentPassword = ParameterTaker.takeString(Parameter.CURRENT_PASSWORD, requestContext);
        String newPasswordFirst = ParameterTaker.takeString(Parameter.NEW_PASSWORD_FIRST, requestContext);
        String newPasswordSecond = ParameterTaker.takeString(Parameter.NEW_PASSWORD_SECOND, requestContext);
        String hashCurrentPassword = lineHasher.hashingLine(currentPassword);
        String hashNewPasswordFirst = lineHasher.hashingLine(newPasswordFirst);
        if (accountChangePassword.isCorrectPassword(newPasswordFirst, newPasswordSecond, user, hashCurrentPassword)) {
            userService.updatePasswordByUserId(id, hashNewPasswordFirst);
            requestContext.addAttribute(Attribute.SUCCESS_MESSAGE, VALID_DATA_KEY);
            return CommandResponse.forward(SETTINGS);
        }
        requestContext.addAttribute(Attribute.ERROR_MESSAGE, INVALID_DATA_KEY);
        return CommandResponse.forward(SETTINGS);
    }
}
