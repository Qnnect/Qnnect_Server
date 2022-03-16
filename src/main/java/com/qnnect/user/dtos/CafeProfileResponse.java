package com.qnnect.user.dtos;

import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.dto.QuestionMainResponse;
import com.qnnect.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CafeProfileResponse {
    private String profileImage;

    private String nickName;

    private Long cafeUserId;

    public static CafeProfileResponse from(CafeUser cafeUser) {
        User user = cafeUser.getUser();
        return new CafeProfileResponse(user.getProfilePicture()
                , user.getNickName(), cafeUser.getId());
    }

    public static List<CafeProfileResponse> listFrom(List<CafeUser> cafeUsers) {
        if(cafeUsers == null){
            return null;
        }

        return cafeUsers.stream()
                .map(CafeProfileResponse::from)
                .filter(CafeProfileResponse -> CafeProfileResponse != null)
                .collect(Collectors.toList());
    }
}
