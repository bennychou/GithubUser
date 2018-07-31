package com.example.githubuser.ui.main;

import android.support.v7.app.AppCompatActivity;

import com.example.githubuser.inject.PerActivity;
import com.example.githubuser.ui.base.BaseActivityModule;

import dagger.Binds;
import dagger.Module;

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {
	@Binds
	@PerActivity
	abstract AppCompatActivity appCompatActivity(MainActivity mainActivity);
}
