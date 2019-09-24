package com.etranzact.service.impl;

import com.etranzact.jpa.entity.CustomerDetails;
import com.etranzact.jpa.entity.Notifier;
import com.etranzact.jpa.repository.CustomerDetailsRepository;
import com.etranzact.jpa.repository.NotifierRepository;
import com.etranzact.model.request.PushModel;
import com.etranzact.service.NotifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Service
@RequiredArgsConstructor
public class NotifierServiceImpl implements NotifierService {

    private final NotifierRepository notifierRepository;
    private final CustomerDetailsRepository customerDetailsRepository;

    @Override
    public void notifier(PushModel request, Long custId) {
        Notifier notifier = new Notifier();
        notifier.setMessage(request.getMessage());
        notifier.setSubject(request.getSubject());
        notifier.setRead(false);

        Optional<CustomerDetails> detailsOptional = customerDetailsRepository.findById(custId);
        if(detailsOptional.isPresent()) {
            CustomerDetails customerDetails = detailsOptional.get();
            notifier.setAccountDetails(customerDetails);
        }
        notifierRepository.save(notifier);


    }

    @Override
    public void updateNotifier(Long id) {
        notifierRepository.findById(id).ifPresent(notifier -> {
            notifier.setRead(true);
            notifierRepository.save(notifier);
        });
    }
}
