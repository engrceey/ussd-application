package com.etranzact.resource;

import com.etranzact.model.request.CustomerDetailsRequest;
import com.etranzact.model.request.UssdRequest;
import com.etranzact.model.response.AccountDetailsResponse;
import com.etranzact.model.response.CustomerDetailsResponse;
import com.etranzact.model.response.UssdResponse;
import com.etranzact.service.AccountDetailsService;
import com.etranzact.service.CustomerDetailsService;
import com.etranzact.service.UssdHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by johnadeshola on 9/21/19.
 */
@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountResource {

    private final CustomerDetailsService customerDetailsService;
    private final AccountDetailsService accountDetailsService;
    private final UssdHandler ussdHandler;

    @PostMapping
    public ResponseEntity<CustomerDetailsResponse> createAccount(@RequestBody CustomerDetailsRequest request) {
        CustomerDetailsResponse response = customerDetailsService.createAccount(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("gen")
    public ResponseEntity<AccountDetailsResponse> generateAccount() {
        AccountDetailsResponse response = accountDetailsService.create();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("deposit")
    public ResponseEntity<BigDecimal> deposit(@RequestParam String tel, @RequestParam BigDecimal amount) {
        BigDecimal response = accountDetailsService.deposit(tel, amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("withdrawal")
    public ResponseEntity<BigDecimal> withdrawal(@RequestParam String tel, @RequestParam BigDecimal amount) {
        BigDecimal response = accountDetailsService.withdrawal(tel, amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("balance")
    public ResponseEntity<BigDecimal> checkBalance(@RequestParam String tel) {
        BigDecimal response = accountDetailsService.checkBalance(tel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("ussd")
    public ResponseEntity<?> handler(@RequestBody UssdRequest request, HttpServletRequest servletRequest) {
        UssdResponse response = ussdHandler.processRequest(request, servletRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
