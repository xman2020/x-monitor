package x.platform.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import x.framework.lang.Result;
import x.platform.monitor.dmo.Metric;
import x.platform.monitor.service.intf.MetricService;

@Controller
@EnableAutoConfiguration
@ImportResource(value = {"classpath:applicationContext.xml"})
@ComponentScan(basePackages = {"x.platform.monitor, x.platform.auto"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/test/hello")
    @ResponseBody
    public String hello() {
        return "HelloWorld!";
    }

    //---------------------------------------------------------

    @Autowired
    private MetricService metricService;

    @RequestMapping("/test/metric/add")
    @ResponseBody
    public Result addMetric(@RequestBody Metric metric) {
        Result result = new Result();
        result.setErrorCode("NO_ERROR");
        result.setMessage("Good metric");
        result.setArgs(new String[]{"a", "b"});
        return result;
    }

    @RequestMapping("/test/metric/get")
    @ResponseBody
    public Metric getMetric() {
        Metric m = new Metric();
        m.setId("001");
        m.setName("CPU_USAGE");
        m.setValue("80.5");
        return m;
    }

}
