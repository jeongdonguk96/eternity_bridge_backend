package com.example.eternity_bridge_backend.memorial.controller;

import com.example.eternity_bridge_backend.common.service.ResponseService;
import com.example.eternity_bridge_backend.memorial.service.MemorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memorial")
@RequiredArgsConstructor
public class MemorialController {

    private final MemorialService memorialService;
    private final ResponseService responseService;



}
