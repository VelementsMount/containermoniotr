package org.liuzhilin.controller;

import io.prometheus.client.Counter;
import org.liuzhilin.pushgateway.config.PushExceptionConfig;
import org.liuzhilin.pushgateway.event.EventPushType;
import org.liuzhilin.pushgateway.event.PushExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;

@RestController
public class CounterController {


    private static Random random = new Random();
    @Autowired
    private ApplicationContext applicationContext;

    private static final Counter requestTotal = Counter.build()
            .name( "my_sample_counter" )
            .labelNames( "status" )
            .help( "A simple Counter to illustrate custom Counters in Spring Boot and Prometheus" ).register();


    @GetMapping("/")
    public String hello() {
        if (random.nextInt( 2 ) > 0) {
            requestTotal.labels( "success" ).inc();
        } else {
            requestTotal.labels( "error" ).inc();
        }
        return "Hello,world!";
    }

    @GetMapping("/pushGateway")
    public String pushGateway() throws IOException {
        applicationContext.publishEvent( new PushExceptionEvent(this,"Hello,world", EventPushType.MQTT,400) );
        return "hello,world!";
    }

}
