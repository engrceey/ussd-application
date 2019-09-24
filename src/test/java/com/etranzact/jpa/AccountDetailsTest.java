package com.etranzact.jpa;

import com.etranzact.jpa.entity.AccountDetails;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnadeshola on 9/23/19.
 */
public class AccountDetailsTest {

    private AccountDetails account;

    @Before
    public void setUp() {
        account =new AccountDetails();
    }

    @Test
    public void testAccountDetails() {
        account = new AccountDetails();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(1000));
        account.setAccountNumber("2019092921");
        assertEquals("2019092921", account.getAccountNumber());
    }

}
