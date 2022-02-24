package com.qnnect.user.Service;

import com.qnnect.user.domain.User;

public interface UserService {
    public void enableNotification(User user, boolean enabledNotification);
}
