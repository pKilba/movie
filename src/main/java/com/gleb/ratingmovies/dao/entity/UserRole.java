package com.gleb.ratingmovies.dao.entity;

import com.gleb.ratingmovies.controller.command.CommandName;

import java.util.HashSet;
import java.util.Set;

public enum UserRole {
    USER(CommandName.MOVIES_PAGE, CommandName.SIGN_UP_PAGE,
            CommandName.REVIEWS_PAGE, CommandName.MOVIE_PAGE, CommandName.LOCALIZATION, CommandName.ACCOUNT_SETTINGS_PAGE, CommandName.PROFILE_PAGE,
            CommandName.CHANGE_GENERAL_INFO, CommandName.LOGOUT, CommandName.LEAVE_COMMENT, CommandName.CHANGE_PASSWORD),

    ADMIN(CommandName.SIGN_UP_PAGE, CommandName.ACCOUNT_SETTINGS_PAGE,
            CommandName.MOVIES_PAGE, CommandName.PROFILE_PAGE, CommandName.CHANGE_GENERAL_INFO, CommandName.LOGOUT, CommandName.REVIEWS_PAGE,
            CommandName.MOVIE_PAGE, CommandName.LEAVE_COMMENT, CommandName.CREATE_MOVIE_PAGE,
            CommandName.USERS_PAGE, CommandName.CHANGE_PASSWORD, CommandName.ACTION_BAN_USER, CommandName.ACTION_UNBAN_USER,
            CommandName.LOCALIZATION, CommandName.CREATE_MOVIE),

    GUEST(CommandName.MOVIES_PAGE, CommandName.SIGN_UP_PAGE, CommandName.LOGIN_PAGE,
            CommandName.REVIEWS_PAGE, CommandName.MOVIE_PAGE, CommandName.LOGIN, CommandName.SIGN_UP, CommandName.LOCALIZATION);


    private final Set<String> commandsName = new HashSet<>();


    UserRole(String... commandsName) {
        this.commandsName.addAll(Set.of(commandsName));
    }

    public boolean isExistCommandName(String command) {
        return this.commandsName.contains(command);
    }

}
