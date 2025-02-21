package com.example.eternity_bridge_backend.pet.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemType {
    TOY("장난감"),
    TREAT("간식"),
    GOODS("물건"),
    ;

    private final String itemType;
}
