package com.example.eternity_bridge_backend.image.entity;

import com.example.eternity_bridge_backend.common.entity.BaseEntity;
import com.example.eternity_bridge_backend.image.enums.ImageDomain;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Image extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ImageDomain imageDomain;
    private Long domainId;
    private String url;
    private int sequence;
    private Long memberId;


    public static Image from(ImageDomain imageDomain, Long domainId, String path, int sequence, Long memberId) {
        return Image.builder()
                .imageDomain(imageDomain)
                .domainId(domainId)
                .url(path)
                .sequence(sequence)
                .memberId(memberId)
                .build();
    }


    public static Image from(ImageDomain imageDomain, String path, int sequence, Long memberId) {
        return Image.builder()
                .imageDomain(imageDomain)
                .url(path)
                .sequence(sequence)
                .memberId(memberId)
                .build();
    }


    public void modifyDomainId(Long domainId) {
        this.domainId = domainId;
    }


    public void modifyUrl(String url) {
        this.url = url;
    }
}
