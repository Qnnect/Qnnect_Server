package com.qnnect.ingredients.service;

import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.common.exception.CustomException;
import com.qnnect.ingredients.domain.EIngredientType;
import com.qnnect.ingredients.domain.Ingredient;
import com.qnnect.ingredients.domain.UserIngredient;
import com.qnnect.ingredients.dto.IngredientResponse;
import com.qnnect.ingredients.repository.IngredientRepository;
import com.qnnect.ingredients.repository.UserIngredientRepository;
import com.qnnect.user.domain.User;
import com.qnnect.user.repositories.UserRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.qnnect.common.exception.ErrorCode.INGREDIENT_NOT_FOUND;
import static com.qnnect.common.exception.ErrorCode.POINT_NOT_ENOUGH;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;
    private final UserIngredientRepository userIngredientRepository;
    private final UserRepository userRepository;

    public List<IngredientResponse> getIngredients(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return IngredientResponse.listFrom(ingredients);
    }

    public List<IngredientResponse> getIngredientsByType(EIngredientType ingredientType){
        List<Ingredient> ingredients = ingredientRepository.findAllByIngredientType(ingredientType);
        return IngredientResponse.listFrom(ingredients);
    }

    @Transactional
    public void buyIngredients(Long ingredientId, User user){
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new CustomException(INGREDIENT_NOT_FOUND));
        if(user.getPoint() < ingredient.getPoint()){
            throw new CustomException(POINT_NOT_ENOUGH);
        }
        user.minusPoint(ingredient.getPoint());
        UserIngredient userIngredient = userIngredientRepository.save(UserIngredient.builder().user(user)
                .ingredient(ingredient).build());
        userRepository.save(user);
    }

    public List<IngredientResponse> getMyIngredients(User user){
        List<UserIngredient> userIngredients = userIngredientRepository.findAllByUser_Id(user.getId());
        List<Ingredient> ingredientList = userIngredients.stream().map(UserIngredient::getIngredient).collect(Collectors.toList());
        return IngredientResponse.listFrom(ingredientList);
    }
}
