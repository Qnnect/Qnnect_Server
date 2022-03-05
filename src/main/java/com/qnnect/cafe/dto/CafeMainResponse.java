package com.qnnect.cafe.dto;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.dto.TodayQuestionResponse;
import com.qnnect.user.dtos.ProfileResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CafeMainResponse {
    private String title;
    private LocalDateTime createdAt;
    private int participantNum;
    private TodayQuestionResponse questionResponse;

    public static CafeMainResponse from(CafeUser cafeUser) {
        Cafe cafe = cafeUser.getCafe();

        return CafeMainResponse.builder()
                .title(cafe.getTitle())
                .createdAt(cafe.getCreatedAt())
                .participantNum(cafe.getCafeUsers().size())
                .questionResponse(new TodayQuestionResponse(cafe))
                .build();
    }

    public static List<CafeMainResponse> listFrom(List<CafeUser> cafeUserList) {
        if(cafeUserList == null){
            return null;
        }
        System.out.println("listFrom");
        return cafeUserList.stream()
                .map(CafeMainResponse::from)
                .collect(Collectors.toList());
    }
}





