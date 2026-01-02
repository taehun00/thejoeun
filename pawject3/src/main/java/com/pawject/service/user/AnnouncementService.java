package com.pawject.service.user;

import java.util.List;

import com.pawject.dto.user.AnnouncementDto;

public interface AnnouncementService {
	void saveAnnouncement(String message); // 공지등록
	// 사용자 전용 조회
	public List<AnnouncementDto> getVisibleAnnouncementsByUserId(int userId);

	// 관리자 전용 수정 페이지
	AnnouncementDto getAnnouncementById(int id);
	
	List<AnnouncementDto> getAllAnnouncements();   // 관리자용

	void updateAnnouncement(int id, String message); // 관리자 수정
	
    void deleteAnnouncement(int id);   // 관리자 삭제

}
