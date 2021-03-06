package com.example.githubuser.ui.main.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubuser.R;
import com.example.githubuser.data.entity.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainUserAdapter extends RecyclerView.Adapter<MainUserAdapter.ViewHolder> {
    private List<User> users;
    private UserListListener userListListener;
    public MainUserAdapter(List<User> users) {
        this.users = users;
    }

    public void setOnItemClickListener(UserListListener listener) {
        this.userListListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final User user = users.get(i);
        Picasso.get().load(user.getAvatarUrl()).into(viewHolder.icon);
        viewHolder.name.setText(user.getName());
        viewHolder.type.setText(user.getType());
        viewHolder.itemView.setOnClickListener(v -> {
            if (userListListener != null)
                userListListener.onItemClicked(users.get(i));
        });
    }

    void updateList(@NonNull final List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    interface UserListListener {
        void onItemClicked(User user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        @BindView(R.id.icon)
        ImageView icon;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.type)
        TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
