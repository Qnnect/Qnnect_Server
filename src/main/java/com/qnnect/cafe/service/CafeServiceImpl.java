package com.qnnect.cafe.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.cafe.dto.CafeCreateRequest;
import com.qnnect.cafe.dto.CafeResponse;
import com.qnnect.cafe.repository.CafeRepository;
import com.qnnect.cafe.repository.CafeUserRepository;
import com.qnnect.common.exception.cafe.CafeMemberExceededExeption;
import com.qnnect.common.exception.cafe.IncorrectCafeCodeException;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

    private final CafeRepository cafeRepository;
    private final CafeUserRepository cafeUserRepository;

    public CafeResponse createCafe(CafeCreateRequest cafeCreateRequest, User user){
        Cafe cafe = cafeRepository.save(cafeCreateRequest.toEntity(user));
        log.info("created cafe");
        cafeUserRepository.save(CafeUser.builder().cafe(cafe).user(user).build());
        log.info("added user to cafe user");
        return new CafeResponse(cafe);
    }

    public CafeResponse joinCafe(String code, User user, long cafeId){
        Cafe cafe = cafeRepository.getById(cafeId);
        long memberNum = cafeUserRepository.countByCafe_Id(cafeId);

        System.out.println(memberNum);
        if(memberNum >= 5){
            throw new CafeMemberExceededExeption();
        }
        System.out.println("member not exceeded");
        System.out.println(cafe.getCode());
        if(!cafe.getCode().equals(code)){
            throw new IncorrectCafeCodeException();
        }
        cafeUserRepository.save(CafeUser.builder().cafe(cafe).user(user).build());
        return new CafeResponse(cafe);
    }
}
