package com.gleb.ratingmovies.dao.entity;

import java.util.Arrays;

public enum Genre {
    COMEDY(1),
    THRILLER(2),
    ACTION(3),
    HORROR(4);


    private final int id;

    Genre(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public static Genre getById(int id) {
        return Arrays.stream(Genre.values())
                .filter(genre -> genre.getId() == id)
                .findFirst().orElse(null);
    }
}
