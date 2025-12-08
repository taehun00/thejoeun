package com.pawject.service.user;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
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
	public UserAuthDto  readAuth1(int userId);
	
	 // 마이페이지 조회
    public UserDto myPage(String email);

    // 아이디 찾기
    public String findId(String nickname);

    // 비밀번호 찾기
    public String findPassword(String nickname, String email);

    // 회원 정보 수정
    public int update(MultipartFile file, UserDto dto);

    // 검색(이메일, 닉네임)
    public List<UserDto> searchUsers(String keyword, String type);

    
    // 전체 유저 목록 조회 (관리자용, 페이징)
    public List<UserDto> listUsers(int pstartno);
    public int selectTotalCnt();
    
    // 특정 유저 조회
    public UserDto selectUser(int userId);

    // 회원 정보 수정(관리자가 닉네임만)
    public int updateNickname(UserDto dto);

    // 회원탈퇴 (사용자)
    public int deleteMember(int userId);

    // 회원탈퇴 (관리자)
    //public int deleteAdmin(String email);
    public int deleteUser(String email);


    


}
