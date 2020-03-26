/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.event;

import org.springframework.core.task.TaskExecutor;

/**
 * 订阅规则
 * 
 * @author xman 2010-7-27
 */
public class SubscribeRule {

    /** 主题 */
    private String        topic;

    /** 事件监听器 */
    private EventListener eventListener;

    /** 任务执行器 */
    private TaskExecutor  taskExecutor;

    /**
     * 获取主题
     * @return 主题
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置主题
     * @param topic 主题
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 获取事件监听器
     * @return 事件监听器
     */
    public EventListener getEventListener() {
        return eventListener;
    }

    /**
     * 设置事件监听器
     * @param eventListener 事件监听器
     */
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    /**
     * 获取任务执行器
     * @return 任务执行器
     */
    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    /**
     * 设置任务执行器
     * @param taskExecutor 任务执行器
     */
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

}
