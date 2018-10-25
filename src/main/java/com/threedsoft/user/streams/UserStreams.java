package com.threedsoft.user.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface UserStreams {
    String USER_INPUT = "user-in";
    String USER_OUTPUT = "user-out";
    
    @Input(USER_INPUT)
    public SubscribableChannel newInboundUser();

    @Output(USER_OUTPUT)
    MessageChannel outboundUser();
}