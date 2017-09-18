package org.liuzhilin.pushgateway.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushExceptionEvent extends CommonEvent{

    private EventPushType eventPushType;
    private Integer statusCode;

    public PushExceptionEvent(Object source) {
        super( source );
    }

    public PushExceptionEvent(Object source, String message) {
        super( source, message );
    }

    public PushExceptionEvent(Object source, String message,EventPushType eventPushType,Integer statusCode){
        super( source, message );
        this.eventPushType = eventPushType;
        this.statusCode = statusCode;
    }


}
