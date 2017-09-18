package org.liuzhilin.pushgateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "pushException")
@Component
public class PushExceptionConfig {
    private String pushGatewayUrl;
    private String pushJobName;
}
