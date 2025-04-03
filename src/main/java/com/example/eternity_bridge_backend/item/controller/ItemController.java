package com.example.eternity_bridge_backend.item.controller;

import com.example.eternity_bridge_backend.common.dto.CommonResult;
import com.example.eternity_bridge_backend.common.dto.PageResult;
import com.example.eternity_bridge_backend.common.service.ResponseService;
import com.example.eternity_bridge_backend.item.dto.CreateItemRequest;
import com.example.eternity_bridge_backend.item.dto.GetItemsRequest;
import com.example.eternity_bridge_backend.item.entity.Item;
import com.example.eternity_bridge_backend.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private static final int DEFAULT_SIZE = 30;

    private final ItemService itemService;
    private final ResponseService responseService;


    // 아이템을 등록한다.
    @PostMapping()
    public CommonResult createItem(
            @RequestBody CreateItemRequest request
    ) {
        Long memberId = 1L;
        itemService.createItem(request, memberId);
        return responseService.getSuccessResult();
    }


    // 아이템을 수정한다.
    @PutMapping("/{id}")
    public CommonResult modifyItem(
            @PathVariable Long id,
            @RequestBody CreateItemRequest request
    ) {
        itemService.modifyItem(request, id);
        return responseService.getSuccessResult();
    }


    // 아이템 목록을 조회한다.
    @GetMapping()
    public PageResult<Item> getItems(
            @RequestParam(value = "cursorValue", required = false) String cursorValue,
            @RequestParam(value = "itemType", required = false) GetItemsRequest request
    ) {
        return responseService.getPageResult(itemService.getItems(cursorValue, request, PageRequest.of(0, DEFAULT_SIZE)));
    }

}
