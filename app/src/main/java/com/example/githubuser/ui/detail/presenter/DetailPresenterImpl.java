package com.example.githubuser.ui.detail.presenter;

import com.example.githubuser.inject.PerFragment;
import com.example.githubuser.ui.base.presenter.BasePresenter;
import com.example.githubuser.ui.detail.view.DetailView;
import com.example.githubuser.ui.main.presenter.MainPresenter;
import com.example.githubuser.ui.main.view.MainView;

import javax.inject.Inject;

@PerFragment
public class DetailPresenterImpl extends BasePresenter<DetailView> implements DetailPresenter {
	@Inject
	protected DetailPresenterImpl(DetailView view) {
		super(view);
	}
}
