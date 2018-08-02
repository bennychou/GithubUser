package com.example.githubuser.ui.detail.presenter;

import android.util.Log;

import com.example.githubuser.data.User;
import com.example.githubuser.inject.PerFragment;
import com.example.githubuser.ui.base.presenter.BasePresenter;
import com.example.githubuser.ui.detail.view.DetailView;

import javax.inject.Inject;

@PerFragment
public class DetailPresenterImpl extends BasePresenter<DetailView> implements DetailPresenter {
	private final User user;
	@Inject
	protected DetailPresenterImpl(DetailView view, User user) {
		super(view);
		this.user = user;
		Log.i("TEST", "User is " + (user == null ? "null" : "not null" + user.getName()));
	}
}
