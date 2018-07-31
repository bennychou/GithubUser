package com.example.githubuser.ui.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseFragment extends AppCompatDialogFragment implements HasSupportFragmentInjector {
	@Inject
	protected Context activityContext;

	@Inject
	DispatchingAndroidInjector<Fragment> childFragmentInjector;

	@Nullable
	private Unbinder viewUnbinder;

	@Override
	public void onAttach(Context context) {
		AndroidSupportInjection.inject(this);
		super.onAttach(context);
	}

	@Override
	public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		viewUnbinder = ButterKnife.bind(this, getView());
	}

	@Override
	public void onDestroyView() {
		if (viewUnbinder != null)
			viewUnbinder.unbind();
		super.onDestroyView();
	}

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return childFragmentInjector;
	}
}
