package com.etranzact.messaging.email;

import com.etranzact.model.request.MailModel;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface EmailSenderService {

    void sendEmail(MailModel mailModel);
}
