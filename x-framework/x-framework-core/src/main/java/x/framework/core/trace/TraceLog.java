/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.trace;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.MDC;

import x.framework.lang.ErrorCode;

/**
 * 跟踪日志
 * 
 * @author xman 2010-7-29
 */
public class TraceLog {

    /** logger */
    private Logger             logger;

    /** 调用方法名 */
    private String             method;

    /** 调用时间阈值 */
    private long               threshold     = 0L;

    /** 调用流水号 */
    private String             invokeNo;

    /** 异常类型 */
    private String             exType        = "N";

    /** 错误码 */
    private String             errorCode     = ErrorCode.SUCCESS;

    /** 超过调用时间阈值 */
    private String             beyondThd     = "N";

    /** 开始时间 */
    private long               beginTime;

    /** 结束时间 */
    private long               endTime;

    /** 日志信息 */
    private StringBuilder      message       = new StringBuilder();

    /** 日志MDC调用流水号Key */
    public static final String MDC_INVOKE_NO = "invokeNo";

    /**
     * 构造函数
     * @param logger logger
     * @param method 调用方法名
     * @param threshold 调用时间阈值
     */
    public TraceLog(Logger logger, String method, long threshold) {
        this.logger = logger;
        this.method = method;
        this.threshold = threshold;
        this.invokeNo = MDC.get(MDC_INVOKE_NO);

        // 产生调用流水号放入MDC
        if (this.invokeNo == null) {
            MDC.put(MDC_INVOKE_NO, UUID.randomUUID().toString().replace("-", ""));
        }
    }

    /**
     * 开始
     */
    public void begin() {
        this.beginTime = System.currentTimeMillis();
    }

    /**
     * 结束
     */
    public void end() {
        if (this.logger.isWarnEnabled()) {
            this.endTime = System.currentTimeMillis();
            long runTime = this.endTime - this.beginTime;

            if (threshold > 0L && runTime > threshold) {
                this.beyondThd = "Y";
            }

            // 日志格式：方法名|执行时间（毫秒）|是否超过阈值|异常类型|错误码
            // 日志示例：ME:CardBiz.register|RT:1211|BT:Y|ET:N|EC:ERROR_CODE
            this.message.append("ME:").append(this.method).append("|RT:").append(runTime).append("|BT:")
                    .append(this.beyondThd).append("|ET:").append(this.exType).append("|EC:").append(this.errorCode);

        }
    }

    /**
     * 重置
     * @param method 调用方法名
     * @param threshold 调用时间阈值
     */
    public void reset(String method, long threshold) {
        this.method = method;
        this.threshold = threshold;
        this.exType = "N";
        this.beyondThd = "N";
    }

    /**
     * 设置异常类型
     * @param exType 异常类型
     */
    public void setExType(String exType) {
        this.exType = exType;
    }

    /**
     * 设置错误码
     * @param errorCode 错误码
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 记日志
     */
    public void log() {
        try {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn(this.message.toString());
            }
        } finally {
            // 从MDC删除调用流水号
            if (this.invokeNo == null) {
                MDC.remove(MDC_INVOKE_NO);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return this.message.toString();
    }

}
