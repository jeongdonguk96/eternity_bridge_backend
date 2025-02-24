package com.example.eternity_bridge_backend.memorial_hall.controller;

import com.example.eternity_bridge_backend.common.dto.CommonResult;
import com.example.eternity_bridge_backend.common.dto.CursorResult;
import com.example.eternity_bridge_backend.memorial_hall.enums.CustomSort;
import com.example.eternity_bridge_backend.common.service.ResponseService;
import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import com.example.eternity_bridge_backend.memorial_hall.service.MemorialHallService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memorials")
@RequiredArgsConstructor
public class MemorialHallController {

    private static final int DEFAULT_SIZE = 10;

    private final MemorialHallService memorialHallService;
    private final ResponseService responseService;


    @PostMapping("")
    public CommonResult createMemorialHall(
            @RequestBody CreateMemorialHallRequest request
    ) {
        Long memberId = 1L;
        memorialHallService.createMemorialHall(request, memberId);
        return responseService.getSuccessResult();
    }


    @GetMapping("")
    public CursorResult<GetMemorialHallsResponse> getSlice(
            @RequestParam(value = "cursorId", required = false) Long cursorId,
            @RequestParam(value = "sort", defaultValue = "desc") CustomSort sort
    ) {
        return memorialHallService.getMemorialHalls(cursorId, sort, PageRequest.of(0, DEFAULT_SIZE));
    }

}
