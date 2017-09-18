package org.liuzhilin.pushgateway.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class CommonEvent extends ApplicationEvent {

    private String message;

    public CommonEvent(Object source) {
        super( source );
    }

    public CommonEvent(Object source, String message) {
        super( source );
        this.message = message;
    }
}
