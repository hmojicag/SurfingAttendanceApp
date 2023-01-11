package mx.ssaj.surfingattendanceapp.ui.users.upsert;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import org.apache.commons.lang3.StringUtils;

import mx.ssaj.surfingattendanceapp.data.SurfingAttendanceDatabase;
import mx.ssaj.surfingattendanceapp.data.model.Users;
import mx.ssaj.surfingattendanceapp.databinding.FragmentUsersUpsertBinding;

public class UserUpsertFragment extends Fragment {
    private static String TAG = "UserUpsertFragment";
    private FragmentUsersUpsertBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserUpsertViewModel userUpsertViewModel = new ViewModelProvider(this).get(UserUpsertViewModel.class);

        binding = FragmentUsersUpsertBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // User Id to edit, if -1 then it's newly addition
        int userId = UserUpsertFragmentArgs.fromBundle(getArguments()).getUserId();
        boolean isNew = userId == -1;

        if (isNew) {// New user getting added
            SurfingAttendanceDatabase.databaseWriteExecutor.execute(() -> {
                int nextUserId = userUpsertViewModel.nextId();
                binding.editTextUpsertUserId.setText(String.valueOf(nextUserId));

                // Disable set BioPhoto
                binding.imageviewUpsertBiophoto.setEnabled(false);
            });
        } else {// Edit existing
            SurfingAttendanceDatabase.databaseWriteExecutor.execute(() -> {
                Users user = userUpsertViewModel.findById(userId);
                binding.editTextUpsertUserId.setText(String.valueOf(user.user));
                binding.editTextUpsertName.setText(user.name);
                binding.editTextUpsertCard.setText(user.mainCard);
                binding.editTextUpsertPassword.setText(user.password);

                requireActivity().runOnUiThread(() -> {
                    binding.switchUpsertActive.setChecked(StringUtils.equals(user.status, "A"));
                });
            });

            // Disable the edition of the Id
            binding.editTextUpsertUserId.setEnabled(false);
        }



        // ON CLICK SAVE
        // -----------------------------------------------------------------------------------------
        binding.buttonSave.setOnClickListener(view -> {
            SurfingAttendanceDatabase.databaseWriteExecutor.execute(() -> {
                Users user;
                if (isNew) {
                    Log.i(TAG, "Creating new user");
                    user = new Users();
                    user.user = Integer.parseInt(binding.editTextUpsertUserId.getText().toString());
                } else {
                    Log.i(TAG, "Updating existing user");
                    user = userUpsertViewModel.findById(userId);
                }

                // Set editable fields
                user.name = binding.editTextUpsertName.getText().toString();
                user.mainCard = binding.editTextUpsertCard.getText().toString();
                user.password = binding.editTextUpsertPassword.getText().toString();
                user.status = binding.switchUpsertActive.isChecked() ? "A" : "B";

                // Update or insert a record
                if (isNew) {
                    userUpsertViewModel.insert(user);
                } else {
                    userUpsertViewModel.update(user);
                }
            });

            // Return one screen back (To Users screen)
            Navigation.findNavController(view).popBackStack();
        });

        // ON CLICK SET BIOPHOTO
        // -----------------------------------------------------------------------------------------
        binding.imageviewUpsertBiophoto.setOnClickListener(view -> {
            // Navigate to Activity using safe args
            mx.ssaj.surfingattendanceapp.ui.users.upsert.UserUpsertFragmentDirections.ActionUsersUpsertFragmentToUpdateBioPhotoFaceDetectionActivity action =
                    UserUpsertFragmentDirections.actionUsersUpsertFragmentToUpdateBioPhotoFaceDetectionActivity();
            action.setUserId(userId);
            Navigation.findNavController(view).navigate(action);
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        // TODO: Do we need to refresh the view here? Like load new data or is it done automatically?

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}