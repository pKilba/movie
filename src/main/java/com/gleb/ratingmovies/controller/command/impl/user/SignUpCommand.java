package com.gleb.ratingmovies.controller.command.impl.user;

import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.controller.command.util.Parameter;
import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.dao.entity.UserRole;
import com.gleb.ratingmovies.dao.entity.UserStatus;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.SignUpService;
import com.gleb.ratingmovies.service.UserService;
import com.gleb.ratingmovies.util.Attribute;
import com.gleb.ratingmovies.util.LineHasher;

import java.sql.Timestamp;

public class SignUpCommand implements Command {

    private static final String SIGN_UP = "/jsp/pages/sign-up.jsp";
    private static final String SIGN_IN = "/jsp/pages/login.jsp";
    private static final SignUpService signUpService = SignUpService.getInstance();
    private static final String PRE_PHOTO = "notAva.jpg";
    private static final String INVALID_DATA_KEY = "invalid.data";
    private static final UserService userService = UserService.getInstance();

    @Override
    public CommandResponse execute(RequestContext requestContext) throws ServiceException {
        String password = ParameterTaker.takeString(Parameter.PASSWORD, requestContext);
        String name = ParameterTaker.takeString(Parameter.NAME, requestContext);
        String login = ParameterTaker.takeString(Parameter.LOGIN, requestContext);
        String email = ParameterTaker.takeString(Parameter.EMAIL, requestContext);
        String telegram = ParameterTaker.takeString(Parameter.TELEGRAM, requestContext);
        boolean isLoginOrMailOrTelegramExist =
                signUpService.isUserLoginExist(login) ||
                        signUpService.isUserEmailExist(email) ||
                        signUpService.isUserTelegramExist(telegram);
        if (!isLoginOrMailOrTelegramExist && signUpService.isValid(login, email, telegram, password, name)) {
            requestContext.addAttribute(Attribute.SAVED_LOGIN, login);
            requestContext.addAttribute(Attribute.SAVED_EMAIL, email);
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            password = ParameterTaker.takeString(Parameter.PASSWORD, requestContext);
            LineHasher lineHasher = new LineHasher();
            User user = User.builder().
                    setLogin(login).
                    setPassword(lineHasher.hashingLine(password)).
                    setUserRole(UserRole.USER).
                    setName(ParameterTaker.takeString(Parameter.NAME, requestContext)).
                    setEmail(ParameterTaker.takeString(Parameter.EMAIL, requestContext)).
                    setTelegram(ParameterTaker.takeString(Parameter.TELEGRAM, requestContext)).
                    setUserStatus(UserStatus.ACTIVE).
                    setCreateTime(nowTime).
                    setProfilePicture(PRE_PHOTO).build();

            userService.save(user);
            return CommandResponse.forward(SIGN_IN);

        } else {
            requestContext.addAttribute(Attribute.ERROR_MESSAGE, INVALID_DATA_KEY);
        }
        return CommandResponse.forward(SIGN_UP);

    }
}

