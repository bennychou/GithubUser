package com.example.githubuser.ui.detail.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubuser.R;
import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.entity.UserProfile;
import com.example.githubuser.ui.base.view.BaseViewFragment;
import com.example.githubuser.ui.detail.presenter.DetailPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class DetailFragment extends BaseViewFragment<DetailPresenter>
		implements DetailView {
	@BindView(R.id.icon)
	ImageView icon;

	@BindView(R.id.name)
	TextView name;

	@BindView(R.id.type)
	TextView type;

	@BindView(R.id.admin)
	TextView admin;

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
		presenter.onLoading();
	}

	@Override
	public void setUserProfile(UserProfile userProfile) {
		Picasso.get().load(userProfile.getAvatarUrl()).into(icon);
		name.setText(userProfile.getName());
		type.setText(userProfile.getType());
		admin.setText(userProfile.isSiteAdmin() ? getText(R.string.yes) : getText(R.string.no));
	}
}
