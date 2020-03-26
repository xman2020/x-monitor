/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.event;

/**
 * 事件引擎接口
 * 
 * @author xman 2010-7-22
 */
public interface EventEngine {

    /**
     * 发布事件
     * @param event 事件
     */
    void publish(Event event);

}
