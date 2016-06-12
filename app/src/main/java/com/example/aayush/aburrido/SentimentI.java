package com.example.aayush.aburrido;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ayushgarg on 12-Jun-16.
 */
public interface SentimentI {
    @GET("analyzesentiment/v1")
    Call<SentimentJ> getSentimentScore(@Query("text") String text, @Query("language") String lng,@Query("apikey" )String token);

}
