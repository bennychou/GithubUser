package com.example.githubuser.ui.detail.presenter;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.inject.PerFragment;
import com.example.githubuser.ui.main.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailProviderModule {
	@PerFragment
	@Provides
	User provideUser(MainActivity mainActivity) {
		return mainActivity.detailUser;
	}
}
