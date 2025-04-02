package com.example.eternity_bridge_backend.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Slice;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SliceResult<T> extends CommonResult {
    Slice<T> valueList;
    Boolean hasNext;
}
