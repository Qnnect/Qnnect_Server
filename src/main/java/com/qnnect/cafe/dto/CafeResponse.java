package com.qnnect.cafe.dto;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.drink.domain.UserDrinkSelected;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.ProfileResponse;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@ApiModel(value = "카페")
public class CafeResponse {

    private LocalDateTime createdAt;

    private String title;

    private ProfileResponse organizer;
    
    private String code;

    public CafeResponse(Cafe entity){
        this.createdAt = entity.getCreatedAt();
        this.title = entity.getTitle();
        this.organizer = ProfileResponse.from(entity.getOrganizer());
        this.code = entity.getCode();
    }
}
