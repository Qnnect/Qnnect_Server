package com.qnnect.drink.service;

import com.qnnect.common.exception.CustomException;
import com.qnnect.common.exception.ErrorCode;
import com.qnnect.drink.domain.DrinkIngredientsFilled;
import com.qnnect.drink.domain.DrinkRecipe;
import com.qnnect.drink.domain.UserDrinkSelected;
import com.qnnect.drink.dtos.DrinkResponse;
import com.qnnect.drink.repository.DrinkIngredientsFilledRepository;
import com.qnnect.drink.repository.DrinkRecipeRepository;
import com.qnnect.drink.repository.DrinkRepository;
import com.qnnect.drink.repository.UserDrinkSelectedRepository;
import com.qnnect.ingredients.domain.EIngredientType;
import com.qnnect.ingredients.domain.Ingredient;
import com.qnnect.ingredients.domain.UserIngredient;
import com.qnnect.ingredients.repository.IngredientRepository;
import com.qnnect.ingredients.repository.UserIngredientRepository;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.qnnect.drink.dtos.DrinkResponse.listFrom;

@Service
@Slf4j
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService{

    private final DrinkRepository drinkRepository;
    private final UserDrinkSelectedRepository userDrinkSelectedRepository;
    private final DrinkIngredientsFilledRepository drinkIngredientsFilledRepository;
    private final DrinkRecipeRepository drinkRecipeRepository;
    private final UserIngredientRepository userIngredientRepository;
    private final IngredientRepository ingredientRepository;


    public List<DrinkResponse> getDrinkList(){
        List<DrinkResponse> drinkResponses = DrinkResponse.listFrom(drinkRepository.findAll());
        return drinkResponses;
    }

    @Override
    public void addIngredient(Long userDrinkSelectedId, Long ingredientsId, User user){
        UserDrinkSelected userDrinkSelected = userDrinkSelectedRepository.getById(userDrinkSelectedId);
        long count = drinkIngredientsFilledRepository.countByUserDrinkSelected_Id(userDrinkSelectedId);
        List<DrinkRecipe> drinkRecipe = drinkRecipeRepository.findAllByDrink_Id(userDrinkSelected.getDrink().getId());
        int recipecount=0;
        int idx = -1;

        while(count >= recipecount){
            idx++;
            System.out.println(drinkRecipe.get(idx).getIngredient().getName());
            recipecount += drinkRecipe.get(idx).getNumber();
        }
        Ingredient currIngredientLevel = drinkRecipe.get(idx).getIngredient();
        Pageable pageable = PageRequest.of(0, 1);
        List<UserIngredient> userIngredient = userIngredientRepository.getByUser_IdAndIngredient_Id(user.getId(), ingredientsId, pageable);
        if(currIngredientLevel.getId() == ingredientsId){// 성공시 로직
            putIngredients(currIngredientLevel, userDrinkSelected, userIngredient);
        }
        wrongIngredients(currIngredientLevel, ingredientsId, userIngredient);
    }

    @Transactional
    public void putIngredients(Ingredient currIngredientLevel, UserDrinkSelected userDrinkSelected,
                               List<UserIngredient> userIngredient){
        log.info("FIlling user Drink");
        drinkIngredientsFilledRepository.save(DrinkIngredientsFilled.builder()
                .ingredient(currIngredientLevel)
                .userDrinkSelected(userDrinkSelected).build());
        userIngredientRepository.deleteById(userIngredient.get(0).getId());

        log.info("SUCCEED on adding ingredients");
        return;
    }

    public void wrongIngredients(Ingredient currIngredientLevel, long ingredientsId
            , List<UserIngredient> userIngredient){
        Ingredient userPutIngredient = ingredientRepository.getById(ingredientsId);
        if(currIngredientLevel.getIngredientType() == userPutIngredient.getIngredientType()){
            if(ingredientsId == 1 || currIngredientLevel.getId() == 1){//얼음인 경우
                throw new CustomException(ErrorCode.WRONG_INGREDIENT_DIFFERENT_LEVEL);
            }
            //같은 레벨의 다른 재료를 소진한경우 재료만 소진되고 throw exception
            System.out.println(userIngredient.get(0).getId());
            userIngredientRepository.deleteById(userIngredient.get(0).getId());
            throw new CustomException(ErrorCode.WRONG_INGREDIENT_SAME_LEVEL);
        }else{
            throw new CustomException(ErrorCode.WRONG_INGREDIENT_DIFFERENT_LEVEL);
        }
    }

}
