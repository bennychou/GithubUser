package com.example.githubuser.ui.main.view;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.ui.base.view.BaseView;

import java.util.List;

public interface MainView extends BaseView {
	void updateUsers(List<User> users);

	void setRefreshing(boolean active);

	void showErrorMessage(String errorMessage);
}
