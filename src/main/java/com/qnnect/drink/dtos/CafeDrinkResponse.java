package com.qnnect.drink.dtos;

import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.comments.domain.Comment;
import com.qnnect.drink.domain.DrinkIngredientsFilled;
import com.qnnect.drink.domain.DrinkRecipe;
import com.qnnect.drink.domain.UserDrinkSelected;
import com.qnnect.ingredients.domain.EIngredientType;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.CafeProfileResponse;
import com.qnnect.user.dtos.ProfileResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class CafeDrinkResponse {
    List<CafeProfileResponse> cafeUsers;
    CafeDrinkCommonResponse currentDrinkInfo;
    boolean currentUser;

    public CafeDrinkResponse(CafeUser drinkOwner, List<CafeUser> cafeUsers,
                             User user, List<DrinkRecipe> drinkRecipes, int size) {

        this.cafeUsers = CafeProfileResponse.listFrom(cafeUsers);
        this.currentDrinkInfo = new CafeDrinkCommonResponse(drinkOwner, drinkRecipes, size);
        this.currentUser = isOwner(drinkOwner, user);
    }

    public static boolean isOwner(CafeUser drinkOwner , User user){
        System.out.println(drinkOwner.getUser().getId());
        System.out.println(user.getId());
        return drinkOwner.getUser().getId().equals(user.getId());
    }
}