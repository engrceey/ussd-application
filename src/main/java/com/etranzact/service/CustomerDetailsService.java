package com.etranzact.service;

import com.etranzact.model.request.CustomerDetailsRequest;
import com.etranzact.model.response.CustomerDetailsResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface CustomerDetailsService {

    @Transactional
    CustomerDetailsResponse createAccount(CustomerDetailsRequest request);

    @Transactional(readOnly = true)
    CustomerDetailsResponse findCustomerByTel(String tel);

    @Transactional(readOnly = true)
    public CustomerDetailsResponse findById(Long id);


}
