package com.qnnect.ingredients.service;

import com.qnnect.ingredients.domain.Ingredient;
import com.qnnect.ingredients.dto.IngredientResponse;
import com.qnnect.ingredients.repository.IngredientRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;

    public List<IngredientResponse> getIngredients(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return IngredientResponse.listFrom(ingredients);
    }
}
