package com.example.githubuser.ui.detail.view;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.entity.UserEvent;
import com.example.githubuser.data.entity.UserProfile;
import com.example.githubuser.ui.base.view.BaseView;

import java.util.List;

public interface DetailView extends BaseView {
    void setUserProfile(UserProfile userProfile);

    void setRefreshingUserProfile(boolean active);

    void setRetryLayout(boolean active);

    void setRefreshingUserEvents(boolean active);

    void updateUserEvents(List<UserEvent> userEvents);

    void showErrorMessage();
}
