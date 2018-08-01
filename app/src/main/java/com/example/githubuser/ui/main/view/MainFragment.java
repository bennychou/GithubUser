package com.example.githubuser.ui.main.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubuser.R;
import com.example.githubuser.data.User;
import com.example.githubuser.ui.base.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainFragment extends BaseFragment implements MainUserAdapter.UserListListener{
    public static final String USERS = "USERS";

    List<User> users;

    @Inject
    MainFragmentListener listener;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    MainUserAdapter mainUserAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users = getArguments().getParcelableArrayList(USERS);
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

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mainUserAdapter = new MainUserAdapter(activityContext, users);
        mainUserAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mainUserAdapter);
    }

    @Override
    public void onItemClicked(User user) {
        listener.showDetails();
    }
}
