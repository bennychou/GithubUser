package com.example.githubuser.ui.detail.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.githubuser.R;
import com.example.githubuser.data.entity.User;
import com.example.githubuser.data.entity.UserEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailUserEventAdapter extends RecyclerView.Adapter<DetailUserEventAdapter.ViewHolder> {
    private List<UserEvent> userEvents;

    public DetailUserEventAdapter(List<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user_event, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailUserEventAdapter.ViewHolder viewHolder, int i) {
        final UserEvent userEvent = userEvents.get(i);
        viewHolder.typeEvent.setText(userEvent.getType());
        viewHolder.repoName.setText(userEvent.getRepo().getName());
        viewHolder.repoUrl.setText(userEvent.getRepo().getUrl());
        viewHolder.repoHead.setText(userEvent.getPayload().getHead());
        viewHolder.publicRepo.setText(userEvent.isPublic() ? "Yes" : "No");
    }

    void updateList(@NonNull final List<UserEvent> userEvents) {
        this.userEvents = userEvents;
        notifyDataSetChanged();
    }

    interface UserListListener {
        void onItemClicked(User user);
    }

    @Override
    public int getItemCount() {
        return userEvents.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        @BindView(R.id.type_event)
        TextView typeEvent;

        @BindView(R.id.repo_name)
        TextView repoName;

        @BindView(R.id.repo_url)
        TextView repoUrl;

        @BindView(R.id.repo_head)
        TextView repoHead;

        @BindView(R.id.public_repo)
        TextView publicRepo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
