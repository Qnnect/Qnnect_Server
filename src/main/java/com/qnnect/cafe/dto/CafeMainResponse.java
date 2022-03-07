package com.qnnect.cafe.dto;

import com.qnnect.cafe.domain.Cafe;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CafeMainResponse {
    private String title;
    private LocalDateTime createdAt;
    private int cafeUserNum;

    public static CafeMainResponse from(Cafe cafe) {

        return CafeMainResponse.builder()
                .title(cafe.getTitle())
                .createdAt(cafe.getCreatedAt())
                .cafeUserNum(cafe.getCafeUsers().size())
                .build();
    }

    public static List<CafeMainResponse> listFrom(List<Cafe> cafeList) {
        if(cafeList == null){
            return null;
        }

        List<CafeMainResponse> cafeMainResponseList = new ArrayList<>();

        for(Cafe cafe:cafeList){
            cafeMainResponseList.add(from(cafe));
        }

        return cafeMainResponseList;
    }
}





