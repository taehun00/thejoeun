package com.pawject.service.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.PetDao;
import com.pawject.dao.UserDao;
import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;
import com.pawject.util.UtilUpload;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

	private static final Logger log = LoggerFactory.getLogger(UserSecurityServiceImpl.class);


	
    @Autowired private UserDao userDao;
    @Autowired private PetDao petDao;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UtilUpload utilUpload;

    /* 파일 업로드 공통 처리 */
    public String uploadFile(MultipartFile file, String existingFile) {
        if(file != null && !file.isEmpty()) {
            try {
                return utilUpload.fileUpload(file);
            } catch (IOException e) {
                throw new RuntimeException("파일 업로드 실패", e);
            }
        }
        return existingFile;
    }

    /* 회원가입 */
    @Transactional
    @Override
    public int insert(MultipartFile file, UserDto dto) {
        // 1. 요청 들어온 원본 값 확인
        log.debug("회원가입 요청 시작 email={}, provider={}, rawPassword={}", 
                  dto.getEmail(), dto.getProvider(), dto.getPassword());

        // 2. provider 강제 세팅
        dto.setProvider("local");

        // 3. 비밀번호 암호화
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        // 4. 파일 업로드 처리
        dto.setUfile(uploadFile(file, null));

        // 5. 중복검사 실행 전 값 확인
        log.debug("중복검사 실행 email={}, provider={}", dto.getEmail(), dto.getProvider());
        int duplicateCnt = userDao.iddoubleByEmail(dto);
        log.debug("중복검사 결과 cnt={}", duplicateCnt);

        if (duplicateCnt > 0) {
            throw new IllegalStateException("이미 존재하는 계정입니다.");
        }

        // 6. 회원가입 실행
        int result = userDao.join(dto);
        log.debug("회원가입 DB insert 결과 result={}", result);

        // 7. 권한 부여
        if (result > 0) {
            AuthDto auth = new AuthDto();
            auth.setUserId(dto.getUserId());   // 시퀀스로 채워진 PK
            auth.setEmail(dto.getEmail());
            auth.setAuth("ROLE_MEMBER");

            userDao.joinRole(auth);
            log.debug("권한 부여 완료 email={}, userId={}", dto.getEmail(), dto.getUserId());
        }


        // 8. 최종 반환
        log.debug("회원가입 처리 완료 email={}, provider={}", dto.getEmail(), dto.getProvider());
        return result;
    }

    /* 회원정보 수정 */
    @Transactional
    @Override
    public int update(MultipartFile file, UserDto dto) {
        dto.setProvider("local");
        UserDto dbUser = userDao.findByEmail(dto);
        if(dbUser == null) return 0;

        // 비밀번호 확인
        if(dto.getPassword() != null && !passwordEncoder.matches(dto.getPassword(), dbUser.getPassword())) {
            return 0;
        }

        dto.setUserId(dbUser.getUserId());
        dto.setUfile(uploadFile(file, dbUser.getUfile()));
        dto.setNickname(dto.getNickname() == null ? dbUser.getNickname() : dto.getNickname());
        dto.setMobile(dto.getMobile() == null ? dbUser.getMobile() : dto.getMobile());
        dto.setPassword(dbUser.getPassword());

        return userDao.update(dto);
    }

    /* 회원탈퇴 */
    @Transactional
    @Override
    public int delete(UserDto dto, boolean requirePasswordCheck) {
    	// provider가 null이면 기본값을 세팅
        if (dto.getProvider() == null) {
            dto.setProvider("local"); // 로컬 기본
        }

    	
    	UserDto dbUser = userDao.findByEmail(dto);
        if (dbUser == null) return 0;

        // provider에 따라 비밀번호 체크 여부 결정
        if (requirePasswordCheck && "local".equals(dbUser.getProvider())) {
            if (dto.getPassword() == null || !passwordEncoder.matches(dto.getPassword(), dbUser.getPassword())) {
                return 0;
            }
        }

        dto.setUserId(dbUser.getUserId());
        petDao.deletePetsByUser(dto.getUserId());

        userDao.deleteRolesByUserId(dto.getUserId());
        return userDao.deleteMember(dto);
    }


    /* 권한 추가 */
    @Override
    public int joinAuth(AuthDto dto) {
        return userDao.joinRole(dto);
    }

    /* 권한 삭제 */
    @Override
    public int deleteRolesByUserId(AuthDto dto) {
        return userDao.deleteRolesByUserId(dto.getUserId());
    }


    /* 로그인 권한 조회 */
    @Override
    public UserAuthDto readAuth(String email, String provider) {
        return userDao.readAuthByEmail(new UserDto(email, provider));
    }

    /* 이메일로 유저 조회 */
    @Override
    public UserDto selectEmail(String email, String provider) {
        return userDao.findByEmail(new UserDto(email, provider));
    }

    /* 이메일 중복검사 */
    @Override
    public int iddouble(String email, String provider) {
        return userDao.iddoubleByEmail(new UserDto(email, provider));
    }

    /* 비밀번호 확인 */
    @Override
    public boolean matchesPassword(String email, String provider, String rawPassword) {
        UserDto dbUser = userDao.findByEmail(new UserDto(email, provider));
        return dbUser != null && dbUser.getPassword() != null &&
               passwordEncoder.matches(rawPassword, dbUser.getPassword());
    }

    /* 관리자용 유저 조회 */
    @Override
	public List<UserDto> listUsers(int pstartno) {
		HashMap<String, Object> para = new HashMap();
		int start = (pstartno - 1 ) * 10 + 1;
		para.put("start", start);
		para.put("end", start + 10 - 1);
		
		return userDao.listUsers(para);
	}

    @Override
    public int selectTotalCnt() {
        return userDao.selectTotalCnt();
    }

    @Override
    public UserDto selectUser(int userId) {
        return userDao.selectUser(userId);
    }

    @Override
	public List<UserDto> searchUsers(String keyword, String type) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("keyword", keyword);
	    params.put("type", type);
	    return userDao.searchUsers(params);
	}

	
	
	@Override
	public UserDto myPage(String email) {
		UserDto dto = new UserDto();
        dto.setEmail(email);
        return userDao.myPage(dto);
	}
}