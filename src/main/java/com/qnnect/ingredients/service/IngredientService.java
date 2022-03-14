package com.qnnect.ingredients.service;

import com.qnnect.ingredients.domain.EIngredientType;
import com.qnnect.ingredients.dto.IngredientResponse;
import com.qnnect.user.domain.User;

import java.util.List;

public interface IngredientService {
    public List<IngredientResponse> getIngredients();
    public List<IngredientResponse> getIngredientsByType(EIngredientType ingredientType);
    public void buyIngredients(Long ingredientId, User user);
    public List<IngredientResponse> getMyIngredients(User user);
}
