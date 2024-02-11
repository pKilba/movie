package com.gleb.ratingmovies.service;

import com.gleb.ratingmovies.service.validator.impl.UserValidator;

public class LoginService {

    private static LoginService instance;
    private final UserValidator userValidator = UserValidator.getInstance();

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }


    public boolean isValid(String login, String password) {
        return userValidator.isValidPassword(password) && userValidator.isValidLogin(login);
    }


}
