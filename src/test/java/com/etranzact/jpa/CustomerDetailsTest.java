package com.etranzact.jpa;

import com.etranzact.jpa.entity.AccountDetails;
import com.etranzact.jpa.entity.CustomerDetails;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnadeshola on 9/23/19.
 */
public class CustomerDetailsTest {

    private CustomerDetails customer;

    @Before
    public void setup() {
        customer = new CustomerDetails();
    }

    @Test
    public void testCustomerDetails() {
        AccountDetails account = new AccountDetails();
        account.setBalance(BigDecimal.valueOf(1000));
        account.setAccountNumber("2019092921");

        customer = new CustomerDetails();
        customer.setId(1L);
        customer.setPin("1234");
        customer.setEmail("johndoe@gmail.com");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setTelephone("07012345678");
        customer.setAccountDetails(account);

        assertEquals("1234", customer.getPin());
    }
}
