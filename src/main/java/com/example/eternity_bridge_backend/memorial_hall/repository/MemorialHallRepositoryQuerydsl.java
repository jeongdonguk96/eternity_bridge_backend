package com.example.eternity_bridge_backend.memorial_hall.repository;

import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallResponse;
import com.example.eternity_bridge_backend.memorial_hall.enums.CustomSort;
import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MemorialHallRepositoryQuerydsl {
    Slice<GetMemorialHallsResponse> findAllByCursorValue(Long cursorId, CustomSort sort, Pageable pageable);
    GetMemorialHallResponse getMemorialHall(Long id);
    void updateViewCount(Long id);
    boolean checkDuplicatedMemorialHall(CreateMemorialHallRequest request, Long memberId);
}
