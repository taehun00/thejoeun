package com.pawject.service.user;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.user.UserMapper;
import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;

@Service
public class UserSecurityServiceImpl implements UserSecurityService{
	@Autowired  UserMapper  dao;  
	@Autowired  PasswordEncoder  pwencoder;
	
	@Override public int join(MultipartFile file, UserDto dto) { //##
	   //0.권한
		AuthDto adto = new AuthDto();
		adto.setEmail(dto.getEmail()); adto.setAuth("ROLE_MEMBER");
		dao.joinAuth(adto);
	   //1.파일올리기
	   String fileName   = null;
	   if(  !file.isEmpty() ) {  // 파일이 비어있는게 아니라면
		   fileName   = file.getOriginalFilename(); // 원본파일이름
		   String uploadPath = "C:/file/";
		   File   img        = new File(uploadPath + fileName);  //java.io.File
		   try { file.transferTo(img); }//파일올리기 
		   catch (IOException e) { e.printStackTrace(); }
		   
	   }else { fileName = "user" + ((int)((Math.random()*7)+1)) + ".png"; }

	   dto.setUfile(fileName); 
	   //2. 암호화 ###
	   dto.setPassword(  pwencoder.encode(  dto.getPassword() )  );
	   return dao.join(dto); 
	}

	@Override
	public int joinAuth(AuthDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public UserAuthDto readAuth(String email) {
        UserDto dto = new UserDto();
        dto.setEmail(email);
        return dao.readAuth(dto);
    }

	@Override
	public UserDto myPage(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findId(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPassword(String nickname, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(UserDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAdmin(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserDto> listUsers(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
