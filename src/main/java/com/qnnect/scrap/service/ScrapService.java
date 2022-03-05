package com.qnnect.scrap.service;


import com.qnnect.scrap.dtos.ScrapResponse;
import com.qnnect.user.domain.User;

import java.util.List;

public interface ScrapService {
    public void addScrap(User user, Long cafeQuestionId);
//    public List<ScrapResponse> getAllScraps(User user);
}
