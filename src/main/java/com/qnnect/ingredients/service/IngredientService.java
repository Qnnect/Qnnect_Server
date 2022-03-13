package com.qnnect.ingredients.service;

import com.qnnect.ingredients.dto.IngredientResponse;

import java.util.List;

public interface IngredientService {
    public List<IngredientResponse> getIngredients();
}
