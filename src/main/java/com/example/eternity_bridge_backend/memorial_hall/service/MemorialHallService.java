package com.example.eternity_bridge_backend.memorial_hall.service;

import com.example.eternity_bridge_backend.common.dto.CursorResult;
import com.example.eternity_bridge_backend.common.enums.CustomSort;
import com.example.eternity_bridge_backend.exception.exception.CommonException;
import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import com.example.eternity_bridge_backend.memorial_hall.repository.MemorialHallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.eternity_bridge_backend.exception.code.MemorialHallErrorCode.DUPLICATED_MEMORIAL_HALL;


@Service
@RequiredArgsConstructor
public class MemorialHallService {

    private final MemorialHallRepository memorialHallRepository;


    // 추모관을 등록한다.
    @Transactional
    public void createMemorialHall(CreateMemorialHallRequest request, Long memberId) {
        checkDuplicatedMemorialHall(request, memberId);
        memorialHallRepository.save(request.from(memberId));
    }


    // 추모관을 슬라이스 형식으로 조회한다.
    public CursorResult<GetMemorialHallsResponse> getMemorialHalls(Long cursorId, CustomSort sort, Pageable pageable) {
        Slice<GetMemorialHallsResponse> memorialHallList = memorialHallRepository.findAllByCursorId(cursorId, sort, pageable);
        Boolean hasNext = memorialHallList.isEmpty()
                ? null
                : memorialHallList.hasNext();

        return new CursorResult<>(memorialHallList, hasNext);
    }


    // 추모관 등록 시 중복체크를 진행한다.
    private void checkDuplicatedMemorialHall(CreateMemorialHallRequest request, Long memberId) {
        if (memorialHallRepository.checkDuplicatedMemorialHall(request, memberId)) {
            throw new CommonException(DUPLICATED_MEMORIAL_HALL);
        }
    }

}
