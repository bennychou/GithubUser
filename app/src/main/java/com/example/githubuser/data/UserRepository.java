package com.example.githubuser.data;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.entity.UserEvent;
import com.example.githubuser.data.entity.UserProfile;
import com.example.githubuser.network.GithubApi;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

	private final GithubApi githubApi;

	private Subject<List<User>> userObservable = PublishSubject.create();
	private Subject<UserProfile> userProfileObservable = PublishSubject.create();
	private Subject<List<UserEvent>> userEventObservable = PublishSubject.create();

	private User cachedUser;

	private List<User> cachedUsers;
	private UserProfile cachedUserProfile;
	private List<UserEvent> cachedUserEvents;

	@Inject
	public UserRepository(GithubApi githubApi) {
		this.githubApi = githubApi;
	}

	public User getUser() {
		return cachedUser;
	}

	public void setUser(User cachedUser) {
		this.cachedUser = cachedUser;
	}

	public Flowable<List<User>> users() {
		return userObservable.toFlowable(BackpressureStrategy.LATEST);
	}

	private List<User> createErrorUsers() {
		User user = new User();
		List<User> users = new ArrayList<>();
		users.add(user);

		return users;
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
				if (response.body() == null) {
					userObservable.onNext(createErrorUsers());
					return;
				}
				cachedUsers = response.body();
				userObservable.onNext(cachedUsers);
			}

			@Override
			public void onFailure(Call<List<User>> call, Throwable t) {
				userObservable.onNext(createErrorUsers());
			}
		});
	}

	public boolean isValidUsers(List<User> users) {
		if (users == null)
			return false;
		if (users.isEmpty())
			return true;
		if (users.size() == 1 && users.get(0).getName() == null)
			return false;

		return true;
	}

	public void refresh() {
		cachedUsers = null;
	}

	public Flowable<UserProfile> userProfile() {
		return userProfileObservable.toFlowable(BackpressureStrategy.LATEST);
	}

	private UserProfile createErrorUserProfile() {
		UserProfile userProfile = new UserProfile();

		return userProfile;
	}

	public void getUserProfileAndEvent() {
		if (!(cachedUserProfile != null && cachedUser.getName().equals(cachedUserProfile.getLogin()))) {
			cachedUserProfile = null;
			cachedUserEvents = null;
		}
		getUserProfile();
		getUserEvents();
	}

	public void getUserProfile() {
		if (cachedUserProfile != null) {
			userProfileObservable.onNext(cachedUserProfile);
			return;
		}

		Call<UserProfile> call = githubApi.getUserProfile(cachedUser.getName());
		call.enqueue(new Callback<UserProfile>() {
			@Override
			public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
				if (response.body() == null) {
					userProfileObservable.onNext(createErrorUserProfile());
					return;
				}
				cachedUserProfile = response.body();
				userProfileObservable.onNext(cachedUserProfile);
			}

			@Override
			public void onFailure(Call<UserProfile> call, Throwable t) {
				userProfileObservable.onNext(createErrorUserProfile());
			}
		});
	}

	public boolean isValidUserProfile(UserProfile userProfile) {
		if (userProfile == null)
			return false;
		if (userProfile.getLogin() == null)
			return false;

		return true;
	}

	public void refreshUserProfile() {
		cachedUserProfile = null;
	}

	public Flowable<List<UserEvent>> userEvents() {
		return userEventObservable.toFlowable(BackpressureStrategy.LATEST);
	}

	private List<UserEvent> createErrorEvents() {
		UserEvent userEvent = new UserEvent();
		List<UserEvent> userEvents = new ArrayList<>();
		userEvents.add(userEvent);

		return userEvents;
	}

	public void getUserEvents() {
		if (cachedUserEvents != null) {
			userEventObservable.onNext(cachedUserEvents);
			return;
		}

		Call<List<UserEvent>> call = githubApi.getUserEvents(cachedUser.getName());
		call.enqueue(new Callback<List<UserEvent>>() {
			@Override
			public void onResponse(Call<List<UserEvent>> call, Response<List<UserEvent>> response) {
				if (response.body() == null) {
					userEventObservable.onNext(createErrorEvents());
					return;
				}
				cachedUserEvents = response.body();
				userEventObservable.onNext(cachedUserEvents);
			}

			@Override
			public void onFailure(Call<List<UserEvent>> call, Throwable t) {
				userEventObservable.onNext(createErrorEvents());
			}
		});
	}

	public boolean isValidUserEvents(List<UserEvent> userEvents) {
		if (userEvents == null)
			return false;
		if (userEvents.isEmpty())
			return true;
		if (userEvents.size() == 1 && userEvents.get(0).getId() == null)
			return false;

		return true;
	}

	public void refreshUserEvents() {
		cachedUserEvents = null;
	}
}
