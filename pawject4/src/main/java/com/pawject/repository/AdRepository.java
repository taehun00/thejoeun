package com.pawject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pawject.domain.Ad;
import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    
    // 1. 활성화된 광고만 조회 (메인 페이지 등에서 사용)
    List<Ad> findAllByActiveTrue();

    // 2. 최신순으로 활성화된 광고 목록 조회 (페이징 처리)
    // Controller에서 사용하면 대량의 광고를 효율적으로 보여줄 수 있습니다.
    Page<Ad> findAllByActiveTrueOrderByCreatedAtDesc(Pageable pageable);

    // 3. 특정 사용자가 올린 광고 목록 조회
    List<Ad> findAllByUserUserId(Long userId);

    // 4. 제목에 특정 키워드가 포함된 광고 검색 (활성 상태인 것만)
    List<Ad> findByTitleContainingAndActiveTrue(String keyword);
}