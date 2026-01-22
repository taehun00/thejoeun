package com.pawject.service.review;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.review.ReviewDao;
import com.pawject.dao.review.ReviewImgDao;
import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired ReviewDao rdao;
	@Autowired ReviewImgDao idao;
	
	@Override
	public List<ReviewDto> reviewSelectAll() {
		return rdao.reviewSelectAll();
	}

	@Override
	public ReviewDto  reviewSelect(int reviewid) {
		return rdao.reviewSelect(reviewid);
	}

	@Override
	public int reviewInsert(ReviewDto dto) {
		return rdao.reviewInsert(dto);
	}

	@Override
	public int reviewUpdate(ReviewDto dto) {
		return rdao.reviewUpdate(dto);
	}

	@Override
	public int reviewDelete(int reviewid) {
		return rdao.reviewDelete(reviewid);
	}

	@Override
	public List<ReviewImgDto> reviewimgselectAll() {
		return idao.reviewimgselectAll();
	}

	@Override
	public List<ReviewImgDto> reviewimgSelect(int reviewid) {
		return idao.reviewimgSelect(reviewid);
	}

	@Override  
	public ReviewImgDto reviewimginsert(int reviewid,  MultipartFile file) {//아작스 버전
		//  ㄴint 아님 주의!

		if (file == null || file.isEmpty()) {
		    return null;
		}

		String uuid = UUID.randomUUID().toString();
        String originName = file.getOriginalFilename();
        String fileName = uuid + "_" + originName;
        String uploadPath =  "C:/upload/";

        File img = new File(uploadPath + fileName);

	    try {
	        file.transferTo(img);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    ReviewImgDto dto = new ReviewImgDto();
	    dto.setReviewid(reviewid);
	    dto.setReviewimgname(fileName);

	    idao.reviewimginsert(dto);

	    return dto;
	}
	
	
	
	@Override  //다 만들고 나니까 깨달은 건데 이거 필요없엉..... 왜했지
	public ReviewImgDto reviewimgupdate(int reviewimgid,MultipartFile file) {
		if (file == null || file.isEmpty()) {
		    return null;
		}

		ReviewImgDto olddto= idao.reviewimgIdSelect(reviewimgid);
		
		String uuid = UUID.randomUUID().toString();
        String originName = file.getOriginalFilename();
        String fileName = uuid + "_" + originName;
        String uploadPath =  "C:/upload/";

        File img = new File(uploadPath + fileName);

	    try {
	        file.transferTo(img);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    ReviewImgDto dto = new ReviewImgDto();
	    dto.setReviewimgid(reviewimgid);   //인서트와 동일하지만 이미지 교체의 경우 리뷰아이디 대신 이미지아이디 필요**
	    dto.setReviewimgname(fileName);

	    int update= idao.reviewimgupdate(dto);

	    if(update>0 && olddto!=null) {
	    	filedelete(olddto.getReviewimgname());
	    }
	    
	    return dto;
	}

	
	

	//파일 삭제 전용 메서드
	private void filedelete(String fileName) {
		String uploadPath = "C:/upload/";
		File img = new File(uploadPath + fileName);
		
		if(img.exists()) {
			img.delete();
		}
	}

	@Override
	public int reviewimgdeleteAll(int reviewid) {
		
		List<ReviewImgDto> img = idao.reviewimgSelect(reviewid);
		
	    for (ReviewImgDto dto : img) {
	    		String fileName=dto.getReviewimgname();
	    		filedelete(dto.getReviewimgname());
	    }
		
		return idao.reviewimgdeleteAll(reviewid);
	}

	@Override
	public int reviewimgdelete(int reviewimgid) {
		ReviewImgDto img = idao.reviewimgIdSelect(reviewimgid);
		
		if(img==null) {return 0;}
		
		int result=idao.reviewimgdelete(reviewimgid);
		if(result>0) {
			filedelete(img.getReviewimgname());	 //if 안쓰면 냅다 파일부터 날려버림 주의
		}
		
		return idao.reviewimgdelete(reviewimgid);
	}

	@Override
	public ReviewImgDto reviewimgIdSelect(int reviewimgid) {
		return idao.reviewimgIdSelect(reviewimgid);
	}

	
	
	//페이징
	@Override
	public List<ReviewDto> reviewSelect10(int pstartno, String condition) {
		HashMap<String, Object> para = new HashMap<>();
		
		int start = (pstartno-1)*10+1;
		int end = start+9;
		
		para.put("start", start);
		para.put("end", end);
		
		List<ReviewDto> list = rdao.reviewSelect10(para);
		
	    if (condition != null) {
	        switch (condition) {
	            case "old":
	            	para.put("condition", "old");
	                break;
	        }
	    }	
		
		//아작스용 - 리뷰별 아이디 매칭되는 이미지 여기서 던져주기 
		for(ReviewDto r : list) {
			List<ReviewImgDto> imgs = idao.reviewimgSelect(r.getReviewid());
			r.setReviewimglist(imgs);
						}
		
		return list;
	}

	@Override
	public int reviewSelectCnt() {
		return rdao.reviewSelectCnt();
	}

	@Override
	public List<ReviewDto> reviewsearch(String keyword, String searchType, String condition, int pstartno) {
		HashMap<String, Object> para = new HashMap<>();
		int start = (pstartno-1)*10+1;
		int end = start+9;
		
		para.put("start", start);
		para.put("end", end);
		
		if (searchType == null) searchType = "all";
		keyword = keyword.toLowerCase(); //대소문자 구분 x
		String searchLike = "%" + keyword + "%";
		
		switch(searchType) {
		case "pettypeid" :	            
			para.put("searchType", "pettypeid");
				//분기1.펫타입
		        if ("고양이".equals(keyword)) {
		            para.put("search", "1");
		        } else if ("강아지".equals(keyword)) {
		            para.put("search", "2");
		        } else {
		            para.put("search", "-1");
		        }
		        break;
		        
				//분기2. 브랜드
				case "brandname" : para.put("searchType", "brandname");
				 				   para.put("search", searchLike);	break;

				//분기3. 사료이름
				case "foodname" : para.put("searchType", "foodname"); 
								  para.put("search", searchLike);	break;
		
				//분기4. 제목+내용+브랜드
				case "all" : para.put("searchType", "all");
							para.put("search", searchLike);	break;

				}//switch
		
			    if (condition != null) {
			        switch (condition) {
			            case "old":
			            	para.put("condition", "old");
			                break;
			        }
			    }	
		
				    List<ReviewDto> list = rdao.reviewsearch(para);
			
				    // 이미지도 여기서 넣어줘야됨!!
				    for (ReviewDto r : list) {
				        List<ReviewImgDto> imgs = idao.reviewimgSelect(r.getReviewid());
				        r.setReviewimglist(imgs);
				    }
			
				    return list;
	}

	@Override
	public int reviewsearchcnt(String keyword, String searchType) {
		HashMap<String, Object> para = new HashMap<>();
		keyword = keyword.toLowerCase(); //대소문자 구분 x
		String searchLike = "%" + keyword + "%";
		
		switch(searchType) {
		case "pettypeid" :	            
			para.put("searchType", "pettypeid");
				//분기1.펫타입
		        if ("고양이".equals(keyword)) {
		            para.put("search", "1");
		        } else if ("강아지".equals(keyword)) {
		            para.put("search", "2");
		        } else {
		            para.put("search", "-1");
		        }
		        break;
		        
				//분기2. 브랜드
				case "brandname" : para.put("searchType", "brandname");
				 				   para.put("search", searchLike);	break;

				//분기3. 사료이름
				case "foodname" : para.put("searchType", "foodname"); 
								  para.put("search", searchLike);	break;
		
				//분기4. 제목+내용+브랜드
				case "all" : para.put("searchType", "all");
							para.put("search", searchLike);	break;
				
							 
				}//switch

		
		return rdao.reviewsearchcnt(para);
	}

	
	@Override
	public UserAuthDto readAuthForReview(UserDto udto){
		return rdao.readAuthForReview(udto);
	}
	
	@Override
	public int selectUserIdForReview(String email) {
		return rdao.selectUserIdForReview(email);
	}

	@Override
	public List<ReviewDto> reviewsearchByFoodid(int foodid) {
		return rdao.reviewsearchByFoodid(foodid);
	}

	@Override
	public int reviewsearchByFoodidCnt(int foodid) {
		return rdao.reviewsearchByFoodidCnt(foodid);
	}
	
	
	
	
	
}
