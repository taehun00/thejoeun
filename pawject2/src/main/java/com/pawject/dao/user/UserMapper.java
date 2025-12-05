package com.pawject.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pawject.dao.MyDao;
import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;

@MyDao
public interface UserMapper {
	public int join(UserDto dto);
	public int countByMobile(String mobile);

	public int joinAuth(AuthDto dto);
	public UserAuthDto readAuth(UserDto dto );
	public UserAuthDto readAuth1(UserDto dto );
	// 마이페이지
	public UserDto myPage(UserDto dto);
    // 아이디 찾기
	public String findId(UserDto dto);
    // 비밀번호 찾기
	public String findPassword(UserDto dto);
    // 정보 수정
	public int update(UserDto dto);
	

	// 정보 수정(권한테이블에도 이메일 동시 수정)
	// HashMap을 이용해 oldEmail, newEmail 전달
    int updateAuthEmail(Map<String, Object> params);

    // 회원탈퇴 (사용자, 관리자)
	public int deleteMember(UserDto dto);
	// 이메일로 유저 조회 (userId 가져오기)
    public UserDto findUserByEmail(String email);
	public int deleteAuthoritiesByUserId(int userId);
	
	
	//UserAdminController 사용
	public List<UserDto> listUsers(@Param("start") int start, @Param("end") int end);
	public UserDto selectUser(@Param("userId") int userId);
    // 정보 수정(관리자가 닉네임만 수정)
 	public int updateNickname(UserDto dto);
 	//
 	List<UserDto> searchUsers(@Param("keyword") String keyword);



}
