package x.platform.monitor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import x.framework.lang.Result;
import x.platform.monitor.dao.intf.MetricDao;
import x.platform.monitor.dmo.Metric;
import x.platform.monitor.service.intf.MetricService;

import java.util.Date;

@Service
public class MetricServiceImpl implements MetricService {

    @Autowired
    private MetricDao metricDao;

    public Result add(Metric metric) {
        Result result = new Result();
        String mainKey = metric.getObjType()+metric.getObjNo()+metric.getMainType()+metric.getMainTypeExt();
        metric.setMainKey(mainKey);
        metric.setSubKey(metric.getSubType());
        metric.setCreateTime(new Date());
        metric.setUpdateTime(metric.getCreateTime());
        this.metricDao.insert(metric);
        return result;
    }

}
