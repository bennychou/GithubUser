package com.example.githubuser.ui.main.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.githubuser.R;
import com.example.githubuser.data.entity.User;
import com.example.githubuser.ui.base.view.BaseViewFragment;
import com.example.githubuser.ui.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void setupRecyclerView() {
		swipeRefresh.setOnRefreshListener(() -> {
			presenter.onRefresh();
		});
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));

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
        Toast.makeText(activityContext, getText(R.string.error_happened), Toast.LENGTH_SHORT).show();
    }
}
