package com.etranzact.messaging.kafka.producer;

import com.etranzact.model.request.MailModel;
import com.etranzact.model.request.PushModel;
import com.etranzact.model.request.SmsModel;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface ProducerService {

    public void sendEmail(MailModel model);

    public void sendPush(PushModel model);

    public void sendSms(SmsModel model);
}
