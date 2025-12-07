package pawject2;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
//import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pawject.dao.exec.ExecBoardDao;
import com.pawject.dao.exec.ExecInfoDao;
//import com.pawject.dto.exec.ExecBoardDto;
//import com.pawject.dto.exec.ExecInfoDto;
import com.pawject.service.exec.ExecBoardService;
import com.pawject.service.exec.ExecInfoService;
//import org.springframework.mock.web.*;  //## 가짜이미지파일

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/root-context.xml")

public class Test_Exec_pawject2 {
	@Autowired ApplicationContext context;
	//@Autowired ServletContext context;
	@Autowired DataSource ds;
	@Autowired SqlSession sqlSession;
	@Autowired ExecBoardDao dao;
	@Autowired ExecInfoDao idao;
	@Autowired ExecBoardService service;
	@Autowired ExecInfoService  iservice;
	
	//운동정보게시판 paging_Test
	@Ignore @Test public void test11() {
//		HashMap<String, Object> para = new HashMap<>();
//		para.put("start", 1); para.put("end", 10);
//		System.out.println(idao.select10(para));
//		System.out.println(idao.selectAll2());
//		System.out.println(idao.selectTotalCnt());
	}

	
	//운동챌린지게시판 paging_Test
	@Ignore @Test public void test10() {
//		HashMap<String, Object> para = new HashMap<>();
//		para.put("start", 1); para.put("end", 10);
//		System.out.println(dao.select10(para));
//		System.out.println(dao.selectTotalCnt());
	}
	
	//운동정보게시판 Search_Test
	@Ignore public void test9() { 
//		HashMap<String, String> para = new HashMap<>();
//		para.put("search", "%t%");
		
//		System.out.println(idao.selectSearch2(para));
	}
	
	//운동챌린지게시판 Search_Test
	@Ignore public void test8() {
//		HashMap<String, String> para = new HashMap<>();
//		para.put("search", "%t%");
//		
//		System.out.println(dao.selectSearch1(para));
	}
	
	
	
	
   //운동정보게시판 service테스트(운동챌린지 게시판 작성시 참고용)
	@Ignore public void test7() {
		//5. delete
//		ExecInfoDto dto = new ExecInfoDto();
//		dto.setExecid(71);
//		System.out.println(idao.delete2(dto));

		//4. update
//		ExecInfoDto dto = new ExecInfoDto();
//		dto.setExectype("걷기"); dto.setDescription("기본적인 야외활동");
//		dto.setAvgkcal30min(90);
//		dto.setExectargetmin(40); dto.setSuitablefor("모든 견종, 노령견 포함"); 
//		dto.setIntensitylevel("저강도"); dto.setExecid(71);
//		System.out.println(iservice.update2(dto));
//		System.out.println();

		//3. select
//		System.out.println(iservice.select2(71));
		
		//2. insert
//		ExecInfoDto dto = new ExecInfoDto();
		//(1) 산책
//		dto.setExectype("산책"); dto.setDescription("기본적인 야외활동/스트레스 해소");
//		dto.setAvgkcal30min(80);
//		dto.setExectargetmin(30); dto.setSuitablefor("모든 견종, 노령견 포함"); 
//		dto.setIntensitylevel("저강도");
		
		//(2) 노즈워크
//		dto.setExectype("노즈워크"); dto.setDescription("간식을 숨겨두고 냄새로 찾게 하는 놀이로, 정신 자극과 집중력 향상에 좋습니다.");
//		dto.setAvgkcal30min(60);
//		dto.setExectargetmin(20); dto.setSuitablefor("실내 생활견, 고양이도 가능"); 
//		dto.setIntensitylevel("저강도");
		
		//(3) 수영
//		dto.setExectype("수영"); dto.setDescription("관절에 부담이 적고 전신 근육을 사용하는 고강도 운동");
//		dto.setAvgkcal30min(120);
//		dto.setExectargetmin(25); dto.setSuitablefor("중형견 이상, 관절 약한 반려동물"); 
//		dto.setIntensitylevel("고강도");

		//(4) 터그놀이
//		dto.setExectype("터그놀이"); dto.setDescription("줄다리기 형태의 놀이로, 근력과 집중력을 동시에 향상");
//		dto.setAvgkcal30min(70);
//		dto.setExectargetmin(15); dto.setSuitablefor("활동적인 소형견, 고양이도 가능"); 
//		dto.setIntensitylevel("중강도");

		//(5) 레이저포인터 추척
//		dto.setExectype("레이저 포인터 추적"); dto.setDescription("고양이에게 인기 있는 실내 운동, 사냥 본능을 자극");
//		dto.setAvgkcal30min(50);
//		dto.setExectargetmin(10); dto.setSuitablefor("고양이 전용, 실내 생활 반려동물"); 
//		dto.setIntensitylevel("중강도");

//		System.out.println(iservice.insert2(dto));
		
		//1. selectAll
//		System.out.println(iservice.selectAll2());
	}
	

   //운동정보게시판dao테스트(테스트완료/운동챌린지 게시판 작성시 참고용)	
	@Ignore public void test5() {
		//5. delete
//		ExecInfoDto dto = new ExecInfoDto();
//		dto.setExecid(70);
//		System.out.println(idao.delete2(dto));

		//4. update
//		ExecInfoDto dto = new ExecInfoDto();
//		dto.setExectype("걷기"); dto.setDescription("기본적인 야외활동");
//		dto.setAvgkcal30min(90);
//		dto.setExectargetmin(40); dto.setSuitablefor("모든 견종, 노령견 포함"); 
//		dto.setIntensitylevel("저강도"); dto.setExecid(70);
//		System.out.println(idao.update2(dto));
//		System.out.println();

		
		//3. select
//		System.out.println(idao.select2(70));
		
		//2. insert
//		ExecInfoDto dto = new ExecInfoDto();
		//(1) 산책
//		dto.setExectype("산책"); dto.setDescription("기본적인 야외활동/스트레스 해소");
//		dto.setAvgkcal30min(80);
//		dto.setExectargetmin(30); dto.setSuitablefor("모든 견종, 노령견 포함"); 
//		dto.setIntensitylevel("저강도");
		
		//(2) 노즈워크
//		dto.setExectype("노즈워크"); dto.setDescription("간식을 숨겨두고 냄새로 찾게 하는 놀이로, 정신 자극과 집중력 향상에 좋습니다.");
//		dto.setAvgkcal30min(60);
//		dto.setExectargetmin(20); dto.setSuitablefor("실내 생활견, 고양이도 가능"); 
//		dto.setIntensitylevel("저강도");
		
		//(3) 수영
//		dto.setExectype("수영"); dto.setDescription("관절에 부담이 적고 전신 근육을 사용하는 고강도 운동");
//		dto.setAvgkcal30min(120);
//		dto.setExectargetmin(25); dto.setSuitablefor("중형견 이상, 관절 약한 반려동물"); 
//		dto.setIntensitylevel("고강도");

		//(4) 터그놀이
//		dto.setExectype("터그놀이"); dto.setDescription("줄다리기 형태의 놀이로, 근력과 집중력을 동시에 향상");
//		dto.setAvgkcal30min(70);
//		dto.setExectargetmin(15); dto.setSuitablefor("활동적인 소형견, 고양이도 가능"); 
//		dto.setIntensitylevel("중강도");

		//(5) 레이저포인터 추척
//		dto.setExectype("레이저 포인터 추적"); dto.setDescription("고양이에게 인기 있는 실내 운동, 사냥 본능을 자극");
//		dto.setAvgkcal30min(50);
//		dto.setExectargetmin(10); dto.setSuitablefor("고양이 전용, 실내 생활 반려동물"); 
//		dto.setIntensitylevel("중강도");
		
//		System.out.println(idao.insert2(dto));

		//1. selectAll
//		System.out.println(idao.selectAll2()); 
	}
	

  //운동챌린지 게시판 service 테스트(완료)
  //※ 지금 상태로 돌려도 이상은 없으나, 
  //혹시나 insert가 안되면 ExecInfoServiceImpl에서 insert파트 try/catch부분 지우기.	(dao테스트도 동일)
	@Ignore public void test6() {
		//5. delete
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setPostid(34); dto.setExecid(74);
//		System.out.println(service.delete1(dto));

		//4. update
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setEtitle("반려동물과 함께하는 노즈워크");
//		dto.setEcontent("노즈워크는 반려동물이 참을성을 길러줍니다."); 
//		dto.setExecid(74); dto.setEimg("노즈워크.png");
//		dto.setPostid(34);
//		MockMultipartFile  file = new MockMultipartFile( "file" , "file.txt" , "text/plain" , "".getBytes()); 
//
//		System.out.println(service.update2( file, dto));
//		System.out.println();
		
		//3. select
//		System.out.println(service.select1(34));
		
		//2.insert
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setExecid(74); dto.setUserid(1);
//		dto.setEtitle("반려동물과 함께하는 산책"); dto.setEcontent("반려동물과 함께하는 산책은 주인과 반려동물 모두에게 긍정적인영향을 줍니다.");
//		dto.setEimg("산책.png");
//		MockMultipartFile  file = new MockMultipartFile( "file" , "file.txt" , "text/plain" , "".getBytes()); 
//		System.out.println( service.insert2( file ,dto) );
		
		//1. selectAll
//		System.out.println(service.selectAll1());
	}
	
  //운동챌린지 게시판 dao 테스트(완료)
	@Ignore public void test4() {
		//5. delete
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setPostid(31); dto.setExecid(46);
//		System.out.println(dao.delete1(dto));
		
		//4. update
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setEtitle("반려동물과 함께하는 노즈워크");
//		dto.setEcontent("노즈워크는 반려동물이 참을성을 길러줍니다."); 
//		dto.setExecid(46); dto.setEimg("노즈워크.png");
//		dto.setPostid(31);
//		System.out.println(dao.update1(dto));
//		System.out.println();

		//3. select
//		System.out.println(dao.select1(31));
		
//		//2. insert
//		ExecBoardDto dto = new ExecBoardDto();
//		dto.setExecid(46); dto.setUserid(1);
//		dto.setEtitle("반려동물과 함께하는 산책"); dto.setEcontent("반려동물과 함께하는 산책은 주인과 반려동물 모두에게 긍정적인영향을 줍니다.");
//		dto.setEimg("산책.png");
//		
//		System.out.println(dao.insert1(dto));
		
		//1. selectAll
//		System.out.println(dao.selectAll1());
		
	}
	
	
	@Ignore public void test1() { System.out.println(context); }
	@Ignore public void test2() { System.out.println(ds); }
	@Ignore public void test3() { System.out.println(sqlSession); }
	
	
}





