package com.pawject.service.exec;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.exec.ExecBoardDao;
import com.pawject.dto.exec.ExecBoardDto;

@Service
public class ExecBoardServiceImpl implements ExecBoardService {

	@Autowired ExecBoardDao dao;
	
	/*운동챌린지게시판 - CRUD*/
	@Override public int insert1(ExecBoardDto dto) { return dao.insert1(dto); }
	@Override public int update1(ExecBoardDto dto) { return dao.update1(dto);}
	@Override public int delete1(ExecBoardDto dto) {return dao.delete1(dto);}
	@Override public List<ExecBoardDto> selectAll1() {return dao.selectAll1();}
	@Override public ExecBoardDto select1(int postId) { dao.updateHit(postId); return dao.select1(postId);}
	
	@Override public ExecBoardDto selectUpdateForm(int postid) {return dao.select1(postid);}

	/*Upload*/
	@Override public int insert2( MultipartFile file,ExecBoardDto dto) {
		if(!file.isEmpty() ) { //팔일이 비었는게 아니라면.
			String fileName = file.getOriginalFilename(); //원본파일이름
			String uploadPath = "C:/file/";
			File    img       = new File(uploadPath + fileName); //java.io.File
			try {
				file.transferTo(img);
				dto.setEimg(fileName);
			}  catch (IOException e) { e.printStackTrace(); }
		}else { dto.setEimg("no.png"); }

		
		return dao.insert2(dto);
	}
	
	@Override public int update2( MultipartFile file,ExecBoardDto dto) {
		if( !file.isEmpty() ) { //파일이 비어있는게 아니라면,
			String fileName = file.getOriginalFilename(); //원본파일 이름
			String uploadPath = "C:/file/";
			File   img        = new File(uploadPath + fileName); //java.io.File
			
			try {
				file.transferTo(img);  //파일올리기
				dto.setEimg(fileName);
			} catch (IOException e) { e.printStackTrace(); }
		}
		return dao.update2(dto);
	}

	/* Search - Ajax */
	@Override public List<ExecBoardDto> selectSearch1(String keyword) {
		HashMap<String, String> para = new HashMap<>();
		para.put("search", "%" +  keyword + "%");
		return dao.selectSearch1(para);
	}
	
	//Paging
	@Override public List<ExecBoardDto> select10(int pstartno) {
		HashMap<String, Object> para = new HashMap();
		int start = (pstartno-1)*10 +1;
		para.put("start", start);
		para.put("end", start + 10 - 1);
		return dao.select10(para);
	}
	@Override public int selectTotalCnt() {return dao.selectTotalCnt();}

	//iddouble
//	@Override public int iddouble1(int postid) {return 0;}

	// ADMIN / MEMBER
//	@Override public int updateAdmin1(ExecBoardDto dto) { return 0; }
//	@Override public int deleteAdmin1(ExecBoardDto dto) {return 0;}
//	@Override public int updateMember1(ExecBoardDto dto) { return 0; }
//	@Override public int deleteMember1(ExecBoardDto dto) { return 0; }
	

}
