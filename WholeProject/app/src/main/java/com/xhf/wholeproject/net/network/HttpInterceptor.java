package com.xhf.wholeproject.net.network;




import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.xhf.wholeproject.base.BaseResponseEntity;
import com.xhf.wholeproject.constant.UserManager;
import com.xhf.wholeproject.model.entity.res.ErrorEntity;
import com.xhf.wholeproject.utils.StringUtil;
import com.xhf.wholeproject.utils.TLog;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * http拦截器
 */

public class HttpInterceptor implements Interceptor {
    private boolean hasLoginDialog = false;

    //
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//
//        Request request =  chain.request().newBuilder()
//                .addHeader("token", StringUtil.isEmpty(UserManager.getInstance().getRefreshToken())? "":UserManager.getInstance().getRefreshToken())
//                .addHeader("openId",StringUtil.isEmpty(UserManager.getInstance().getOpenId())? "":UserManager.getInstance().getOpenId())
//                .build();
//
//        Response response = chain.proceed(request);
//        ResponseBody responseBody = response.peekBody(1024 * 1024);
//        ResponseBody responseBodyLog = response.peekBody(1024 * 1024);
//
//        TLog.d("Log", "Request url : " + response.request().url());
//        TLog.d("Log", "Response code : " + response.code());
//        TLog.d("Log", "接收响应 ==  " + responseBodyLog.string());
//
//        if (response.code() >= 200 && response.code() < 300) {
//            return chain.proceed(request);
//        } else {
//            Gson gson = new Gson();
//            ErrorEntity err = gson.fromJson(responseBody.string(), ErrorEntity.class);
//            throw new HttpExceptioin(String.valueOf(response.code()), new Throwable("Http Err:" + err.getStatus()+" : "+err.getError()));
//        }
//    }
//

    /**
     *
     */
    class HttpExceptioin extends RuntimeException {
         HttpExceptioin(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private static final String TAG = "HttpInterceptor";

    public HttpInterceptor() {
    }


    @NonNull
    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("token", StringUtil.isEmpty(UserManager.getInstance().getRefreshToken()) ? "" : UserManager.getInstance().getRefreshToken())
//                .addHeader("openId", StringUtil.isEmpty(UserManager.getInstance().getOpenId()) ? "" : UserManager.getInstance().getOpenId())
                .build();
        Response originalResponse = null;
        originalResponse = chain.proceed(request);
        ResponseBody responseBody = originalResponse.peekBody(1024 * 1024);
        ResponseBody responseBodyLog = originalResponse.peekBody(1024 * 1024);
        BufferedSource source = originalResponse.body().source();
        source.request(Integer.MAX_VALUE);
        if (originalResponse.code() >= 200 && originalResponse.code() < 300) {
            Buffer buffer = source.buffer();
            Charset uTF8 = Charset.forName("UTF-8");
            String data = buffer.clone().readString(uTF8);
            BaseResponseEntity responseEntity = GsonTools.changeGsonToBean(data, BaseResponseEntity.class);
            TLog.d("Log", "请求状态为-------" + responseEntity.getStatus());
            String byteString = source.buffer().snapshot().utf8();
            ResponseBody responseBody1 = ResponseBody.create(null, byteString);
            return originalResponse.newBuilder().body(responseBody1).build();
        } else {
            Gson gson = new Gson();
            ErrorEntity err = gson.fromJson(responseBody.string(), ErrorEntity.class);
            throw new HttpExceptioin(String.valueOf(originalResponse.code()), new Throwable("Http Err:" + err.getStatus() + " : " + err.getError()));
        }

    }

}

