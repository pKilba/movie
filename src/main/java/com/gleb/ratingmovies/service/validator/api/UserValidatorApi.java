package com.gleb.ratingmovies.service.validator.api;

public interface UserValidatorApi {


    /**
     * @param login    login user
     * @param email    email user
     * @param telegram telegram user
     * @param password password user
     * @param name     name user
     * @return true or false, result correct or not correct date about user
     */
    boolean isValid(String login, String email, String telegram, String password, String name);
}