package com.qnnect.drink.domain;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class UserDrinkSelected {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DRINK_ID")
    private Drink drink;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "userDrinkSelected")
    private List<DrinkIngredientsFilled> drinkIngredientsFilled = new ArrayList<>();


    @Builder
    public UserDrinkSelected(User user, Drink drink){
        setUser(user);
        setDrink(drink);
    }

    public void setUser(User user){
        if (Objects.isNull(this.user)) {
            this.user = user;
        }
    }

    public void setDrink(Drink drink){
        if (Objects.isNull(this.drink)) {
            this.drink = drink;
        }
    }

}
