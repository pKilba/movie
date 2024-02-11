package com.gleb.ratingmovies.service;

import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.service.validator.impl.UserValidator;

public class AccountChangePassword {

    private static final UserValidator userValidator = UserValidator.getInstance();

    private static AccountChangePassword instance;

    private AccountChangePassword() {

    }

    public static AccountChangePassword getInstance() {
        if (instance == null) {
            instance = new AccountChangePassword();
        }
        return instance;
    }


    public boolean
    isCorrectPassword(String newPasswordFirst, String newPasswordSecond, User user, String currentPassword) {


        return isValidPassword(newPasswordFirst) &&
                isEqualFirstNewPasswordAndSecond(newPasswordFirst, newPasswordSecond) &&
                isEqualPasswordUserAndCurrentPassword(user, currentPassword);
    }

    public boolean isEqualFirstNewPasswordAndSecond(String newPasswordFirst, String newSecondPassword) {
        return newPasswordFirst.equals(newSecondPassword);
    }

    public boolean isEqualPasswordUserAndCurrentPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public boolean isValidPassword(String password) {

        return userValidator.isValidPassword(password);
    }


}
