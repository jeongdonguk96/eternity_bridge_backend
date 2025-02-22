package com.example.eternity_bridge_backend.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListResult<T> extends CommonResult {
    private List<T> dataList;
}
