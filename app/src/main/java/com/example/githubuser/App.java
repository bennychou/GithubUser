package com.example.githubuser;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {
	@Inject
	DispatchingAndroidInjector<Activity> activityInjector;

	@Override
	public void onCreate() {
		super.onCreate();
		DaggerAppComponent.builder().create(this).inject(this);
	}

	@Override
	public AndroidInjector<Activity> activityInjector() {
		return activityInjector;
	}
}