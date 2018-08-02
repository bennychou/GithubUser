package com.example.githubuser.ui.main.view;

import com.example.githubuser.data.User;
import com.example.githubuser.ui.base.view.BaseView;

import java.util.List;

public interface MainView extends BaseView {
	void updateUsers(List<User> users);

	void setRefreshing(boolean active);

	void showErrorMessage(String errorMessage);

	void showUserDetail(User user);
}