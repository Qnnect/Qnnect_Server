package com.qnnect.ingredients.service;

import com.qnnect.common.exception.CustomException;
import com.qnnect.ingredients.domain.EIngredientType;
import com.qnnect.ingredients.domain.Ingredient;
import com.qnnect.ingredients.domain.UserIngredient;
import com.qnnect.ingredients.dto.IngredientResponse;
import com.qnnect.ingredients.repository.IngredientRepository;
import com.qnnect.ingredients.repository.UserIngredientRepository;
import com.qnnect.user.domain.User;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;
    private final UserIngredientRepository userIngredientRepository;

    public List<IngredientResponse> getIngredients(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return IngredientResponse.listFrom(ingredients);
    }

    public List<IngredientResponse> getIngredientsByType(EIngredientType ingredientType){
        List<Ingredient> ingredients = ingredientRepository.findAllByIngredientType(ingredientType);
        return IngredientResponse.listFrom(ingredients);
    }

//    @Transactional
//    public List<IngredientResponse> buyIngredients(Long ingredientId, User user){
//        Ingredient ingredient = ingredientRepository.findById(ingredientId)
//                .orElseThrow(new CustomException());
//        user.minusPoint(ingredient.getPoint());
//        userIngredientRepository.save(UserIngredient.builder().)
//        return IngredientResponse.listFrom(ingredients);
//    }
}
