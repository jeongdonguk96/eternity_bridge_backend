package com.example.eternity_bridge_backend.exception.code;

import org.springframework.http.HttpStatus;

public interface CommonErrorCode {
    int getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
