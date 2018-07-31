package com.example.githubuser.ui.main.view;

import android.support.v4.app.Fragment;

import com.example.githubuser.inject.PerFragment;
import com.example.githubuser.ui.base.view.BaseFragmentModule;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

@Module(includes = BaseFragmentModule.class)
public abstract class MainFragmentModule {
    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    abstract Fragment fragment(MainFragment mainFragment);
}
