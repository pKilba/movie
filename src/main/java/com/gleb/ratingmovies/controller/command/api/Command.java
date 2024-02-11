package com.gleb.ratingmovies.controller.command.api;

import com.gleb.ratingmovies.controller.command.ApplicationCommand;
import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.exception.ServiceException;

public interface Command {

    /**
     * Executes request using parameters of RequestContext object and returns
     * CommandResult object with necessary transaction instructions
     *
     * @param request an object which contains all request parameters
     *                and attributes and session attributes.
     * @return a command result with transaction instructions.
     * @throws ServiceException if logical errors occurs and also
     *                          it's a wrapper for lower errors.
     */
    CommandResponse execute(RequestContext request) throws ServiceException;

    /**
     * with name.
     *
     * @param name the name of command
     * @return the command
     */
    static Command withName(String name) {
        return ApplicationCommand.of(name);
    }

}
