package com.xhf.wholeproject.model.entity.res;

/***
 *Date：6/11/21
 *
 *author:Xu.Mr
 *
 *content:默认token的接口实体类
 */
public class RefreshTokenEntity {
    /**
     * •token 新令牌
     * •refreshToken 新的刷新令牌
     * •expire 令牌时效 秒
     */

    private String openId;
    private String token;
    private String refreshToken;
    private long expire;
    private Object user;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

}