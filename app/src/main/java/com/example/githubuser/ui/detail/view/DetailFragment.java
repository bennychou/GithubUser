package com.example.githubuser.ui.detail.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubuser.R;
import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.entity.UserEvent;
import com.example.githubuser.data.entity.UserProfile;
import com.example.githubuser.ui.CircleTransform;
import com.example.githubuser.ui.base.view.BaseViewFragment;
import com.example.githubuser.ui.detail.presenter.DetailPresenter;
import com.example.githubuser.ui.main.view.MainUserAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailFragment extends BaseViewFragment<DetailPresenter>
		implements DetailView {
	@BindView(R.id.progress)
	ProgressBar progress;

	@BindView(R.id.button_retry)
	Button retry;

	@BindView(R.id.content)
	ScrollView content;

	@BindView(R.id.icon)
	ImageView icon;

	@BindView(R.id.id)
	TextView id;

	@BindView(R.id.admin)
    TextView admin;

	@BindView(R.id.name)
	TextView name;

	@BindView(R.id.bio)
	TextView bio;

	@BindView(R.id.location)
	TextView location;

	@BindView(R.id.blog)
	TextView blog;

	private DetailUserEventAdapter detailUserEventAdapter;

	@NonNull
	public static DetailFragment newInstance() {
		return new DetailFragment();
	}
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_detail, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		((AppCompatActivity) getActivity()).getSupportActionBar().hide();
		presenter.onLoading();
	}

	@Override
	public void onPause() {
		super.onPause();
		((AppCompatActivity) getActivity()).getSupportActionBar().show();
	}

    @OnClick(R.id.button_close)
	public void onCloseClicked() {
		getActivity().onBackPressed();
	}

    @OnClick(R.id.button_retry)
	public void onRetryClicked() {
		presenter.onRefreshUserProfileAndEvents();
	}

	@Override
	public void setUserProfile(UserProfile userProfile) {
		Picasso.get().load(userProfile.getAvatarUrl()).transform(new CircleTransform()).into(icon);
		id.setText(userProfile.getLogin());
		admin.setVisibility(userProfile.isSiteAdmin() ? View.VISIBLE : View.GONE);
		name.setText(userProfile.getName());
		bio.setText(userProfile.getBio());
		blog.setText(userProfile.getBlog());
		blog.setMovementMethod(LinkMovementMethod.getInstance());
		location.setText(userProfile.getLocation());
	}

	@Override
	public void setRefreshingUserProfile(boolean active) {
		progress.setVisibility(active ? View.VISIBLE : View.GONE);
		retry.setVisibility(View.GONE);
		content.setVisibility(active ? View.GONE : View.VISIBLE);
	}

	@Override
	public void setRetryLayout(boolean active) {
		progress.setVisibility(View.GONE);
		retry.setVisibility(View.VISIBLE);
		content.setVisibility(View.GONE);
	}

	@Override
	public void showErrorMessage() {
		Toast.makeText(activityContext, getText(R.string.error_happened), Toast.LENGTH_SHORT).show();
	}
}
