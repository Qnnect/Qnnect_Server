package com.qnnect.drink.domain;

import com.qnnect.ingredients.domain.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DrinkIngredientsFilled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_DRINK_SELECTED_ID")
    private UserDrinkSelected userDrinkSelected;

    @ManyToOne
    @JoinColumn(name = "USER_INGREDIENTS_ID")
    private Ingredient ingredients;
}
