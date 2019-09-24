package com.etranzact.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDetailsRequest implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String telephone;

    private String email;

    @Size(min = 4, max = 4, message = "Pin is a 4 digit number")
    @NotNull(message = "Pin cannot be empty")
    private String pin;

}
