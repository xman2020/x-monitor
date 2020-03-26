/**
 * xxx
 * Copyright (c) 2012-2014 All Rights Reserved.
 */
package x.framework.lang;

import java.io.Serializable;

/**
 * 错误信息
 * <p>
 * 用于对字段校验后，错误信息返回
 * 
 * @author xman 2014年1月21日
 */
public class ErrorInfo implements Serializable {

    private static final long serialVersionUID = 8945141535048234650L;

    /** 出错字段 */
    private String            field            = "";

    /** 错误码 */
    private String            errorCode        = "";

    /** 结果信息 */
    private String            message          = "";

    /** 错误信息参数 */
    private String[]          args;

    /**
     * 构造函数
     */
    public ErrorInfo() {
    }

    /**
     * 构造函数
     * @param field
     * @param errorCode
     * @param message
     */
    public ErrorInfo(String field, String errorCode, String message) {
        this.field = field;
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * 构造函数
     * @param field
     * @param errorCode
     */
    public ErrorInfo(String field, String errorCode) {
        this.field = field;
        this.errorCode = errorCode;
    }

    /**
     * 构造函数
     * @param field
     * @param errorCode
     */
    public ErrorInfo(String field, String errorCode, String[] args) {
        this.field = field;
        this.args = args;
        this.errorCode = errorCode;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
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

}
