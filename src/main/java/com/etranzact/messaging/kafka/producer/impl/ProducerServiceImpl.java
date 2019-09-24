package com.etranzact.messaging.kafka.producer.impl;

import com.etranzact.messaging.kafka.consumer.SourceOutput;
import com.etranzact.messaging.kafka.producer.ProducerService;
import com.etranzact.model.request.MailModel;
import com.etranzact.model.request.PushModel;
import com.etranzact.model.request.SmsModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    private final SourceOutput sourceOutput;

    @Override
    public void sendEmail(MailModel model) {
        sourceOutput
                .sendEmail()
                .send(MessageBuilder
                        .withPayload(model)
                        .build());
        log.info("email notification sent!!");
    }

    @Override
    public void sendPush(PushModel model) {
        sourceOutput
                .sendPush()
                .send(MessageBuilder
                        .withPayload(model)
                        .build());
        log.info("push notification sent!!");
    }

    @Override
    public void sendSms(SmsModel model) {
        sourceOutput
                .sendSimpleSms()
                .send(MessageBuilder
                        .withPayload(model)
                        .build());
        log.info("sms notification sent!!");
    }
}
