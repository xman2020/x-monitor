/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import x.framework.core.dao.Card;
import x.framework.core.dao.CardDao;
import x.framework.core.trace.ServiceTrace;
import x.framework.lang.Result;

/**
 * 卡服务实现
 * 
 * @author xman 2012-5-26
 */
@Service("cardService")
@ServiceTrace
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao cardDao;

    /**
     * {@inheritDoc}
     */
    @Transactional("datasource2")
    public Result save(Card card) {
        Result result = new Result();
        
        if (card.getId() == null) {
            this.cardDao.insert(card);
        } else {
            this.cardDao.update(card);
        }
        
        return result;
    }

}
 