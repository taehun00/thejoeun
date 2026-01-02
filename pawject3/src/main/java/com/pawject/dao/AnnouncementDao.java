package com.pawject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pawject.dto.user.AnnouncementDto;

@Mapper
public interface AnnouncementDao {
	void insertAnnouncement(AnnouncementDto announcement);
	List<AnnouncementDto> selectVisibleAnnouncementsByUserId(@Param("userId") int userId);

	AnnouncementDto selectById(@Param("id") int id);
	
    List<AnnouncementDto> selectAllAnnouncements();

    void updateAnnouncement(@Param("id") int id,
            @Param("message") String message);
    
    void deleteAnnouncementById(@Param("id") int id);
}
