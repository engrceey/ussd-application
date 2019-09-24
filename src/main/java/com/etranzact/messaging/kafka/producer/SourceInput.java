package com.etranzact.messaging.kafka.producer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface SourceInput {

    String EMAIL_INPUT = "email-input";
    String PUSH_INPUT = "push-input";
    String SMS_INPUT = "sms-input";

    @Input(EMAIL_INPUT)
    SubscribableChannel receiveEmail();

    @Input(PUSH_INPUT)
    SubscribableChannel receivePushNotification();

    @Input(SMS_INPUT)
    SubscribableChannel receiveSimpleSms();
}
