package com.example.eternity_bridge_backend.utils;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.eternity_bridge_backend.image.enums.ImageDomain;
import org.springframework.web.multipart.MultipartFile;

public class S3Utils {

    // 파일명을 재조합한다.
    public static String extractFilename(ImageDomain domain, Long memberId, MultipartFile file) {
        String fullCurrentMonth = DateUtils.getFullCurrentMonth();
        String originalFilename = file.getOriginalFilename();
        String randomNumber = CommonUtils.generateRandom4Digit();

        return String.format("%d/%s/%s/%s-%s", memberId, domain, fullCurrentMonth, originalFilename, randomNumber);
    }


    // Metadata 정보를 설정한다.
    public static ObjectMetadata setObjectMetadata(MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        return metadata;
    }

}
