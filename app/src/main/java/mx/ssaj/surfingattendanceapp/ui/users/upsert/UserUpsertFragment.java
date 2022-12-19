package mx.ssaj.surfingattendanceapp.ui.users.upsert;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import mx.ssaj.surfingattendanceapp.R;
import mx.ssaj.surfingattendanceapp.data.SurfingAttendanceDatabase;
import mx.ssaj.surfingattendanceapp.data.model.Users;
import mx.ssaj.surfingattendanceapp.databinding.FragmentUsersUpsertBinding;

public class UserUpsertFragment extends Fragment {

    public static final String EXTRA_REPLY = "mx.ssaj.surfingattendanceapp.ui.users.upsert.REPLY";

    private FragmentUsersUpsertBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserUpsertViewModel userUpsertViewModel = new ViewModelProvider(this).get(UserUpsertViewModel.class);

        binding = FragmentUsersUpsertBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.buttonSave.setOnClickListener(view -> {
            SurfingAttendanceDatabase.databaseWriteExecutor.execute(() -> {
                Users user = new Users();
                user.user = Integer.parseInt(binding.editTextUserId.getText().toString());
                user.name = binding.editTextUserName.getText().toString();
                userUpsertViewModel.insert(user);
            });
            //Navigation.findNavController(view).navigate(R.id.action_usersUpsertFragment_to_usersFragment);
            Navigation.findNavController(view).popBackStack();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}