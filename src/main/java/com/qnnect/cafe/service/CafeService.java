package com.qnnect.cafe.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.dto.CafeCreateRequest;
import com.qnnect.cafe.dto.CafeDetailResponse;
import com.qnnect.user.domain.User;

public interface CafeService {
    public Cafe createCafe(CafeCreateRequest cafeCreateRequest,
                           User user);
    CafeDetailResponse getCafe(Long cafeId, User user);
    CafeDetailResponse joinCafe(String code, User user, long cafeId);
    void deleteCafe(Long cafeId, User user);
    public Cafe updateCafe(Long cafeId, CafeCreateRequest cafeCreateRequest, User user);
}
