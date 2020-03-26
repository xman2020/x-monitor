/**
 * xxx
 * Copyright (c) 2011-2011 All Rights Reserved.
 */
package x.framework.lang;

/**
 * 回调接口
 *
 * @author xman 2011-11-22
 */
public interface CallBack<T> {

    /**
     * 调用
     * @return 结果
     */
    T invoke();
    
}
