package com.example.githubuser.network;

import com.example.githubuser.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubApi {
    @GET("users")
    Call<List<User>> getUsers();
}
