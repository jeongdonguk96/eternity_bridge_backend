package com.example.eternity_bridge_backend.memorial_hall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomSort {
    OLDEST("OLDEST"),
    LATEST("LATEST"),
    VIEWS("VIEWS"),
    POPULARITY("POPULARITY"),
    ;

    private final String value;
}
