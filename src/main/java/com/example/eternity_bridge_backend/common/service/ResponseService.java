package com.example.eternity_bridge_backend.common.service;

import com.example.eternity_bridge_backend.common.dto.CommonResult;
import com.example.eternity_bridge_backend.common.dto.ListResult;
import com.example.eternity_bridge_backend.common.dto.SingleResult;
import com.example.eternity_bridge_backend.exception.exception.CommonException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        result.setSuccessResult();

        return result;
    }


    public <T> ListResult<T> getListResult(List<T> dataList) {
        ListResult<T> result = new ListResult<>();
        result.setDataList(dataList);
        result.setSuccessResult();

        return result;
    }


    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        result.setSuccessResult();

        return result;
    }


    public CommonResult getFailResult(CommonException ex) {
        CommonResult result = new CommonResult();
        result.setFailResult(ex);

        return result;
    }

}
