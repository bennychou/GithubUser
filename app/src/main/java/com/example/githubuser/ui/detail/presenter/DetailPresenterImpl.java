package com.example.githubuser.ui.detail.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.githubuser.data.UserRepository;
import com.example.githubuser.data.entity.User;
import com.example.githubuser.inject.PerFragment;
import com.example.githubuser.ui.base.presenter.BasePresenter;
import com.example.githubuser.ui.detail.view.DetailView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@PerFragment
public class DetailPresenterImpl extends BasePresenter<DetailView> implements DetailPresenter {
	private final User user;

	private final UserRepository userRepository;

	private CompositeDisposable subscriptions = new CompositeDisposable();
	@Inject
	protected DetailPresenterImpl(DetailView view, User user, UserRepository userRepository) {
		super(view);
		this.user = user;
		this.userRepository = userRepository;
	}

	@Override
	public void onStart(@Nullable Bundle savedInstanceState) {
		super.onStart(savedInstanceState);
		subscriptions.add(userRepository.userProfile()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(userProfile -> {
//					view.setRefreshing(false);
					view.setUserProfile(userProfile);
				}, throwable -> {
//					view.setRefreshing(false);
//					view.showErrorMessage(throwable.toString());
					Log.e("Error", throwable.toString());
				})
		);
	}

	@Override
	public void onEnd() {
		super.onEnd();
		subscriptions.clear();
		userRepository.refreshUserProfile();
	}

	@Override
	public void onLoading() {
		userRepository.getUserProfile(user.getName());
	}
}
