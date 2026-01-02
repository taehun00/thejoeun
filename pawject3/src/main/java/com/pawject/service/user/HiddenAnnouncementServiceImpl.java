package com.pawject.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.dao.user.HiddenAnnouncementDao;

@Service
public class HiddenAnnouncementServiceImpl implements HiddenAnnouncementService {

    private final HiddenAnnouncementDao hiddenDao;

    public HiddenAnnouncementServiceImpl(HiddenAnnouncementDao hiddenDao) {
        this.hiddenDao = hiddenDao;
    }

    @Transactional
    @Override
    public void hideAnnouncement(int userId, int announcementId) {
        
    	hiddenDao.insertHidden(userId, announcementId);
    	
//    	try {
//    		hiddenDao.insertHidden(userId, announcementId);
//    	} catch (DuplicateKeyException e) {
//    		
//    	}
    }

    @Override
    public List<Integer> getHiddenAnnouncements(int userId) {
        return hiddenDao.selectByUserId(userId);
    }

    @Override
    public void unhideAnnouncement(int userId, int announcementId) {
        hiddenDao.deleteHidden(userId, announcementId);
    }
}

