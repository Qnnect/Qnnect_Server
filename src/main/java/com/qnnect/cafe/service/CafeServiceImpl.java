package com.qnnect.cafe.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.cafe.dto.CafeRequest;
import com.qnnect.cafe.dto.CafeDetailResponse;
import com.qnnect.cafe.repository.CafeRepository;
import com.qnnect.cafe.repository.CafeUserRepository;
import com.qnnect.common.exception.cafe.CafeMemberExceededExeption;
import com.qnnect.common.exception.cafe.IncorrectCafeCodeException;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

    private final CafeRepository cafeRepository;
    private final CafeUserRepository cafeUserRepository;

    @Transactional
    public Cafe createCafe(CafeRequest cafeRequest, User user){
        Cafe cafe = cafeRepository.save(cafeRequest.toEntity());
        log.info("created cafe");
        CafeUser cafeUser = cafeUserRepository.save(CafeUser.builder().cafe(cafe).user(user).build());
        log.info("added user to cafe user");

       return cafe;
    }

    @Transactional
    public CafeDetailResponse joinCafe(String code, User user, long cafeId){
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
        CafeUser currentCafeUser = cafeUserRepository.findByCafe_IdAndUser_Id(cafeId, user.getId());
        return new CafeDetailResponse(cafe, currentCafeUser);
    }

    @Transactional(readOnly=true)
    public CafeDetailResponse getCafe(Long cafeId, User user){
        CafeUser currentCafeUser = cafeUserRepository.findByCafe_IdAndUser_Id(cafeId, user.getId());
        Cafe cafe = cafeRepository.getById(cafeId);
        return new CafeDetailResponse(cafe, currentCafeUser);
    }

    @Transactional
    @Override
    public void deleteCafe(Long cafeId, User user){
        cafeRepository.deleteById(cafeId);
    }

    @Transactional
    @Override
    public void leaveCafe(Long cafeId, User user){
        System.out.println("finding cafeUser");
        CafeUser cafeUser = cafeUserRepository.findByCafe_IdAndUser_Id(cafeId, user.getId());
        System.out.println("delete cafe user");
        cafeUserRepository.delete(cafeUser);

        if(cafeUserRepository.existsByCafe_Id(cafeId)){
            System.out.println("deleting cafe");
            cafeRepository.deleteById(cafeId);
        }
    }

    @Transactional
    @Override
    public Cafe updateCafe(Long cafeId, CafeRequest cafeCreateRequest, User user){
        Cafe cafe = cafeRepository.getById(cafeId);
        if(cafeCreateRequest.getDiaryColor() != null){
            cafe.setDiaryColor(cafeCreateRequest.getDiaryColor());
        }
        if(cafeCreateRequest.getGroupType() != null){
            cafe.setGroupType(cafeCreateRequest.getGroupType());
        }
        if(cafeCreateRequest.getQuestionCycle() != null){
            cafe.setQuestionCycle(cafeCreateRequest.getQuestionCycle());
        }
        if(cafeCreateRequest.getTitle() != null){
            cafe.setTitle(cafeCreateRequest.getTitle());
        }
        return cafe;
    }
}
