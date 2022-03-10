package com.qnnect.common.exception.question;

public class CafeQuestionDatePassed extends RuntimeException{
    private static final String MESSAGE = "질문을 답변할 수 있는 기간이 지났습니다";

    public CafeQuestionDatePassed() {
        super(MESSAGE);
    }
}
