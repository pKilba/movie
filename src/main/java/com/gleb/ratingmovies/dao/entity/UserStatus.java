package com.gleb.ratingmovies.dao.entity;

import java.util.Arrays;

public enum UserStatus {
    ACTIVE(1),
    BANNED(2);
    private final int id;

    UserStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserStatus getById(int id) {
        return Arrays.stream(UserStatus.values())
                .filter(status -> status.getId() == id)
                .findFirst().orElse(null);
    }
}
