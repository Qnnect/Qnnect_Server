package com.qnnect.common.exception.cafe;

import com.qnnect.common.exception.CustomException;

public class IncorrectCafeCodeException extends CustomException {
    private static final String MESSAGE = "해당 코드는 올바르지 못한 코드입니다.";

    public IncorrectCafeCodeException() {
        super(MESSAGE);
    }
}
