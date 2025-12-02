package com.pawject.service.review;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.review.ReviewDao;
import com.pawject.dto.review.ReviewDto;
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired ReviewDao rdao;
	
	@Override
	public List<ReviewDto> reviewSelectAll() {
		return rdao.reviewSelectAll();
	}

	@Override
	public int reviewSelect(int reviewid) {
		return rdao.reviewSelect(reviewid);
	}

	@Override
	public int reviewInsert(ReviewDto dto, MultipartFile file) {
		if(!file.isEmpty()) {
			String uuid = UUID.randomUUID().toString();
			String originName=file.getOriginalFilename();
			String fileName=uuid + "_" + originName;
			String uploadPath="C:/file/";
			File img = new File(uploadPath+fileName);
			
			try {
				file.transferTo(img);
				dto.setReviewimg(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return rdao.reviewInsert(dto);
	}

	@Override
	public int reviewUpdate(ReviewDto dto, MultipartFile file) {
		if(!file.isEmpty()) {
			String fileName=file.getOriginalFilename();
			String uploadPath="C:/file/";
			File img = new File(uploadPath+fileName);
			
			try {
			file.transferTo(img);
			dto.setReviewimg(fileName);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return rdao.reviewUpdate(dto);
	}

	@Override
	public int reviewDelete(int reviewid) {
		return rdao.reviewDelete(reviewid);
	}

}
