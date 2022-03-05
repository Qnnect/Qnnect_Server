package com.qnnect.cafe.service;

import com.qnnect.cafe.dto.CafeCreateRequest;
import com.qnnect.cafe.dto.CafeDetailResponse;
import com.qnnect.user.domain.User;

public interface CafeService {
    public void createCafe(CafeCreateRequest cafeCreateRequest,
                           User user);
    public CafeDetailResponse getCafe(Long cafeId);
    public CafeDetailResponse joinCafe(String code, User user, long cafeId);
}
