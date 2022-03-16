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
    List<DrinkIngredientsFilledResponse> currentDrinkIngredientsFilled;
    int ice;
    int iceFilled;
    int base;
    int baseFilled;
    int main;
    int mainFilled;
    int topping;
    int toppingFilled;
    boolean currentUser;

    public CafeDrinkResponse(CafeUser drinkOwner, List<CafeUser> cafeUsers,
                             User user, List<DrinkRecipe> drinkRecipes, int size) {
        List<DrinkIngredientsFilled> drinkIngredientsFilled = drinkOwner.getUserDrinkSelected()
                .getDrinkIngredientsFilled();
        this.cafeUsers = CafeProfileResponse.listFrom(cafeUsers);
        this.currentDrinkIngredientsFilled =
                DrinkIngredientsFilledResponse.listFrom(drinkIngredientsFilled);
        this.ice = drinkRecipes.get(0).getNumber();
        this.base = drinkRecipes.get(1).getNumber();
        this.main = drinkRecipes.get(2).getNumber();
        this.topping = drinkRecipes.get(3).getNumber();

        if (size > ice) {
            iceFilled = ice;
            size-=ice;
        } else {
            this.iceFilled = size;
            size=0;
        }

        if (size > base) {
            baseFilled = base;
            size-=base;
        } else {
            this.baseFilled = size;
            size=0;
        }

        if (size > main) {
            mainFilled = main;
            size-=main;
        } else {
            this.mainFilled = size;
            size=0;
        }

        if (size > topping) {
            toppingFilled = topping;
            size-=topping;
        } else {
            this.toppingFilled = size;
            size=0;
        }

        this.currentUser = isOwner(drinkOwner, user);
    }

    public static boolean isOwner(CafeUser drinkOwner , User user){
        System.out.println(drinkOwner.getUser().getId());
        System.out.println(user.getId());
        return drinkOwner.getUser().getId().equals(user.getId());
    }
}
