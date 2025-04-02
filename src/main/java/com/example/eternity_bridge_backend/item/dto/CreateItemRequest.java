package com.example.eternity_bridge_backend.item.dto;

import com.example.eternity_bridge_backend.item.entity.Item;
import com.example.eternity_bridge_backend.item.enums.ItemType;

public record CreateItemRequest(
        ItemType itemType,
        String name,
        String url
) {

    public Item from() {
        return Item.builder()
                .itemType(this.itemType)
                .name(this.name)
                .url(this.url)
                .build();
    }
}
