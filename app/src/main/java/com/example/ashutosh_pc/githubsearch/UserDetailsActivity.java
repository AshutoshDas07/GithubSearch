package com.example.ashutosh_pc.githubsearch;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserDetailsActivity extends AppCompatActivity {

    String default_url="https://api.github.com/users/";
    TextView profileName,repos,followers,following,bio,blogAddress;
    CircleImageView profileImage;
    LinearLayout about,blog,organizations;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);
        about=findViewById(R.id.about_details);
        blog=findViewById(R.id.blog_details);
        organizations=findViewById(R.id.orga);
        bio=findViewById(R.id.bio);
        blogAddress=findViewById(R.id.blog);
        repos=findViewById(R.id.repos_num);
        followers=findViewById(R.id.followers_num);
        following=findViewById(R.id.following_num);
        profileName=findViewById(R.id.profile_name);
        profileImage=findViewById(R.id.profile_image);

        Intent receivedIntent=getIntent();

        String currentItem=receivedIntent.getStringExtra("currentItem");
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder().url(default_url+currentItem).build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String userInfo=response.body().string();
                Gson gson = new Gson();

                final UserInfo myuser=gson.fromJson(userInfo,UserInfo.class);

                UserDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        profileName.setText(myuser.getName());
                        Picasso.get().load(myuser.getAvatar_url()).into(profileImage);
                        Log.e("TAG", "run: "+myuser.getPublic_repos() );
                        repos.setText(""+myuser.getPublic_repos());
                        followers.setText(""+myuser.getFollowers());
                        following.setText(""+myuser.getFollowing());
                        bio.setText(myuser.getBio());
                        blogAddress.setText(myuser.getBlog());
                    }
                });

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bio.getVisibility()==View.VISIBLE) {
                    bio.setVisibility(View.GONE);
                }else {
                    bio.setVisibility(View.VISIBLE);
                }
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blogAddress.getVisibility()==View.VISIBLE){
                    blogAddress.setVisibility(View.GONE);
                }else{
                    blogAddress.setVisibility(View.VISIBLE);
                }

            }
        });

    }
}
