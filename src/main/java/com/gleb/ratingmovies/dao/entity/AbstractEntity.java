package com.gleb.ratingmovies.dao.entity;

public abstract class AbstractEntity<T> {
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

}
