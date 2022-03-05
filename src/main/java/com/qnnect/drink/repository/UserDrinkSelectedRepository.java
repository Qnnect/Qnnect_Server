package com.qnnect.drink.repository;

import com.qnnect.drink.domain.UserDrinkSelected;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDrinkSelectedRepository extends
        JpaRepository<UserDrinkSelected, Long> {
}
