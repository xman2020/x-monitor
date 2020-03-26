/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.event;

/**
 * 事件监听器接口
 * 
 * @author xman 2010-7-22
 */
public interface EventListener {

    /**
     * 处理事件
     * @param event 事件
     */
    void handle(Event event);

}
