package com.etranzact.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by johnadeshola on 9/24/19.
 */
@Setter
@Getter
public class SmsStatus {

    private Integer groupId;
    private String groupName;
    private Integer id;
    private String name;
    private String description;
    private String action;
}
