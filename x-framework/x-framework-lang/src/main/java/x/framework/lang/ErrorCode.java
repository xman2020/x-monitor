/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.lang;

/**
 * 错误码
 * 
 * @author xman 2010-7-20
 */
public class ErrorCode {

    /** 成功 */
    public static final String SUCCESS                                 = "success";

    /** 失败 */
    public static final String FAIL                                    = "fail";

    /** 系统异常 */
    @Description("系统内部异常")
    public static final String ERROR_SYSTEM                            = "error.system";

    /** 远程调用异常 */
    @Description("系统内部异常")
    public static final String ERROR_REMOTE_INVOKE                     = "error.remote.invoke";

}
