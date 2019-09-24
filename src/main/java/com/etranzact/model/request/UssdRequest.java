package com.etranzact.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class UssdRequest {

    private String code;
    private String telephone;
    private String pin;
}
