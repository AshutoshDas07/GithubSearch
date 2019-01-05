package com.example.ashutosh_pc.githubsearch;

import android.os.Parcel;
import android.os.Parcelable;

class Items {
    String login, avatar_url;

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public Items() {
    }

    public Items(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }
}
