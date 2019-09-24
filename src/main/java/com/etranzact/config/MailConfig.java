package com.etranzact.config;

import com.etranzact.core.utils.SecurityUtils;
import com.etranzact.model.request.MailProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class MailConfig {

    private final MailProperty mailConfig;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailConfig.getHost());
        javaMailSender.setPort(mailConfig.getPort());
        javaMailSender.setUsername(mailConfig.getUsername());
        javaMailSender.setPassword(SecurityUtils.decryptStringData(SecurityUtils.decryptStringData(mailConfig.getPassword())));

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailConfig.getProtocol());
        props.put("mail.smtp.auth", mailConfig.getSmtpAuth());
        props.put("mail.smtp.starttls.enable", mailConfig.getStarttlsEnable());
        props.put("mail.smtp.starttls.required", mailConfig.getStarttlsRequired());
        props.put("mail.debug", mailConfig.getDebug());

        return javaMailSender;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage() {
        return new SimpleMailMessage();
    }
}
