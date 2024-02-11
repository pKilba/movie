package com.gleb.ratingmovies.controller.command;

public class CommandResponse {
    private final String page;
    private final boolean isRedirect;
    private String Line;

    public CommandResponse(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }

    public static CommandResponse redirect(String page) {
        return new CommandResponse(page, true);
    }

    public static CommandResponse forward(String page) {
        return new CommandResponse(page, false);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}

