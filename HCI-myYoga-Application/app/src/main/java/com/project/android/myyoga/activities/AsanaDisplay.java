package com.project.android.myyoga.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.project.android.myyoga.R;
import com.project.android.myyoga.config.Config;
import com.project.android.myyoga.config.ObjectPreferences;
import com.project.android.myyoga.config.SessionManager;
import com.project.android.myyoga.model.User;
import com.project.android.myyoga.model.YogaAsana;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class AsanaDisplay extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static AsyncHttpClient syncHttpClient= new SyncHttpClient();
    public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private static final String TAG = "AsanaDisplay";
    private static final int RECOVERY_REQUEST = 1;
    YogaAsana yAsana = new YogaAsana();
    private String username = "";
    private YouTubePlayerView youTubePlayerView;
    private ObjectPreferences objectPreferences;
    private ListView listview;
    ArrayAdapter<String> adapter;
    TextView textView;
    String yName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asana);
        Intent intent= getIntent();
        yName=intent.getStringExtra("YOGANAME");
        displayYoga();
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        listview = (ListView) findViewById(R.id.steps_list);
        youTubePlayerView.initialize(Config.YOUTUBE_API_KEY, this);
        textView = (TextView) findViewById(R.id.yoga_name);
        textView.setText(yName);
        objectPreferences = (ObjectPreferences) this.getApplication();
        SessionManager sessionManager = objectPreferences.getSessionManager();
        User user = sessionManager.getObject("User", User.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
        if (!restored) {
            youTubePlayer.cueVideo(yAsana.getYoutubeValue());
            String[] steps = (yAsana.getYogaDescription()).split(".-");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, steps);
            listview.setAdapter(adapter);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubePlayerView;
    }

    private void displayYoga() {
        Log.i(TAG, "Inside displayYoga");
        // TODO: GET the name from the other Activity
        //String name = ;
        RequestParams requestParams = new RequestParams();
        requestParams.add("yoganame", yName);
        //new getVideoData(requestParams).execute();
        invokeWS(requestParams);
    }

    private static AsyncHttpClient getClient()
    {
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null)
            return syncHttpClient;
        return asyncHttpClient;
    }
    private void invokeWS(RequestParams requestParams) {
        AsyncHttpClient client = new AsyncHttpClient();
        getClient().get("http://10.0.2.2:8080/MyYoga_-_Project_-_RESTAPI/yoga/fetchYoga", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                try {
                    String responseString = new String(response, "UTF-8");
                    JSONObject obj = new JSONObject(responseString);
                    Log.i(obj.toString(), "This is JSON response");
                    if (obj.getBoolean("Status")) {
                        JSONObject yogaJSON = obj.getJSONObject("Aasana");
                        yAsana.setYogaName(yogaJSON.getString("asanaName"));
                        yAsana.setYogaLevel(yogaJSON.getString("Level"));
                        yAsana.setYoutubeValue(yogaJSON.getString("youtubeId"));
                        yAsana.setYogaDescription(yogaJSON.getString("Steps"));
                    } else {
                        fetchFailed();
                    }

                } catch (JSONException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Resource not found", Toast.LENGTH_SHORT).show();
                } else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void fetchFailed() {
        //TODO: Redirect the activity to the List of yoga Asanas Activities.
        Toast.makeText(getBaseContext(), "No Fetch Possible", Toast.LENGTH_LONG).show();

    }

    class getVideoData extends AsyncTask<Void,String,Void>{

        RequestParams requestParams;
        public getVideoData(RequestParams requestParams) {
            this.requestParams = requestParams;
        }

        @Override
        protected Void doInBackground(Void... params) {
            invokeWS(requestParams);
            return null;
        }
    }
}