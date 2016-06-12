package com.example.aayush.aburrido;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ayushgarg on 12-Jun-16.
 */
public interface ApiInterface {
    @GET("events/search")
    Call<JSONObject> getEvents(@Query("venue.city") String venue, @Query("token" )String token, @Query("page") String page);
}
