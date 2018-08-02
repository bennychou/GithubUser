package com.example.githubuser.ui.detail.presenter;

import com.example.githubuser.inject.PerFragment;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DetailPresenterModule {
	@Binds
	@PerFragment
	abstract DetailPresenter detailPresenter(DetailPresenterImpl detailPresenterImpl);
}
