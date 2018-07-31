package com.example.githubuser;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = AppModule.class)
interface AppComponent extends AndroidInjector<App> {
	@Component.Builder
	abstract class Builder extends AndroidInjector.Builder<App> {
	}
}
