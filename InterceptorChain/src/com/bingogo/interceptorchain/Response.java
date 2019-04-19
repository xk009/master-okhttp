package com.bingogo.interceptorchain;

/**
 * Created by bingo on 19/04/19.
 */
public class Response {
    private String url;

    public Response(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
