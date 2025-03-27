package com.example.eternity_bridge_backend.oauth2.domain;


import com.example.eternity_bridge_backend.member.entity.Member;

public interface OAuth2 {

    String getProvider(); // 플랫폼명
    String getEmail(); // 플랫폼 가입 시 제출한 email


    Member from(OAuth2 oAuth2);
}
