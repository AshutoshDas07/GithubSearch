package com.example.ashutosh_pc.githubsearch;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo {

    String login,name,blog,bio,avatar_url;
    Integer public_repos,followers,following;

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getBlog() {
        return blog;
    }

    public String getBio() {
        return bio;
    }

    public Integer getPublic_repos() {
        return public_repos;
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollowing() {
        return following;
    }

}
