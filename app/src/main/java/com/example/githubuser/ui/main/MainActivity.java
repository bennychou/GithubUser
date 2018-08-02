package com.example.githubuser.ui.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.githubuser.R;
import com.example.githubuser.data.User;
import com.example.githubuser.network.GithubApi;
import com.example.githubuser.ui.base.BaseActivity;
import com.example.githubuser.ui.detail.view.DetailFragment;
import com.example.githubuser.ui.main.view.MainFragment;
import com.example.githubuser.ui.main.view.MainFragmentListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements MainFragmentListener {
	public static final String USER_KEY = "USER_KEY";

	public User detailUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null)
			detailUser = savedInstanceState.getParcelable(USER_KEY);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (findFragmentById(R.id.fragment_container) == null)
			addFragment(R.id.fragment_container, MainFragment.newInstance());
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(USER_KEY, detailUser);
	}

	@Override
	public void showDetails(User user) {
		this.detailUser = user;
		replaceFragment(R.id.fragment_container, DetailFragment.newInstance());
	}
}
