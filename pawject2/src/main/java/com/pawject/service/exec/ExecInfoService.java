package com.pawject.service.exec;

import java.util.HashMap;
import java.util.List;

import com.pawject.dto.exec.ExecBoardDto;
import com.pawject.dto.exec.ExecInfoDto;

public interface ExecInfoService {	
	//운동정보(운동챌린지게시판 작성시 참고용) (MEMBER)
	public int insert2(ExecInfoDto dto);
	public int update2(ExecInfoDto dto);
	public int delete2(ExecInfoDto dto);
	public List<ExecInfoDto> selectAll2();
	public ExecInfoDto select2(int execId);
	public ExecInfoDto       selectUpdateForm(int execid);

	
	/*Search - Ajax*/
	public List<ExecInfoDto> selectSearch2(String keyword);
	
	/*paging*/
	public List<ExecInfoDto> select10(int pstartno); //1(1,10), 2(11,20), 3(21,30)
	public 		int   selectTotalCnt();

	
	/* idouble */
//	public int iddouble(int execid);
	
	/* admin  - 전체유저정보 selectAll 
	           / 게시글아이디(postid) 주면 해당게시글 찾기
	           / 수정하기 updateMember
	           / 삭제하기 deleteMember*/
	
//	public int updateMember(ExecInfoDto dto);
//	public int deleteMember(ExecInfoDto dto);
	
	/* security */
//	public int insertAuth(ExecAuthDto dto);
//	public ExecBoardAuthDto readAuth(ExecBoardAuthDto dto);

	
}
