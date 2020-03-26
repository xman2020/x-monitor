/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.lang;

/**
 * 应用异常
 * 
 * @author xman 2010-7-12
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -101473286311108767L;

    /** 错误码 */
    protected String          errorCode;

    /** 错误信息参数 */
    protected String[]        args;

    /**
     * 构造函数
     * @param errorCode 错误码
     */
    public AppException(String errorCode) {
        super("error:" + errorCode);
        this.errorCode = errorCode;
    }

    /***
     * 构造函数
     * @param result 结果类
     */
    @Deprecated
    public AppException(Result result) {
        super(result.getMessage());
        this.errorCode = result.getErrorCode();
    }

    /**
     * 构造函数
     * @param errorCode 错误码
     * @param message 异常信息
     * @param args 错误信息参数
     */
    public AppException(String errorCode, String message) {
        super("error:" + errorCode + "|msg:" + message);
        this.errorCode = errorCode;
    }

    /**
     * 构造函数
     * @param errorCode 错误码
     * @param args 错误信息参数
     */
    public AppException(String errorCode, String message, String[] args) {
        super("error:" + errorCode + "|msg:" + message);
        this.errorCode = errorCode;
        this.args = args;
    }

    /**
     * 构造函数
     * @param errorCode 错误码
     * @param args 错误信息参数
     */
    public AppException(String errorCode, String[] args) {
        super("error:" + errorCode);
        this.errorCode = errorCode;
        this.args = args;
    }

    /**
     * 构造函数
     * @param errorCode 错误码
     * @param cause 原异常
     */
    public AppException(String errorCode, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
    }

    /**
     * 构造函数
     * @param errorCode 错误码
     * @param message 异常信息
     * @param cause 原异常
     */
    public AppException(String errorCode, String message, Throwable cause) {
        super("error:" + errorCode + "|msg:" + message, cause);
        this.errorCode = errorCode;
    }

    /**
     * 获取错误码
     * @return 错误码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return the args
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * 覆盖原方法，解决抓取堆性能开销 {@inheritDoc}
     */
    public Throwable fillInStackTrace() {
        return this;
    }

}
