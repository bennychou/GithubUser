package com.example.githubuser.ui.main.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubuser.R;
import com.example.githubuser.data.User;
import com.example.githubuser.network.GithubApi;
import com.example.githubuser.ui.base.view.BaseFragment;
import com.example.githubuser.ui.base.view.BaseViewFragment;
import com.example.githubuser.ui.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainFragment extends BaseViewFragment<MainPresenter>
        implements MainUserAdapter.UserListListener, MainView {
	@BindView(R.id.swipe_refresh)
	SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MainUserAdapter mainUserAdapter;

    @NonNull
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
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
		swipeRefresh.setOnRefreshListener(() -> {
			presenter.onRefresh();
		});
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mainUserAdapter = new MainUserAdapter(new ArrayList<>());
        mainUserAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mainUserAdapter);
    }

    @Override
    public void onItemClicked(User user) {
        presenter.onItemClicked(user);
    }

    @Override
    public void updateUsers(List<User> users) {
        if (mainUserAdapter != null)
            mainUserAdapter.updateList(users);
    }

    @Override
    public void setRefreshing(boolean active) {
		swipeRefresh.setRefreshing(active);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }
}
