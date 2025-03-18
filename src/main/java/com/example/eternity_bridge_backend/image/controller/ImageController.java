package com.example.eternity_bridge_backend.image.controller;

import com.example.eternity_bridge_backend.image.enums.ImageDomain;
import com.example.eternity_bridge_backend.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    // S3에 이미지 파일을 업로드하고 DB에 이미지 데이터를 생성한다.
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createImage(
            @RequestPart(name = "file") MultipartFile file,
            @RequestParam(name = "domain") ImageDomain domain
    ) throws IOException {
        Long memberId = 1L;
        return imageService.createImage(file, domain, memberId);
    }

}
