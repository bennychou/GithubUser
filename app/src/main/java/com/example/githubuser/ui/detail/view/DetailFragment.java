package com.example.githubuser.ui.detail.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class DetailFragment extends BaseViewFragment<DetailPresenter>
		implements DetailView {
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
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activityContext);
        recyclerView.setLayoutManager(layoutManager);
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
				layoutManager.getOrientation());
		recyclerView.addItemDecoration(dividerItemDecoration);

        detailUserEventAdapter = new DetailUserEventAdapter(new ArrayList<>());
        recyclerView.setAdapter(detailUserEventAdapter);
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
	public void updateUserEvents(List<UserEvent> userEvents) {
        detailUserEventAdapter.updateList(userEvents);
	}
}
