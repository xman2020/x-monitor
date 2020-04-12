package x.platform.monitor.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import x.framework.page.Page;
import x.framework.page.PageResult;
import x.platform.monitor.Application;
import x.platform.monitor.dao.intf.MetricDao;
import x.platform.monitor.dmo.Metric;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MetricDaoTest {

    @Autowired
    private MetricDao metricDao;

    @Test
    public void testInsert() {
        Metric metric = new Metric();
        metric.setObjType("服务器x");
        metric.setObjNo("120000000011");
        metric.setMainType("cpuxxxxx");
        metric.setMainTypeExt("190827190540");
        metric.setSubType("usagexxx");
        metric.setValue("90.5");
        metric.setValueType("numb");
        metric.setCollectTime(new Date());
        String mainKey = metric.getObjType() + metric.getObjNo() + metric.getMainType() + metric.getMainTypeExt();
        metric.setMainKey(mainKey);
        metric.setSubKey(metric.getSubType());
        metric.setCreateTime(new Date());
        metric.setUpdateTime(metric.getCreateTime());
        this.metricDao.insert(metric);
    }

    @Test
    public void testUpdate() {
        Metric metric = this.metricDao.getById("21af4d99275544a4abbc92e75ca12e74");
        metric.setObjType("服务器y");
        metric.setObjNo("120000000012");
        metric.setMainType("cpuxxxxy");
        metric.setMainTypeExt("190827190541");
        metric.setSubType("usagexxy");
        metric.setValue("90.6");
        metric.setValueType("numx");
        metric.setCollectTime(new Date());
        String mainKey = metric.getObjType() + metric.getObjNo() + metric.getMainType() + metric.getMainTypeExt();
        metric.setMainKey(mainKey);
        metric.setSubKey(metric.getSubType());
        metric.setUpdateTime(new Date());
        this.metricDao.update(metric);
    }

    @Test
    public void testDelete() {
        Metric metric = new Metric();
        metric.setId("21af4d99275544a4abbc92e75ca12e74");
        this.metricDao.delete(metric);
    }

    @Test
    public void testGetById() {
        Metric metric = this.metricDao.getById("a5e858b0fd314a7583e5ed89695bdb24");
    }

    @Test
    public void testSelectByPage() throws ParseException {
        Metric metric = new Metric();
        //objType、objNo、mainType、mainTypeExt、collectTimeFrom、collectTimeTo
        metric.setObjType("服务器x");
        //metric.setObjNo("220000000005");
        metric.setMainType("cpuxxxxx");
        metric.setMainTypeExt("190907101202");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        metric.setCollectTimeFrom(sdf.parse("2019-09-27 00:00:00"));
        metric.setCollectTimeTo(sdf.parse("2019-09-28 23:59:59"));
        Page page = new Page();
        page.setCurrentPage(1);
        PageResult<Metric> pageList = this.metricDao.selectByPage(metric, page);
    }

    //@Test
    public void generate() throws ParseException {
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Metric metric = new Metric();
        metric.setObjType("服务器x");
        metric.setObjNo("120000000011");
        metric.setMainType("cpuxxxxx");
        metric.setMainTypeExt("190907101202");
        metric.setSubType("usagexxx");
        metric.setValue("90.5");
        metric.setValueType("numb");
        metric.setCollectTime(new Date());
        String mainKey = metric.getObjType() + metric.getObjNo() + metric.getMainType() + metric.getMainTypeExt();
        metric.setMainKey(mainKey);
        metric.setSubKey(metric.getSubType());
        metric.setCreateTime(new Date());
        metric.setUpdateTime(metric.getCreateTime());

        for (int i = 0; i < 100; i++) {
            metric.setObjNo(String.valueOf(220000000000L + i));
            metric.setValue(String.valueOf(random.nextInt(100)));
            mainKey = metric.getObjType() + metric.getObjNo() + metric.getMainType() + metric.getMainTypeExt();
            metric.setMainKey(mainKey);
            metric.setCollectTime(sdf.parse("2019-09-" + String.format("%02d", random.nextInt(30)) + " 10:21:18"));
            metric.setCreateTime(new Date());
            metric.setUpdateTime(metric.getCreateTime());
            this.metricDao.insert(metric);
        }
    }

}
