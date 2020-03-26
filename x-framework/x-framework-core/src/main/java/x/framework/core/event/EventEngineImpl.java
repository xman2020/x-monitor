/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import x.framework.core.oo.ErrorCode;
import x.framework.lang.AppException;

/**
 * 事件引擎实现
 * 
 * @author xman 2010-7-22
 */
public class EventEngineImpl implements EventEngine, InitializingBean {

    /** 订阅规则Map，key：主题，value：监听列表 */
    private Map<String, List<SubscribeRule>> subscribeRuleMap = new HashMap<String, List<SubscribeRule>>();

    /** 订阅规则列表 */
    private List<SubscribeRule>              subscribeRuleList;

    /**
     * {@inheritDoc}
     */
    public void publish(final Event event) {
        List<SubscribeRule> topicSubscribeRuleList = this.subscribeRuleMap.get(event.getTopic());

        if (topicSubscribeRuleList == null)
            throw new AppException(ErrorCode.ERROR_EVENT_TOPIC_NOT_EXIST, "事件主题不存在！");

        for (final SubscribeRule subscribeRule : topicSubscribeRuleList) {
            subscribeRule.getTaskExecutor().execute(new Runnable() {
                public void run() {
                    subscribeRule.getEventListener().handle(event);
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        for (SubscribeRule subscribeRule : this.subscribeRuleList) {
            String topic = subscribeRule.getTopic();
            List<SubscribeRule> topicSubscribeRuleList = this.subscribeRuleMap.get(topic);

            if (topicSubscribeRuleList == null) {
                topicSubscribeRuleList = new ArrayList<SubscribeRule>();
                this.subscribeRuleMap.put(topic, topicSubscribeRuleList);
            }

            topicSubscribeRuleList.add(subscribeRule);
        }
    }

    /**
     * 设置订阅规则列表
     * @param subscribeRuleList 订阅规则列表
     */
    public void setSubscribeRuleList(List<SubscribeRule> subscribeRuleList) {
        this.subscribeRuleList = subscribeRuleList;
    }

}
