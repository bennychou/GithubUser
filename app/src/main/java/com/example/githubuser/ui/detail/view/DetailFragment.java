package com.example.githubuser.ui.detail.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubuser.R;
import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.entity.UserEvent;
import com.example.githubuser.data.entity.UserProfile;
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
	RelativeLayout content;

	@BindView(R.id.icon)
	ImageView icon;

	@BindView(R.id.id)
	TextView id;

	@BindView(R.id.name)
	TextView name;

	@BindView(R.id.company)
	TextView company;

	@BindView(R.id.location)
	TextView location;

	@BindView(R.id.email)
	TextView email;

	@BindView(R.id.blog)
	TextView blog;

	@BindView(R.id.followers)
	TextView followers;

	@BindView(R.id.following)
	TextView following;

	@BindView(R.id.swipe_refresh)
	SwipeRefreshLayout swipeRefresh;

	@BindView(R.id.no_event)
	TextView noEvent;

	@BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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
        setupRecyclerView();
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.onLoading();
	}

    private void setupRecyclerView() {
		swipeRefresh.setOnRefreshListener(() -> presenter.onRefreshUserEvents());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activityContext);
        recyclerView.setLayoutManager(layoutManager);
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
				layoutManager.getOrientation());
		recyclerView.addItemDecoration(dividerItemDecoration);

        detailUserEventAdapter = new DetailUserEventAdapter(new ArrayList<>());
        recyclerView.setAdapter(detailUserEventAdapter);
    }

    @OnClick(R.id.button_retry)
	public void onRetryClicked() {
		presenter.onRefreshUserProfileAndEvents();
	}

	@Override
	public void setUserProfile(UserProfile userProfile) {
		Picasso.get().load(userProfile.getAvatarUrl()).into(icon);
		id.setText(userProfile.getLogin());
		name.setText(userProfile.getName());
		company.setText(userProfile.getCompany());
		blog.setText(userProfile.getBlog());
		location.setText(userProfile.getLocation());
		email.setText(userProfile.getEmail());
		followers.setText(Integer.toString(userProfile.getFollowers()));
		following.setText(Integer.toString(userProfile.getFollowing()));
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
	public void setRefreshingUserEvents(boolean active) {
		if (active)
			noEvent.setVisibility(View.GONE);
		swipeRefresh.setRefreshing(active);
	}

	@Override
	public void updateUserEvents(List<UserEvent> userEvents) {
		if (userEvents.isEmpty())
			noEvent.setVisibility(View.VISIBLE);
		else
			noEvent.setVisibility(View.GONE);
        detailUserEventAdapter.updateList(userEvents);
	}

	@Override
	public void showErrorMessage() {
		Toast.makeText(activityContext, getText(R.string.error_happened), Toast.LENGTH_SHORT).show();
	}
}
