package com.example.eternity_bridge_backend.exception.exception;

import com.example.eternity_bridge_backend.exception.code.CommonErrorCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private final CommonErrorCode errorCode;

    public CommonException(CommonErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
