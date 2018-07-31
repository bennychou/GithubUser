package com.example.githubuser.ui.base.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.githubuser.ui.base.view.BaseView;

public abstract class BasePresenter<T extends BaseView> implements Presenter {
	protected final T view;

	protected BasePresenter(T view) {
		this.view = view;
	}

	@Override
	public void onStart(@Nullable Bundle savedInstanceState) {
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	}

	@Override
	public void onEnd() {
	}
}
