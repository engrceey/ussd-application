package com.etranzact.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Component
@ConfigurationProperties(prefix = "mail")
@Setter
@Getter
public class MailProperty {

    private String supportEmail;
    private String host;
    private String username;
    private String password;
    private Integer port;
    private String protocol;
    private String smtpAuth;
    private String starttlsEnable;
    private String starttlsRequired;
    private String debug;
    private String noreply;
    private String support;
}
