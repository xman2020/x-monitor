/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.lang;

import java.io.Serializable;

/**
 * 新版结果类
 * 
 * @author xman 2016-4-19
 */
public class NewResult implements Serializable {

    private static final long serialVersionUID = 3064878420286334344L;

    /** null未知，true成功，false失败 */
    protected Boolean         success;

    /** 错误码 */
    protected String          error;

    /** 结果信息 */
    protected String          message;

    /** 错误参数 */
    protected String[]        args;

    /** 扩展字段 */
    protected String          extend;

    /**
     * 是否成功
     * @return true成功，false不成功
     */
    public boolean isOk() {
        return this.success == true;
    }

    /**
     * 是否未知
     * @return true未知，false非未知
     */
    public boolean isUnknown() {
        return this.success == null;
    }

    /**
     * 是否失败
     * @return ture失败，false不失败，也不能代表成功哦
     */
    public boolean isFail() {
        return this.success == false;
    }

    /**
     * 成功
     */
    public void ok() {
        this.success = true;
    }

    /**
     * 未知
     */
    public void unknown() {
        this.success = null;
    }

    /**
     * 未知
     * @param error 错误码
     */
    public void unknown(String error) {
        this.unknown();
        this.error = error;
    }

    /**
     * 未知
     * @param error 错误码
     * @param message 结果信息
     */
    public void unknown(String error, String message) {
        this.unknown(error);
        this.message = message;
    }

    /**
     * 未知
     * @param error 错误码
     * @param message 结果信息
     * @param args 错误参数
     */
    public void unknown(String error, String message, String[] args) {
        this.unknown(error, message);
        this.args = args;
    }

    /**
     * 失败
     */
    public void fail() {
        this.success = false;
    }

    /**
     * 失败
     * @param error 错误码
     */
    public void fail(String error) {
        this.fail();
        this.error = error;
    }

    /**
     * 失败
     * @param error 错误码
     * @param message 结果信息
     */
    public void fail(String error, String message) {
        this.fail(error);
        this.message = message;
    }

    /**
     * 失败
     * @param error 错误码
     * @param message 结果信息
     * @param args 错误参数
     */
    public void fail(String error, String message, String[] args) {
        this.fail(error, message);
        this.args = args;
    }

    /**
     * 拷贝结果
     * @param result 拷贝目标结果
     * @return 拷贝后的结果
     */
    public NewResult copy(NewResult result) {
        if (result != null) {
            this.success = result.success;
            this.error = result.error;
            this.message = result.message;
            this.args = result.args;
            this.extend = result.extend;
        }

        return this;
    }

    /**
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * 获取错误码
     * @return 错误码
     */
    public String getError() {
        return error;
    }

    /**
     * 设置错误码
     * @param error 错误码
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 获取结果信息
     * @return 结果信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置结果信息
     * @param message 结果信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the args
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * @param args the args to set
     */
    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * @return the extend
     */
    public String getExtend() {
        return extend;
    }

    /**
     * @param extend the extend to set
     */
    public void setExtend(String extend) {
        this.extend = extend;
    }

}
