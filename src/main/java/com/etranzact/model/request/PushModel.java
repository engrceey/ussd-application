package com.etranzact.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushModel implements Serializable {
    private Long custId;
    private String subject = "";
    private String message = "";
    private boolean isRead = false;
}
