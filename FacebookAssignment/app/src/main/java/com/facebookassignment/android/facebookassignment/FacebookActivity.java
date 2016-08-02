package com.facebookassignment.android.facebookassignment;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FacebookActivity extends ActionBarActivity implements AsyncTaskFinishListener {

    Toolbar toolbar;
    ListView listView;
    PostAdapter adpt;
    List<PostModel> posts;

    URLConnector urlConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);


        listView = (ListView)findViewById(R.id.listview_facebookact_items);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Newsfeed");
        getSupportActionBar().setIcon(R.drawable.logo);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        posts = new ArrayList<PostModel>();
        String urlStr = "https://dl.dropboxusercontent.com/s/7rvknz9e6tfprun/facebookFeed.json";
       // new PostInformation().execute(urlStr);

        urlConnector = new URLConnector(getApplicationContext(),new FaceBookParser(),this);
        urlConnector.executeURL(urlStr);

        adpt = new PostAdapter(this,R.layout.facebook_item,posts);
        listView.setAdapter(adpt);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facebook, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void notifyUpdateData(List<PostModel> list) {
        adpt.setItemList(list);
        adpt.notifyDataSetChanged();
    }




}
