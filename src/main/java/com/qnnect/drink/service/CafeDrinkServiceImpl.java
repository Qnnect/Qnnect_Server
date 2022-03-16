package com.qnnect.drink.service;

import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.cafe.repository.CafeUserRepository;
import com.qnnect.drink.domain.UserDrinkSelected;
import com.qnnect.drink.repository.DrinkRepository;
import com.qnnect.drink.repository.UserDrinkSelectedRepository;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CafeDrinkServiceImpl implements CafeDrinkService{

    private final DrinkRepository drinkRepository;
    private final CafeUserRepository cafeUserRepository;
    private final UserDrinkSelectedRepository userDrinkSelectedRepository;

    @Transactional
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


}
