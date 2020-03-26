/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.service;

import x.framework.core.dao.Card;
import x.framework.lang.Result;

/**
 * 卡服务
 * 
 * @author xman 2012-5-26
 */
public interface CardService {

    /**
     * 保存卡
     * @param card 卡
     * @return 结果
     */
    Result save(Card card);

}
