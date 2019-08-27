package x.platform.monitor.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import x.framework.lang.Result;
import x.platform.monitor.Application;
import x.platform.monitor.dao.intf.MetricDao;
import x.platform.monitor.dmo.Metric;
import x.platform.monitor.service.intf.MetricService;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MetricServiceTest {

    @Autowired
    private MetricService metricService;

    @Test
    public void testAdd() {
        Metric metric = new Metric();
        metric.setObjType("服务器x");
        metric.setObjNo("120000000001");
        metric.setMainType("cpuxxxxx");
        metric.setMainTypeExt("190827190540");
        metric.setSubType("usagexxx");
        metric.setValue("90.5");
        metric.setValueType("numb");
        metric.setCollectTime(new Date());
        this.metricService.add(metric);
    }

}
