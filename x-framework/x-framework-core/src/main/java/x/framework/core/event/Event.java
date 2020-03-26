/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.event;

/**
 * 事件
 * 
 * @author xman 2010-7-22
 */
public class Event {

    /** 事件主题 */
    private String topic;

    /** 源对象 */
    private Object source;

    /** 附加对象 */
    private Object addition;

    /**
     * 构造函数
     * @param topic 事件主题
     * @param source 源对象
     * @param addition 附加对象
     */
    public Event(String topic, Object source, Object addition) {
        this.topic = topic;
        this.source = source;
        this.addition = addition;
    }

    /**
     * 获取事件主题
     * @return 事件主题
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置事件主题
     * @param topic 事件主题
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 获取源对象
     * @return 源对象
     */
    public Object getSource() {
        return source;
    }

    /**
     * 设置源对象
     * @param source 源对象
     */
    public void setSource(Object source) {
        this.source = source;
    }

    /**
     * 获取附加对象
     * @return 附加对象
     */
    public Object getAddition() {
        return addition;
    }

    /**
     * 设置附加对象
     * @param addition 附加对象
     */
    public void setAddition(Object addition) {
        this.addition = addition;
    }

}
