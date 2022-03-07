package com.qnnect.common.exception.cafe;

import com.qnnect.common.exception.CustomException;

public class QuestionNotScrappedException extends CustomException {
    private static final String MESSAGE = "해당 질문은 스크랩된 질문이 아닙니다.";

    public QuestionNotScrappedException() {
        super(MESSAGE);
    }
}
