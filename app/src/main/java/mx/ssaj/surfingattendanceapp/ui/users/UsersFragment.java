package mx.ssaj.surfingattendanceapp.ui.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import mx.ssaj.surfingattendanceapp.data.model.Users;
import mx.ssaj.surfingattendanceapp.databinding.FragmentUsersBinding;

public class UsersFragment extends Fragment {

    private FragmentUsersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UsersViewModel usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.buttonAddUser.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        RecyclerView recyclerViewUsers = binding.recyclerViewUsers;
        final UserListItemAdapter userListItemAdapter = new UserListItemAdapter(new UserListItemAdapter.UsersDiff());
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewUsers.setAdapter(userListItemAdapter);

        usersViewModel.getAllUsersLive().observe(this.getViewLifecycleOwner(), users -> {
            userListItemAdapter.submitList(users);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}