package com.example.githubuser.ui.main.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.UserRepository;
import com.example.githubuser.inject.PerFragment;
import com.example.githubuser.ui.base.presenter.BasePresenter;
import com.example.githubuser.ui.main.view.MainFragmentListener;
import com.example.githubuser.ui.main.view.MainView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@PerFragment
public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {
	private final UserRepository userRepository;

	private final MainFragmentListener mainFragmentListener;

	private CompositeDisposable subscriptions = new CompositeDisposable();

	@Inject
	protected MainPresenterImpl(MainView view, UserRepository userRepository, MainFragmentListener listener) {
		super(view);
		this.userRepository = userRepository;
		this.mainFragmentListener = listener;
	}

	@Override
	public void onStart(@Nullable Bundle savedInstanceState) {
		super.onStart(savedInstanceState);
		subscriptions.add(userRepository.users()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(users -> {
					view.setRefreshing(false);
					if (userRepository.isValidUsers(users))
						view.updateUsers(users);
					else
						view.showErrorMessage("");
				}, throwable -> {
					view.setRefreshing(false);
					view.showErrorMessage(throwable.toString());
					Log.e("Error", throwable.toString());
				})
		);
	}

	@Override
	public void onEnd() {
		super.onEnd();
		subscriptions.clear();
	}

	@Override
	public void onLoading() {
		getUsers();
	}

	@Override
	public void onRefresh() {
		userRepository.refresh();
		getUsers();
	}

	@Override
	public void onItemClicked(User user) {
		mainFragmentListener.showDetails(user);
	}

	private void getUsers() {
		view.setRefreshing(true);
		userRepository.getUsers();
	}
}
