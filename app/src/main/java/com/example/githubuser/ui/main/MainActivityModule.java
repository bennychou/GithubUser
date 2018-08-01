package com.example.githubuser.ui.main;

import android.support.v7.app.AppCompatActivity;

import com.example.githubuser.inject.PerActivity;
import com.example.githubuser.inject.PerFragment;
import com.example.githubuser.ui.base.BaseActivityModule;
import com.example.githubuser.ui.detail.view.DetailFragment;
import com.example.githubuser.ui.detail.view.DetailFragmentModule;
import com.example.githubuser.ui.main.view.MainFragment;
import com.example.githubuser.ui.main.view.MainFragmentListener;
import com.example.githubuser.ui.main.view.MainFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {
	@PerFragment
	@ContributesAndroidInjector(modules = MainFragmentModule.class)
	abstract MainFragment mainFragmentInjector();

	@PerFragment
	@ContributesAndroidInjector(modules = DetailFragmentModule.class)
	abstract DetailFragment detailFragmentInjector();

	@Binds
	@PerActivity
	abstract AppCompatActivity appCompatActivity(MainActivity mainActivity);

	@Binds
	@PerActivity
	abstract MainFragmentListener mainFragmentListener(MainActivity mainActivity);
}
