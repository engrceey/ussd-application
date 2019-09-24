package com.etranzact.service.impl;

import com.etranzact.core.exceptions.CustomException;
import com.etranzact.core.utils.AccountNumberGenerator;
import com.etranzact.core.utils.ModelMapperUtils;
import com.etranzact.jpa.entity.AccountDetails;
import com.etranzact.jpa.entity.CustomerDetails;
import com.etranzact.jpa.repository.AccountDetailsRepository;
import com.etranzact.jpa.repository.CustomerDetailsRepository;
import com.etranzact.messaging.sms.SmsService;
import com.etranzact.model.request.SmsModel;
import com.etranzact.model.response.AccountDetailsResponse;
import com.etranzact.service.AccountDetailsService;
import com.etranzact.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AccountDetailsServiceImpl implements AccountDetailsService {

    private final AccountDetailsRepository accountDetailsRepository;
    private final CustomerDetailsRepository customerDetailsRepository;
    private final NotificationService notificationService;
    private final SmsService smsService;

    @Override
    @Caching(
            put = {@CachePut(value = "accountDetailsCache", key = "#result.id")},
            evict = {@CacheEvict(value = "allAccountDetailsCache", allEntries = true)}
    )
    public AccountDetailsResponse create() {
        AccountDetails details = new AccountDetails();
        details.setAccountNumber(AccountNumberGenerator.generate());
        details.setBalance(BigDecimal.ZERO);
        details = accountDetailsRepository.save(details);
        return ModelMapperUtils.map(details, AccountDetailsResponse.class);
    }

    @Override
    @Caching(
            put = {@CachePut(value = "accountDetailsCache", key = "#tel")},
            evict = {@CacheEvict(value = "allAccountDetailsCache", allEntries = true)}
    )
    public BigDecimal deposit(String tel, BigDecimal amount) {
        Optional<CustomerDetails> detailsOptional = customerDetailsRepository.findByTelephone(tel);
        if (detailsOptional.isPresent()) {
            CustomerDetails customerDetails = detailsOptional.get();
            Optional<AccountDetails> optionalAccount = accountDetailsRepository.findByAccountNumber(customerDetails.getAccountDetails().getAccountNumber());
            if (optionalAccount.isPresent()) {
                AccountDetails account = optionalAccount.get();
                BigDecimal total = account.getBalance().add(amount);
                account.setBalance(total);
                account = accountDetailsRepository.save(account);
                String message = "Dear Customer, \n\nYour account has been credit successful. \n\nYour Account Balance is: \t " + account.getBalance() + "\n\n Thank you.";
                String subject = "Account Deposit";
                notificationService.sendEmail(subject, message, tel);
                smsService.sendSms(new SmsModel("", Collections.singletonList(customerDetails.getTelephone()), "Dear Customer, your account has been credited with " + amount + ". Your new balance is " + account.getBalance()));
                return account.getBalance();
            }
        }
        throw new CustomException("Customer details cannot be found");

    }

    @Override
    @Caching(
            put = {@CachePut(value = "accountDetailsCache", key = "#tel")},
            evict = {@CacheEvict(value = "allAccountDetailsCache", allEntries = true)}
    )
    public BigDecimal withdrawal(String tel, BigDecimal amount) {

        Optional<CustomerDetails> detailsOptional = customerDetailsRepository.findByTelephone(tel);
        if (detailsOptional.isPresent()) {
            CustomerDetails customerDetails = detailsOptional.get();
            Optional<AccountDetails> optionalAccount = accountDetailsRepository.findByAccountNumber(customerDetails.getAccountDetails().getAccountNumber());
            if (optionalAccount.isPresent()) {
                AccountDetails account = optionalAccount.get();
                if (account.getBalance().compareTo(amount) > -1) {
                    BigDecimal total = account.getBalance().subtract(amount);
                    account.setBalance(total);
                    account = accountDetailsRepository.save(account);
                    String message = "Dear Customer, \n\nYour account has been debit with successful.. \n\nYour Account Balance is: \t " + account.getBalance() + "\n\n Thank you.";
                    String subject = "Account Deposit";
                    notificationService.sendEmail(subject, message, tel);
                    smsService.sendSms(new SmsModel("", Collections.singletonList(customerDetails.getTelephone()), "Dear Customer, your account has been debited with " + amount + ". Your new balance is " + account.getBalance()));
                    return account.getBalance();
                } else {
                    throw new CustomException("You do not have sufficient credit balance");
                }
            }
        }
        throw new CustomException("Customer details cannot be found");
    }

    @Override
    @Cacheable(value = "accountDetailsCache", key = "#tel")
    public BigDecimal checkBalance(String tel) {
        Optional<CustomerDetails> detailsOptional = customerDetailsRepository.findByTelephone(tel);
        if (detailsOptional.isPresent()) {
            CustomerDetails customerDetails = detailsOptional.get();
            Optional<AccountDetails> optionalAccount = accountDetailsRepository.findByAccountNumber(customerDetails.getAccountDetails().getAccountNumber());
            if (optionalAccount.isPresent()) {
                AccountDetails accountDetails = optionalAccount.get();
                String message = "Dear Customer, \n\nYour account account balance is : \t " + accountDetails.getBalance() + "\n\n Thank you.";
                String subject = "Account Deposit";
                notificationService.sendEmail(subject, message, tel);
                return accountDetails.getBalance();
            }
        }
        throw new CustomException("Customer details cannot be found");
    }
}
