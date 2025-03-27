package com.example.eternity_bridge_backend.oauth2.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OAuth2Provider {
    kakao("kakao"),
    ;

    private final String value;
}
