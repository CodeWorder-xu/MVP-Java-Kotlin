package com.xhf.wholeproject.net.service;


import com.xhf.wholeproject.base.BaseResEntity;
import com.xhf.wholeproject.model.entity.res.RefreshTokenEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/***
 *Date：6/11/21
 *
 *author:Xu.Mr
 *
 *content:
 */
public interface MyService {
    /**
     * 刷新token
     *
     * @param openId       用户唯一标识
     * @param refreshToken 刷新令牌
     * @return
     */
    @GET("user/refreshtoken")
    Observable<BaseResEntity<RefreshTokenEntity>> refreshToken(@Query("openId") String openId, @Query("refreshToken") String refreshToken, @Query("type") String type);

}
