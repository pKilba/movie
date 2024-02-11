package com.gleb.ratingmovies.controller.command;

import com.gleb.ratingmovies.controller.command.api.Command;
import com.gleb.ratingmovies.controller.command.impl.admin.*;
import com.gleb.ratingmovies.controller.command.impl.general.*;
import com.gleb.ratingmovies.controller.command.impl.user.*;

import java.util.Arrays;

public enum ApplicationCommand {
    GO_TO_SIGN_UP_PAGE(new GoToSignUpPageCommand(), CommandName.SIGN_UP_PAGE),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand(), CommandName.LOGIN_PAGE),
    GO_TO_USER_PAGE(new GoToUserPageCommand(), CommandName.PROFILE_PAGE),
    GO_TO_MOVIE_PAGE(new GoToMoviePageCommand(), CommandName.MOVIE_PAGE),
    LEAVE_COMMENT(new AddCommentCommand(), CommandName.LEAVE_COMMENT),
    CHANGE_PASSWORD(new ChangePasswordCommand(), CommandName.CHANGE_PASSWORD),
    GO_TO_ACCOUNT_SETTING_PAGE(new GoToAccountSettingPageCommand(), CommandName.ACCOUNT_SETTINGS_PAGE),
    GO_TO_CREATE_MOVIE(new GoToAddMoviePageCommand(), CommandName.CREATE_MOVIE_PAGE),
    CREATE_MOVIE(new CreateMovieCommand(), "createMovie"),
    GO_TO_USERS_PAGE(new GoToUsersPageCommand(), CommandName.USERS_PAGE),
    GO_TO_MOVIES_PAGE(new GoToMoviesPageCommand(), CommandName.MOVIES_PAGE),
    SIGN_UP(new SignUpCommand(), CommandName.SIGN_UP),
    LOGIN(new LoginCommand(), CommandName.LOGIN),
    ACTION_BAN_USER(new ActionBanUserCommand(), CommandName.ACTION_BAN_USER),
    ACTION_UNBAN_USER(new ActionUnbanUserCommand(), CommandName.ACTION_UNBAN_USER),
    CHANGE_GENERAL_INFO(new ChangeGeneralInfoCommand(), CommandName.CHANGE_GENERAL_INFO),
    GO_TO_REVIEWS_PAGE(new GoToReviewsPageCommand(), CommandName.REVIEWS_PAGE),
    LOGOUT(new LogOutCommand(), CommandName.LOGOUT),
    LOCALIZATION(new LocalizationCommand(), CommandName.LOCALIZATION);

    private final Command command;
    private final String commandName;

    ApplicationCommand(Command command, String commandName) {
        this.command = command;
        this.commandName = commandName;
    }

    public Command getCommand() {
        return command;
    }

    public static ApplicationCommand getByString(String commandString) {
        return Arrays.stream(ApplicationCommand.values())
                .filter(command -> command.toString()
                        .equalsIgnoreCase(commandString))
                .findFirst()
                .orElse(null);
    }

    public String getCommandName() {
        return commandName;
    }

    public static Command of(String name) {
        for (ApplicationCommand action : ApplicationCommand.values()) {
            if (action.getCommandName().equalsIgnoreCase(name)) {
                return action.command;
            }
        }
        return null;
    }
}
