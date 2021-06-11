package com.xhf.wholeproject.base;

/***
 *Date：6/11/21
 *
 *author:Xu.Mr
 *
 *content:
 */
public class BaseResEntity <T> {

    /**
     * requestId : 请求标识
     * httpCode :
     * code : 200//状态码
     * message : 状态信息
     * result : 返回值
     */

    private String requestId;
    private String httpCode;
    private int code;
    private String message;
    private T result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseResEntity{"
                +   "requestId='" + requestId + '\''
                +   ", httpCode='" + httpCode + '\''
                +   ", code=" + code
                +   ", message='" + message + '\''
                +   ", result=" + result
                +   '}';
    }
}