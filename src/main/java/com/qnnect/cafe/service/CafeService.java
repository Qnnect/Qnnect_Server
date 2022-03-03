package com.qnnect.cafe.service;

import com.qnnect.cafe.dto.CafeCreateRequest;
import com.qnnect.cafe.dto.CafeResponse;
import com.qnnect.user.domain.User;

public interface CafeService {
    public CafeResponse createCafe(CafeCreateRequest cafeCreateRequest,
                                   User user);
}
