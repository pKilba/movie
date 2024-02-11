package com.gleb.ratingmovies.controller.command.impl.user;

import com.gleb.ratingmovies.util.Attribute;
import com.gleb.ratingmovies.util.ParameterTaker;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.CommandName;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.util.Parameter;
import com.gleb.ratingmovies.controller.command.request.RequestContext;

public class LocalizationCommand implements Command {
    private static final String RU = "ru";
    private static final String EN_LOCALE = "en_US";
    private static final String RU_LOCALE = "ru_RU";
    private static final String PARAMETER_SPLITERATOR = "&";
    private static final String LOGIN_PAGE = "ratingMovies?command=" + CommandName.LOGIN_PAGE;
    private static final String SIGN_UP_PAGE = "ratingMovies?command=" + CommandName.SIGN_UP_PAGE;

    @Override
    public CommandResponse execute(RequestContext requestContext) {
        String language = ParameterTaker.takeString(Parameter.LANGUAGE, requestContext);
        String locale = getLocaleByLanguage(language);
        requestContext.addSession(Attribute.LANGUAGE, locale);
        String page = requestContext.getHeader();
        if (page != null) {
            String prevCommand = extractCommand(page);
            if (CommandName.LOGIN.equals(prevCommand) || CommandName.SIGN_UP.equals(prevCommand)) {
                page = changeCommandToCommandPage(prevCommand);
            }
        }
        return CommandResponse.redirect(page);
    }

    private String getLocaleByLanguage(String language) {
        if (RU.equals(language)) {
            return RU_LOCALE;
        }
        return EN_LOCALE;
    }

    private String changeCommandToCommandPage(String prevCommand) {
        switch (prevCommand) {
            case CommandName.LOGIN:
                return LOGIN_PAGE;
            case CommandName.SIGN_UP:
                return SIGN_UP_PAGE;
            default:
                return EN_LOCALE;
        }


    }

    private String extractCommand(String url) {
        int commandIndex = url.indexOf(Parameter.COMMAND) + Parameter.COMMAND.length() + 1;
        int lastCommandIndex = url.indexOf(PARAMETER_SPLITERATOR);
        if (lastCommandIndex == -1) {
            return url.substring(commandIndex);
        } else {
            return url.substring(commandIndex, lastCommandIndex);
        }
    }
}
