package com.example.githubuser.network;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.entity.UserEvent;
import com.example.githubuser.data.entity.UserProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {
    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{name}")
    Call<UserProfile> getUserProfile(@Path("name") String name);

    @GET("users/{name}/events")
    Call<List<UserEvent>> getUserEvents(@Path("name") String name);
}
