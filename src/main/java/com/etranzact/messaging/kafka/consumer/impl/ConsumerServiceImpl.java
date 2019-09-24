package com.etranzact.messaging.kafka.consumer.impl;

import com.etranzact.messaging.email.EmailSenderService;
import com.etranzact.messaging.kafka.consumer.ConsumerService;
import com.etranzact.messaging.kafka.producer.SourceInput;
import com.etranzact.messaging.push.PushService;
import com.etranzact.messaging.sms.SmsService;
import com.etranzact.model.request.MailModel;
import com.etranzact.model.request.PushModel;
import com.etranzact.model.request.SmsModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    private final EmailSenderService emailSenderService;
    private final PushService pushService;
    private final SmsService smsService;

    @Override
    @StreamListener(SourceInput.EMAIL_INPUT)
    public void consumeEmail(MailModel model) {
        emailSenderService.sendEmail(model);
        log.info("received email notification");
    }

    @Override
    @StreamListener(SourceInput.PUSH_INPUT)
    public void consumePushNotification(PushModel model) {
        pushService.pushNotifier(model);
        log.info("received push notification");
    }

    @Override
    @StreamListener(SourceInput.SMS_INPUT)
    public void consumeSimpleSms(SmsModel model) {
        smsService.sendSms(model);
        log.info("received simple sms notification");
    }
}
