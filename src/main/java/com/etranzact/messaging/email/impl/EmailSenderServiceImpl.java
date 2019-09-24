package com.etranzact.messaging.email.impl;

import com.etranzact.messaging.email.EmailSenderService;
import com.etranzact.model.request.MailModel;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMail;
    private final Configuration templateConfiguration;

    @Value("${mail.noreply}")
    private String fromEmail;

    @Override
    public void sendEmail(MailModel mailModel) {
        sendPlainEmail(mailModel);

    }

    private void sendPlainEmail(MailModel model) {
        simpleMail.setFrom(fromEmail);
        simpleMail.setTo(model.getTo());
        simpleMail.setBcc(model.getBcc());
        simpleMail.setCc(model.getCc());
        simpleMail.setSubject(model.getSubject());
        try {
            if (model.isUseTemplate()) {
                simpleMail.setText(FreeMarkerTemplateUtils.processTemplateIntoString(templateConfiguration.getTemplate(model.getTemplateName(), model.getLocale()), model.getMessageMap()));
            } else {
                simpleMail.setText(model.getMessage());
            }
            simpleMail.setSentDate(new Date());
            javaMailSender.send(simpleMail);
            log.info("Plain Email Sent !");
        } catch (IOException | TemplateException e) {
            log.error("error occurred sending plain email to client, error message::: {}", e.getMessage());
        }
    }
}
