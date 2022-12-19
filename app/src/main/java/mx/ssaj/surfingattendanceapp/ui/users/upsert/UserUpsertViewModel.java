package mx.ssaj.surfingattendanceapp.ui.users.upsert;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import mx.ssaj.surfingattendanceapp.data.model.Users;
import mx.ssaj.surfingattendanceapp.data.repositories.UsersRepository;

public class UserUpsertViewModel extends AndroidViewModel {

    private UsersRepository usersRepository;

    public UserUpsertViewModel(Application application) {
        super(application);
        usersRepository = new UsersRepository(application);
    }

    public void insert(Users user) {
        usersRepository.insert(user);
    }

}