package pawject1_swc;

import java.net.UnknownHostException;
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

import com.pawject.dao.Disswc.DiseaseDao;
import com.pawject.dto.Disswc.DisswcDto;
import com.pawject.service.Disswc.DiseaseService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/root-context.xml")
public class TestDis1 {
	
	@Autowired ApplicationContext context;
	@Autowired DataSource        ds;
	@Autowired SqlSession 	     Session;
//	@Autowired TestDao           dao;
	@Autowired DiseaseDao        dao;
	@Autowired DiseaseService    service;
	
	@Ignore @Test public void test1() { System.out.println(context); }
	@Ignore @Test public void test2() { System.out.println(ds); }
	@Ignore @Test public void test3() { System.out.println(Session); }
	
	@Test public void test6() throws UnknownHostException{
		HashMap<String,String> para = new HashMap<>();
		para.put("search", "%t%");
		
		System.out.println(dao.selectSearch(para));
		
	}
	
	
	 @Ignore @Test public void test5()  throws UnknownHostException {
		 //5. delete
//		 DisswcDto  dto = new DisswcDto();
//		 dto.setDisno(1);
//		 System.out.println(service.delete(131)); }
		 
		//4. update
//		 DisswcDto dto = new DisswcDto();
//		 dto.setDisno(1);
//		 dto.setDisname("질환명12");
//		 dto.setDisex("질환테스트 설명12");
//		 dto.setKindpet("대형견12");
//		 dto.setInfval("30%12");
//		 dto.setMannote("주의 내용12");
//		 dto.setBhit(22);
//		 System.out.println(service.update(dto));
//	 }
		 
		//3. select
		 DisswcDto dto = new DisswcDto();
		 dto.setDisno(66);
		 System.out.println(service.select(132));
	 }
		 
		//2. insert
//		 DisswcDto dto = new DisswcDto();
//		 dto.setDisno(77);
//		 dto.setDisname("질환명");
//		 dto.setDisex("질환테스트 설명");
//		 dto.setKindpet("대형견");
//		 dto.setInfval("30%");
//		 dto.setMannote("주의 내용");
//		 dto.setBhit(2);
//		 System.out.println(service.insert(dto));
//	 }
	 
	 
//	//////1. selectAll
//	 Map<String, Object> params = new HashMap<>();
//	 params.put("start", 1);
//	 params.put("end", 10);
//	 List<DisswcDto> list = dao.selectAll(params);
//      System.out.println(service.selectAll(params)); }
// 
	
	@Ignore @Test public void test4()  {
		 //5. delete
		 DisswcDto  dto = new DisswcDto();
		 dto.setDisno(1);
		 System.out.println(dao.delete(1)); }
		 
//			//4. update
//		 DisswcDto dto = new DisswcDto();
//		 dto.setDisno(1);
//		 dto.setDisname("질환명");
//		 dto.setDisex("질환테스트 설명");
//		 dto.setKindpet("대형견");
//		 dto.setInfval("30%");
//		 dto.setMannote("주의 내용");
//		 dto.setBhit(11);
//		 System.out.println(dao.update(dto));
//	 }
	 	 
//		//3. select
//		 DisswcDto dto = new DisswcDto();
//		 dto.setDisno(66);
//		 System.out.println(dao.select(10));
//	 }
	 
//		//2. insert
//		 DisswcDto dto = new DisswcDto();
//		 dto.setDisno(66);
//		 dto.setDisname("질환명");
//		 dto.setDisex("질환테스트 설명");
//		 dto.setKindpet("대형견");
//		 dto.setInfval("30%");
//		 dto.setMannote("주의 내용");
//		 dto.setBhit(1);
//		 System.out.println(dao.insert(dto));
//	 }
		
			 
	 //1. selectAll
//		 Map<String, Object> params = new HashMap<>();
//		 params.put("start", 1);
//		 params.put("end", 10);
//		 List<DisswcDto> list = dao.selectAll(params);
//	      System.out.println(dao.selectAll(params)); }
//	 }

}
