package com.qnnect.common.exception.cafe;

public class CafeMemberExceededExeption extends RuntimeException{
    private static final String MESSAGE = "이미 꽉찬 카페입니다.";

    public CafeMemberExceededExeption() {
        super(MESSAGE);
    }
}
