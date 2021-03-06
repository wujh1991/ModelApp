package com.volley.wjh.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.volley.wjh.bean.User;
import com.volley.wjh.bean.Note;

import com.volley.wjh.dao.UserDao;
import com.volley.wjh.dao.NoteDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig noteDaoConfig;

    private final UserDao userDao;
    private final NoteDao noteDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        noteDaoConfig = daoConfigMap.get(NoteDao.class).clone();
        noteDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        noteDao = new NoteDao(noteDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Note.class, noteDao);
    }
    
    public void clear() {
        userDaoConfig.getIdentityScope().clear();
        noteDaoConfig.getIdentityScope().clear();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

}
