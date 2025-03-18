package com.example.eternity_bridge_backend.image.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageDomain {
    PET("PET"),
    ITEM("ITEM"),
    ;

    private final String value;
}
