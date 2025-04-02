package com.example.eternity_bridge_backend.memorial_hall.service;

import com.example.eternity_bridge_backend.common.dto.SliceResult;
import com.example.eternity_bridge_backend.exception.exception.CommonException;
import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallResponse;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import com.example.eternity_bridge_backend.memorial_hall.enums.CustomSort;
import com.example.eternity_bridge_backend.memorial_hall.repository.MemorialHallRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import static com.example.eternity_bridge_backend.exception.code.MemorialHallErrorCode.DUPLICATED_MEMORIAL_HALL;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemorialHallService {

    private final MemorialHallRepository memorialHallRepository;


    // 추모관을 생성한다.
    public void createMemorialHall(CreateMemorialHallRequest request, Long memberId) {
        String trxKey = MDC.get("trxKey");

        checkDuplicatedMemorialHall(request, memberId, trxKey);
        log.info("[{}] 추모관 생성 전 중복체크 결과 => 중복없음", trxKey);
        
        memorialHallRepository.save(request.from(memberId));
        log.info("[{}] 추모관 생성 성공", trxKey);
    }


    // 추모관 목록을 슬라이스 형식으로 조회한다.
    public SliceResult<GetMemorialHallsResponse> getMemorialHallsBySlice(Long cursorValue, CustomSort sort, Pageable pageable) {
        String trxKey = MDC.get("trxKey");

        Slice<GetMemorialHallsResponse> memorialHallList = memorialHallRepository.findAllByCursorValue(cursorValue, sort, pageable);
        Boolean hasNext = memorialHallList.isEmpty()
                ? null
                : memorialHallList.hasNext();

        log.info("[{}] 조회된 추모관 목록 {}개", trxKey, memorialHallList.getSize());
        log.info("[{}] hasNext 여부 = {}", trxKey, hasNext);

        return new SliceResult<>(memorialHallList, hasNext);
    }


    // 추모관을 조회한다.
    public GetMemorialHallResponse getMemorialHall(Long id) {
        return memorialHallRepository.getMemorialHall(id);
    }


    // 추모관 생성 시 중복체크를 진행한다.
    private void checkDuplicatedMemorialHall(CreateMemorialHallRequest request, Long memberId, String trxKey) {
        if (memorialHallRepository.checkDuplicatedMemorialHall(request, memberId)) {
            log.info("[{}] 추모관 생성 전 중복체크 결과 => 중복있음", trxKey);
            throw new CommonException(DUPLICATED_MEMORIAL_HALL);
        }
    }

}
