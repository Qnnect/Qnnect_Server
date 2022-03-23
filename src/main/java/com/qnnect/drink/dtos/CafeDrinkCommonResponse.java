package com.qnnect.drink.dtos;

import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.drink.domain.DrinkIngredientsFilled;
import com.qnnect.drink.domain.DrinkRecipe;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.CafeProfileResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class CafeDrinkCommonResponse {
    private Long userDrinkSelectedId;
    private String userDrinkName;
    private List<DrinkIngredientsFilledResponse> currentDrinkIngredientsFilled;
    private int ice;
    private int iceFilled;
    private int base;
    private int baseFilled;
    private int main;
    private int mainFilled;
    private int topping;
    private int toppingFilled;

    public CafeDrinkCommonResponse(CafeUser drinkOwner,
                              List<DrinkRecipe> drinkRecipes, int size) {
        if (drinkOwner.getUserDrinkSelected() != null) {
            List<DrinkIngredientsFilled> drinkIngredientsFilled = drinkOwner.getUserDrinkSelected()
                    .getDrinkIngredientsFilled();
            this.userDrinkSelectedId = drinkOwner.getUserDrinkSelected().getId();
            this.currentDrinkIngredientsFilled =
                    DrinkIngredientsFilledResponse.listFrom(drinkIngredientsFilled);
            this.userDrinkName = drinkOwner.getUserDrinkSelected().getDrink().getName();
            this.ice = drinkRecipes.get(0).getNumber();
            this.base = drinkRecipes.get(1).getNumber();
            this.main = drinkRecipes.get(2).getNumber();
            this.topping = drinkRecipes.get(3).getNumber();
            System.out.println(drinkOwner.getUser().getNickName() + drinkIngredientsFilled.size());
            size = drinkIngredientsFilled.size();
            if (size > ice) {
                iceFilled = ice;
                size -= ice;
            } else {
                this.iceFilled = size;
                size = 0;
            }

            if (size > base) {
                baseFilled = base;
                size -= base;
            } else {
                this.baseFilled = size;
                size = 0;
            }

            if (size > main) {
                mainFilled = main;
                size -= main;
            } else {
                this.mainFilled = size;
                size = 0;
            }

            if (size > topping) {
                toppingFilled = topping;
                size -= topping;
            } else {
                this.toppingFilled = size;
                size = 0;
            }
        }

    }
}
