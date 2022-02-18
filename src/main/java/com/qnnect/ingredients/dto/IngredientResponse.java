package com.qnnect.ingredients.dto;

import com.qnnect.ingredients.domain.EIngredientType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;


@ApiModel(value="재료 가져오기")
@Getter
public class IngredientResponse {

    @ApiModelProperty(value = "재료 아이디", example = "1")
    private Long id;

    @ApiModelProperty(value = "재료명", example = "녹차가루")
    private String name;

    @ApiModelProperty(value = "재료 종류", example = "토핑")
    private EIngredientType ingredientType;
}
