package com.example.pleasure.instagramclient;

import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PhotosActivity extends ActionBarActivity {

    //public static final String CLIENT_ID = "";
    public static final String CLIENT_ID = "";
    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter aPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        // Ask for data, use 3rd party libs: android async http, picasso

        // Initialize array list
        photos = new ArrayList<>();

        // create the adapter, link it to source
        //aPhotos = new InstagramPhotosAdapter(this, photos);

        //ListView lvPhotos = (ListView) findViewById(R.id.lvphotos);



        // create the adapter
        aPhotos = new InstagramPhotosAdapter(this, photos);

        // find the Listview from the layout
        ListView lvPhotos = (ListView)findViewById(R.id.lvPhotos);

        // Set the adapter binding it to the ListView
        lvPhotos.setAdapter(aPhotos);

        // Trigger the API request
        fetchPopularPhotos();
    }

    public void fetchPopularPhotos() {
//        - Polupar: https://api.instagram.com/v1/media/popular?access_token=ACCESS-TOKEN
//
//        - Type: {"data" => [x] => "type"} ("image" or "video")
//        - URL: { "data" => [x] => "images" => "standard_resolution" => "url"}
//        - Caption: { "data" => [x] => "caption" => "text" }
//        - Author: data [x] user username
        // Instagram API media/popular


        // https://api.instagram.com/v1/media/popular?client_id=CLIENT-ID
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

        // Create the network client
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, null, new JsonHttpResponseHandler() {
            // onSuccess (worked, 200)

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // get a JSON dictionary
                // super.onSuccess(statusCode, headers, response);
                //Log.i("DEBUG", response.toString());


                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data");
                    // iterate array of posts
                    for (int i = 0; i < photosJSON.length(); i++) {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        // decode the attributes of the json into a data model
                        InstagramPhoto photo = new InstagramPhoto();
                        photo.username = photoJSON.getJSONObject("user").getString("username");
                        photo.caption = photoJSON.getJSONObject("caption").getString("text");
                        photo.imageType = photoJSON.getString("type");
                        photo.imageUrl = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        photo.likesCount = photoJSON.getJSONObject("likes").getInt("count");
                        if (photo.imageType.equals("image"))
                            photos.add(photo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                aPhotos.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
