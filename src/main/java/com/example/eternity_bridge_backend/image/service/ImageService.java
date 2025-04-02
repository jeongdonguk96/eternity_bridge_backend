package com.example.eternity_bridge_backend.image.service;

import com.example.eternity_bridge_backend.exception.code.ImageErrorCode;
import com.example.eternity_bridge_backend.exception.exception.CommonException;
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
    public String createImage(MultipartFile file, ImageDomain domain, Long memberId) throws IOException {
        String trxKey = MDC.get("trxKey");

        String fileName = s3Service.upload(file, domain, memberId, trxKey);
        Image image = Image.from(domain, S3_URL + fileName, 1, memberId);
        imageRepository.save(image);
        log.info("[{}] 이미지 저장 성공", trxKey);

        return image.getUrl();
    }


    // 단일 파일을 재동륵한다.
    @Transactional
    public String reCreateImage(MultipartFile file, ImageDomain domain, String oldUrl, Long memberId) throws IOException {
        String trxKey = MDC.get("trxKey");

        // 1. 새로운 이미지를 S3에 업로드한다.
        String fileName = s3Service.upload(file, domain, memberId, trxKey);

        // 2. 기존 이미지를 S3에서 삭제한다.
        String oldFileName = oldUrl.replaceFirst("https://[^/]+/", "");
        s3Service.delete(oldFileName);
        log.info("[{}] 기존 S3 파일 삭제 성공", trxKey);

        // 3. 기존 image 엔티티를 수정한다.
        Image image = imageRepository.getImage(oldUrl, memberId).orElseThrow(
                () -> new CommonException(ImageErrorCode.IMAGE_NOT_FOUND)
        );
        image.modifyUrl(S3_URL + fileName);
        log.info("[{}] 이미지 변경 성공", trxKey);

        return image.getUrl();
    }


    // 단일 파일을 변경한다.
    @Transactional
    public String modifyImage(MultipartFile file, Long id, Long memberId) throws IOException {
        String trxKey = MDC.get("trxKey");

        // 1. 기존 image 엔티티를 조회한다.
        Image image = imageRepository.findById(id).orElseThrow(
                () -> new CommonException(ImageErrorCode.IMAGE_NOT_FOUND)
        );

        // 2. 새로운 이미지를 S3에 업로드한다.
        String fileName = s3Service.upload(file, image.getImageDomain(), memberId, trxKey);

        // 3. 기존 이미지를 S3에서 삭제한다.
        String oldFileName = image.getUrl().replaceFirst("https://[^/]+/", "");
        s3Service.delete(oldFileName);
        log.info("[{}] 기존 S3 파일 삭제 성공", trxKey);

        // 4. 기존 image 엔티티를 수정한다.
        image.modifyUrl(S3_URL + fileName);
        log.info("[{}] 이미지 변경 성공", trxKey);

        return image.getUrl();
    }


    // 이미지의 domainId를 변경한다.
    @Transactional
    public void modifyDomainId(String url, Long memberId, Long domainId) {
        Image image = imageRepository.getImage(url, memberId).orElseThrow(
                () -> new CommonException(ImageErrorCode.IMAGE_NOT_FOUND)
        );
        image.modifyDomainId(domainId);
    }

}
