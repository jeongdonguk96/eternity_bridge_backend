package com.example.eternity_bridge_backend.member.dto;

import com.example.eternity_bridge_backend.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    public Member toEntity() {
        return Member.builder()
                .name(this.getName())
                .email(this.getEmail())
                .password(this.getPassword())
                .phoneNumber(this.getPhoneNumber())
                .build();
    }
}
