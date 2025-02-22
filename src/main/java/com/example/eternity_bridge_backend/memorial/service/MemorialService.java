package com.example.eternity_bridge_backend.memorial.service;

import com.example.eternity_bridge_backend.memorial.repository.MemorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemorialService {

    private final MemorialRepository memorialRepository;


}
