package com.example.githubuser;

import android.app.Application;

import com.example.githubuser.inject.PerActivity;
import com.example.githubuser.network.NetworkModule;
import com.example.githubuser.ui.main.MainActivity;
import com.example.githubuser.ui.main.MainActivityModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class, NetworkModule.class})
abstract class AppModule {
	@Binds
	abstract Application application(App app);

	@PerActivity
	@ContributesAndroidInjector(modules = MainActivityModule.class)
	abstract MainActivity mainActivityInjector();
}
