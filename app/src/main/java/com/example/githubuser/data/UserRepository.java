package com.example.githubuser.data;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.example.githubuser.R;
import com.example.githubuser.network.GithubApi;
import com.example.githubuser.ui.main.view.MainFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

	private final GithubApi githubApi;

	private PublishSubject<List<User>> userObservable = PublishSubject.create();

	private List<User> cachedUsers;

	private boolean cacheIsDirty = false;
	private boolean firstTime = true;

	@Inject
	public UserRepository(GithubApi githubApi) {
		this.githubApi = githubApi;
	}

	public Flowable<List<User>> users() {
		return userObservable.toFlowable(BackpressureStrategy.LATEST);
	}

	public void getUsers() {
		if (cachedUsers != null && !cacheIsDirty) {
			userObservable.onNext(cachedUsers);
			return;
		}

		if (cacheIsDirty || firstTime) {
			firstTime = false;
			Call<List<User>> call = githubApi.getUsers();
			call.enqueue(new Callback<List<User>>() {
				@Override
				public void onResponse(Call<List<User>> call, Response<List<User>> response) {
					cachedUsers = response.body();
					userObservable.onNext(cachedUsers);
				}

				@Override
				public void onFailure(Call<List<User>> call, Throwable t) {
					userObservable.onError(t);
				}
			});
		}
	}

	public void refresh() {
		cacheIsDirty = true;
	}
}
