package com.etranzact.service.impl;

import com.etranzact.core.utils.AppUtils;
import com.etranzact.model.request.CustomerDetailsRequest;
import com.etranzact.model.request.UssdModel;
import com.etranzact.model.request.UssdRequest;
import com.etranzact.model.response.UssdResponse;
import com.etranzact.service.AccountDetailsService;
import com.etranzact.service.CustomerDetailsService;
import com.etranzact.service.UssdHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UssdHandlerImpl implements UssdHandler {

    private final AccountDetailsService accountDetailsService;
    private final CustomerDetailsService customerDetailsService;
    private final UssdModel ussdModel;

    private static final String INIT_CODE = "*222#";
    private static final String PREVIOUS = "9";
    private static final String CREATE = "1";
    private static final String DEPOSIT = "2";
    private static final String WITHDRAWAL = "3";
    private static final String BALANCE = "4";
    private static final String EXIT_CODE = "0";

    private ArrayList<String> menuStates = new ArrayList<>();

    @Override
    public UssdResponse processRequest(UssdRequest request, HttpServletRequest servletRequest) {

        if (request.getCode() != null) {
            if (ussdModel.getInit() != null && ussdModel.getInit().equals(INIT_CODE)) {
                init();
                log.info("entered ==> {}", AppUtils.toJson(init()));
                request = generateUssdRequest(request.getCode(), request.getTelephone(), request.getPin());
                if (request.getCode().equals(ussdModel.getCreate())) {
                    confirm();
                    request = generateUssdRequest(request.getCode(), request.getTelephone(), request.getPin());
                    CustomerDetailsRequest cust = new CustomerDetailsRequest();
                    cust.setEmail(servletRequest.getParameter("email"));
                    cust.setFirstName(servletRequest.getParameter("firstName"));
                    cust.setLastName(servletRequest.getParameter("lastName"));
                    cust.setPin(request.getPin());
                    cust.setTelephone(request.getTelephone());
                    customerDetailsService.createAccount(cust);
                } else if (request.getCode().equals(ussdModel.getDeposit())) {
                    confirm();
                    request = generateUssdRequest(request.getCode(), request.getTelephone(), request.getPin());
                    accountDetailsService.deposit(request.getTelephone(), BigDecimal.valueOf(Double.parseDouble(servletRequest.getParameter("amount"))));

                } else if (request.getCode().equals(ussdModel.getWithdrawal())) {
                    confirm();
                    request = generateUssdRequest(request.getCode(), request.getTelephone(), request.getPin());
                    accountDetailsService.withdrawal(request.getTelephone(), BigDecimal.valueOf(Double.parseDouble(servletRequest.getParameter("amount"))));
                } else if (request.getCode().equals(ussdModel.getBalance())) {
                    confirm();
                    request = generateUssdRequest(request.getCode(), request.getTelephone(), request.getPin());
                    accountDetailsService.checkBalance(request.getTelephone());
                }
            }
        }
        return null;
    }


    UssdResponse init() {
        UssdResponse response = new UssdResponse();
        Map<String, String> map = new HashMap<>();
        map.put("1", "Create Account");
        map.put("2", "Account Deposit");
        map.put("3", "Account Withdrawal");
        map.put("4", "Account Balance");
        response.setMessage(map);
        return response;
    }

    UssdResponse confirm() {
        UssdResponse response = new UssdResponse();
        Map<String, String> map = new HashMap<>();
        map.put("1", "Confirm");
        map.put("9", "Back");
        map.put("0", "Exit");
        response.setMessage(map);
        return response;
    }

    private UssdRequest generateUssdRequest(String code, String telephone, String pin) {
        UssdRequest ussdRequest = new UssdRequest();
        ussdRequest.setCode(code);
        ussdRequest.setPin(pin);
        ussdRequest.setTelephone(telephone);
        return ussdRequest;
    }

}
