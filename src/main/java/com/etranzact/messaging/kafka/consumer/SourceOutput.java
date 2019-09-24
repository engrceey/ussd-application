package com.etranzact.messaging.kafka.consumer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface SourceOutput {

    String EMAIL_OUTPUT = "email-output";
    String PUSH_OUTPUT = "push-output";
    String SMS_OUTPUT = "sms-output";

    @Output(EMAIL_OUTPUT)
    MessageChannel sendEmail();

    @Output(PUSH_OUTPUT)
    MessageChannel sendPush();

    @Output(SMS_OUTPUT)
    MessageChannel sendSimpleSms();
}
