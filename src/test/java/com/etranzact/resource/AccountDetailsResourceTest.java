package com.etranzact.resource;

import com.etranzact.core.utils.AppUtils;
import com.etranzact.model.request.CustomerDetailsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by johnadeshola on 9/23/19.
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class AccountDetailsResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createAccountDetailsTest() throws Exception {
        CustomerDetailsRequest request = new CustomerDetailsRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setTelephone("0909091919");
        request.setEmail("johndoe@gmail.com");
        request.setPin("1224");

        String data = AppUtils.toJson(request);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void generateAccountTest() throws Exception {
        mockMvc.perform(post("/accounts/gen")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void depositTest() throws Exception {
        mockMvc.perform(post("/accounts/deposit")
                .param("tel", "08020202122")
                .param("amount", "1000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void withdrawalTest() throws Exception {
        mockMvc.perform(post("/accounts/withdrawal")
                .param("tel", "08020202122")
                .param("amount", "100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void checkBalanceTest() throws Exception {
        mockMvc.perform(get("/accounts/balance")
                .param("tel", "08020202122")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}
