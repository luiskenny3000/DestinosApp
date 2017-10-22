package com.kaisapp.destinosapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<Destiny> list = new ArrayList<>();
    RecyclerView recyclerView;
    DestinyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new DestinyAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestData();
    }

    private void requestData(){
        Request request = new Request.Builder()
                .url("https://kaisapp-api.herokuapp.com/api/hotels")
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String json = response.body().string();
                    Log.i("JSON", json);

                    Type listType = new TypeToken<ArrayList<Destiny>>() {}.getType();
                    ArrayList<Destiny> listTemp = (ArrayList<Destiny>)new Gson().fromJson(json, listType);

                    if(listTemp!=null && !listTemp.isEmpty()){
                        list.clear();
                        list.addAll(listTemp);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }

                }
            }
        });
    }
}
