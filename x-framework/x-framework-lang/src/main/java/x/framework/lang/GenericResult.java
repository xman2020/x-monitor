/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.lang;

/**
 * 泛型结果类<br>
 * 可返回指定类型的结果
 * 
 * @author xman 2010-8-3
 */
public class GenericResult<T> extends Result {

    private static final long serialVersionUID = 6884388024939222192L;

    /** 结果对象 */
    protected T               object;

    /**
     * 获取结果对象
     * @return 结果对象
     */
    public T getObject() {
        return object;
    }

    /**
     * 设置结果对象
     * @param object 结果对象
     */
    public void setObject(T object) {
        this.object = object;
    }

}
