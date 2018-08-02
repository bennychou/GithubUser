package com.example.githubuser.data;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.entity.UserEvent;
import com.example.githubuser.data.entity.UserProfile;
import com.example.githubuser.network.GithubApi;

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
	private PublishSubject<UserProfile> userProfileObservable = PublishSubject.create();
	private PublishSubject<List<UserEvent>> userEventObservable = PublishSubject.create();

	private List<User> cachedUsers;
	private UserProfile cachedUserProfile;
	private List<UserEvent> cachedUserEvents;

	@Inject
	public UserRepository(GithubApi githubApi) {
		this.githubApi = githubApi;
	}

	public Flowable<List<User>> users() {
		return userObservable.toFlowable(BackpressureStrategy.LATEST);
	}

	public void getUsers() {
		if (cachedUsers != null) {
			userObservable.onNext(cachedUsers);
			return;
		}

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

	public void refresh() {
		cachedUsers = null;
	}

	public Flowable<UserProfile> userProfile() {
		return userProfileObservable.toFlowable(BackpressureStrategy.LATEST);
	}

	public void getUserProfileAndEvent(String name) {
		if (!(cachedUserProfile != null && name.equals(cachedUserProfile.getLogin()))) {
			cachedUserProfile = null;
			cachedUserEvents = null;
		}
		getUserProfile(name);
		getUserEvents(name);
	}

	public void getUserProfile(String name) {
		if (cachedUserProfile != null) {
			userProfileObservable.onNext(cachedUserProfile);
			return;
		}

		Call<UserProfile> call = githubApi.getUserProfile(name);
		call.enqueue(new Callback<UserProfile>() {
			@Override
			public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
				cachedUserProfile = response.body();
				userProfileObservable.onNext(cachedUserProfile);
			}

			@Override
			public void onFailure(Call<UserProfile> call, Throwable t) {
				userProfileObservable.onError(t);
			}
		});
	}

	public void refreshUserProfile() {
		cachedUserProfile = null;
	}

	public Flowable<List<UserEvent>> userEvents() {
		return userEventObservable.toFlowable(BackpressureStrategy.LATEST);
	}

	public void getUserEvents(String name) {
		if (cachedUserEvents != null) {
			userEventObservable.onNext(cachedUserEvents);
			return;
		}

		Call<List<UserEvent>> call = githubApi.getUserEvents(name);
		call.enqueue(new Callback<List<UserEvent>>() {
			@Override
			public void onResponse(Call<List<UserEvent>> call, Response<List<UserEvent>> response) {
				cachedUserEvents = response.body();
				userEventObservable.onNext(cachedUserEvents);
			}

			@Override
			public void onFailure(Call<List<UserEvent>> call, Throwable t) {
				userEventObservable.onError(t);
			}
		});
	}

	public void refreshUserEvents() {
		cachedUserEvents = null;
	}
}
