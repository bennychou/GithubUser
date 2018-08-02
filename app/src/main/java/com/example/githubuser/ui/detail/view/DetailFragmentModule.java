package com.example.githubuser.ui.detail.view;

import android.support.v4.app.Fragment;

import com.example.githubuser.inject.PerFragment;
import com.example.githubuser.ui.base.view.BaseFragmentModule;
import com.example.githubuser.ui.detail.presenter.DetailPresenterModule;
import com.example.githubuser.ui.main.view.MainFragment;
import com.example.githubuser.ui.main.view.MainView;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

@Module(includes = {
		BaseFragmentModule.class,
		DetailPresenterModule.class
})
public abstract class DetailFragmentModule {
	@Binds
	@Named(BaseFragmentModule.FRAGMENT)
	@PerFragment
	abstract Fragment fragment(DetailFragment detailFragment);

	@Binds
	@PerFragment
	abstract DetailView detailView(DetailFragment detailFragment);
}
