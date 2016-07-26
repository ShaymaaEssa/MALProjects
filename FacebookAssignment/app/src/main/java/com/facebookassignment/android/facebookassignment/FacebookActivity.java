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


public class FacebookActivity extends ActionBarActivity {

    Toolbar toolbar;
    ListView listView;
    PostAdapter adpt;
    List<PostModel> posts;

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
        new PostInformation().execute(urlStr);

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

    class PostInformation extends AsyncTask<String, Void, String> {
        HttpURLConnection urlConnection ;
        BufferedReader reader;

        Exception mException;

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);

                //open connection

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    //Toast.makeText(getApplicationContext(),"Nothing to display",Toast.LENGTH_SHORT).show();
                    return null;
                }
                 reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                String postJsonStr = buffer.toString();
                return postJsonStr;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                mException = e;
                //Toast.makeText(getApplicationContext(),"There is Malformed URL Exception",Toast.LENGTH_SHORT).show();
            } catch (ProtocolException e) {
                e.printStackTrace();
                mException = e;
                //Toast.makeText(getApplicationContext(),"There is Protocol Exception in the connection",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                mException = e;
                //Toast.makeText(getApplicationContext(),"There is IO Exception",Toast.LENGTH_SHORT).show();
            }finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        //Toast.makeText(getApplicationContext(),"Error closing stream",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(String postJsonStr) {
            super.onPostExecute(postJsonStr);

            //to handle exception in doInBackGround method
            if (mException instanceof MalformedURLException)
                Toast.makeText(getApplicationContext(),"There is Malformed URL Exception",Toast.LENGTH_SHORT).show();
            else if (mException instanceof ProtocolException)
                Toast.makeText(getApplicationContext(),"There is Protocol Exception in the connection",Toast.LENGTH_SHORT).show();
            else if (mException instanceof IOException)
                Toast.makeText(getApplicationContext(),"There is IO Exception",Toast.LENGTH_SHORT).show();

            try {
                getPostInfoFromJson(postJsonStr);
            }catch (JSONException e){
                Toast.makeText(getApplicationContext(),"Error in handling Json data",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getPostInfoFromJson(String postJsonStr) throws JSONException{
        // These are the names of the JSON objects that need to be extracted.
        final String NAME = "userName";
        final String COMMENTS_COUNT = "comments";
        final String LIKES_COUNT = "likes";
        final String POST_IMAGE = "postImage";
        final String POST_DESC = "postText";
        final String POST_TIME = "postTime";
        final String POST_TYPE = "postType";
        final String SHARES_COUNT = "shares";
        final String USER_IMAGE = "userPic";

        JSONObject postInfoJson = new JSONObject(postJsonStr);

        PostModel post = new PostModel(postInfoJson.getString(NAME),
                    postInfoJson.getString(COMMENTS_COUNT),
                    postInfoJson.getString(LIKES_COUNT),
                    postInfoJson.getString(POST_IMAGE),
                    postInfoJson.getString(POST_DESC),
                    postInfoJson.getString(POST_TIME),
                    postInfoJson.getString(POST_TYPE),
                    postInfoJson.getString(SHARES_COUNT),
                    postInfoJson.getString(USER_IMAGE));
        posts.add(post);
        adpt.setItemList(posts);
        adpt.notifyDataSetChanged();



    }
}
