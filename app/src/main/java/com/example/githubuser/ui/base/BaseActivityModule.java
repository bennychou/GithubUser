package com.example.githubuser.ui.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.githubuser.inject.PerActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule {
	static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";

	@Binds
	@PerActivity
	abstract Activity activity(AppCompatActivity appCompatActivity);

	@Binds
	@PerActivity
	abstract Context activityContext(Activity activity);

	@Provides
	@Named(ACTIVITY_FRAGMENT_MANAGER)
	@PerActivity
	static FragmentManager activityFragmentManager(AppCompatActivity appCompatActivity) {
		return appCompatActivity.getSupportFragmentManager();
	}
}
