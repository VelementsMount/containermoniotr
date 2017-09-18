package org.liuzhilin.pushgateway.listener;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;
import org.liuzhilin.pushgateway.event.PushExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HandlePushExceptionEvent {

    @Autowired
    private CollectorRegistry registry;

    @Autowired
    private PushGateway pushGateway;

    @Value("${pushException.pushJobName}")
    private String pushJobName;

    @Autowired
    private SimpleDateFormat simpleDateFormat;


    private Counter counter;

    private Gauge gauge;

    @PostConstruct
    public void init() {
        counter = Counter.build()
                .name( "push_exception_event_count" )
                .labelNames( "event_push_type", "status", "message","instance" )
                .help( "push exception event number." ).register( registry );

        gauge = Gauge.build()
                .name( "push_exception_event_time" )
                .labelNames( "event_push_type", "status", "message", "time","instance" )
                .help( "last push exception event time" )
                .register( registry );
    }

    @Async
    @EventListener
    public void handlePushExceptionEvent(PushExceptionEvent pushExceptionEvent) throws IOException {
        String hostName = InetAddress.getLocalHost().getHostAddress();
        counter.labels( pushExceptionEvent.getEventPushType().getType(), pushExceptionEvent.getStatusCode().toString(),
                pushExceptionEvent.getMessage(),hostName).inc();
        gauge.labels( pushExceptionEvent.getEventPushType().getType(), pushExceptionEvent.getStatusCode().toString(),
                pushExceptionEvent.getMessage(), simpleDateFormat.format( new Date() ),hostName).setToCurrentTime();
        pushGateway.pushAdd( registry, pushJobName );
    }


}
