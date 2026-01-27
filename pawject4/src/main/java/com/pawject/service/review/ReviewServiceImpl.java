package com.pawject.service.review;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.pawject.dao.review.ReviewDao;
import com.pawject.dao.review.ReviewImgDao;
import com.pawject.dto.review.ReviewDto;
import com.pawject.dto.review.ReviewImgDto;
import com.pawject.util.UtilUpload;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired private ReviewDao rdao;
    @Autowired private ReviewImgDao idao;
    @Autowired private UtilUpload utilUpload;

    
    // 단건조회
    @Override
    public ReviewDto reviewSelect(int reviewid) {
        return rdao.reviewSelect(reviewid);
    }
    
    //리뷰-이미지리스트 매칭
    @Override
    public List<ReviewImgDto> reviewimgSelect(int reviewid) {
        return idao.reviewimgSelect(reviewid);
    }
    
    //유저확인
    @Override
    public int selectUserIdForReview(String email) {
        return rdao.selectUserIdForReview(email);
    }

    //글쓰기
    @Transactional
    @Override
    public int reviewInsertWithImg(ReviewDto dto, List<MultipartFile> files) {
        rdao.reviewInsert(dto);
        int reviewid = dto.getReviewid();

        saveReviewImages(reviewid, files);

        return reviewid;
    }

    // 수정
    @Transactional
    @Override
    public int reviewUpdatetWithImg(ReviewDto dto, List<MultipartFile> files) {
        int updated = rdao.reviewUpdate(dto);
        if (updated == 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 글만 수정할 수 있습니다.");
        }
        int reviewid = dto.getReviewid();

        // 기존 이미지 조회
        List<ReviewImgDto> oldImgs = idao.reviewimgSelect(reviewid);

        // DB 삭제
        idao.reviewimgdeleteAll(reviewid);

        // 파일 삭제
        if (oldImgs != null) {
            for (ReviewImgDto img : oldImgs) {
                if (img != null && img.getReviewimgname() != null) {
                    filedelete(img.getReviewimgname());
                }
            }
        }
        // 신규 insert
        saveReviewImages(reviewid, files);

        return reviewid;
    }

    // 이미지 삭제
    @Override
    public int reviewimgdeleteById(int reviewid) {
        List<ReviewImgDto> imgList = idao.reviewimgSelect(reviewid);

        if (imgList != null) {
            for (ReviewImgDto dto : imgList) {
                if (dto != null && dto.getReviewimgname() != null) {
                    filedelete(dto.getReviewimgname());
                }
            }
        }

        return idao.reviewimgdeleteAll(reviewid);
    }

    //글삭제
    @Override
    public int reviewDelete(int reviewid, int userid) {
        int deleted = rdao.reviewDelete(reviewid, userid);
        if (deleted == 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 글만 삭제할 수 있습니다.");
        }
        return deleted;
    }
    
    //페이징카운트
    @Override
    public int reviewSelectCnt() {
        return rdao.reviewSelectCnt();
    }
    
    //페이징
    @Override
    public List<ReviewDto> reviewSelect10(int pageNo, String condition) {
        HashMap<String, Object> para = new HashMap<>();

        int start = (pageNo - 1) * 10 + 1;
        int end = start + 9;

        para.put("start", start);
        para.put("end", end);

        if ("old".equals(condition)) {
            para.put("condition", "old");
        }

        List<ReviewDto> list = rdao.reviewSelect10(para);

        for (ReviewDto r : list) {
            List<ReviewImgDto> imgs = idao.reviewimgSelect(r.getReviewid());
            r.setReviewimglist(imgs);
        }

        return list;
    }
    
    //검색카운ㅌ
    @Override
    public int reviewsearchcnt(String keyword, String searchType) {
        HashMap<String, Object> para = new HashMap<>();

        keyword = keyword.toLowerCase();
        String searchLike = "%" + keyword + "%";

        if (searchType == null) searchType = "all";

        switch (searchType) {
            case "pettypeid":
                para.put("searchType", "pettypeid");
                if ("고양이".equals(keyword)) para.put("search", "1");
                else if ("강아지".equals(keyword)) para.put("search", "2");
                else para.put("search", "-1");
                break;

            case "brandname":
                para.put("searchType", "brandname");
                para.put("search", searchLike);
                break;

            case "foodname":
                para.put("searchType", "foodname");
                para.put("search", searchLike);
                break;

            default:
                para.put("searchType", "all");
                para.put("search", searchLike);
                break;
        }

        return rdao.reviewsearchcnt(para);
    }
    //검색
    @Override
    public List<ReviewDto> reviewsearch(String keyword, String searchType, String condition, int pageNo) {
        HashMap<String, Object> para = new HashMap<>();

        int start = (pageNo - 1) * 10 + 1;
        int end = start + 9;

        para.put("start", start);
        para.put("end", end);

        if (searchType == null) searchType = "all";

        keyword = keyword.toLowerCase();
        String searchLike = "%" + keyword + "%";

        switch (searchType) {
            case "pettypeid":
                para.put("searchType", "pettypeid");
                if ("고양이".equals(keyword)) para.put("search", "1");
                else if ("강아지".equals(keyword)) para.put("search", "2");
                else para.put("search", "-1");
                break;

            case "brandname":
                para.put("searchType", "brandname");
                para.put("search", searchLike);
                break;

            case "foodname":
                para.put("searchType", "foodname");
                para.put("search", searchLike);
                break;

            default:
                para.put("searchType", "all");
                para.put("search", searchLike);
                break;
        }

        if ("old".equals(condition)) para.put("condition", "old");

        List<ReviewDto> list = rdao.reviewsearch(para);

        for (ReviewDto r : list) {
            List<ReviewImgDto> imgs = idao.reviewimgSelect(r.getReviewid());
            r.setReviewimglist(imgs);
        }

        return list;
    }

    // 모달(foodid 검색-검색게시판 연동)
    @Override
    public List<ReviewDto> reviewsearchByFoodid(int foodid) {
        return rdao.reviewsearchByFoodid(foodid);
    }

    @Override
    public int reviewsearchByFoodidCnt(int foodid) {
        return rdao.reviewsearchByFoodidCnt(foodid);
    }


    // 내부 공통 부품
    private void saveReviewImages(int reviewid, List<MultipartFile> files) {
        if (files == null || files.isEmpty()) return;

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;

            String savedPath;
            try {
                savedPath = utilUpload.fileUpload(file, "reviewimg");
            } catch (IOException e) {
                throw new RuntimeException("이미지 업로드 실패", e);
            }

            ReviewImgDto imgDto = new ReviewImgDto();
            imgDto.setReviewid(reviewid);
            imgDto.setReviewimgname(savedPath);

            idao.reviewimginsert(imgDto);
        }
    }
    
    //파일삭제
    private void filedelete(String savedPath) {
        // savedPath: "reviewimg/xxx.png"
        File img = new File("C:/upload/", savedPath);
        if (img.exists()) img.delete();
    }
}
