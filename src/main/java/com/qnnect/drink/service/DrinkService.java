package com.qnnect.drink.service;

import com.qnnect.drink.dtos.DrinkResponse;
import com.qnnect.user.domain.User;

import java.util.List;

public interface DrinkService {
    public List<DrinkResponse> getDrinkList();
    public void addIngredient(Long userDrinkSelectedId, Long ingredientsId, User user);
}
