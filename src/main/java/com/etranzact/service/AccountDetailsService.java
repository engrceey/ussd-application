package com.etranzact.service;

import com.etranzact.jpa.entity.AccountDetails;
import com.etranzact.model.response.AccountDetailsResponse;

import java.math.BigDecimal;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface AccountDetailsService {

    AccountDetailsResponse create();

    BigDecimal deposit(String tel, BigDecimal amount);

    BigDecimal withdrawal(String tel, BigDecimal amount);

    BigDecimal checkBalance(String tel);
}
