package com.qnnect.drink.service;

import com.qnnect.user.domain.User;

public interface CafeDrinkService {
    public void addCafeDrinks(User user, Long cafeId, Long drinkId);
}
