package com.facebookassignment.android.facebookassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOSTAFA on 26/07/2016.
 */
public class PostAdapter extends ArrayAdapter<PostModel> {

    List<PostModel> posts = new ArrayList<PostModel>();
    Context context;

    TextView name;
    TextView time;
    ImageView personalImg;
    TextView postDesc;
    ImageView postImg;
    TextView likeInteractive;
    TextView shareInteractive;
    TextView commentInteractive;
    Button likeBtn;
    Button commentBtn;
    Button shareBtn;

    public PostAdapter (Context context,int postItemResourceId,List<PostModel>posts){
        super(context,postItemResourceId,posts);
        this.posts = posts;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v = convertView;
        if (v == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.facebook_item,parent,false);
        }
        
        PostModel post = posts.get(position);
        if (post != null){
            name = (TextView) v.findViewById(R.id.txt_facebookact_name);
            time = (TextView) v.findViewById(R.id.txt_facebookact_time);
            personalImg = (ImageView)v.findViewById(R.id.img_facebookact_personalimg);
            postDesc = (TextView)v.findViewById(R.id.txt_facebookact_desc);
            postImg = (ImageView)v.findViewById(R.id.img_facebookact_postimg);
            likeInteractive = (TextView)v.findViewById(R.id.txt_facebookact_likeinteractive);
            commentInteractive = (TextView)v.findViewById(R.id.txt_facebookact_commentinteractive);
            shareInteractive = (TextView)v.findViewById(R.id.txt_facebookact_shareinteractive);
            likeBtn = (Button)v.findViewById(R.id.btn_facebookact_like);
            commentBtn = (Button)v.findViewById(R.id.btn_facebookact_comment);
            shareBtn = (Button) v.findViewById(R.id.btn_facebookact_share);

            if (name != null)
                name.setText(post.getUserName());
            if (time != null)
                time.setText(post.getPostTime());
            Picasso.with(context)
                    .load(post.getUserPic())
                    .placeholder(R.drawable.personalimg_error)
                    .error(R.drawable.personalimg_error)
                    .into(personalImg);
            if (postDesc != null)
                postDesc.setText(post.getPostTxt());
            Picasso.with(context)
                    .load(post.getPostImg())
                    .placeholder(R.drawable.postimg_error)
                    .error(R.drawable.postimg_error)
                    .into(postImg);
            if (likeInteractive != null)
                likeInteractive.setText(post.getLikes());
            if (commentInteractive != null)
                commentInteractive.setText(post.getComments());
            if (shareInteractive != null)
                shareInteractive.setText(post.getPostShares());


        }

        return v;

    }

    @Override
    public int getCount() {
        if (posts != null)
            return posts.size();
        else return 0;
    }

    @Override
    public PostModel getItem(int position) {
        if (posts!= null)
            return posts.get(position);
        else return null;
    }

    @Override
    public long getItemId(int position) {
        if (posts != null)
            return posts.get(position).hashCode();
        else return 0;
    }

    public List<PostModel> getItemList(){
        return posts;
    }

    public void setItemList (List<PostModel> posts){
        this.posts = posts;
    }
}
