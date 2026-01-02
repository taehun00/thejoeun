package com.pawject.service.user;

import java.util.List;

public interface HiddenAnnouncementService {
	void hideAnnouncement(int userId, int announcementId);
    List<Integer> getHiddenAnnouncements(int userId);
    void unhideAnnouncement(int userId, int announcementId);

}
