package com.pawject.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HiddenAnnouncementDao {
	void insertHidden(@Param("userId") int userId, @Param("announcementId") int announcementId);
    List<Integer> selectByUserId(@Param("userId") int userId);
    void deleteHidden(@Param("userId") int userId, @Param("announcementId") int announcementId);

}
