package com.qnnect.common.exception.cafe;

public class CafeQuestionNotFoundException extends RuntimeException{
    private static final String MESSAGE = "해당 질문은 이 카페에 존재하지 않습니다.";

    public CafeQuestionNotFoundException() {
        super(MESSAGE);
    }
}
