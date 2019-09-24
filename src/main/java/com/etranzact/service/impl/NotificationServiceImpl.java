package com.etranzact.service.impl;

import com.etranzact.core.constants.AppConstant;
import com.etranzact.jpa.entity.CustomerDetails;
import com.etranzact.jpa.repository.CustomerDetailsRepository;
import com.etranzact.messaging.kafka.producer.ProducerService;
import com.etranzact.model.request.MailModel;
import com.etranzact.model.request.PushModel;
import com.etranzact.model.response.CustomerDetailsResponse;
import com.etranzact.service.CustomerDetailsService;
import com.etranzact.service.NotificationService;
import com.etranzact.service.NotifierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.etranzact.core.constants.AppConstant.Email.PLAIN;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final ProducerService producerService;
    private final CustomerDetailsRepository customerDetailsRepository;
    private final NotifierService notifierService;

    @Override
    public void sendEmail(String subject, String message, String tel) {
        Optional<CustomerDetails> detailsOptional = customerDetailsRepository.findByTelephone(tel);
        if(detailsOptional.isPresent()) {
            CustomerDetails customer = detailsOptional.get();
            MailModel model = new MailModel();
            model.setFrom(AppConstant.Email.FROM);
            model.setTo(new String[]{customer.getEmail()});
            model.setSubject(subject);
            model.setMessage(message);
            model.setType(PLAIN);
            producerService.sendEmail(model);
        }
    }

    @Override
    public void pushNotification(String subject, String message, String tel) {
        Optional<CustomerDetails> detailsOptional = customerDetailsRepository.findByTelephone(tel);
        if(detailsOptional.isPresent()) {
            CustomerDetails customer = detailsOptional.get();
            PushModel model = new PushModel();
            model.setMessage(message);
            model.setSubject(subject);
            model.setCustId(customer.getId());
            producerService.sendPush(model);
            notifierService.notifier(model, customer.getId());
        }
    }
}
