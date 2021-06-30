package com.xhf.wholeproject.constant;

import android.content.Context;
import android.text.TextUtils;

import com.orhanobut.hawk.Hawk;
import com.xhf.wholeproject.model.entity.res.InfoMessageEntity;

import java.util.ArrayList;
import java.util.List;

import static com.xhf.wholeproject.constant.DB_Key.USER_INFO;
import static com.xhf.wholeproject.constant.DB_Key.USER_LIST;
import static com.xhf.wholeproject.constant.DB_Key.USER_TOKEN;

/***
 *Date：2021/3/22
 *
 *author:Xu.Mr
 *
 *content:当前存储的信息
 */
public class UserManager {
    private static UserManager userManager;
    private static List<InfoMessageEntity> userList = new ArrayList<InfoMessageEntity>();

    public static UserManager getInstance() {
        UserManager inst = userManager;
        if (inst == null) {
            synchronized (UserManager.class) {
                inst = userManager;
                if (inst == null) {
                    inst = new UserManager();
                    userManager = inst;
                }
            }
        }
        return userManager;
    }

    /**
     * 获取用户列表
     */
    public List<InfoMessageEntity> getUserList() {
        userList.clear();
        List<InfoMessageEntity> users = Hawk.get(USER_LIST);
        if (users != null) {
            userList.addAll(users);
        }
        return userList;
    }

    /**
     * 将新用户加入用户列表第一个  并存数据库  更新当前用户
     */
    public void addUserToDB(InfoMessageEntity user) {
        getUserList();
        List<InfoMessageEntity> tepList = new ArrayList<InfoMessageEntity>();
        for (int i = 0; i < userList.size(); i++) {
            if (!userList.get(i).getNickName().equals(user.getNickName())) {
                tepList.add(userList.get(i));
            }
        }
        userList.clear();
        userList.add(user);
        userList.addAll(tepList);
        Hawk.put(USER_LIST, userList);
        Hawk.put(USER_INFO, user);
    }

    public Boolean isUserLogin() {
        return getToken() != null ? true : false;
    }

    /**
     * 获取用户信息
     *
     * @return UserInfoEntity
     */
    public InfoMessageEntity getUserInfo() {
        return Hawk.get(USER_INFO);
    }

    /**
     * 获取OpenID
     *
     * @return
     */
    public String getToken() {
        String openId = null;
        InfoMessageEntity userEntity = Hawk.get(USER_INFO);
        if (userEntity != null) {
            openId = userEntity.getToken();
        }
        return openId;
    }

    /**
     * 获取userName
     *
     * @return
     */
    public String getAccount() {
        String account = null;
        InfoMessageEntity userLoginInfoEntity = Hawk.get(USER_INFO);
        if (userLoginInfoEntity != null) {
            account = userLoginInfoEntity.getNickName();
        }
        return account;
    }

    /**
     * 判断是否已经补全信息
     *
     * @return
     */
    public Boolean getFullValue(InfoMessageEntity infoMessageEntity) {
        boolean hasFullValue = false;
        String idNumber = null;
        InfoMessageEntity userLoginInfoEntity = Hawk.get(USER_INFO);
        if (userLoginInfoEntity != null) {
//            idNumber = userLoginInfoEntity.getIdNumber();
        }
        if (TextUtils.isEmpty(idNumber)) {
            hasFullValue = false;
        } else {
            hasFullValue = true;
        }
        return hasFullValue;
    }


    /**
     * 删除所有账号
     */
    public void delAllUser(Context context) {
        Hawk.delete(USER_INFO);
        List<InfoMessageEntity> tepList = new ArrayList<InfoMessageEntity>();
        for (int i = 0; i < userList.size(); i++) {
            tepList.add(userList.get(i));
        }
        userList.clear();
        userList.addAll(tepList);
        Hawk.put(USER_LIST, userList);
    }

    /**
     * 删除所有账号
     */
    public void delUser(InfoMessageEntity infoMessageEntity, Context context) {

        getUserList();
        List<InfoMessageEntity> tepList = new ArrayList<InfoMessageEntity>();
        for (int i = 0; i < tepList.size(); i++) {
//            if (infoMessageEntity.getIdNumber() == userList.get(i).getIdNumber()) {
//                tepList.remove(i);
//            }
            tepList.add(userList.get(i));
        }
        userList.clear();
        userList.addAll(tepList);
        Hawk.put(USER_LIST, userList);
    }


    /**
     * 获取刷新令牌
     *
     * @return
     */
    public String getRefreshToken() {
        String tokenDB = Hawk.get(USER_TOKEN);
        return tokenDB;
    }

}
