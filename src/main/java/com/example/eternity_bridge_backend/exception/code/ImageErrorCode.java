package com.example.eternity_bridge_backend.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ImageErrorCode implements CommonErrorCode {
    IMAGE_NOT_FOUND(400, "이미지를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
