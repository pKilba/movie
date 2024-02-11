package com.gleb.ratingmovies.dao.entity;

public final class ColumnName {
    private ColumnName() {
    }
    //Table "users"

    public static final String USER_ID = "user_id";
    public static final String USER = "user";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role_id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "mail";
    public static final String USER_TELEGRAM = "account_telegram";
    public static final String USER_STATUS = "status_id";
    public static final String USER_CREATE_TIME = "create_time";
    public static final String USER_PROFILE_PICTURE = "profile_picture";

    //Table movies
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_POSTER = "poster";
    public static final String MOVIE_ABOUT = "about";
    public static final String MOVIE_RELEASE_DATE = "movie_release_date";
    public static final String MOVIE_RATING = "rating";
    //public static final String MOVIE_AMOUNT_DISLIKE = "amount_dislike";
    public static final String MOVIE_GENRE_ID = "genre_id";
    public static final String MOVIE_NAME = "name";
    public static final String MOVIE_PRODUCER = "producer";
    public static final String MOVIE_DURATION = "duration";
    public static final String MOVIE_BACKGROUND = "background";
    public static final String MOVIE_LINK = "link_movie";


    //Table comments
    public static final String COMMENT_ID = "comment_id";
    public static final String COMMENT_MESSAGE = "message";
    public static final String COMMENT_MOVIE_ID = "movie_id";
    public static final String COMMENT_USER_ID = "user_id";
    public static final String COMMENT_CREATE_TIME = "create_time";

}
