package com.example.aayush.aburrido;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ayushgarg on 12-Jun-16.
 */
public interface ApiInterface2 {
    @GET("venues/{number}")
    Call<JSONObject2> getVenues(@Path("number") String venue, @Query("token" )String token);
}
