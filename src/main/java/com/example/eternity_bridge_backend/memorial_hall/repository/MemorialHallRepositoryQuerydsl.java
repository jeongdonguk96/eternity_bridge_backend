package com.example.eternity_bridge_backend.memorial_hall.repository;

import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MemorialHallRepositoryQuerydsl {
    Slice<GetMemorialHallsResponse> findAllByCursorId(Long cursorId, String sort, Pageable pageable);
    boolean checkDuplicatedMemorialHall(CreateMemorialHallRequest request, Long memberId);
}
