package com.etranzact.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Setter
@Getter
public class AccountDetailsResponse implements Serializable {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;

}
