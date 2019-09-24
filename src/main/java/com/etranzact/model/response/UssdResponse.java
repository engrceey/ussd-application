package com.etranzact.model.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class UssdResponse implements Serializable {

    private Map<String, String> message;

}
