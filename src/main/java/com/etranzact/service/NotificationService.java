package com.etranzact.service;

import com.etranzact.model.request.MailModel;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface NotificationService {

    public void sendEmail(String subject, String message, String tel);

    public void pushNotification(String subject, String message, String tel);
}
