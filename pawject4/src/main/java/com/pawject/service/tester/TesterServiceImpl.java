package com.pawject.service.tester;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.tester.TesterDao;
import com.pawject.domain.Tester;
import com.pawject.domain.Testerimg;
import com.pawject.domain.User;
import com.pawject.dto.tester.TesterAdminRequestDto;
import com.pawject.dto.tester.TesterAdminResponseDto;
import com.pawject.dto.tester.TesterUserRequestDto;
import com.pawject.dto.tester.TesterUserResponseDto;
import com.pawject.repository.TesterRepository;
import com.pawject.repository.UserRepository;
import com.pawject.util.FileStorageService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class TesterServiceImpl implements TesterService {
	private final  TesterDao dao;
	private final  TesterRepository repo;  
	private final  UserRepository urepo;
	private final FileStorageService fileStorageService;

	//단건조회
	@Transactional(readOnly = true)
	@Override
	public TesterAdminResponseDto findById(Long testerid) {

	    Tester t = repo.findById(testerid)
	            .filter(x -> !x.isDeleted())   //delete도 같이 조회됨 제거 필요
	            .orElseThrow(() -> new IllegalArgumentException("글 없음"));

	    dao.updateViews(testerid); // 조회수 증가

	    return TesterAdminResponseDto.from(t);
	}

    //관리자 글쓰기
	@Override
	public TesterAdminResponseDto adminWrite(Long userid, TesterAdminRequestDto dto, List<MultipartFile> files) {

	    User user = urepo.findById(userid).orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

	    Tester tester = new Tester();
	    tester.setCategory(dto.getCategory());
	    tester.setTitle(dto.getTitle());
	    tester.setContent(dto.getContent());

	    // foodid: 0이면 미선택으로 보고 null 처리
	    if (dto.getFoodid() == 0) tester.setFoodid(null);
	    else tester.setFoodid(dto.getFoodid());

	    tester.setStatus(dto.getStatus());
	    tester.setIsnotice(dto.getIsnotice());

	    // posttype: null이면 관리자 공고(1)로 고정
	    tester.setPosttype(dto.getPosttype() == null ? 1 : dto.getPosttype());

	    tester.setUser(user);

	    // 이미지 업로드
	    if (files != null && !files.isEmpty()) {
	        files.forEach(file -> {
	            String url = fileStorageService.upload(file);
	            Testerimg img = new Testerimg();
	            img.setImgsrc(url);
	            img.setTester(tester);
	            tester.getTesterimg().add(img);
	        });
	    }

	    Tester saved = repo.save(tester);
	    return TesterAdminResponseDto.from(saved);
	}

		//유저 글쓰기
	@Override
	public TesterUserResponseDto userWrite(Long userid, TesterUserRequestDto dto, List<MultipartFile> files) {
		  //유저 확인
			User user = urepo.findById(userid).orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
		  //글 내용 채우기
		  Tester tester = new Tester();
		  tester.setCategory("후기");
		  tester.setTitle(dto.getTitle());
		  tester.setContent(dto.getContent());
		  tester.setUser(user);
		  //고정 - status 의미x
		  tester.setPosttype(0);
		  tester.setIsnotice(0);
		  tester.setStatus(0);
		  
		// 이미지 업로드
		  if (files != null && !files.isEmpty()) {
			    files.forEach(file -> {
			        String url = fileStorageService.upload(file);
			        Testerimg img = new Testerimg();
			        img.setImgsrc(url);
			        img.setTester(tester);
			        tester.getTesterimg().add(img);
			    });
			}
		  
		    Tester saved = repo.save(tester);
		    return TesterUserResponseDto.from(saved);
	}
	
	//관리자 수정
	@Override
	public TesterAdminResponseDto adminUpdate(Long userid, Long testerid, TesterAdminRequestDto dto, List<MultipartFile> files) {

	    Tester tester = repo.findById(testerid)
	            .filter(t -> !t.isDeleted())
	            .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

	    tester.setCategory(dto.getCategory());
	    tester.setTitle(dto.getTitle());
	    tester.setContent(dto.getContent());

	    // foodid: 0이면 미선택으로 보고 null 처리
	    if (dto.getFoodid() == 0) tester.setFoodid(null);
	    else tester.setFoodid(dto.getFoodid());

	    tester.setStatus(dto.getStatus());
	    tester.setIsnotice(dto.getIsnotice());

	    // posttype: null이면 기존값 유지
	    if (dto.getPosttype() != null) {
	        tester.setPosttype(dto.getPosttype());
	    }

	    // 이미지 갱신 로직
	    if (files != null && !files.isEmpty()) {
	        tester.getTesterimg().clear();
	        files.forEach(file -> {
	            String url = fileStorageService.upload(file);
	            Testerimg image = new Testerimg();
	            image.setImgsrc(url);
	            image.setTester(tester);
	            tester.getTesterimg().add(image);
	        });
	    }

	    Tester update = repo.save(tester);
	    return TesterAdminResponseDto.from(update);
	}
	
	//유저 수정
	@Override
	public TesterUserResponseDto userUpdate(Long userid, Long testerid, TesterUserRequestDto dto, List<MultipartFile> files) {
		Tester tester = repo.findById(testerid)
                .filter(t -> !t.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
			if(!tester.getUser().getUserId().equals(userid)){
			throw new SecurityException("본인 글만 수정할 수 있습니다.");
			}
			//내용 수정
			tester.setTitle(dto.getTitle());
			tester.setContent(dto.getContent());
			
			// 이미지 갱신 로직
			if (files != null && !files.isEmpty()) {
			    tester.getTesterimg().clear();  //초기화
			    files.forEach(file -> {
			        String url = fileStorageService.upload(file);
			        Testerimg image = new Testerimg();
			        image.setImgsrc(url);
			        image.setTester(tester);
			        tester.getTesterimg().add(image);
			    });
			} 
			
			Tester update = repo.save(tester);
			return TesterUserResponseDto.from(update);
		}


	//삭제
	@Override
	public void delete(Long testerid, Long userid) {

	    Tester tester = repo.findById(testerid)
	            .filter(t -> !t.isDeleted())
	            .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

	    User loginUser = urepo.findById(userid)
	            .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

	    //본인만->관리자도 가능하게 교체 
	    boolean isOwner = tester.getUser() != null && tester.getUser().getUserId().equals(userid);
	    boolean isAdmin = loginUser.getRole() != null && loginUser.getRole().equals("ROLE_ADMIN");

	    if (!isOwner && !isAdmin) {
	        throw new SecurityException("삭제 권한이 없습니다.");
	    }

	    tester.setDeleted(true);
	    repo.save(tester);
	}

	///////////
	@Transactional(readOnly = true)
	@Override  //페이징+정렬+모집공고만보기
	public List<TesterAdminResponseDto> select20Tester(String condition, int pageNo) {
	    HashMap<String,Object> para = new HashMap<>();
	    int start = (pageNo-1)*20 + 1; 
	    int end   = start + 19;
	    para.put("start", start);
	    para.put("end", end);
	    
	    if (condition != null) {
	        switch (condition) {
	            case "old": para.put("condition", "old"); break;  //오래된순
	            case "new": para.put("condition", "new"); break;	//최신
	            case "close": para.put("condition", "close"); break;	//최신
	            case "open": para.put("condition", "open"); break;	//최신
	            case "openOnly": para.put("condition", "openOnly"); break;	//모집중
	            case "closeOnly": para.put("condition", "closeOnly"); break;	//마감
	            default: para.put("condition", "new"); break;
	        }//switch
	    }//if
	    
	    List<TesterAdminResponseDto> tester = dao.select20Tester(para);
		return tester;
	}
	
	@Override
	public int countByTesterPaging(String condition) {
	    HashMap<String, Object> para = new HashMap<>();
	    if (condition != null) {
	        switch (condition) {
	            case "openOnly": para.put("condition", "openOnly"); break;
	            case "closeOnly": para.put("condition", "closeOnly"); break;
	            default: para.put("condition", "new"); break;
	        }
	    }
	    return dao.countByTesterPaging(para);
	}
	
	@Override
	public List<TesterAdminResponseDto> searchTester(String keyword, String searchType, String condition, int pageNo) {

	    if (keyword == null) keyword = "";
	    keyword = keyword.toLowerCase();

	    if (searchType == null) searchType = "all";

	    HashMap<String,Object> para = new HashMap<>();
	    int start = (pageNo-1)*20 + 1;
	    int end   = start + 19;
	    para.put("start", start);
	    para.put("end", end);

	    String searchLike = "%" + keyword + "%";

	    switch (searchType) {
        case "title":
            para.put("searchType", "title");
            para.put("search", searchLike);
            break;
        case "content":
            para.put("searchType", "content");
            para.put("search", searchLike);
            break;
        case "nickname":
            para.put("searchType", "nickname");
            para.put("search", searchLike);
            break;
        case "all":
            para.put("searchType", "all");
            para.put("search", searchLike);
            break;
    }

	    if (condition != null) {
	        switch (condition) {
	            case "old":
	            case "new":
	            case "openOnly":
	            case "closeOnly":
	                para.put("condition", condition);
	                break;
	            default:
	                para.put("condition", "new");
	                break;
	        }
	    }

	    return dao.searchTester(para);
	}
	
	
	@Override
	public int searchTesterCnt(String keyword, String searchType, String condition) {
	    if (keyword == null) keyword = "";
	    keyword = keyword.toLowerCase();
	    if (searchType == null) searchType = "all";
	    HashMap<String,Object> para = new HashMap<>();
	    String searchLike = "%" + keyword + "%";

	    switch (searchType) {
        case "title":
            para.put("searchType", "title");
            para.put("search", searchLike);
            break;
        case "content":
            para.put("searchType", "content");
            para.put("search", searchLike);
            break;
        case "nickname":
            para.put("searchType", "nickname");
            para.put("search", searchLike);
            break;
        case "all":
            para.put("searchType", "all");
            para.put("search", searchLike);
            break;
    }

	    if (condition != null) {
	        switch (condition) {
	            case "openOnly": para.put("condition", "openOnly"); break;
	            case "closeOnly": para.put("condition", "closeOnly"); break;
	            default: para.put("condition", "new"); break;
	        }
	    }

	    return dao.searchTesterCnt(para);
	}
	@Override
	public int updateIsnotice(Long testerid) {
		dao.updateIsnotice(testerid);
		return dao.selectIsnotice(testerid);  //재조회해서 반환
	}
	@Override
	public int updateStatus(Long testerid) {
		 dao.updateStatus(testerid);
		return dao.selectStatus(testerid);
	}
	
	
	
	@Override
	public int updateViews(Long testerid) {
		return dao.updateViews(testerid);
	}

	@Override
	public int selectIsnotice(Long testerid) {
		return dao.selectIsnotice(testerid);
	}

	@Override
	public int selectStatus(Long testerid) {
		return dao.selectStatus(testerid);
	}



}