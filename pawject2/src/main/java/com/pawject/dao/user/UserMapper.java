package com.pawject.dao.user;

import com.pawject.dao.MyDao;
import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;

@MyDao
public interface UserMapper {
	public int join(UserDto dto);
	public int joinAuth(AuthDto dto);
	public UserAuthDto readAuth(UserDto dto );  //email, password, 권한들
	// 마이페이지
	public UserDto myPage(UserDto dto);
    // 아이디 찾기
	public String findId(UserDto dto);
    // 비밀번호 찾기
	public String findPassword(UserDto dto);
    // 정보 수정
	public int update(UserDto dto);
    // 회원탈퇴 (사용자, 관리자)
	public int deleteMember(UserDto dto);
	public int deleteAdmin(UserDto dto);
}
