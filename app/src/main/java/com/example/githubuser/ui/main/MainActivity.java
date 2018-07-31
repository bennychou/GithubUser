package com.example.githubuser.ui.main;

import android.os.Bundle;
import android.util.Log;

import com.example.githubuser.R;
import com.example.githubuser.data.User;
import com.example.githubuser.network.GithubApi;
import com.example.githubuser.ui.base.BaseActivity;
import com.example.githubuser.ui.main.view.MainFragment;
import com.example.githubuser.ui.main.view.MainFragmentListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements MainFragmentListener {
	@Inject
	GithubApi githubApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			addFragment(R.id.fragment_container, new MainFragment());
		}

		Call<List<User>> call = githubApi.getUsers();
		call.enqueue(new Callback<List<User>>() {
			@Override
			public void onResponse(Call<List<User>> call, Response<List<User>> response) {
				Log.i("TEST", "User: " + response.body().get(0).getName());
			}

			@Override
			public void onFailure(Call<List<User>> call, Throwable t) {

			}
		});
	}

	@Override
	public void showDetails() {

	}
}
