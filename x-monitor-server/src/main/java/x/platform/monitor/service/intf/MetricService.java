package x.platform.monitor.service.intf;

import x.framework.lang.Result;
import x.platform.monitor.dmo.Metric;

public interface MetricService {

    Result add(Metric metric);

}
