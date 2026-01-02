package com.pawject.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawject.dao.AnnouncementDao;
import com.pawject.dto.user.AnnouncementDto;


@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
    private AnnouncementDao announcementDao;

	
	@Override
	public void saveAnnouncement(String message) {
		AnnouncementDto dto = new AnnouncementDto();
        dto.setMessage(message);
        announcementDao.insertAnnouncement(dto);
		
	}

	@Override
	public List<AnnouncementDto> getVisibleAnnouncementsByUserId(int userId) {
        return announcementDao.selectVisibleAnnouncementsByUserId(userId);
    }
	
	@Override
    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementDao.selectAllAnnouncements();
    }

    @Override
    public void deleteAnnouncement(int announcementId) {
        announcementDao.deleteAnnouncementById(announcementId);
    }

	@Override
	public AnnouncementDto getAnnouncementById(int id) {
		return announcementDao.selectById(id);
	}

	@Override
	public void updateAnnouncement(int id, String message) {
		announcementDao.updateAnnouncement(id, message);
		
	}

}
