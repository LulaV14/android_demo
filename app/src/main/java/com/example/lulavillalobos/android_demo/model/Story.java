package com.example.lulavillalobos.android_demo.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lulavillalobos on 9/4/17.
 */

public class Story implements  Comparable<Story> {

    private int story_id;
    private String story_title;
    private String title;
    private String story_url;
    private String created_at;
    private String author;
    private int deleted;

    public Story(int story_id, String title, String story_url, String author, String created_at, int deleted) {
        this.story_id = story_id;
        this.title = title;
        this.story_title = title;
        this.story_url = story_url;
        this.author = author;
        this.created_at = created_at;
        this.deleted = deleted;
    }

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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    /**
     * Method to compare Dates in order to be able to sort by Date
     */
    @Override
    public int compareTo(Story story) {
        String story_date_string1 = getCreatedAt();
        String story_date_string2 = story.getCreatedAt();

        Date story_date1 = null, story_date2 = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            story_date1 = sdf.parse(story_date_string1);
            story_date2 = sdf.parse(story_date_string2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return story_date1.compareTo(story_date2);
    }

}
