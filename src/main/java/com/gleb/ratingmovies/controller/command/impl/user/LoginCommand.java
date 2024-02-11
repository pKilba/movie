package com.gleb.ratingmovies.controller.command.impl.user;

import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.LoginService;
import com.gleb.ratingmovies.util.Attribute;
import com.gleb.ratingmovies.util.LineHasher;
import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.CommandName;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.util.Parameter;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.dao.entity.UserRole;
import com.gleb.ratingmovies.dao.entity.UserStatus;
import com.gleb.ratingmovies.service.UserService;

import java.util.Optional;

public class LoginCommand implements Command {

    private static final String PROFILE_PAGE_COMMAND = "ratingMovies?command=" + CommandName.PROFILE_PAGE + "&id=";
    private static final String INCORRECT_DATA_KEY = "incorrect";
    private static final String FREEZE_USER_KEY = "banned";
    private static final UserService service = UserService.getInstance();
    private static final LoginService loginService = LoginService.getInstance();
    private static final String LOGIN = "/jsp/pages/login.jsp";


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        String login = ParameterTaker.takeString(Parameter.LOGIN, request);
        String pass = ParameterTaker.takeString(Parameter.PASSWORD, request);
        LineHasher lineHasher = new LineHasher();
        String hashPass = lineHasher.hashingLine(pass);
        Optional<User> user = service.findUserByLoginAndPassword(login, hashPass);
        if (user.isPresent() && loginService.isValid(login, pass)) {
            if (user.get().getUserStatus() != UserStatus.BANNED) {
                long id = user.get().getId();
                UserRole role = user.get().getUserRole();
                request.addSession(Attribute.USER_ID, id);
                request.addSession(Attribute.ROLE, role);
                request.addSession(Attribute.LOGIN, user.get().getLogin());
                request.addSession(Attribute.PHOTO, user.get().getProfilePicture());
                return CommandResponse.redirect(PROFILE_PAGE_COMMAND + id);
            }
            request.addAttribute(Attribute.ERROR_MESSAGE, FREEZE_USER_KEY);
        } else {
            request.addAttribute(Attribute.ERROR_MESSAGE, INCORRECT_DATA_KEY);
        }
        request.addAttribute(Attribute.SAVED_LOGIN, login);
        return CommandResponse.forward(LOGIN);
    }
}

