package com.xhf.wholeproject.utils;

import android.content.Context;
import android.icu.text.IDNA;

import com.example.greendao.dao.DaoSession;
import com.example.greendao.dao.InfoMessageEntityDao;
import com.xhf.wholeproject.model.entity.res.InfoMessageEntity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/***
 *Date：21-6-28
 *
 *author:Xu.Mr
 *
 *content:数据库操作的方法
 */
public class DataUtils {
    private static final boolean DUBUG = true;
    private DbManager manager;
    private DaoSession daoSession;

    public DataUtils(Context context) {
        manager = DbManager.getInstance();
        manager.init(context);
        daoSession = manager.getDaoSession();
        manager.setDebug(DUBUG);
    }

    /**
     * 添加数据，如果有重复则覆盖
     */
    public void insertStudent(InfoMessageEntity userInfoBean) {
        manager.getDaoSession().insertOrReplace(userInfoBean);
    }

    /**
     * 添加多条数据，需要开辟新的线程
     */
    public void insertMult(final List<InfoMessageEntity> userInfoBeans) {
        manager.getDaoSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (InfoMessageEntity userInfoBean : userInfoBeans) {
                    manager.getDaoSession().insertOrReplace(userInfoBean);
                }
            }
        });
    }


    /**
     * 删除数据
     */
    public void deleteStudent(InfoMessageEntity userInfoBean) {
        manager.getDaoSession().delete(userInfoBean);
    }

    /**
     * 删除全部数据
     */
    public void deleteAll(Class cls) {
        manager.getDaoSession().deleteAll(cls);

    }

    /**
     * 更新数据
     */
    public void update(InfoMessageEntity userInfoBean) {
        manager.getDaoSession().update(userInfoBean);
    }

    /**
     * 按照主键返回单条数据
     */
    public InfoMessageEntity listOne(long key) {
        return manager.getDaoSession().load(InfoMessageEntity.class, key);
    }

    /**
     * 根据指定条件查询数据
     */
    public List<InfoMessageEntity> query(Class cls, String value) {
        //查询构建器
        QueryBuilder<InfoMessageEntity> builder = manager.getDaoSession().queryBuilder(cls);

        List<InfoMessageEntity> list = builder.where(InfoMessageEntityDao.Properties.Phone.eq(value))
                .list();
        return list;
    }


    public List<InfoMessageEntity> queryData(String s, String where) {
        List<InfoMessageEntity> userInfoBeans = daoSession.queryRaw(InfoMessageEntity.class, " where " + where + " = ?", s);
        return userInfoBeans;
    }


    /**
     * 查询全部数据
     */
    public List<InfoMessageEntity> queryAll(Class cls) {
        return manager.getDaoSession().loadAll(cls);
    }
}