package com.qnnect.user.dtos;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.cafe.dto.CafeDetailResponse;
import com.qnnect.cafe.dto.CafeMainResponse;
import com.qnnect.cafe.dto.CafeQuestionResponse;
import com.qnnect.cafe.dto.CafeUserResponse;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MainResponse {

    private ProfileResponse user;
    private List<CafeMainResponse> cafeMainResponseList;

    public MainResponse(User user, List<CafeUser> cafeUser){

        this.user = ProfileResponse.from(user);

        if(cafeUser == null){
            this.cafeMainResponseList = null;
        }else {
            System.out.println("else");
            this.cafeMainResponseList = CafeMainResponse.listFrom(cafeUser);
        }
    }

}
