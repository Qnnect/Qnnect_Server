package com.qnnect.cafe.dto;

import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.drink.domain.DrinkIngredientsFilled;
import com.qnnect.drink.domain.UserDrinkSelected;
import com.qnnect.drink.dtos.DrinkIngredientsFilledResponse;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.ProfileResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
public class CafeUserResponse {
    private ProfileResponse user;
    private String userDrinkSelected;
    private List<DrinkIngredientsFilledResponse> drinkIngredientsFilledResponseList;

    public static CafeUserResponse from(CafeUser cafeUser) {
        List<DrinkIngredientsFilled> drinkIngredientsFilled = new ArrayList<>();
        UserDrinkSelected userDrinkSelected = cafeUser.getUserDrinkSelected();
        if(userDrinkSelected != null){
            drinkIngredientsFilled = userDrinkSelected.getDrinkIngredientsFilled();
        }

        return CafeUserResponse.builder()
                .user(ProfileResponse.from(cafeUser.getUser()))
                .userDrinkSelected(userDrinkSelected != null ? userDrinkSelected.getDrink().getName() : null)
                .drinkIngredientsFilledResponseList(DrinkIngredientsFilledResponse
                        .listFrom(drinkIngredientsFilled))
                .build();
    }


    public static List<CafeUserResponse> listFrom(List<CafeUser> cafeUsers,
                                                  CafeUser currentCafeUser) {
        
        return cafeUsers.stream()
                .filter(cafeUser -> cafeUser.getUser() != currentCafeUser.getUser())
                .map(CafeUserResponse::from)
                .collect(Collectors.toList());
    }
}
