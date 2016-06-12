package com.example.aayush.aburrido;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ListActivity extends AppCompatActivity {
    ArrayAdapterClass adapter;
    ArrayList<String> list;
    JSONObject object;
    SentimentJ sentimentJ;
    ArrayList<Event> locs;
    JSONObject2 object2;
    Data data;
    int j;
    double score = 0.0;
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                //actionBar.show();
            }
          //  mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
//    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            if (AUTO_HIDE) {
//                delayedHide(AUTO_HIDE_DELAY_MILLIS);
//            }
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
       // findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        ListView listView = (ListView) findViewById(R.id.fullscreen_content);
        list = new ArrayList<>();
        list.add("Events");
        adapter = new ArrayAdapterClass(ListActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Call<JSONObject> call = ApiClient.getInterface().getEvents("New+York","GNNCNM4DODCK25W33MWD","1");
                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        if (response.isSuccessful()) {
                            object = response.body();
                            data = object.result;
//                    if(gridActivity!=null){
//                        adapter = new GridAdapter(gridActivity,movies);
//                    }else{
//                        adapter = new GridAdapter(mainActivity,movies);
//                    }
//                    gridView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                            if(data==null) {
                                Toast.makeText(ListActivity.this, "Null stuff", Toast.LENGTH_SHORT).show();

                            }
                            else
                                Toast.makeText(ListActivity.this, "Downloaded", Toast.LENGTH_SHORT).show();
                            ArrayList<Data.Events> fEvents;
                            fEvents = data.getEvents();

                            for(j = 0;j<fEvents.size();j++){
                                Data.Events f = fEvents.get(j);
                                String venue = f.getVenueId();
                                Call<JSONObject2> call2 = ApiClient2.getInterface().getVenues(venue,"1");
                                call2.enqueue(new Callback<JSONObject2>() {
                                    @Override
                                    public void onResponse(Call<JSONObject2> call2, Response<JSONObject2> response) {
                                        if (response.isSuccessful()) {
                                            object2 = response.body();
                                            locs.add(j,object2.result);
//                    if(gridActivity!=null){
//                        adapter = new GridAdapter(gridActivity,movies);
//                    }else{
//                        adapter = new GridAdapter(mainActivity,movies);
//                    }
//                    gridView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                                            if(data==null) {
                                                Toast.makeText(ListActivity.this, "Null stuff", Toast.LENGTH_SHORT).show();

                                            }
                                            else
                                                Toast.makeText(ListActivity.this, "Downloaded", Toast.LENGTH_SHORT).show();
                                             }
                                    }

                                    @Override
                                    public void onFailure(Call<JSONObject2> call2, Throwable t) {
//                if(gridActivity!=null){
//                    Toast.makeText(gridActivity, "Failed to download", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(mainActivity, "Failed to download", Toast.LENGTH_SHORT).show();
//                }
                                        Toast.makeText(ListActivity.this, "Failed to download", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            KeywordDatabaseSchema db = new KeywordDatabaseSchema(ListActivity.this);
                            for(j=0;j< locs.size();j++){
                                db.insertKeyWord(data.events.get(j).id,"event",data.events.get(j).getName().getText());
                                Twitterazzi twitterazzi = new Twitterazzi();
                                ArrayList<String> tweets = twitterazzi.getTweet(data.events.get(j).getName().getText(),data.events.get(j).getName().getText());
                                EventDataBaseSchema eventDb = new EventDataBaseSchema(ListActivity.this);
                                score = 0.0;
                                for(int index = 0;index<tweets.size();index++){
                                    Call<SentimentJ> callS = SentimentC.getInterface().getSentimentScore(tweets.get(index),"eng","d69a2e47-5c64-48f8-909a-b54258bddfcf");
                                    callS.enqueue(new Callback<SentimentJ>() {
                                        @Override
                                        public void onResponse(Call<SentimentJ> call, Response<SentimentJ> response) {
                                            if(response.isSuccessful()){
                                                sentimentJ = response.body();
                                                score += sentimentJ.getSentiment().getAggregate().score + 1 ;
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<SentimentJ> call, Throwable t) {

                                        }
                                    });
                                }
                                if(tweets.size()!=0){
                                    score = score/tweets.size();
                                }
                                else{
                                    score = 0;
                                }

                                eventDb.insertEntry(data.events.get(j).id,""+tweets.size(),""+score,data.events.get(j).getName().getText(),locs.get(j).getLatitude()+","+locs.get(j).getLatitude());

                            }
                            EventDataBaseSchema eventNew = new EventDataBaseSchema(ListActivity.this);
                            ArrayList<cursorObject> c = eventNew.getTop5Entry();


                            Intent i = new Intent(ListActivity.this,ResultsActivity.class);
                            i.putExtra("arrayList",c);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
//                if(gridActivity!=null){
//                    Toast.makeText(gridActivity, "Failed to download", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(mainActivity, "Failed to download", Toast.LENGTH_SHORT).show();
//                }
                        Toast.makeText(ListActivity.this, "Failed to download", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
