package com.gleb.ratingmovies.service.validator.impl;


import com.gleb.ratingmovies.service.validator.api.UserValidatorApi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class UserValidator implements UserValidatorApi {

    private static final String NAME_PATTERN = "[A-zА-яЁё]+";
    private static final String TELEGRAM_PATTERN = "^@[a-zA-Z0-9+]+$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    private static final Pattern COMPILED_PATTERN_NAME = Pattern.compile(NAME_PATTERN);
    private static final Pattern COMPILED_PATTERN_TELEGRAM = Pattern.compile(TELEGRAM_PATTERN);
    private static final Pattern COMPILED_PATTERN_EMAIL = Pattern.compile(EMAIL_PATTERN);
    private static final int MIN_USER_NAME_LENGTH = 2;
    private static final int MAX_USER_NAME_LENGTH = 32;
    private static final int MIN_LOGIN_LENGTH = 2;
    private static final int MAX_LOGIN_LENGTH = 32;
    private static final int MAX_EMAIL_LENGTH = 64;
    private static final int MIN_EMAIL_LENGTH = 2;
    private static final int MAX_TELEGRAM_LENGTH = 32;
    private static final int MIN_TELEGRAM_LENGTH = 2;
    private static UserValidator instance;

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        if (instance == null) {
            instance = new UserValidator();
        }
        return instance;
    }

    @Override
    public boolean isValid(String login, String email, String telegram, String password, String name) {
        if (isValidName(name)
                && isValidLogin(login)
                && isValidTelegram(telegram)
                && isValidPassword(password)
                && isValidEmail(email)) {
            return true;
        }
        return false;
    }

    public boolean isValidPassword(String password) {
        if (password == null || password.length() > MAX_LOGIN_LENGTH
                || password.length() < MIN_LOGIN_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean isValidTelegram(String telegram) {
        Matcher matcher = COMPILED_PATTERN_TELEGRAM.matcher(telegram);
        boolean isCorrect = matcher.matches();
        if (!isCorrect ||
                telegram.length() < MIN_TELEGRAM_LENGTH ||
                telegram.length() > MAX_TELEGRAM_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean isValidName(String name) {
        Matcher matcher = COMPILED_PATTERN_NAME.matcher(name);
        boolean isCorrect = matcher.matches();
        if (!isCorrect || name.length() > MAX_USER_NAME_LENGTH
                || name.length() < MIN_USER_NAME_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean isValidLogin(String login) {

        if (login == null || login.length() > MAX_LOGIN_LENGTH || login.length() < MIN_LOGIN_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean isValidEmail(String email) {
        Matcher matcher = COMPILED_PATTERN_EMAIL.matcher(email);
        boolean isCorrect = matcher.matches();
        if (!isCorrect || email.length() > MAX_EMAIL_LENGTH
                || email.length() < MIN_EMAIL_LENGTH
        ) {
            return false;
        }
        return true;
    }

}
