package com.pawject.service.user;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.user.UserDto;
import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserAuthDto;

public interface UserSecurityService {

    /* 회원가입 */
    int insert(MultipartFile file, UserDto dto);

    /* 회원정보 수정 */
    int update(MultipartFile file, UserDto dto);

    /* 회원탈퇴 */
    int delete(UserDto dto, boolean requirePasswordCheck);

    /* 권한 추가/삭제 */
    int joinAuth(AuthDto dto);
    int deleteAuth(AuthDto dto);

    /* 로그인 권한 조회 */
    UserAuthDto readAuth(String email, String provider);

    /* 이메일로 유저 조회 */
    UserDto selectEmail(String email, String provider);

    /* 이메일 중복검사 */
    int iddouble(String email, String provider);

    /* 비밀번호 확인 */
    boolean matchesPassword(String email, String provider, String rawPassword);

    /* 관리자용 유저 조회 */
    List<UserDto> listUsers(UserDto dto);
    int selectTotalCnt();
    UserDto selectUser(int userId);

    /* 검색 */
    List<UserDto> searchUsers(String keyword, String type);
}