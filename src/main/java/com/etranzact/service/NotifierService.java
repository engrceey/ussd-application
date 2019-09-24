package com.etranzact.service;

import com.etranzact.model.request.PushModel;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface NotifierService {

    public void notifier(PushModel request, Long custId);

    public void updateNotifier(Long id);
}
