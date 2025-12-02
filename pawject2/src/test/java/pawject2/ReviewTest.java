package pawject2;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pawject.dao.review.ReviewDao;
import com.pawject.dto.review.ReviewDto;
import com.pawject.service.review.ReviewService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/root-context.xml")
public class ReviewTest {
	@Autowired SqlSession se; @Autowired DataSource data; @Autowired ApplicationContext context;
	@Ignore	@Test public void test1() { System.out.println(se); System.out.println(data); System.out.println(context); }
	
	@Autowired ReviewDao dao; ReviewDto dto = new ReviewDto();
	
	@Ignore @Test
	public void test01() {
	//	 #{userid},#{brandid},#{foodid},#{reviewimg},#{rating},#{title},#{reviewcomment} sysdate)
		dto.setUserid(1);
		dto.setBrandid(3);
		dto.setFoodid(4);
		dto.setReviewimg("*");
		dto.setRating(5);
		dto.setTitle("test01");
		dto.setReviewcomment("daotest");
		System.out.println(dao.reviewInsert(dto));
	}
	
	@Ignore @Test
	public void test02() {
		System.out.println(dao.reviewSelectAll());
	}
	
	 @Ignore @Test
	public void test03() {
		System.out.println(dao.reviewSelect(2));
	}
	 
	 @Ignore @Test
	 public void test04() {
//			set brandid=#{brandid}, foodid=#{foodid}, reviewimg=#{reviewimg}, 
//							rating=#{rating}, title=#{title}, 
//							reviewcomment=#{reviewcomment}, updatedat=sysdate
//				where reviewid=#{reviewid} 		 
		 dto.setBrandid(1);
		 dto.setFoodid(1);
		 dto.setReviewimg("");
		 dto.setRating(3);
		 dto.setTitle("update");
		 dto.setReviewcomment("updatetest");
		 dto.setReviewid(2);
		 
		 System.out.println(dao.reviewUpdate(dto));
		 
	 }
	 
	 @Ignore @Test
	 public void test05() {
		 System.out.println(dao.reviewDelete(2));
	 }
	 
	 /////////////////////////////////////////////////////////////////////
	 
	 @Autowired ReviewService service;
	 @Ignore @Test
	 public void test11() {
			//	 #{userid},#{brandid},#{foodid},#{reviewimg},#{rating},#{title},#{reviewcomment} sysdate)
				dto.setUserid(1);
				dto.setBrandid(3);
				dto.setFoodid(4);
				dto.setReviewimg("*");
				dto.setRating(5);
				dto.setTitle("test01");
				dto.setReviewcomment("servicetest");
			//	System.out.println(service.reviewInsert(dto));
			}
	 
	 @Ignore @Test
		public void test12() {
			System.out.println(service.reviewSelectAll());
		}	 

	 
	 @Ignore @Test
		public void test13() {
			System.out.println(service.reviewSelect(3));
		}
	 
	 @Ignore @Test
	 public void test14() {
 
		 dto.setBrandid(1);
		 dto.setFoodid(1);
		 dto.setReviewimg("");
		 dto.setRating(3);
		 dto.setTitle("update");
		 dto.setReviewcomment("updatetest");
		 dto.setReviewid(3);
		 
		// System.out.println(service.reviewUpdate(dto));	 
	 
	 }
	 
	 @Ignore @Test
	 public void test15() {
		 System.out.println(service.reviewDelete(3));
	 }	 
	 
	 
}
