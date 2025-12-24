package com.pawject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.AuthDto;
import com.pawject.dto.UserAuthDto;
import com.pawject.dto.UserDto;

@Mapper
public interface UserDao {
<<<<<<< HEAD
	/* 회원관리 */
	public int insertUser(UserDto dto);
	public UserAuthDto readAuthByEmail(UserDto dto);
	public UserDto findByEmail(UserDto dto);
	public int iddoubleByEmail(UserDto dto);
	public int updateUser(UserDto dto);
	public int deleteUser(UserDto dto);
	
	/* 권한관리 */
	public int insertAuth(AuthDto dto);
	public int deleteAuth(AuthDto dto);
}
=======

    // 회원가입
    int join(UserDto dto);

    // 로그인 (이메일로 이메일, 비밀번호, 권한)
    UserAuthDto readAuthByEmail(UserDto dto);

    // 이메일로 유저정보 찾기
    UserDto findByEmail(UserDto dto);

    // 이메일 중복검사
    int iddoubleByEmail(UserDto dto);

    // 정보수정 (사용자)
    int update(UserDto dto);

    // 회원탈퇴 (사용자)
    int deleteMember(UserDto dto);

    // 권한 부여
    int joinRole(AuthDto dto);

    // 권한 삭제
    int deleteRolesByUserId(int userId);


    // 마이페이지
    UserDto myPage(UserDto dto);
    // 아이디 찾기
    String findId(UserDto dto);

    // 비밀번호 찾기
    String findPassword(UserDto dto);

    // 정보수정 (관리자) 닉네임만 수정
    int updateNickname(UserDto dto);

    // 회원탈퇴 (관리자) - 이메일로 유저 조회
    UserDto findUserByEmail(String email);

    // 전체 유저 조회 (관리자) + 페이징
    List<UserDto> listUsers(UserDto dto);

    // 전체 유저 수
    int selectTotalCnt();

    // 특정 유저 조회 (관리자용)
    UserDto selectUser(int userId);

    // 검색
    List<UserDto> searchUsers(Map<String, Object> params);

    // 로그인: 이메일 기준 사용자 + 권한
    UserAuthDto readAuth(UserDto dto);
}
>>>>>>> 3271200de55a609a2cf6f402aff476a5d66f00c8
