package com.gvenet.mby.pojos;

import java.io.Serializable;

public class Item implements Serializable {
    private Long id;

    @Override
    public String toString() {
        return "title: " + title + " | type: " + type + " | description: " + description + " | url: " + url;
    }

    private String title;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String type;
    private String description;
    private String url;

    public Item() {
    }
    public Item(String title, String type, String description, String url) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
