package com.etranzact.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "message")
public class PageModel {

    private String welcomePage;
    private String exitPage;
    private String depositPage;
    private String createPage;
    private String withdrawalPage;
    private String balancePage;
    private String errorPage;
}
