package com.etranzact.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by johnadeshola on 9/24/19.
 */
@Setter
@Getter
public class SmsResponse {

    private String bulkId;
    private Set<SmsMessage> messages;
}
