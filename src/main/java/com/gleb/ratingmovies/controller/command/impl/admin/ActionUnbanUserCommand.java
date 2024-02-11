package com.gleb.ratingmovies.controller.command.impl.admin;

import com.gleb.ratingmovies.controller.command.CommandResponse;
import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.AdminService;


public class ActionUnbanUserCommand implements Command {

    private static final AdminService adminService = AdminService.getInstance();
    private static final String JSON = "json";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        String response = adminService.unbanUserById(request);
        CommandResponse commandResult = new CommandResponse(JSON, false);
        commandResult.setLine(response);
        return commandResult;
    }
}
