package com.etranzact.core.exceptions;

/**
 * * Created by johnadeshola on 9/21/19.
 */
public class CustomException extends RuntimeException {

    private String message;
    private int code;
    private String desc;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, String desc) {
        super(message);
        this.desc = desc;
    }

}
