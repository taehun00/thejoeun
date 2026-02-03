package com.pawject.service.exec;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.pawject.dto.exec.ExecsmartDto;


public interface ExecsmartService {
	public int insertsmart( MultipartFile file , ExecsmartDto sdto);
	public int updatesmart( MultipartFile file , ExecsmartDto sdto);
	public int deletesmart(  int postid );
	public List<ExecsmartDto> selectAllsmart();
	public ExecsmartDto       selectsmart(int postid);
	public ExecsmartDto       selectsmartUpdateForm(int postid);  //수정하기폼
	
	/* PAGING */
	public List<ExecsmartDto> selectsmart10(int pageNo);
	public int		    selectsmartTotalCnt();
	
	/* SEARCH + PAGING */
	public List<ExecsmartDto> selectsmart3( String keyword, int pageNo ); //##
	public int				  selectsmartSearchTotalCnt(String keyword); //##

}
