package fu.prm391.project.androidcommerce.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.User;

/**
 * Created by Lam on 2/28/2018.
 */

public class UserViewModel extends AndroidViewModel {
    public UserViewModel(@NonNull Application application) {
        super(application);
    }
//    private final LiveData<List<User>> userList;
//
//    private AppDatabase appDatabase;
//
//    public UserViewModel(@NonNull Application application) {
//        super(application);
//
//        appDatabase = AppDatabase.getAppDatabase(this.getApplication());
//        userList = appDatabase.userDAO().getAll();
//    }
//
//    public LiveData<List<User>> getUserList() {
//        return userList;
//    }
//
//    private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {
//        private AppDatabase db;
//
//        deleteAsyncTask(AppDatabase appDatabase){
//            db = appDatabase;
//        }
//
//        @Override
//        protected Void doInBackground(User... users) {
//            db.userDAO().delete(users[0]);
//            return null;
//        }
//    }
}
