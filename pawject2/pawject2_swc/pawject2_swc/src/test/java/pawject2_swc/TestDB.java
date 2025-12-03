package pawject2_swc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pawject2.dao.OXDisMapper;
import com.pawject2.dto.Disease_ox;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/root-context.xml")
public class TestDB {
	@Autowired ApplicationContext  context;
	@Autowired DataSource  ds;
	@Autowired SqlSession  sqlSession;
	@Autowired OXDisMapper  dao;
	
	@Test public void test4() {
		
	//////selectAll
		Map<String, Integer> params = new HashMap<>();
		params.put("start", 1);
		params.put("end", 10);
		List<Disease_ox> list = dao.selectAll(params);
		for (Disease_ox d : list) {
			System.out.println(d);
		}
		
		
		
		 //////select
//		Disease_ox  dto = new Disease_ox();
//		dto.setOxno(11); 
//		System.out.println(dao.select(dto));
		
		
		
		
	   //////delete
//			Disease_ox  dto = new Disease_ox();
//			dto.setOxno(11); 
//			System.out.println(dao.delete(dto));
//		
			
			
		////// update
//		Disease_ox  dto = new Disease_ox();
//		dto.setOxno(61); dto.setDisno(2);
//		dto.setOxquestion("질문 ox 퀴즈 update");
//		dto.setOxanswer("O");
//		dto.setOxcomment("좋은 선택2");
//		System.out.println(dao.update(dto));
		
		
		////// insert
//		Disease_ox  dto = new Disease_ox();
//		dto.setOxno(61); dto.setDisno(2);
//		dto.setOxquestion("질문 ox 퀴즈");
//		dto.setOxanswer("O");
//		dto.setOxcomment("좋은 선택");
//		dto.setOxbhit(1);
//		System.out.println(dao.insert(dto));
		
	}
	
	
	@Ignore @Test public void test1() {System.out.println(context);}
	@Ignore @Test public void test2() {System.out.println(ds);}
	@Ignore @Test public void test3() {System.out.println(sqlSession);}

}
