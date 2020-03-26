/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.http;

import java.util.Map;

/**
 * HTTP上下文
 * 
 * @author xman 2016年4月14日
 */
public class HttpContext {

    /** 地址 */
    private String              address;

    /** 参数 */
    private Map<String, String> params;

    /** 连接超时 */
    private int                 connectTimeout = 1000;

    /** 读超时 */
    private int                 readTimeout    = 3000;

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the params
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    /**
     * @return the connectTimeout
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * @param connectTimeout the connectTimeout to set
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * @return the readTimeout
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * @param readTimeout the readTimeout to set
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

}
