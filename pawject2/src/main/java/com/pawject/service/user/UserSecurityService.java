package com.pawject.service.user;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;

public interface UserSecurityService {
	//회원가입
	public int join(MultipartFile file, UserDto dto);
	
	// 권한 부여
    int joinAuth(AuthDto dto);
    
    // 권한 조회
	public UserAuthDto  readAuth(String email);
	
	 // 마이페이지 조회
    public UserDto myPage(String email);

    // 아이디 찾기
    public String findId(String nickname);

    // 비밀번호 찾기
    public String findPassword(String nickname, String email);

    // 회원 정보 수정
    public int update(UserDto dto);

    // 회원탈퇴 (사용자)
    public int deleteMember(int userId);

    // 회원탈퇴 (관리자)
    public int deleteAdmin(String email);

    // 전체 유저 목록 조회 (관리자용, 페이징)
    public List<UserDto> listUsers(int start, int end);

	
	

}
