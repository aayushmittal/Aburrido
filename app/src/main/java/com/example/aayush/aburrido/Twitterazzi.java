package com.example.aayush.aburrido;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;


public class Twitterazzi {

    private static Twitter twitter;

    static final String TWITTER_CONSUMER_KEY = "2716182542-zunHYWwZQ68piLyHlg4opi8Hutp1LTz5CT93lrD";
    static final String TWITTER_CONSUMER_SECRET = "tUdtF2VXeW7vhNt9cDdKNdnU9Cb8KN2jsV6a7C4I79BDl";

    static final String URL_TWITTER_AUTH = "auth_url";
    static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
    static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";

    public Twitterazzi() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
        builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
        Configuration configuration = builder.build();

        TwitterFactory factory = new TwitterFactory(configuration);
        twitter = factory.getInstance();
    }

    public ArrayList<String> getTweet(String EVENT, String keyW) {
        ArrayList<String> out = new ArrayList<String>();

            try {
                Query query = new Query(keyW);
                QueryResult result;
                do {
                    result = twitter.search(query);
                    List<Status> tweets = result.getTweets();
                    for (Status tweet : tweets) {
                        out.add(tweet.getText());
                    }
                } while ((query = result.nextQuery()) != null);
            } catch (TwitterException te) {

            }


        return out;

    }
}

