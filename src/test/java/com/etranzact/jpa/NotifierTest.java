package com.etranzact.jpa;

import com.etranzact.jpa.entity.AccountDetails;
import com.etranzact.jpa.entity.CustomerDetails;
import com.etranzact.jpa.entity.Notifier;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnadeshola on 9/23/19.
 */
public class NotifierTest {

    private Notifier notifier;

    @Before
    public void setUp() {
        notifier = new Notifier();
    }

    @Test
    public void testNotifier() {

        AccountDetails account = new AccountDetails();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(1000));
        account.setAccountNumber("2019092921");

        CustomerDetails customer = new CustomerDetails();
        customer.setId(1L);
        customer.setPin("1234");
        customer.setEmail("johndoe@gmail.com");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setTelephone("07012345678");
        customer.setAccountDetails(account);

        notifier = new Notifier();
        notifier.setId(1L);
        notifier.setSubject("Greetings");
        notifier.setMessage("Hi John Doe, Java is awesome");
        notifier.setRead(Boolean.FALSE);
        notifier.setAccountDetails(customer);

        assertEquals("John", notifier.getAccountDetails().getFirstName());
    }
}
