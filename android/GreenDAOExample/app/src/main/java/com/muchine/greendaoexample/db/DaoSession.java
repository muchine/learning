package com.muchine.greendaoexample.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.muchine.greendaoexample.db.Repo;
import com.muchine.greendaoexample.db.User;

import com.muchine.greendaoexample.db.RepoDao;
import com.muchine.greendaoexample.db.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig repoDaoConfig;
    private final DaoConfig userDaoConfig;

    private final RepoDao repoDao;
    private final UserDao userDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        repoDaoConfig = daoConfigMap.get(RepoDao.class).clone();
        repoDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        repoDao = new RepoDao(repoDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(Repo.class, repoDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        repoDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
    }

    public RepoDao getRepoDao() {
        return repoDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
