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



    public PostAdapter (Context context,int postItemResourceId,List<PostModel>posts){
        super(context,postItemResourceId,posts);
        this.posts = posts;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View v ;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.facebook_item,parent,false);
            viewHolder.name = (TextView) v.findViewById(R.id.txt_facebookact_name);
            viewHolder.time = (TextView) v.findViewById(R.id.txt_facebookact_time);
            viewHolder.personalImg = (ImageView)v.findViewById(R.id.img_facebookact_personalimg);
            viewHolder.postDesc = (TextView)v.findViewById(R.id.txt_facebookact_desc);
            viewHolder.postImg = (ImageView)v.findViewById(R.id.img_facebookact_postimg);
            viewHolder.likeInteractive = (TextView)v.findViewById(R.id.txt_facebookact_likeinteractive);
            viewHolder.commentInteractive = (TextView)v.findViewById(R.id.txt_facebookact_commentinteractive);
            viewHolder.shareInteractive = (TextView)v.findViewById(R.id.txt_facebookact_shareinteractive);
            viewHolder.likeBtn = (Button)v.findViewById(R.id.btn_facebookact_like);
            viewHolder.commentBtn = (Button)v.findViewById(R.id.btn_facebookact_comment);
            viewHolder.shareBtn = (Button) v.findViewById(R.id.btn_facebookact_share);

            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        
        PostModel post = posts.get(position);
        if (post != null){


            if (viewHolder.name != null)
                viewHolder.name.setText(post.getUserName());
            if (viewHolder.time != null)
                viewHolder.time.setText(post.getPostTime());
            Picasso.with(context)
                    .load(post.getUserPic())
                    .placeholder(R.drawable.personalimg_error)
                    .error(R.drawable.personalimg_error)
                    .into(viewHolder.personalImg);
            if (viewHolder.postDesc != null)
                viewHolder.postDesc.setText(post.getPostTxt());
            Picasso.with(context)
                    .load(post.getPostImg())
                    .placeholder(R.drawable.postimg_error)
                    .error(R.drawable.postimg_error)
                    .into(viewHolder.postImg);
            if (viewHolder.likeInteractive != null)
                viewHolder.likeInteractive.setText(post.getLikes());
            if (viewHolder.commentInteractive != null)
                viewHolder.commentInteractive.setText(post.getComments());
            if (viewHolder.shareInteractive != null)
                viewHolder.shareInteractive.setText(post.getPostShares());
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

    class ViewHolder {
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
    }


}
