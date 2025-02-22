package com.example.eternity_bridge_backend.exception;

import com.example.eternity_bridge_backend.common.dto.CommonResult;
import com.example.eternity_bridge_backend.common.service.ResponseService;
import com.example.eternity_bridge_backend.exception.exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionHandler {

    private final ResponseService responseService;


    @ExceptionHandler(CommonException.class)
    public CommonResult handleException(CommonException ex) {
        return responseService.getFailResult(ex);
    }

}
