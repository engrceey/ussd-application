package com.etranzact.service.impl;

import com.etranzact.core.exceptions.CustomException;
import com.etranzact.core.utils.AccountNumberGenerator;
import com.etranzact.core.utils.ModelMapperUtils;
import com.etranzact.core.utils.SecurityUtils;
import com.etranzact.jpa.entity.AccountDetails;
import com.etranzact.jpa.entity.CustomerDetails;
import com.etranzact.jpa.repository.AccountDetailsRepository;
import com.etranzact.jpa.repository.CustomerDetailsRepository;
import com.etranzact.model.request.CustomerDetailsRequest;
import com.etranzact.model.response.CustomerDetailsResponse;
import com.etranzact.service.CustomerDetailsService;
import com.etranzact.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    private final CustomerDetailsRepository customerDetailsRepository;
    private final AccountDetailsRepository accountDetailsRepository;
    private final NotificationService notificationService;

    @Override
    @Caching(
            put = {@CachePut(value = "customerDetailsCache", key = "#result.id")},
            evict = {@CacheEvict(value = "allCustomerDetailsCache", allEntries = true)}
    )
    public CustomerDetailsResponse createAccount(CustomerDetailsRequest request) {
        customerDetailsRepository.findByTelephone(request.getTelephone()).ifPresent(customer -> {
            throw new CustomException("Account has been registered with this telephone number");
        });

        CustomerDetails customer = new CustomerDetails();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setTelephone(request.getTelephone());
        customer.setPin(SecurityUtils.twoXEncryptStringData(request.getPin()));

        AccountDetails account = new AccountDetails();
        account.setAccountNumber(AccountNumberGenerator.generate());
        account.setBalance(BigDecimal.ZERO);
        accountDetailsRepository.save(account);
        customer.setAccountDetails(account);

        customer = customerDetailsRepository.save(customer);
        String message = "Dear " + customer.getFirstName() + "," + "\n\nYour account has been created successful. \n\nAccount number: \t " + customer.getAccountDetails().getAccountNumber() + "\n\n Thank you.";
        String subject = "Account Opening";
        notificationService.pushNotification(subject, message, customer.getTelephone());
        notificationService.sendEmail(subject, message, customer.getTelephone());

        return ModelMapperUtils.map(customer, CustomerDetailsResponse.class);
    }

    @Override
    @Cacheable(value = "customerDetailsCache", key = "#tel")
    public CustomerDetailsResponse findCustomerByTel(String tel) {
        Optional<CustomerDetails> detailsOptional = customerDetailsRepository.findByTelephone(tel);
        if (detailsOptional.isPresent()) {
            CustomerDetails customerDetails = detailsOptional.get();
            return ModelMapperUtils.map(customerDetails, CustomerDetailsResponse.class);
        }
        throw new CustomException("Customer details cannot be found");
    }

    @Override
    @Cacheable(value = "customerDetailsCache", key = "#id")
    public CustomerDetailsResponse findById(Long id) {
        Optional<CustomerDetails> detailsOptional = customerDetailsRepository.findById(id);
        if (detailsOptional.isPresent()) {
            CustomerDetails customerDetails = detailsOptional.get();
            return ModelMapperUtils.map(customerDetails, CustomerDetailsResponse.class);
        }
        throw new CustomException("Customer details cannot be found");
    }

}
