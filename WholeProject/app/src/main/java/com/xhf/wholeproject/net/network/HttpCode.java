package com.xhf.wholeproject.net.network;

/***
 *Date：6/11/21
 *
 *author:Xu.Mr
 *
 *content:Http的状态码
 */
public class HttpCode {
    /**
     * 错误请求
     */
    public static final int BAD_REQUEST = 400;
    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;
    /**
     * 服务器拒绝提供所请求的资源
     */
    public static final int FORBIDDEN = 403;
    /**
     * 未找到
     */
    public static final int NOT_FOUND = 404;
    /**
     * 方法不允许
     */
    public static final int METHOD_NOT_ALLOWED = 405;
    /**
     * 无法访问
     */
    public static final int NOT_ACCEPTABLE = 406;
    /**
     * 代理服务器认证要求
     */
    public static final int PROXY_AUTHENTICATION_REQUIRED = 407;
    /**
     * 请求超时
     */
    public static final int REQUEST_TIMEOUT = 408;
    /**
     * 冲突
     */
    public static final int CONFLICT = 409;
    /**
     * 已经不存在
     */
    public static final int GONE = 410;
    /**
     * 需要数据长度
     */
    public static final int LENGTH_REQUIRED = 411;
    /**
     * 先决条件错误
     */
    public static final int PRECONDITION_FAILED = 412;
    /**
     * 请求实体过大
     */
    public static final int REQUEST_ENTITY_TOO_LARGE = 413;
    /**
     * 请求URI过长
     */
    public static final int REQUEST_URI_TOO_LONG = 414;
    /**
     * 不支持的媒体格式
     */
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;
    /**
     * 请求范围无法满足
     */
    public static final int REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    /**
     * 期望失败
     */
    public static final int EXPECTATION_FAILED = 417;
    /**
     * 内部服务器错误
     */
    public static final int INTERNAL_SERVER_ERROR = 500;
    /**
     * 未实现
     */
    public static final int NOT_IMPLEMENTED = 501;
    /**
     * 错误的网关
     */
    public static final int BAD_GATEWAY = 502;
    /**
     * 服务无法获得
     */
    public static final int SERVICE_UNAVAILABLE = 503;
    /**
     * 网关超时
     */
    public static final int GATEWAY_TIMEOUT = 504;
    /**
     * 不支持的 HTTP 版本
     */
    public static final int HTTP_VERSION_NOT_SUPPORTED = 505;

    /**
     * 成功
     */
    public static final int SUCCESS = 200;
    /**
     * Token过期
     */
    public static final int TOKENTIMEOUT = 0;
    /**
     * Token失效
     */
    public static final int TOKEN_FALSE = 600002;

}