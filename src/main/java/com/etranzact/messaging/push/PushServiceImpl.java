package com.etranzact.messaging.push;

import com.etranzact.model.request.PushModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PushServiceImpl implements PushService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void pushNotifier(PushModel model) {
        simpMessagingTemplate.convertAndSend("${spring.cloud.stream.bindings.push-input.destination}", model);
    }

}
