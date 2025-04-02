package com.example.eternity_bridge_backend.item.service;

import com.example.eternity_bridge_backend.exception.code.ItemErrorCode;
import com.example.eternity_bridge_backend.exception.exception.CommonException;
import com.example.eternity_bridge_backend.image.service.ImageService;
import com.example.eternity_bridge_backend.item.dto.CreateItemRequest;
import com.example.eternity_bridge_backend.item.dto.GetItemsRequest;
import com.example.eternity_bridge_backend.item.entity.Item;
import com.example.eternity_bridge_backend.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ImageService imageService;
    private final ItemRepository itemRepository;


    // 아이템을 등록한다.
    public void createItem(CreateItemRequest request, Long memberId) {
        String trxKey = MDC.get("trxKey");

        checkDuplicatedItem(request, trxKey);
        log.info("[{}] 아이템 등록 전 중복체크 결과 => 중복없음", trxKey);

        Item item = itemRepository.save(request.from());
        log.info("[{}] 아이템 등록 성공", trxKey);

        imageService.modifyDomainId(item.getUrl(), memberId, item.getId());
        log.info("[{}] 이미지 엔티티의 도메인 ID 변경 성공", trxKey);
    }


    // 아이템을 수정한다.
    @Transactional
    public void modifyItem(CreateItemRequest request, Long id) {
        String trxKey = MDC.get("trxKey");

        Item item = itemRepository.findById(id).orElseThrow(
                () -> new CommonException(ItemErrorCode.ITEM_NOT_FOUND)
        );
        log.info("[{}] 아이템 조회 성공", trxKey);

        item.modifyItem(request);
        log.info("[{}] 아이템 수정 성공", trxKey);
    }


    // 아이템 목록을 조회한다.
    public Page<Item> getItems(String cursorValue, GetItemsRequest request, Pageable pageable) {
        String trxKey = MDC.get("trxKey");

        Page<Item> itemList = itemRepository.getItems(cursorValue, request, pageable);
        log.info("[{}] 조회된 아이템 목록 {}개", trxKey, itemList.getSize());
        log.info("[{}] 전체 페이지 수 = {}", trxKey, itemList.getTotalPages());

        return itemList;
    }


    // 아이템 등록 시 중복체크를 진행한다.
    private void checkDuplicatedItem(CreateItemRequest request, String trxKey) {
        if (itemRepository.checkDuplicatedItem(request)) {
            log.info("[{}] 아이템 등록 전 중복체크 결과 => 중복있음", trxKey);
            throw new CommonException(ItemErrorCode.DUPLICATED_ITEM);
        }
    }

}
