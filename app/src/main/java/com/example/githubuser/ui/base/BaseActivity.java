package com.example.githubuser.ui.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {
	@Inject
	@Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
	FragmentManager fragmentManager;

	@Inject
	DispatchingAndroidInjector<Fragment> fragmentInjector;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return fragmentInjector;
	}

	protected final void addFragment(@IdRes int containerId, Fragment fragment) {
		fragmentManager.beginTransaction()
				.add(containerId, fragment)
				.commit();
	}
}
