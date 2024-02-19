package com.gleb.ratingmovies.service.validator.impl;

import com.gleb.ratingmovies.service.validator.api.MovieValidatorApi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieValidator implements MovieValidatorApi {
    private static final String NAME_PATTERN = "[A-zА-яЁё\\s]+";
    private static final String NUMBER_PATTERN = "[0-9]+";
    private static final Pattern COMPILED_PATTERN_NAME = Pattern.compile(NAME_PATTERN);
    private static final Pattern COMPILED_PATTERN_NUMBER = Pattern.compile(NUMBER_PATTERN);
    private static final int MAX_FILM_NAME_LENGTH = 64;
    private static final int MAX_FILM_ABOUT_LENGTH = 4080;
    private static final int MAX_FILM_PRODUCER_LENGTH = 64;
    private static final int MAX_FILM_NUMBER = 10000;
    private static final int MIN_FILM_NUMBER = 0;
    private static final int MIN_FILM_ABOUT_LENGTH = 8;
    private static final int MIN_FILM_PRODUCER_LENGTH = 2;
    private static final int MIN_FILM_NAME_LENGTH = 2;

    private static MovieValidator instance;

    private MovieValidator() {

    }

    public static MovieValidator getInstance() {
        if (instance == null) {
            instance = new MovieValidator();
        }
        return instance;
    }


    @Override
    public boolean isValid(String name, int duration, String producer, String about) {
        if (isValidName(name)
                && isValidNameProducer(producer)
//                && isValidNumber(like)
//                && isValidNumber(dislike)
                && isValidNumber(duration)
                && isValidAbout(about)) {
            return true;
        }
        return false;

    }

    public boolean isValidName(String name) {
//        Matcher matcher = COMPILED_PATTERN_NAME.matcher(name);
//        boolean isCorrect = matcher.matches();
        if ( name.length() > MAX_FILM_NAME_LENGTH
                || name.length() < MIN_FILM_NAME_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean isValidNumber(int number) {
        Matcher matcher = COMPILED_PATTERN_NUMBER.matcher(Integer.toString(number));
        boolean isCorrect = matcher.matches();
        if (!isCorrect || number > MAX_FILM_NUMBER
                || number < MIN_FILM_NUMBER) {
            return false;
        }
        return true;
    }

    public boolean isValidNameProducer(String name) {
        Matcher matcher = COMPILED_PATTERN_NAME.matcher(name);
        boolean isCorrect = matcher.matches();
        if (!isCorrect || name.length() > MAX_FILM_PRODUCER_LENGTH
                || name.length() < MIN_FILM_PRODUCER_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean isValidAbout(String name) {
        if (name.length() > MAX_FILM_ABOUT_LENGTH
                || name.length() < MIN_FILM_ABOUT_LENGTH) {
            return false;
        }
        return true;
    }


}