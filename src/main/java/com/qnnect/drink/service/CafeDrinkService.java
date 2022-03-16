package com.qnnect.drink.service;

import com.qnnect.drink.dtos.CafeDrinkResponse;
import com.qnnect.user.domain.User;

public interface CafeDrinkService {
    public void addCafeDrinks(User user, Long cafeId, Long drinkId);
    public CafeDrinkResponse getCurrentDrink(User user, long cafeUserId, long cafeId);
}
