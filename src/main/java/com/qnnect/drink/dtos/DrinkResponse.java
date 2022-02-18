package com.qnnect.drink.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value="음료 가져오기")
@Getter
public class DrinkResponse {

    @ApiModelProperty(value = "음료 아이디", example = "1")
    private Long id;

    @ApiModelProperty(value = "음료 이름", example = "딸기라떼")
    private String name;

    @ApiModelProperty(value = "음료 레시피", example = "얼음 몇개, 물 얼마나, 가루")
    private String recipe;

}
