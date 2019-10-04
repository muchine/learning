package com.muchine.greendaoexample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.muchine.greendaoexample.db.DaoMaster;
import com.muchine.greendaoexample.db.DaoMaster.DevOpenHelper;
import com.muchine.greendaoexample.db.DaoSession;
import com.muchine.greendaoexample.db.Repo;
import com.muchine.greendaoexample.db.RepoDao;
import com.muchine.greendaoexample.db.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DevOpenHelper helper = new DevOpenHelper(this, "notes-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoSession = new DaoMaster(db).newSession();
    }

    public void onButtonClicked(View v) {
        createRepo();
        getRepos();
    }

    public void onDeleteAllClicked(View v) {
        daoSession.getUserDao().deleteAll();
        daoSession.getRepoDao().deleteAll();
        Toast.makeText(getApplicationContext(), "All Deleted", Toast.LENGTH_LONG).show();
    }

    private void createRepo() {
        Repo repo = createRepoFixture();

        RepoDao repoDao = daoSession.getRepoDao();
        repoDao.insert(repo);
        Log.d("Dao Example", "Inserted new repo, id: " + repo.getId());

        repo.addUser(createUserFixture("Sejoon Lim", "37"));
        repo.addUser(createUserFixture("Tom Kim", "29"));

        repo.update();
        Log.d("Dao Example", "Updated new repo, id: " + repo.getId());
    }

    private void getRepos() {
        RepoDao userDao = daoSession.getRepoDao();
        List<Repo> repos = userDao.queryBuilder().list();
        for (Repo repo : repos) {
            Log.d("Dao Example", "repo: " + repo.toString());
        }
    }

    private Repo createRepoFixture() {
        Repo repo = new Repo();
        repo.setTitle("The see in my old drawer.");
        repo.setLanguage("ko");
        repo.setWatchers_count(100);

//        List<User> users = repo.getUserRepos();

//        User sejoon = new User();
//        sejoon.setName("Sejoon Lim");
//        sejoon.setAge(new Short("37"));
//        users.add(sejoon);
//
//        User tom = new User();
//        tom.setName("Tom Kim");
//        tom.setAge(new Short("29"));
//        users.add(tom);

        return repo;
    }

    private User createUserFixture(String name, String age) {
        User user = new User();
        user.setName(name);
        user.setAge(new Short(age));

        return user;
    }

}
