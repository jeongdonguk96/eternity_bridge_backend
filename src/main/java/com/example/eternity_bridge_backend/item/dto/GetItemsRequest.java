package com.example.eternity_bridge_backend.item.dto;

import com.example.eternity_bridge_backend.item.enums.ItemType;

public record GetItemsRequest(
        ItemType itemType
) {}
