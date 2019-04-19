package com.bingogo.interceptorchain;

/**
 * Created by bingo on 19/04/19.
 */
public class Request {
    private String url;

    public Request(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
