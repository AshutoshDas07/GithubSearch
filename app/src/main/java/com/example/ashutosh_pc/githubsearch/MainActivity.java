package com.example.ashutosh_pc.githubsearch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String user_Input;
    String default_Input;
    String url;
    GitAdapter myadapter;
    final OkHttpClient client=new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchMyQuery((android.support.v7.widget.SearchView)search.getActionView());
        return true;

    }

    private void SearchMyQuery(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                user_Input= query;
                default_Input="https://api.github.com/search/users?q=";
                url=default_Input+user_Input;
                if(url.equals(default_Input)){
                    Toast.makeText(MainActivity.this,"Enter the Name",Toast.LENGTH_SHORT).show();

                }else{
                    final Request request = new Request.Builder().url(url).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            final String result = response.body().string();
                            Gson gson = new Gson();
                            SearchResult object_Result=gson.fromJson(result,SearchResult.class);
                            final ArrayList<Items> item=object_Result.getItems();

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void run() {
                                    RecyclerView myView =findViewById(R.id.items_List);
                                    myadapter = new GitAdapter(item);
                                    myView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                    myView.setAdapter(myadapter);

                                }
                            });
                        }
                    });
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
