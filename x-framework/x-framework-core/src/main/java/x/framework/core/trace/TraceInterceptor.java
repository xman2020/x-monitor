/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.trace;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import x.framework.core.oo.ErrorCode;
import x.framework.lang.AppException;

/**
 * 跟踪拦截器
 * 
 * @author xman 2010-7-29
 */
public class TraceInterceptor implements MethodInterceptor, InitializingBean {

    /** logger */
    private Logger logger;

    /** 日志名 */
    private String loggerName;

    /** 调用时间阈值 */
    private long   threshold = 1000L;

    /**
     * {@inheritDoc}
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        String methodName = target.getClass().getSimpleName() + "." + method.getName();
        Object result = null;
        TraceLog traceLog = new TraceLog(this.logger, methodName, this.threshold);

        try {
            traceLog.begin();
            result = invocation.proceed();
        } catch (AppException appEx) {
            traceLog.setExType("A");
            traceLog.setErrorCode(appEx.getErrorCode());
            throw appEx;
        } catch (Throwable t) {
            traceLog.setExType("T");
            traceLog.setErrorCode(ErrorCode.ERROR_SERVICE_INTERCEPTOR_INVOKE);
            throw t;
        } finally {
            traceLog.end();
            traceLog.log();
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        if (this.loggerName == null) {
            this.logger = LoggerFactory.getLogger(TraceInterceptor.class);
        } else {
            this.logger = LoggerFactory.getLogger(this.loggerName);
        }
    }

    /**
     * 设置调用时间阈值
     * @param threshold 调用时间阈值
     */
    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }

    /**
     * 设置日志名
     * @param loggerName 日志名
     */
    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

}
