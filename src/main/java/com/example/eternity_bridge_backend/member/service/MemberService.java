package com.example.eternity_bridge_backend.member.service;

import com.example.eternity_bridge_backend.exception.code.MemberErrorCode;
import com.example.eternity_bridge_backend.exception.exception.CommonException;
import com.example.eternity_bridge_backend.member.entity.Member;
import com.example.eternity_bridge_backend.member.repository.MemberRepository;
import com.example.eternity_bridge_backend.oauth2.domain.OAuth2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    // OAuth 회원의 회원가입을 진행한다.
    public Member singUp(OAuth2 oAuth2) {
        if (isDuplicatedEmail(oAuth2.getEmail())) {
            throw new CommonException(MemberErrorCode.DUPLICATED_EMAIL);
        }

        Member newMember = oAuth2.from(oAuth2);
        return memberRepository.save(newMember);
    }


    // 회원 존재 여부를 확인한다.
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                ()-> new CommonException(MemberErrorCode.USER_NOT_FOUND));
    }


    // 이메일로 회원 정보를 조회한다.
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


    // 중복 이메일 여부를 확인한다.
    public boolean isDuplicatedEmail(String email) {
        return memberRepository.isDuplicatedEmail(email);
    }

}
