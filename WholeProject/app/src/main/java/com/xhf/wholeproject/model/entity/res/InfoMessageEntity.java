package com.xhf.wholeproject.model.entity.res;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/***
 *Date：2021/3/22
 *
 *author:Xu.Mr
 *
 *content:个人信息的实体类
 */
@Entity
public class InfoMessageEntity {
    @Id(autoincrement = true)//设置自增长
    private Long id;
    @Index(unique = true)//设置唯一性
    private String userid;
    private String name;//真实姓名
    private String nickName;//昵称
    private String age;//年龄
    private String gender;//性别
    private String phone;//手机号
    private String mailbox;//邮箱
    private String passWord;//密码
    private String account;//账号
    private String IDNum;//身份证
    private String birthday;//生日
    private String token;

    @Generated(hash = 545949476)
    public InfoMessageEntity(Long id, String userid, String name, String nickName,
            String age, String gender, String phone, String mailbox,
            String passWord, String account, String IDNum, String birthday,
            String token) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.nickName = nickName;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.mailbox = mailbox;
        this.passWord = passWord;
        this.account = account;
        this.IDNum = IDNum;
        this.birthday = birthday;
        this.token = token;
    }

    @Generated(hash = 1123924180)
    public InfoMessageEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
