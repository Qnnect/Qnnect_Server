package com.qnnect.scrap.service;


import com.qnnect.scrap.dtos.ScrapResponse;
import com.qnnect.user.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ScrapService {

    public void addScrap(User user, Long cafeQuestionId);

    public void deleteScrap(User user, Long cafeQuestionId);

//    public List<ScrapResponse> getAllScraps(Pageable pageable, User user);

    public List<ScrapResponse> getCafeScraps(Pageable pageable, User user, Long cafeId);
}