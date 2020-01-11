package x.platform.monitor.mdb.kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import x.platform.monitor.dmo.Metric;
import x.platform.monitor.mdb.intf.MetricMdb;
import x.platform.monitor.service.intf.MetricService;

@Repository
public class MetricMdbKafka implements MetricMdb {

    //./kafka-topics.sh --create --zookeeper localhost:2181/kafka --replication-factor 1 --partitions 1 --topic metric

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private MetricService metricService;

    public void sendMetric(Metric metric) {
        String metricJson = JSON.toJSONString(metric);
        this.kafkaTemplate.send("metric", metricJson);
    }

    //@KafkaListener(topics = "metric")
    public void receiveMetric(ConsumerRecord<String, String> record) {
        Metric metric = JSON.parseObject(record.value(), Metric.class);
        this.metricService.add(metric);
    }

}
