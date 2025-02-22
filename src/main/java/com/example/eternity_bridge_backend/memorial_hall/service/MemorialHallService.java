package com.example.eternity_bridge_backend.memorial_hall.service;

import com.example.eternity_bridge_backend.common.dto.CursorResult;
import com.example.eternity_bridge_backend.common.enums.CustomSort;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import com.example.eternity_bridge_backend.memorial_hall.repository.MemorialHallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemorialHallService {

    private final MemorialHallRepository memorialHallRepository;


    public CursorResult<GetMemorialHallsResponse> getMemorialHalls(Long cursorId, CustomSort sort, Pageable pageable) {
        Slice<GetMemorialHallsResponse> memorialHallList = memorialHallRepository.findAllByCursorId(cursorId, sort, pageable);
        Boolean hasNext = memorialHallList.isEmpty()
                ? null
                : memorialHallList.hasNext();

        return new CursorResult<>(memorialHallList, hasNext);
    }

}
