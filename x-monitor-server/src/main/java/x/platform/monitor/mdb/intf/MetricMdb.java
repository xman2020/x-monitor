package x.platform.monitor.mdb.intf;

import x.platform.monitor.dmo.Metric;

public interface MetricMdb {

    void sendMetric(Metric metric);

}
