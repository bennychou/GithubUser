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

	@Inject
	GithubApi githubApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			addFragment(R.id.fragment_container, new MainFragment());
		}
	}

	@Override
	public void showDetails() {
		Log.i("TEST", "Show detail");
		replaceFragment(R.id.fragment_container, new DetailFragment());
	}
}
