package com.example.eternity_bridge_backend.item.repository;

import com.example.eternity_bridge_backend.item.dto.CreateItemRequest;
import com.example.eternity_bridge_backend.item.dto.GetItemsRequest;
import com.example.eternity_bridge_backend.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryQuerydsl {
    Page<Item> getItems(String cursorValue, GetItemsRequest request, Pageable pageable);
    boolean checkDuplicatedItem(CreateItemRequest request);
}
