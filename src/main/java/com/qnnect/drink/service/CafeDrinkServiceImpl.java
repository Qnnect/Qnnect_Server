package com.qnnect.drink.service;

import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.cafe.repository.CafeUserRepository;
import com.qnnect.drink.domain.DrinkRecipe;
import com.qnnect.drink.domain.UserDrinkSelected;
import com.qnnect.drink.dtos.CafeDrinkResponse;
import com.qnnect.drink.repository.DrinkRecipeRepository;
import com.qnnect.drink.repository.DrinkRepository;
import com.qnnect.drink.repository.UserDrinkSelectedRepository;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CafeDrinkServiceImpl implements CafeDrinkService{

    private final DrinkRepository drinkRepository;
    private final CafeUserRepository cafeUserRepository;
    private final UserDrinkSelectedRepository userDrinkSelectedRepository;
    private final DrinkRecipeRepository drinkRecipeRepository;

    @Transactional
    @Override
    public void addCafeDrinks(User user,Long cafeId, Long drinkId){
        //우선 userDrinkselected를 생성
        UserDrinkSelected userDrinkSelected = userDrinkSelectedRepository.save(UserDrinkSelected.builder()
                .user(user).drink(drinkRepository.getById(drinkId)).build());
        //이후 해당 정보를 cafeUser에 저장해준다.
        CafeUser cafeUser = cafeUserRepository.findByCafe_IdAndUser_Id(cafeId,
                user.getId());
        cafeUser.setUserDrinkSelected(userDrinkSelected);
        cafeUserRepository.save(cafeUser);
    }

    @Transactional
    @Override
    public CafeDrinkResponse getCurrentDrink(User user, long cafeUserId, long cafeId){
        CafeUser drinkOwner = cafeUserRepository.getById(cafeUserId);
        List<CafeUser> cafeUsers = cafeUserRepository.findAllByCafe_Id(drinkOwner.getCafe().getId());
        List<DrinkRecipe> drinkRecipe = drinkRecipeRepository.findAllByDrink_Id(drinkOwner.getUserDrinkSelected()
                .getDrink().getId());
        int size = drinkOwner.getUserDrinkSelected().getDrinkIngredientsFilled().size();
        return new CafeDrinkResponse(drinkOwner,cafeUsers , user, drinkRecipe, size);
    }
}
