package com.example.foobar.heloeworldrest.API;

import com.example.foobar.heloeworldrest.Bean.GithubBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by foobar on 8/22/16.
 */
public interface gitapi {

    String ENDPOINT = "https://api.github.com";

    @GET("/users/{username}")
    Call<GithubBean> getUser(@Path("username") String username);
}
