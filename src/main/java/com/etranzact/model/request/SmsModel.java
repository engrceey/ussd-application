package com.etranzact.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsModel implements Serializable {

    @NotNull
    @Size(min = 3, max = 11, message = "minimum character is 3 and maximum is 11")
    private String from;

    @NotNull
    private List<String> to = new ArrayList<>();

    @NotNull
    private String text;

}
