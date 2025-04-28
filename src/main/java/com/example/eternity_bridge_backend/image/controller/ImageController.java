package com.example.eternity_bridge_backend.image.controller;

import com.example.eternity_bridge_backend.common.dto.CommonResult;
import com.example.eternity_bridge_backend.common.dto.SingleResult;
import com.example.eternity_bridge_backend.common.service.ResponseService;
import com.example.eternity_bridge_backend.image.enums.ImageDomain;
import com.example.eternity_bridge_backend.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final ResponseService responseService;


    // S3에 이미지 파일을 업로드하고 DB에 이미지 데이터를 생성한다.
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SingleResult<String> createImage(
            @RequestPart(name = "file") MultipartFile file,
            @RequestParam(name = "domain") ImageDomain domain
    ) throws IOException {
        Long memberId = 1L;
        return responseService.getSingleResult(imageService.createImage(file, domain, memberId));
    }


    // S3에 이미지 파일을 업데이트하고 DB에 이미지 데이터를 변경한다. (재등록)
    @PostMapping(value = "/retry", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SingleResult<String> reCreateImage(
            @RequestPart(name = "file") MultipartFile file,
            @RequestParam(name = "domain") ImageDomain domain,
            @RequestParam(name = "oldUrl") String oldUrl
    ) throws IOException {
        Long memberId = 1L;
        return responseService.getSingleResult(imageService.reCreateImage(file, domain, oldUrl, memberId));
    }


    // S3에 이미지 파일을 업로드하고 DB에 이미지 데이터를 생성한다. (변경)
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SingleResult<String> modifyImage(
            @RequestPart(name = "file") MultipartFile file,
            @PathVariable(name = "id") Long id
    ) throws IOException {
        Long memberId = 1L;
        return responseService.getSingleResult(imageService.modifyImage(file, id, memberId));
    }

}
