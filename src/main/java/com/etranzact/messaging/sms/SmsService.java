package com.etranzact.messaging.sms;

import com.etranzact.model.request.SmsModel;
import com.etranzact.model.response.SmsResponse;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface SmsService {

    public SmsResponse sendSms(SmsModel model);
}
