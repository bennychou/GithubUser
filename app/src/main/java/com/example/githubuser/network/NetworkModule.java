package com.example.githubuser.network;

import com.example.githubuser.data.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    public static final String BASE_URL = "https://api.github.com/";

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public GithubApi provideGithubApi(Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }

    @Singleton
    @Provides
    public UserRepository provideUserRepository(GithubApi githubApi) {
        return new UserRepository(githubApi);
    }
}
