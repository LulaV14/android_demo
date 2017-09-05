package com.example.lulavillalobos.android_demo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lulavillalobos on 9/4/17.
 */

public class StoryList {

    @SerializedName("hits")
    private List<Story> stories;

    public List<Story> getStories() {
        return stories;
    }
}
