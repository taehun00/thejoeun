package com.pawject.service.exec;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawject.dao.exec.ExecInfoDao;
import com.pawject.dto.exec.ExecInfoDto;

@Service
public class ExecInfoServiceImpl implements ExecInfoService {

	@Autowired ExecInfoDao idao;

	//운동정보(운동챌린지 게시판을 작성하기 위한 참고용 / insert, select, selectAll만 실행가능하게하기) 
	@Override public int insert2(ExecInfoDto dto) {return idao.insert2(dto);}
	@Override public List<ExecInfoDto> selectAll2() {return idao.selectAll2() ;}
	@Override public ExecInfoDto select2(int execId) {return idao.select2(execId);}
	@Override public int update2(ExecInfoDto dto) {return idao.update2(dto);}
	@Override public int delete2(ExecInfoDto dto) {return idao.delete2(dto);}
	@Override public ExecInfoDto selectUpdateForm(int execid) {return idao.select2(execid);}


	//Search - ajax
	@Override public List<ExecInfoDto> selectSearch2(String keyword) {
		HashMap<String, String> para = new HashMap<>();
		para.put("search", "%" +  keyword + "%"); // keyword가 포함되어 있는
		return idao.selectSearch2(para);
	}

	//Paging
	@Override public List<ExecInfoDto> select10(int pstartno) { //1(1,10), 2(11,20), 3(21,30)
		HashMap<String, Object> para = new HashMap();
		int start = (pstartno-1)*10 + 1;
		para.put("start", start); // 1 11 21
		para.put("end", start + 10 - 1);
		return idao.select10(para);
	}
	@Override public int selectTotalCnt() {return idao.selectTotalCnt();}

	//iddouble
//	@Override public int iddouble(int execid) {return 0;}

	
	//MEMBER
//	@Override public int updateMember(ExecInfoDto dto) {return 0;}
//	@Override public int deleteMember(ExecInfoDto dto) {return 0;}


}
