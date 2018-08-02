package com.example.githubuser.ui.detail.presenter;

import com.example.githubuser.data.User;
import com.example.githubuser.inject.PerFragment;
import com.example.githubuser.ui.main.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DetailPresenterModule {
	@Binds
	@PerFragment
	abstract DetailPresenter detailPresenter(DetailPresenterImpl detailPresenterImpl);
}
