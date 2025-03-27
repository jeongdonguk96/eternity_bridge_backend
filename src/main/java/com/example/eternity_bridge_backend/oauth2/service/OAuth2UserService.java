package com.example.eternity_bridge_backend.oauth2.service;

import com.example.eternity_bridge_backend.member.entity.Member;
import com.example.eternity_bridge_backend.member.service.MemberService;
import com.example.eternity_bridge_backend.oauth2.domain.KakaoOAuth2;
import com.example.eternity_bridge_backend.oauth2.domain.OAuth2;
import com.example.eternity_bridge_backend.oauth2.domain.PrincipalDetails;
import com.example.eternity_bridge_backend.oauth2.enums.OAuth2Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2Provider provider = OAuth2Provider.valueOf(userRequest.getClientRegistration().getRegistrationId());
        OAuth2 oAuth2 = null;
        switch (provider) {
            case kakao -> oAuth2 = new KakaoOAuth2(attributes);
        }

        Member member = memberService.findByEmail(oAuth2.getEmail());

        if (Objects.isNull(member)) {
            member = memberService.singUp(oAuth2);
        }

        return new PrincipalDetails(member, attributes);
    }

}
