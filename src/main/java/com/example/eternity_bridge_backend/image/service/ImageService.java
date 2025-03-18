package com.example.eternity_bridge_backend.image.service;

import com.example.eternity_bridge_backend.image.entity.Image;
import com.example.eternity_bridge_backend.image.enums.ImageDomain;
import com.example.eternity_bridge_backend.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3Service s3Service;
    private final ImageRepository imageRepository;

    @Value("https://${cloud.aws.s3.bucket}.s3.ap-northeast-2.amazonaws.com/")
    private String S3_URL;


    // 단일 파일을 저장한다.
    @Transactional
    public String createImage(MultipartFile file, ImageDomain domain, Long memberId) throws IOException {
        String trxKey = MDC.get("trxKey");
        String fileName = s3Service.upload(file, domain, memberId, trxKey);
        Image image = Image.from(domain, S3_URL + fileName, 1, memberId);
        imageRepository.save(image);
        log.info("[{}] 이미지 저장 성공", trxKey);

        return image.getUrl();
    }

}
