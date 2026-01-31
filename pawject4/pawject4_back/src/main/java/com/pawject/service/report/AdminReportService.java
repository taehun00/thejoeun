package com.pawject.service.report;

import com.pawject.dao.review.ReviewDao;
import com.pawject.domain.*;
import com.pawject.dto.report.AdminReportResponseDto;
import com.pawject.repository.ReportActionRepository;
import com.pawject.repository.ReportRepository;
import com.pawject.repository.TesterRepository;

import org.springframework.transaction.annotation.Transactional; 
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminReportService {

    private final ReportRepository reportRepository;
    private final ReportActionRepository reportActionRepository;
    private final ReviewDao reviewDao;
    private final TesterRepository testerRepository;

    /** 전체 조회 */
    @Transactional(readOnly = true)
    public List<AdminReportResponseDto> getAllReports(int page, int size) {

        int start = page * size;
        int end = start + size;

        return reportRepository.findAllPaging(start, end)
                .stream()
                .map(AdminReportResponseDto::from)
                .toList();
    }


    /** 타입별 조회 */
    @Transactional(readOnly = true)
    public List<AdminReportResponseDto> getReportsByType(
            ReportTargetType type,
            int page,
            int size
    ) {
        int start = page * size;
        int end = start + size;

        return reportRepository.findByTargetTypePaging(
                        type.name(),   // 🔥 enum → String
                        start,
                        end
                )
                .stream()
                .map(AdminReportResponseDto::from)
                .toList();
    }


    /** 신고 처리 */
    public void handleReport(
            Long reportId,
            Long adminId,
            ReportStatus status,
            ReportActionType action,
            String note
    ) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("신고 없음"));

        // 실제 게시글 삭제
        if (action == ReportActionType.DELETE) {
            deleteTarget(report);
        } else if (action == ReportActionType.IGNORE) {
            // 아무 것도 하지 않고 신고 처리 상태만 기록
        }

        // 신고 처리 기록 저장
        ReportAction reportAction = ReportAction.builder()
                .report(report)
                .status(status)   // PENDING / RESOLVED / REJECTED
                .action(action)   // DELETE / IGNORE
                .adminId(adminId)
                .note(note)
                .build();

        reportActionRepository.save(reportAction);
    }

    /** 실제 게시글 삭제 */
    private void deleteTarget(Report report) {
        if (report.getTargetType() == ReportTargetType.REVIEW) {
            reviewDao.deleteReviewByAdmin(report.getTargetId());
        } else if (report.getTargetType() == ReportTargetType.TESTER) {
            testerRepository.deleteById(report.getTargetId());
        }
    }
}
