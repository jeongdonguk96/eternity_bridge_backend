package com.example.eternity_bridge_backend.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ItemErrorCode implements CommonErrorCode {
    ITEM_NOT_FOUND(400, "아이템을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    DUPLICATED_ITEM(400, "이미 등록한 아이템입니다.", HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
