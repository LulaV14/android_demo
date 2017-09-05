package com.example.lulavillalobos.android_demo.network;

import com.example.lulavillalobos.android_demo.model.StoryList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lulavillalobos on 9/4/17.
 */

public interface ApiInterface {

    @GET("search_by_date?query=android")
    Call<StoryList> getStories();
}
