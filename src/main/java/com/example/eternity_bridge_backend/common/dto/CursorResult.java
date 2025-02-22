package com.example.eternity_bridge_backend.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Slice;


@Getter
@Setter
@NoArgsConstructor
public class CursorResult<T> extends CommonResult {
    Slice<T> valueList;
    Boolean hasNext;
}
