package com.example.githubuser.ui.main.presenter;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.ui.base.presenter.Presenter;

public interface MainPresenter extends Presenter {
	void onLoading();
	void onRefresh();
	void onItemClicked(User user);
}
