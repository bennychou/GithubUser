package com.example.githubuser.ui.main;

import android.os.Bundle;

import com.example.githubuser.R;
import com.example.githubuser.data.UserRepository;
import com.example.githubuser.data.entity.User;
import com.example.githubuser.ui.base.BaseActivity;
import com.example.githubuser.ui.detail.view.DetailFragment;
import com.example.githubuser.ui.main.view.MainFragment;
import com.example.githubuser.ui.main.view.MainFragmentListener;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainFragmentListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (findFragmentById(R.id.fragment_container) == null)
			addFragment(R.id.fragment_container, MainFragment.newInstance());
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void showDetails() {
		replaceFragment(R.id.fragment_container, DetailFragment.newInstance());
	}
}
