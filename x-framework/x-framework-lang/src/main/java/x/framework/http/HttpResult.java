/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.http;

/**
 * HTTP结果
 * 
 * @author xman 2016年4月14日
 */
public class HttpResult {

    /** null未知，true成功，false失败 */
    private Boolean   success;

    /** 结果字符串 */
    private String    result;

    /** 异常 */
    private Throwable error;

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
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the error
     */
    public Throwable getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(Throwable error) {
        this.error = error;
    }

}
