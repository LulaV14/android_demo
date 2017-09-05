package com.example.lulavillalobos.android_demo.model;

import java.util.Date;

/**
 * Created by lulavillalobos on 9/4/17.
 */

public class Story {

    private int story_id;
    private String story_title;
    private String title;
    private String story_url;
    private String created_at;
    private String author;

    public Story() {

    }

    public int getId() {
        return story_id;
    }

    public void setId(int story_id) {
        this.story_id = story_id;
    }

    public String getStoryTitle() {
        return story_title;
    }

    public void setStoryTitle(String story_title) {
        this.story_title = story_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return story_url;
    }

    public void setUrl(String story_url) {
        this.story_url = story_url;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
