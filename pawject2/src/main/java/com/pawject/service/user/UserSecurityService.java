package com.pawject.service.user;

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;

public interface UserSecurityService {
	public int join(MultipartFile file, UserDto dto);
	public UserAuthDto  readAuth(String email);
	
	

}
