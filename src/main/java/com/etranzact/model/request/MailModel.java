package com.etranzact.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailModel implements Serializable {

    private String subject;
    private String from;
    private String to[] = new String[0];
    private String bcc[] = new String[0];
    private String cc[] = new String[0];
    private String message;
    private String type = "html";
    private Map<String, String> attachedFiles;
    private Locale locale = Locale.ENGLISH;
    private String templateName;
    private boolean hasAttachment = false;
    private boolean useTemplate = false;
    private Boolean hasUrlLocation = false;
    private Map<String, String> messageMap;
    private byte[] attachmentBytes;
    private String attachmentContentType;
    private String attachmentFileName;
    private String description;
    private String url;

}
