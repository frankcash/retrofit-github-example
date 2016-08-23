package com.example.foobar.heloeworldrest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foobar.heloeworldrest.API.gitapi;
import com.example.foobar.heloeworldrest.Bean.GithubBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity implements Callback<GithubBean> {

    public final static String EXTRA_USERNAME = "foobar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){

        EditText editText = (EditText) findViewById(R.id.edit_username);
        String username = editText.getText().toString();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd't'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(gitapi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        gitapi gitApi = retrofit.create(gitapi.class);

        Call<GithubBean> call = gitApi.getUser(username);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<GithubBean> call, Response<GithubBean> response){
        int code = response.code();
        if (code == 200){
            GithubBean user = response.body();
//            Toast.makeText(this, "Got the user: " + user.name, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, DisplayUserInfo.class);
            intent.putExtra(EXTRA_USERNAME, user.name);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Did not work: " + String.valueOf(code), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<GithubBean> call, Throwable t){
        Toast.makeText(this, "Nope", Toast.LENGTH_LONG).show();
    }
}
