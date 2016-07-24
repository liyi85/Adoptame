package com.example.andrearodriguez.adoptame.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public class Bebe {

    @JsonIgnore
    private String id;

    @JsonIgnore
    private boolean publishedByMe;

    private String url;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPublishedByMe() {
        return publishedByMe;
    }

    public void setPublishedByMe(boolean publishedByMe) {
        this.publishedByMe = publishedByMe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
