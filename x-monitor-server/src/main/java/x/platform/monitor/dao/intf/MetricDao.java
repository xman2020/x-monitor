package x.platform.monitor.dao.intf;

import x.framework.page.Page;
import x.framework.page.PageResult;
import x.platform.monitor.dmo.Metric;

public interface MetricDao {

    int insert(Metric metric);

    int update(Metric metric);

    int delete(Metric metric);

    Metric getById(String id);

    PageResult selectByPage(Metric metric, Page page);

}
