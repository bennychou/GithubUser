package com.example.githubuser.ui.main.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubuser.R;
import com.example.githubuser.data.entity.User;
import com.example.githubuser.ui.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User> users;
    private UserListListener userListListener;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_USER = 1;
    private static final int headerCount = 1;
    public MainUserAdapter(List<User> users) {
        this.users = users;
    }

    public void setOnItemClickListener(UserListListener listener) {
        this.userListListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_USER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_header, viewGroup, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_user, viewGroup, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            final User user = users.get(i - headerCount);
            Picasso.get().load(user.getAvatarUrl()).transform(new CircleTransform()).into(((ViewHolder) viewHolder).icon);
            ((ViewHolder) viewHolder).name.setText(user.getName());
//            ((ViewHolder) viewHolder).type.setText(user.getType());
            ((ViewHolder) viewHolder).admin.setVisibility(user.isSiteAdmin() ? View.VISIBLE : View.GONE);
            ((ViewHolder) viewHolder).itemView.setOnClickListener(v -> {
                if (userListListener != null)
                    userListListener.onItemClicked(users.get(i));
            });
        }
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
        return headerCount + users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        @BindView(R.id.icon)
        ImageView icon;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.type)
        TextView type;

        @BindView(R.id.admin)
        TextView admin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        @BindView(R.id.header)
        TextView header;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
