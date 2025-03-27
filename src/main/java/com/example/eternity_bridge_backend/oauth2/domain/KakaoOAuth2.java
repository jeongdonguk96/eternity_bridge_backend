package com.example.eternity_bridge_backend.oauth2.domain;


import com.example.eternity_bridge_backend.member.entity.Member;

import java.util.Map;

public class KakaoOAuth2 implements OAuth2 {
    private Map<String, Object> attributes;

    public KakaoOAuth2(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return (String) kakaoAccount.get("email");
    }

    @Override
    public Member from(OAuth2 oAuth2) {
        return Member.builder()
                .email(oAuth2.getEmail())
                .provider(oAuth2.getProvider())
                .build();
    }
}
