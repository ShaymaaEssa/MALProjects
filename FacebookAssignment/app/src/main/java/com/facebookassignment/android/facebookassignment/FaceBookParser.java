package com.facebookassignment.android.facebookassignment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOSTAFA on 03/08/2016.
 */
public class FaceBookParser extends JSONParser {
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


    List<PostModel> postModelList = new ArrayList<PostModel>();
    @Override
    public List dataParsing(String postJsonStr) throws JSONException {


        JSONArray postArray = new JSONArray(postJsonStr);

        for (int i = 0 ;i<postArray.length();i++){
            JSONObject jsonObject = postArray.getJSONObject(i);
            PostModel post = new PostModel(jsonObject.getString(NAME),
                    jsonObject.getString(COMMENTS_COUNT),
                    jsonObject.getString(LIKES_COUNT),
                    jsonObject.getString(POST_IMAGE),
                    jsonObject.getString(POST_DESC),
                    jsonObject.getString(POST_TIME),
                    jsonObject.getString(POST_TYPE),
                    jsonObject.getString(SHARES_COUNT),
                    jsonObject.getString(USER_IMAGE));

            postModelList.add(post);
        }

        return postModelList;


    }
}
