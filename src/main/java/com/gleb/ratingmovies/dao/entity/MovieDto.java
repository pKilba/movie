package com.gleb.ratingmovies.dao.entity;

public class MovieDto extends AbstractEntity<Long> {
    private String name;
    private String poster;
    private String about;
    private String releaseDate;
    private double rating;  // Replace amountLike and amountDislike with a single rating
    private Genre genre;
    private String producer;
    private int duration;
    private String background;
    private String link_movie;

    public MovieDto(String name, String poster, String about, String releaseDate, double rating, Genre genre, String producer, int duration, String background, String link_movie) {
        this.name = name;
        this.poster = poster;
        this.about = about;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genre = genre;
        this.producer = producer;
        this.duration = duration;
        this.background = background;
        this.link_movie = link_movie;
    }

    public String getLink_movie() {
        return link_movie;
    }

    public void setLink_movie(String link_movie) {
        this.link_movie = link_movie;
    }

    public MovieDto(String name, String poster, String about, String releaseDate, double rating, Genre genre, String producer, int duration, String background) {
        this.name = name;
        this.poster = poster;
        this.about = about;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genre = genre;
        this.producer = producer;
        this.duration = duration;
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
