package com.etranzact.messaging.kafka.consumer;

import com.etranzact.model.request.MailModel;
import com.etranzact.model.request.PushModel;
import com.etranzact.model.request.SmsModel;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface ConsumerService {

    public void consumeEmail(MailModel model);

    public void consumePushNotification(PushModel model);

    public void consumeSimpleSms(SmsModel model);
}
