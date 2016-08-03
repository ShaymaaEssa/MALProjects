package com.facebookassignment.android.facebookassignment;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by MOSTAFA on 01/08/2016.
 */
public class URLConnector extends AsyncTask<String,Void,String>{
    String urlStr;
    HttpURLConnection urlConnection ;
    BufferedReader reader;
    Exception mException;
    String errorMsg;
    Context context;
    JSONParser parser;
    AsyncTaskFinishListener finishListener;
    ErrorHandling errorHandling;


    public URLConnector (Context context,JSONParser parser,AsyncTaskFinishListener finishListener,ErrorHandling errorHandling){
        this.context = context;
        this.parser = parser;
        this.finishListener = finishListener;
        this.errorHandling = errorHandling;
    }
    public void executeURL (String urlStr){
        this.urlStr = urlStr;
        execute(urlStr);
    }

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
    protected void onPostExecute(String s) {
        //to handle exception in doInBackGround method
        if(mException != null)
        {
            errorHandling.errorExceptionHandling(mException);
        }
        else
            try {
                if (finishListener != null)
                     finishListener.notifyUpdateData(parser.dataParsing(s));
            }catch (JSONException e){
               errorHandling.errorExceptionHandling(e);
            }




    }
}
