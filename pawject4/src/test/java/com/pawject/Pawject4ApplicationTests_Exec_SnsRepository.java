package com.pawject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.domain.ExecComment;
import com.pawject.domain.ExecFollow;
import com.pawject.domain.ExecHashtag;
import com.pawject.domain.ExecImage;
import com.pawject.domain.ExecPost;
import com.pawject.domain.ExecPostLike;
import com.pawject.domain.ExecRetweet;
import com.pawject.domain.User;
import com.pawject.repository.ExecCommentRepository;
import com.pawject.repository.ExecFollowRepository;
import com.pawject.repository.ExecHashtagRepository;
import com.pawject.repository.ExecImageRepository;
import com.pawject.repository.ExecPostLikeRepository;
import com.pawject.repository.ExecPostRepository;
import com.pawject.repository.ExecRetweetRepository;
import com.pawject.repository.UserRepository;

 
/**
 * Repository CRUD 통합테스트
 * 순서 : AppUser →
*/
@SpringBootTest
@Transactional  // org.springframework.transaction.annotation.Transactional
class Pawject4ApplicationTests_Exec_SnsRepository {
	
	@Autowired private UserRepository          userRepository;
	@Autowired private ExecPostRepository      execPostRepository;
	@Autowired private ExecImageRepository     execImageRepository;
	@Autowired private ExecCommentRepository   execCommentRepository;
	@Autowired private ExecFollowRepository    execFollowRepository;
	@Autowired private ExecHashtagRepository   execHashtagRepository;
	@Autowired private ExecPostLikeRepository  execPostLikeRepository;
	@Autowired private ExecRetweetRepository   execRetweetRepository;
	
	//테스트 공통 데이터
	private User user1;
	private User user2;
	private ExecPost    post;
	
	/**
	 * 공통 준비 : 사용자 2명 + 게시글 1글
	*/
	@BeforeEach
	void setup() {
		//사용자 생성
		String email1 = "user1_" + UUID.randomUUID() + "@test.com";
		String email2 = "user2_" + UUID.randomUUID() + "@test.com";
		
		user1 = new User();
		user1.setEmail(email1);
		user1.setPassword("pass123");
		user1.setNickname("user1");
		user1.setProvider("local");
		//user1.setDeleted(false);
		
		user2 = new User();
		user2.setEmail(email2);
		user2.setPassword("pass123");
		user2.setNickname("user2");
		user2.setProvider("local");
		//user2.setDeleted(false);
		
		userRepository.save(user1);
		userRepository.save(user2);
		//게시글 생성
		
		post = new ExecPost();
		post.setContent("테스트 게시글");
		post.setUser(user1);
		post.setDeleted(false);
		execPostRepository.save(post);
	}
    // ---------------------------------------------------------------------
    // AppUserRepository
    // ---------------------------------------------------------------------
	@Test
	@DisplayName("■ AppUserRepository-CRUD ")
	void testAppUserRepository() {
		assertThat(userRepository.existsByEmailAndProviderNative(user1.getEmail())).isGreaterThanOrEqualTo(1);
		assertThat(userRepository.findByNickname(user1.getNickname())).isPresent();
		
		user1.setNickname("first");
		userRepository.save(user1);
		User updated = userRepository.findById(user1.getUserId()).orElseThrow(); //
		assertThat(updated.getNickname()).isEqualTo("first");
		
		assertThat(userRepository.findByEmail(user1.getEmail())).isPresent();
		
		Long deleteId = user2.getUserId(); //
		userRepository.deleteById(deleteId);
		assertThat(userRepository.findById(deleteId)).isEmpty();
	}    // ---------------------------------------------------------------------
    // ExecImageRepository ( 이미지생성, 단건조회, 삭제후 조회 불가확인 )
    // ---------------------------------------------------------------------
	@Test
	@DisplayName("■ ExecImageRepository-CRUD ")
	void testImageRepository() {
		// 이미지생성 (save)
		ExecImage image = new ExecImage();
		image.setSrc("1.png");
		image.setPost(post);
		execImageRepository.save(image);
		
		// 단건조회 (findById)
		assertThat(  execImageRepository.findById( image.getId() )  ).isPresent();
		
		// 삭제후 조회 불가확인 (delete:객체, findById:번호)
		execImageRepository.delete(image);
		assertThat(  execImageRepository.findById( image.getId() )  ).isEmpty();
		
	}
	
	// ---------------------------------------------------------------------
	// ExecHashtagRepository - HashTag
//	// ---------------------------------------------------------------------
//	@Test
//	@DisplayName("■ PostRepository-CRUD ")
//	void testPostRepository() {
//		// 해쉬태그 연결 후 검색
//		ExecHashtag tag = new ExecHashtag();
//		tag.setName("haha"); 
//		execHashtagRepository.save(tag);
//		
//		post.getHashtags().add(tag);  // List<Hashtag> hashtags
//		execPostRepository.save(post);
//		
//		List<ExecPost> byTag = execPostRepository.findByHashtags_NameAndDeletedFalse("haha");
//		assertThat(byTag).isNotEmpty();
//		
//		// 게시글수정
//		// 게시글 삭제 후 조회 불가 
//	} 	
	
	@Test
	@DisplayName("■ HashtagRepository-CRUD ")
	void testHashtagRepository() {
		ExecHashtag tag = new ExecHashtag();
		tag.setName("haha"); 
		execHashtagRepository.save(tag);
		// 양방향 관계 동기화
		post.getHashtags().add(tag);   // List<Post> posts
		tag.getPosts().add(post);   
		execPostRepository.save(post);
		
		Optional<ExecHashtag> withPosts  = execHashtagRepository.findByNameWithPosts("haha");
		assertThat(withPosts).isPresent();
		assertThat(withPosts.get().getPosts()).isNotEmpty();
	}
	

    // ---------------------------------------------------------------------
    // ExecPostRepository - hashtags / 
    // ---------------------------------------------------------------------
	@Test
	@DisplayName("■ ExecPostRepository-CRUD ")
	void testPostRepository() {
		// 글삽입시 : 해쉬태그 연결 후 검색
		ExecHashtag tag = new ExecHashtag();
		tag.setName("haha"); 
		execHashtagRepository.save(tag);
		
		post.getHashtags().add(tag);  // List<Hashtag> hashtags
		execPostRepository.save(post);
		
		List<ExecPost> byTag = execPostRepository.findByHashtags_NameAndDeletedFalse("haha");
		assertThat(byTag).isNotEmpty();
		
		// 게시글수정
		post.setContent("수정된 게시글");
		execPostRepository.save(post);  
		assertThat(  execPostRepository.findById( post.getId() ).get().getContent()  ).isEqualTo("수정된 게시글");
		 
		// 게시글 삭제 후 조회 불가 
		post.setDeleted(true);
		execPostRepository.save(post);
		assertThat(execPostRepository.findByDeletedFalse()).isEmpty();
		
		//페이징테스트
		for(int i=0; i<5; i++) {
			ExecPost extra = new ExecPost();
			extra.setContent("게시글 " + i);
			extra.setUser(user1);
			extra.setDeleted(false);
			execPostRepository.save(extra);
		}
		
		// 전체글 페이징
		List<ExecPost>  pagedPosts = execPostRepository.findPostsWithPaging(1, 3);
		assertThat(pagedPosts).hasSize(3);
		
		// 특정유저 좋아요  ## 좋아할때 추가
		// 내가쓴 + 리트윗 페이징   ## 리트윗 추가
		
	} 
    // ---------------------------------------------------------------------
    // ExecCommentRepository - 특정게시글의 삭제되지 않은 댓글 목록 조회 /삭제되지 않은 댓글 수 집계
    // ---------------------------------------------------------------------
	@Test
	@DisplayName("■ ExecCommentRepository-CRUD ")
	void testCommentRepository() {
		// 댓글 생성
		ExecComment comment = new ExecComment();	
		comment.setContent("테스트 댓글"); 
		comment.setUser(user2);
		comment.setPost(post);
		comment.setDeleted(false);
		execCommentRepository.save(comment);
		
		// 특정게시글의 삭제되지 않은 댓글 목록 조회
		List<ExecComment>  comments = execCommentRepository.findByPostIdAndDeletedFalse(post.getId());
		assertThat(comments).hasSize(1);
		
		// 댓글 수정
		comment.setContent("수정된 댓글");
		execCommentRepository.save(comment);
		assertThat(  execCommentRepository.findById( comment.getId() ).get().getContent()  ).isEqualTo("수정된 댓글");
		
		// soft delete 처리 댓글 삭제 - 삭제되지 않은 댓글 수 집계
		comment.setDeleted(true);
		execCommentRepository.save(comment);
		assertThat(  execCommentRepository.countByPostIdAndDeletedFalse(post.getId())  ).isEqualTo(0L);

	}

    // ---------------------------------------------------------------------
    // ExecPostLikeRepository 
	// - 특정 게시글의 좋아요 수 집계 / 특정 유저가 특정 게시글에 좋아요 했는지 여부 
	// - 좋아요 취소 (조회없이 바로 삭제) / 특정유저의 특정게시글 좋아요 조회
    // ---------------------------------------------------------------------
	@Test
	@DisplayName("■ ExecPostLikeRepository-CRUD ")
	void testPostLikeRepository() {
		// 좋아요 생성 (user2 → post) 
		ExecPostLike like= new ExecPostLike(user2, post);
		execPostLikeRepository.save(like);
		
		// 특정 유저가 특정 게시글에 좋아요 했는지
		Optional<ExecPostLike> found = execPostLikeRepository.findByUser_IdAndPost_Id(user2.getUserId(), post.getId());
		assertThat(found).isPresent();
		
		// 특정 게시글의 좋아요 수  집계
		assertThat( execPostLikeRepository.countByUser_IdAndPost_Id(user2.getUserId(), post.getId())  ).isEqualTo(1L);
		 
		// 좋아요 취소 (조회없이 바로 삭제) / 특정유저의 특정게시글 좋아요 조회
		execPostLikeRepository.deleteByUserAndPost(user2.getUserId(), post.getId()); 
		assertThat( execPostLikeRepository.countByUser_IdAndPost_Id(user2.getUserId(), post.getId())  ).isEqualTo(0L);

	}
	
    // ---------------------------------------------------------------------
    // ExecRetweetRepository  
    // ---------------------------------------------------------------------
	@Test
	@DisplayName("■ ExecRetweetRepository-CRUD ")
	void testRetweetRepository() {
		// 리트윗 생성 (user2 → post) 
		ExecRetweet retweet = new ExecRetweet(user2, post);
		execRetweetRepository.save(retweet);

		// 특정유저의 특정게시글 리트윗 조회
		Optional<ExecRetweet>  found  = execRetweetRepository.findByUserAndOriginalPost(user2.getUserId(), post.getId());
		assertThat(found).isPresent();
		
		// 특정게시글의 리트윗 수
		assertThat(  execRetweetRepository.countByOriginalPostId(  post.getId()  )  ).isEqualTo(1L);
		
		// 리트윗취소
		execRetweetRepository.deleteByUserAndOriginalPost(   user2.getUserId(), post.getId()  );
        assertThat(execRetweetRepository.countByUserAndOriginalPost(user2.getUserId(), post.getId())).isEqualTo(0L);
        assertThat(execRetweetRepository.countByOriginalPostId(post.getId())).isEqualTo(0L);
	}
	
	// ---------------------------------------------------------------------
	// ExecFollowRepository
	// ---------------------------------------------------------------------
	@Test
	@DisplayName("■ ExecFollowRepository - CRUD + 팔로잉/팔로워 조회/집계")
	void testFollowRepository() {
	    // 팔로우 생성 (user1 → user2)
		ExecFollow follow = new ExecFollow(user1, user2);
	    execFollowRepository.save(follow);

	    // 팔로우 단건 조회
	    assertThat(execFollowRepository.findByFollower_IdAndFollowee_Id(user1.getUserId(), user2.getUserId())).isPresent();

	    // 팔로잉 목록 조회 (EntityGraph로 followee 조인)
	    List<ExecFollow> followings = execFollowRepository.findByFollower_Id(user1.getUserId());
	    assertThat(followings).hasSize(1);
	    assertThat(followings.get(0).getFollowee().getUserId()).isEqualTo(user2.getUserId());

	    // 팔로워 목록 조회 (EntityGraph로 follower 조인)
	    List<ExecFollow> followers = execFollowRepository.findByFollowee_Id(user2.getUserId());
	    assertThat(followers).hasSize(1);
	    assertThat(followers.get(0).getFollower().getUserId()).isEqualTo(user1.getUserId());

	    // 팔로잉/팔로워 수 집계
	    assertThat(execFollowRepository.countByFollower_Id(user1.getUserId())).isEqualTo(1L);
	    assertThat(execFollowRepository.countByFollowee_Id(user2.getUserId())).isEqualTo(1L);

	    // 삭제 후 조회 불가 확인
	    execFollowRepository.delete(follow);
	    assertThat(execFollowRepository.findByFollower_IdAndFollowee_Id(user1.getUserId(), user2.getUserId())).isEmpty();
	}
	
 
	
	
}
