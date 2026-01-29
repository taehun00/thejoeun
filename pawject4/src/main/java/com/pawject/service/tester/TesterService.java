package com.pawject.service.tester;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.tester.TesterAdminRequestDto;
import com.pawject.dto.tester.TesterAdminResponseDto;
import com.pawject.dto.tester.TesterUserRequestDto;
import com.pawject.dto.tester.TesterUserResponseDto;

public interface TesterService {
	//repository
	public TesterAdminResponseDto findById(Long testerid);
    public TesterAdminResponseDto adminWrite(Long userid, TesterAdminRequestDto dto, List<MultipartFile> files);
    public TesterUserResponseDto userWrite(Long userid, TesterUserRequestDto dto, List<MultipartFile> files);
	public TesterAdminResponseDto adminUpdate(Long userid, Long testerid, TesterAdminRequestDto dto,
			List<MultipartFile> files, List<Long> keepImgIds);
	public TesterUserResponseDto userUpdate(Long userid, Long testerid, TesterUserRequestDto dto,
			List<MultipartFile> files, List<Long> keepImgIds);
    public void delete(Long testerid, Long userid);   // 소프트삭제
	
	//mybatis
	public List<TesterAdminResponseDto> select20Tester(String condition, int pageNo);
	public int countByTesterPaging(String condition);

	public List<TesterAdminResponseDto> searchTester( String keyword, String searchType, String condition, int pageNo);
	public int searchTesterCnt(String keyword, String searchType, String condition);


	public int updateIsnotice(Long testerid);
	public int selectIsnotice(Long testerid);
	public int updateStatus(Long testerid);
	public int selectStatus(Long testerid);
	public int updateViews(Long testerid);
}
