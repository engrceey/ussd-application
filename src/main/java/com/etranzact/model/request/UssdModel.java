package com.etranzact.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Component
@ConfigurationProperties(prefix = "ussd.service.code")
@Setter
@Getter
public class UssdModel {

    private String exit;
    private String previous;
    private String init;
    private String create;
    private String deposit;
    private String withdrawal;
    private String balance;
}

