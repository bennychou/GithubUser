package com.example.githubuser.ui.base.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.example.githubuser.ui.base.presenter.Presenter;

import javax.inject.Inject;

public abstract class BaseViewFragment<T extends Presenter> extends BaseFragment implements BaseView {
	@Inject
	protected T presenter;

	@Override
	public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		presenter.onStart(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		presenter.onPause();
	}

	@CallSuper
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		presenter.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroyView() {
		presenter.onEnd();
		super.onDestroyView();
	}
}
