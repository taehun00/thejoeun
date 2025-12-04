package com.pawject.service.user;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.user.UserMapper;
import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;

@Service
public class UserSecurityServiceImpl implements UserSecurityService{
	@Autowired  UserMapper  dao;  
	@Autowired  PasswordEncoder  pwencoder;
	
	@Transactional
	@Override public int join(MultipartFile file, UserDto dto) { //##
	   
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
	   
	   // 3. mobile 중복 체크
	    int count = dao.countByMobile(dto.getMobile());
	    if (count > 0) {
	        // 이미 존재하면 예외 발생시켜 컨트롤러에서 처리
	        throw new DuplicateKeyException("이미 등록된 휴대폰 번호입니다.");
	    }

	   // 4. users 테이블 insert
	    int result = dao.join(dto);

	    // 5. authorities 테이블 insert (users가 성공한 뒤)
	    if (result > 0) {
	        AuthDto adto = new AuthDto();
	        adto.setUserId(dto.getUserId());
	        adto.setAuth("ROLE_MEMBER");
	        dao.joinAuth(adto);
	    }


	   
	   return result; 
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
	public UserAuthDto readAuth1(int userId) {
		UserDto dto = new UserDto();
        dto.setUserId(userId);
		return dao.readAuth1(dto);
	}

	@Override
	public UserDto myPage(String email) {
		UserDto dto = new UserDto();
        dto.setEmail(email);
        return dao.myPage(dto);
	}

	@Override
	public String findId(String nickname) {
		UserDto dto = new UserDto();
        dto.setNickname(nickname);
        return dao.findId(dto);
	}

	@Override
	public String findPassword(String nickname, String email) {
		UserDto dto = new UserDto();
        dto.setNickname(nickname);
        dto.setEmail(email);
        return dao.findPassword(dto);

	}

	@Transactional
	@Override
	public int update(MultipartFile file, UserDto dto) {
		UserAuthDto dbUser = dao.readAuth1(dto);
		if(dbUser == null) {return 0;}
		
		// 비밀번호 암호화 처리
	    if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
	        dto.setPassword(pwencoder.encode(dto.getPassword()));
	    } else {
	        // 비밀번호를 수정하지 않은 경우 기존 비밀번호 유지
	        dto.setPassword(dbUser.getPassword());
	    }

	    
			//파일올리기
			String fileName   = null;
			if(  !file.isEmpty() ) {  // 파일이 비어있는게 아니라면
				fileName   = file.getOriginalFilename(); // 원본파일이름
				String uploadPath = "C:/file/";
			    File   img        = new File(uploadPath + fileName);  //java.io.File
				try { file.transferTo(img); }//파일올리기 
				catch (IOException e) { e.printStackTrace(); }
				   
			}else { fileName = "user" + ((int)((Math.random()*7)+1)) + ".png"; }
	
			dto.setUfile(fileName); 
			
			int result = dao.update(dto);
			
		    


			return result;

	}

	@Override
	public int deleteMember(int userId) {
		dao.deleteAuthoritiesByUserId(userId);

		UserDto dto = new UserDto();
        dto.setUserId(userId);
        return dao.deleteMember(dto);
	}

	@Override
	public int deleteAdmin(String email) {
		UserDto dto = new UserDto();
        dto.setEmail(email);

        UserAuthDto dbUser = dao.readAuth(dto);
        if (dbUser == null) {
            return 0; // 사용자 없음
        }
        dao.deleteAuthoritiesByUserId(dbUser.getUserId());

        
        return dao.deleteAdmin(dto);

	}

	@Override
	public List<UserDto> listUsers(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
