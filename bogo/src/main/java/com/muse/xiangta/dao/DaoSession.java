package com.muse.xiangta.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.muse.xiangta.msg.modle.Msg;
import com.muse.xiangta.modle.JsonData;
import com.muse.xiangta.modle.ReadFile;

import com.muse.xiangta.dao.MsgDao;
import com.muse.xiangta.dao.JsonDataDao;
import com.muse.xiangta.dao.ReadFileDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig msgDaoConfig;
    private final DaoConfig jsonDataDaoConfig;
    private final DaoConfig readFileDaoConfig;

    private final MsgDao msgDao;
    private final JsonDataDao jsonDataDao;
    private final ReadFileDao readFileDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        msgDaoConfig = daoConfigMap.get(MsgDao.class).clone();
        msgDaoConfig.initIdentityScope(type);

        jsonDataDaoConfig = daoConfigMap.get(JsonDataDao.class).clone();
        jsonDataDaoConfig.initIdentityScope(type);

        readFileDaoConfig = daoConfigMap.get(ReadFileDao.class).clone();
        readFileDaoConfig.initIdentityScope(type);

        msgDao = new MsgDao(msgDaoConfig, this);
        jsonDataDao = new JsonDataDao(jsonDataDaoConfig, this);
        readFileDao = new ReadFileDao(readFileDaoConfig, this);

        registerDao(Msg.class, msgDao);
        registerDao(JsonData.class, jsonDataDao);
        registerDao(ReadFile.class, readFileDao);
    }
    
    public void clear() {
        msgDaoConfig.getIdentityScope().clear();
        jsonDataDaoConfig.getIdentityScope().clear();
        readFileDaoConfig.getIdentityScope().clear();
    }

    public MsgDao getMsgDao() {
        return msgDao;
    }

    public JsonDataDao getJsonDataDao() {
        return jsonDataDao;
    }

    public ReadFileDao getReadFileDao() {
        return readFileDao;
    }

}
