package com.seb42.main30.seb42_main_030.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {

    @Getter
    private ExceptionCode exceptionCode;

    public BusinessException (ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
