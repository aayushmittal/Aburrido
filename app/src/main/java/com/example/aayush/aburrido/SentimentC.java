package com.example.aayush.aburrido;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ayushgarg on 12-Jun-16.
 */
public class SentimentC {
    private static SentimentI eService;

    public static SentimentI getInterface()
    {
        if (eService == null)
        {
           /* Gson gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                    .create();*/
            Gson gson=new GsonBuilder().create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.havenondemand.com/1/api/sync/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            //builder makes retrofit object in it
            //telling retrofit to use gson for conversion into object

            eService = retrofit.create(SentimentI.class);
        }
        return eService;
    }
}



