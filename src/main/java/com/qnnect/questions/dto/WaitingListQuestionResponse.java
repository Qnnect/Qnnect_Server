package com.qnnect.questions.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class WaitingListQuestionResponse {
    private long questionId;
    private LocalDate createdAt;
    private String content;


}
