package com.xhf.wholeproject.base;

/***
 *Date：2021/1/14
 *
 *author:Xu.Mr
 *
 *content:
 *
 *@param  <T>
 */
public class BaseResponseEntity<T> {
    private T data;
    private String msg;
    private int status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}