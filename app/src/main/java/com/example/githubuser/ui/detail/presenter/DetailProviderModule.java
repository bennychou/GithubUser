package com.example.githubuser.ui.detail.presenter;

import android.support.annotation.Nullable;

import com.example.githubuser.data.User;
import com.example.githubuser.inject.PerActivity;
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
