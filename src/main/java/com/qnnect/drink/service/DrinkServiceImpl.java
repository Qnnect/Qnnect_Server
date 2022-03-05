package com.qnnect.drink.service;

import com.qnnect.drink.dtos.DrinkResponse;
import com.qnnect.drink.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.qnnect.drink.dtos.DrinkResponse.listFrom;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService{

    private final DrinkRepository drinkRepository;

    public List<DrinkResponse> getDrinkList(){
        List<DrinkResponse> drinkResponses = DrinkResponse.listFrom(drinkRepository.findAll());
        return drinkResponses;
    }


}
