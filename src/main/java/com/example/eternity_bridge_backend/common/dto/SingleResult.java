package com.example.eternity_bridge_backend.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SingleResult<T> extends CommonResult {
    private T data;
}
