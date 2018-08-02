package com.example.githubuser.ui.main.presenter;

import com.example.githubuser.inject.PerFragment;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainPresenterModule {
	@Binds
	@PerFragment
	abstract MainPresenter mainPresenter(MainPresenterImpl mainPresenterImpl);
}
