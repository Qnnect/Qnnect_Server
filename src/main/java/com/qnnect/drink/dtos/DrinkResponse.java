package com.qnnect.drink.dtos;

import com.qnnect.drink.domain.Drink;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel(value="음료 가져오기")
@Getter
@Builder
public class DrinkResponse {

    @ApiModelProperty(value = "음료 아이디", example = "1")
    private Long id;

    @ApiModelProperty(value = "음료 이름", example = "딸기라떼")
    private String name;

    @ApiModelProperty(value = "음료 레시피", example = "얼음 몇개, 물 얼마나, 가루")
    private String recipe;


    public static DrinkResponse from(Drink drink) {
        return DrinkResponse.builder()
                .id(drink.getId())
                .name(drink.getName())
                .recipe(drink.getRecipe())
                .build();
    }

    public static List<DrinkResponse> listFrom(List<Drink> drinks) {
        return drinks.stream()
                .map(DrinkResponse::from)
                .collect(Collectors.toList());
    }
}
