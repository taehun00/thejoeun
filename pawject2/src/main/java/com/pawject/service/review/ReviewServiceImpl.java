package com.pawject.service.review;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.review.ReviewDao;
import com.pawject.dao.review.ReviewImgDao;
import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
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
        String uploadPath = "C:/file/";

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
	
	
	//mvc 버전 - 일괄 업로드
//	public int reviewimginsert(int reviewid, List<MultipartFile> files) {
//		int successcnt=0;
//		
//	    for (MultipartFile file : files) {
//	        if (file.isEmpty()) { continue; }  //!아님 이미지 없으면 스킵하고 업로드
//	        String uuid = UUID.randomUUID().toString();
//	        String originName = file.getOriginalFilename();
//	        String fileName = uuid + "_" + originName; //이거 안하면 db에서 이미지 이름 중복 시 충돌남
//	        String uploadPath = "C:/file/";
//
//	        File img = new File(uploadPath + fileName);
//
//	        try {
//	            file.transferTo(img);
//	            successcnt++;  //빼먹으면 카운트 0이됨 영원히....
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//
//	        ReviewImgDto dto = new ReviewImgDto();
//	        dto.setReviewid(reviewid);
//	        dto.setReviewimgname(fileName);
//
//	        idao.reviewimginsert(dto);
//	    }//for
//	    return successcnt;
//	}
	
	
	@Override
	public ReviewImgDto reviewimgupdate(int reviewimgid,MultipartFile file) {
		if (file == null || file.isEmpty()) {
		    return null;
		}

		ReviewImgDto olddto= idao.reviewimgIdSelect(reviewimgid);
		
		String uuid = UUID.randomUUID().toString();
        String originName = file.getOriginalFilename();
        String fileName = uuid + "_" + originName;
        String uploadPath = "C:/file/";

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

	
	
//	@Override
//	public int reviewimgupdate(int reviewid, List<MultipartFile> files) {
//	
//		int successcnt=0;
//		
//	    for (MultipartFile file : files) {
//	        if (file.isEmpty()) { continue; }
//	        String uuid = UUID.randomUUID().toString();
//	        String originName = file.getOriginalFilename();
//	        String fileName = uuid + "_" + originName;
//	        String uploadPath = "C:/file/";
//
//	        File img = new File(uploadPath + fileName);
//
//	        try {
//	            file.transferTo(img);
//	            successcnt++;  
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//
//	        ReviewImgDto dto = new ReviewImgDto();
//	        dto.setReviewid(reviewid);
//	        dto.setReviewimgname(fileName);
//
//	        idao.reviewimgupdate(dto);
//	    }//for
//	    return successcnt;
//	}

	//파일 삭제 전용 메서드
	private void filedelete(String fileName) {
		String uploadPath = "C:/file/";
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

	
	
	
	
	
}
