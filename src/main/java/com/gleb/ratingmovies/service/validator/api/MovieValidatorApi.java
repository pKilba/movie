package com.gleb.ratingmovies.service.validator.api;

public interface MovieValidatorApi {


    /**
     * @param name     name movie
     * @param duration duration movie
     * @param producer producer movie
     * @param about    about movie
     * @return true or false, result correct or not correct date about film
     */
    boolean isValid(String name, int duration, String producer, String about);
}
