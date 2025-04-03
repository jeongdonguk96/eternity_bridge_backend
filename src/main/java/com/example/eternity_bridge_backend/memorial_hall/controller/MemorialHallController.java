package com.example.eternity_bridge_backend.memorial_hall.controller;

import com.example.eternity_bridge_backend.common.dto.CommonResult;
import com.example.eternity_bridge_backend.common.dto.SliceResult;
import com.example.eternity_bridge_backend.common.dto.SingleResult;
import com.example.eternity_bridge_backend.common.service.ResponseService;
import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallResponse;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import com.example.eternity_bridge_backend.memorial_hall.enums.CustomSort;
import com.example.eternity_bridge_backend.memorial_hall.service.MemorialHallService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/memorials")
@RequiredArgsConstructor
public class MemorialHallController {

    private static final int DEFAULT_SIZE = 9;

    private final MemorialHallService memorialHallService;
    private final ResponseService responseService;


    // 추모관을 생성한다.
    @PostMapping()
    public CommonResult createMemorialHall(
            @RequestBody CreateMemorialHallRequest request
    ) {
        Long memberId = 1L;
        memorialHallService.createMemorialHall(request, memberId);
        return responseService.getSuccessResult();
    }


    // 커서 기반 슬라이스 방식으로 추모관 목록을 조회한다.
    @GetMapping()
    public SliceResult<GetMemorialHallsResponse> getMemorialHallsBySlice(
            @RequestParam(value = "cursorValue", required = false) Long cursorValue,
            @RequestParam(value = "sort", defaultValue = "latest") CustomSort sort
    ) {
        return responseService.getSliceResult(memorialHallService.getMemorialHallsBySlice(cursorValue, sort, PageRequest.of(0, DEFAULT_SIZE)));
    }


    // 추모관을 조회한다.
    @GetMapping("/{id}")
    public SingleResult<GetMemorialHallResponse> getMemorialHall(
            @PathVariable(value = "id") Long id
    ) {
        return responseService.getSingleResult(memorialHallService.getMemorialHall(id));
    }

}
