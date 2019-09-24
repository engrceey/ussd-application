package com.etranzact.messaging.push;

import com.etranzact.model.request.PushModel;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface PushService {

    public void pushNotifier(PushModel model);
}
