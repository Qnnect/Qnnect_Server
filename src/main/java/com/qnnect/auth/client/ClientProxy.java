package com.qnnect.auth.client;

import com.qnnect.user.domain.User;

public interface ClientProxy {
    User getUserData(String accessToken);
}
