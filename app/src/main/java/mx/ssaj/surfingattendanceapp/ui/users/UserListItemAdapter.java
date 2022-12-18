package mx.ssaj.surfingattendanceapp.ui.users;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import mx.ssaj.surfingattendanceapp.data.model.Users;

public class UserListItemAdapter extends ListAdapter<Users, UserListItemViewHolder> {


    protected UserListItemAdapter(@NonNull DiffUtil.ItemCallback<Users> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public UserListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserListItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListItemViewHolder holder, int position) {
        Users user = getItem(position);
        holder.bind(user);
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), StringUtils.truncate(user.name, 25) + " Clicked!!", Toast.LENGTH_SHORT).show();
        });
    }

    public static class UsersDiff extends DiffUtil.ItemCallback<Users> {
        @Override
        public boolean areItemsTheSame(@NonNull Users oldItem, @NonNull Users newItem) {
            return oldItem.user == newItem.user;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Users oldItem, @NonNull Users newItem) {
            return oldItem.user == newItem.user && StringUtils.equals(oldItem.name, newItem.name);
        }
    }

}
