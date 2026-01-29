package com.pawject.service.tester;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.tester.TesterDao;
import com.pawject.domain.Tester;
import com.pawject.domain.Testerimg;
import com.pawject.domain.User;
import com.pawject.dto.tester.TesterAdminRequestDto;
import com.pawject.dto.tester.TesterAdminResponseDto;
import com.pawject.dto.tester.TesterImgDto;
import com.pawject.dto.tester.TesterUserRequestDto;
import com.pawject.dto.tester.TesterUserResponseDto;
import com.pawject.repository.TesterImgRepository;
import com.pawject.repository.TesterRepository;
import com.pawject.repository.UserRepository;
import com.pawject.util.UtilUpload;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TesterServiceImpl implements TesterService {

	private final TesterDao dao;
	private final TesterRepository repo;
	private final TesterImgRepository imgrepo;
	private final UserRepository urepo;
	private final UtilUpload utilUpload;


	// 이미지 유틸 - 여유 될때 부품화
	private void filedelete(String savedPath) {
		try {
			if (savedPath == null || savedPath.isBlank()) return;

			File imgFile;
			if (savedPath.matches("^[a-zA-Z]:\\\\.*") || savedPath.matches("^[a-zA-Z]:/.*")) {
				imgFile = new File(savedPath);
			} else {
				String normalized = savedPath.replace("\\", "/");
				if (normalized.startsWith("/")) normalized = normalized.substring(1);

				imgFile = new File("C:/upload/", normalized);
			}

			if (imgFile.exists()) imgFile.delete();
		} catch (Exception e) {
		}
	}

	// 이미지 저장
	private void saveTesterImages(Tester tester, List<MultipartFile> files) {
		if (files == null || files.isEmpty()) return;

		for (MultipartFile file : files) {
			if (file == null || file.isEmpty()) continue;

			String savedPath;
			try {
				savedPath = utilUpload.fileUpload(file, "testerimg");
			} catch (Exception e) {
				throw new RuntimeException("이미지 업로드 실패", e);
			}

			Testerimg img = new Testerimg();
			img.setImgsrc(savedPath);
			img.setTester(tester);

			tester.getTesterimg().add(img);
		}
	}

	// 엔티티(객체) -> DTO 이미지 리스트 변환 (id+src)
	private List<TesterImgDto> extractImgList(Tester t) {
		if (t == null || t.getTesterimg() == null) return List.of();

		return t.getTesterimg().stream()
				.filter(x -> x != null && x.getImgsrc() != null && !x.getImgsrc().isBlank())
				.map(x -> TesterImgDto.builder()
						.testerimgid(x.getTesterimgid())
						.imgsrc(x.getImgsrc())
						.build())
				.toList();
	}

	// 목록/검색에서 사용할 이미지 맵(testerid -> imgList)
	private Map<Long, List<TesterImgDto>> buildImgMap(List<Long> testerids) {
		if (testerids == null || testerids.isEmpty()) return Map.of();

		List<Testerimg> imgs = imgrepo.findByTester_TesteridIn(testerids);

		return imgs.stream()
				.filter(img -> img != null
						&& img.getTester() != null
						&& img.getTester().getTesterid() != null
						&& img.getImgsrc() != null
						&& !img.getImgsrc().isBlank())
				.collect(Collectors.groupingBy(
						img -> img.getTester().getTesterid(),
						Collectors.mapping(img -> TesterImgDto.builder()
								.testerimgid(img.getTesterimgid())
								.imgsrc(img.getImgsrc())
								.build(), Collectors.toList())
				));
	}

/////////////////////////jpa
	//단건조회
	@Transactional(readOnly = true)
	@Override
	public TesterAdminResponseDto findById(Long testerid) {

		Tester t = repo.findById(testerid)
				.filter(x -> !x.isDeleted())
				.orElseThrow(() -> new IllegalArgumentException("글 없음"));

		dao.updateViews(testerid);
		return TesterAdminResponseDto.from(t);
	}

	//관리자 작성
	@Override
	public TesterAdminResponseDto adminWrite(Long userid, TesterAdminRequestDto dto, List<MultipartFile> files) {

		User user = urepo.findById(userid)
				.orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

		Tester tester = new Tester();
		tester.setCategory(dto.getCategory());
		tester.setTitle(dto.getTitle());
		tester.setContent(dto.getContent());
		tester.setFoodid(dto.getFoodid() == 0 ? null : dto.getFoodid());
		tester.setStatus(dto.getStatus());
		tester.setIsnotice(dto.getIsnotice());
		tester.setPosttype(dto.getPosttype() == null ? 1 : dto.getPosttype());
		tester.setUser(user);

		saveTesterImages(tester, files);
		Tester saved = repo.save(tester);

		return TesterAdminResponseDto.from(saved);
	}
	
	//유저 작성
	@Override
	public TesterUserResponseDto userWrite(Long userid, TesterUserRequestDto dto, List<MultipartFile> files) {

		User user = urepo.findById(userid)
				.orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

		Tester tester = new Tester();
		tester.setCategory("후기"); // 고정
		tester.setTitle(dto.getTitle());
		tester.setContent(dto.getContent());
		tester.setUser(user);

		tester.setPosttype(0);
		tester.setIsnotice(0);
		tester.setStatus(0);

		saveTesterImages(tester, files);

		Tester saved = repo.save(tester);

		return TesterUserResponseDto.from(saved);
	}
	
	//관리자 수정
	@Override
	public TesterAdminResponseDto adminUpdate(Long userid, Long testerid, TesterAdminRequestDto dto,
			List<MultipartFile> files, List<Long> keepImgIds) {

		Tester tester = repo.findById(testerid)
				.filter(t -> !t.isDeleted())
				.orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

		tester.setCategory(dto.getCategory());
		tester.setTitle(dto.getTitle());
		tester.setContent(dto.getContent());
		tester.setFoodid(dto.getFoodid() == 0 ? null : dto.getFoodid());
		tester.setStatus(dto.getStatus());
		tester.setIsnotice(dto.getIsnotice());

		if (dto.getPosttype() != null) {
			tester.setPosttype(dto.getPosttype());
		}

		// keep 없으면 전체 삭제
		if (keepImgIds == null) keepImgIds = List.of();

		List<Testerimg> oldImgs = tester.getTesterimg();
		if (oldImgs != null) {
			for (Testerimg img : List.copyOf(oldImgs)) {
				if (img == null) continue;

				Long imgId = img.getTesterimgid();
				if (imgId == null) continue;

				if (!keepImgIds.contains(imgId)) {
					filedelete(img.getImgsrc());
					tester.getTesterimg().remove(img);
				}
			}
		}

		saveTesterImages(tester, files);

		Tester update = repo.save(tester);

		return TesterAdminResponseDto.from(update);
	}
	
	//유저 수정
	@Override
	public TesterUserResponseDto userUpdate(Long userid, Long testerid, TesterUserRequestDto dto,
			List<MultipartFile> files, List<Long> keepImgIds) {

		Tester tester = repo.findById(testerid)
				.filter(t -> !t.isDeleted())
				.orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

		if (!tester.getUser().getUserId().equals(userid)) {
			throw new SecurityException("본인 글만 수정할 수 있습니다.");
		}

		tester.setTitle(dto.getTitle());
		tester.setContent(dto.getContent());

		if (keepImgIds == null) keepImgIds = List.of();

		List<Testerimg> oldImgs = tester.getTesterimg();
		if (oldImgs != null) {
			for (Testerimg img : List.copyOf(oldImgs)) {
				if (img == null) continue;

				Long imgId = img.getTesterimgid();
				if (imgId == null) continue;

				if (!keepImgIds.contains(imgId)) {
					filedelete(img.getImgsrc());
					tester.getTesterimg().remove(img);
				}
			}
		}

		saveTesterImages(tester, files);

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

		boolean isOwner = tester.getUser() != null && tester.getUser().getUserId().equals(userid);
		boolean isAdmin = "ROLE_ADMIN".equals(loginUser.getRole());

		if (!isOwner && !isAdmin) {
			throw new SecurityException("삭제 권한이 없습니다.");
		}

		if (tester.getTesterimg() != null) {
			for (Testerimg img : tester.getTesterimg()) {
				if (img == null) continue;
				filedelete(img.getImgsrc());
			}
		}

		tester.getTesterimg().clear();

		tester.setDeleted(true);
		repo.save(tester);
	}

	///////////////////////////mybatis
	
	// 목록/검색 (MyBatis + JPA 이미지 붙이기)
	@Transactional(readOnly = true)
	@Override
	public List<TesterAdminResponseDto> select20Tester(String condition, int pageNo) {

		HashMap<String, Object> para = new HashMap<>();
		int start = (pageNo - 1) * 20 + 1;
		int end = start + 19;

		para.put("start", start);
		para.put("end", end);

		if (condition != null) {
			switch (condition) {
				case "old": para.put("condition", "old"); break;
				case "new": para.put("condition", "new"); break;
				case "close": para.put("condition", "close"); break;
				case "open": para.put("condition", "open"); break;
				case "openOnly": para.put("condition", "openOnly"); break;
				case "closeOnly": para.put("condition", "closeOnly"); break;
				default: para.put("condition", "new"); break;
			}
		}

		List<TesterAdminResponseDto> list = dao.select20Tester(para);

		// 목록 imgids
		List<Long> ids = list.stream()
				.map(TesterAdminResponseDto::getTesterid)
				.filter(x -> x != null)
				.toList();

		Map<Long, List<TesterImgDto>> imgMap = buildImgMap(ids);

		// imgList 붙이기
		for (TesterAdminResponseDto dto : list) {
			if (dto == null || dto.getTesterid() == null) continue;
			dto.setImgList(imgMap.getOrDefault(dto.getTesterid(), List.of()));
		}

		return list;
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

		HashMap<String, Object> para = new HashMap<>();
		int start = (pageNo - 1) * 20 + 1;
		int end = start + 19;

		para.put("start", start);
		para.put("end", end);

		String searchLike = "%" + keyword + "%";

		switch (searchType) {
			case "title": para.put("searchType", "title"); para.put("search", searchLike); break;
			case "content": para.put("searchType", "content"); para.put("search", searchLike); break;
			case "nickname": para.put("searchType", "nickname"); para.put("search", searchLike); break;
			default: para.put("searchType", "all"); para.put("search", searchLike); break;
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

		List<TesterAdminResponseDto> list = dao.searchTester(para);

		List<Long> ids = list.stream()
				.map(TesterAdminResponseDto::getTesterid)
				.filter(x -> x != null)
				.toList();

		Map<Long, List<TesterImgDto>> imgMap = buildImgMap(ids);

		for (TesterAdminResponseDto dto : list) {
			if (dto == null || dto.getTesterid() == null) continue;
			dto.setImgList(imgMap.getOrDefault(dto.getTesterid(), List.of()));
		}

		return list;
	}

	@Override
	public int searchTesterCnt(String keyword, String searchType, String condition) {

		if (keyword == null) keyword = "";
		keyword = keyword.toLowerCase();
		if (searchType == null) searchType = "all";

		HashMap<String, Object> para = new HashMap<>();
		String searchLike = "%" + keyword + "%";

		switch (searchType) {
			case "title": para.put("searchType", "title"); para.put("search", searchLike); break;
			case "content": para.put("searchType", "content"); para.put("search", searchLike); break;
			case "nickname": para.put("searchType", "nickname"); para.put("search", searchLike); break;
			default: para.put("searchType", "all"); para.put("search", searchLike); break;
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
		return dao.selectIsnotice(testerid);
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
