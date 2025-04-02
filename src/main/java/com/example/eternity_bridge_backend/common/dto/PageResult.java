package com.example.eternity_bridge_backend.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
public class PageResult<T> extends CommonResult {
    private Page<T> dataList;
}
