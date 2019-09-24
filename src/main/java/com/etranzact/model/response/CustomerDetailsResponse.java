package com.etranzact.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
public class CustomerDetailsResponse implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String telephone;

    private String email;

    private AccountDetailsResponse accountDetails;

}
