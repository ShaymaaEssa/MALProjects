package com.facebookassignment.android.facebookassignment;

/**
 * Created by MOSTAFA on 26/07/2016.
 */
public class PostModel {
    String userName;
    String comments;
    String likes;
    String postImg;
    String postTxt;
    String postTime;
    String postType;
    String postShares;
    String userPic;

    public PostModel(String userName, String comments, String likes, String postImg, String postTxt, String postTime, String postType, String postShares, String userPic) {
        this.userName = userName;
        this.comments = comments;
        this.likes = likes;
        this.postImg = postImg;
        this.postTxt = postTxt;
        this.postTime = postTime;
        this.postType = postType;
        this.postShares = postShares;
        this.userPic = userPic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    public String getPostTxt() {
        return postTxt;
    }

    public void setPostTxt(String postTxt) {
        this.postTxt = postTxt;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostShares() {
        return postShares;
    }

    public void setPostShares(String postShares) {
        this.postShares = postShares;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }
}
