package org.liuzhilin.pushgateway.event;

import lombok.Getter;

@Getter
public enum EventPushType {

    MQTT( "mqtt" );

    private String type;

    EventPushType(String type) {
        this.type = type;
    }
}
