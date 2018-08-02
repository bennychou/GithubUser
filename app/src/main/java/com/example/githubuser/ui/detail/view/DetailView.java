package com.example.githubuser.ui.detail.view;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.entity.UserProfile;
import com.example.githubuser.ui.base.view.BaseView;

public interface DetailView extends BaseView {
    void setUserProfile(UserProfile userProfile);
}
