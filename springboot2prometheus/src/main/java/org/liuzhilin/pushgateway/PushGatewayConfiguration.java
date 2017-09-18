package org.liuzhilin.pushgateway;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class PushGatewayConfiguration {

    @Value("${pushException.pushGatewayUrl}")
    private String pushGatewayUrl;


    @Bean
    public CollectorRegistry collectorRegistry() {
        CollectorRegistry collectorRegistry = new CollectorRegistry();
        return collectorRegistry;
    }

    @Bean
    public PushGateway pushGateway() {
        PushGateway pushGateway = new PushGateway( pushGatewayUrl );
        return pushGateway;
    }

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
    }

}
