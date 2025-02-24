package com.example.eternity_bridge_backend.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemorialHallErrorCode implements CommonErrorCode {
    MEMORIAL_HALL_NOT_FOUND(400, "해당 추모관을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    DUPLICATED_MEMORIAL_HALL(400, "이미 생성한 추모관입니다.", HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
