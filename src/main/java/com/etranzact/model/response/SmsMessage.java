package com.etranzact.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by johnadeshola on 9/24/19.
 */
@Setter
@Getter
public class SmsMessage {

    private String to;
    private SmsStatus status;
    private String messageId;
    private Integer smsCount;
}
