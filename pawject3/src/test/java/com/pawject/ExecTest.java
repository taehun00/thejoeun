package com.pawject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.dao.exec.ExecinfoDao;
import com.pawject.dao.exec.ExecsmartDao;
import com.pawject.dao.exec.SaveweatherDao;
import com.pawject.dao.exec.WalkingcourseDao;
import com.pawject.dto.exec.ExecinfoDto;
import com.pawject.dto.exec.ExecsmartDto;
import com.pawject.dto.exec.SaveweatherDto;
import com.pawject.dto.exec.WalkingcourseDto;
import com.pawject.service.exec.ExecinfoService;
import com.pawject.service.exec.ExecsmartService;
import com.pawject.service.exec.SaveweatherService;
import com.pawject.service.exec.WalkingcourseService;

@SpringBootTest
@Transactional   // 테스트 실행중 발생 db변경 자동 롤백
public class ExecTest {
	@Autowired ExecsmartDao     sdao;
	@Autowired ExecinfoDao      idao;
	@Autowired SaveweatherDao   wdao;
	@Autowired WalkingcourseDao cdao;
	
	
	
	////////////////////////////////////////////////////////
	// ExecsmartDao_Test / sdao
//	@Disabled  @Test public void ExecsmartDao_Test() {
//        // 1. selectAll (리스트가 null이 아님을 확인)
//        assertThat(sdao.selectAllsmart()).isNotNull();
//        assertThat(sdao.selectAllsmart()).isInstanceOfAny(java.util.List.class);
//        //System.out.println(".....1. " + sdao.selectAllsmart());
//        
//        // 2. insert
//        ExecsmartDto sdto_i = new ExecsmartDto();
//        //sdto_i.setPostid(1);   // 테스트용 PK (실제 DB와 충돌 없도록 큰 값 사용)
//        sdto_i.setExecid(1);
//        sdto_i.setUserid(1);
//        sdto_i.setCourseid(1);
//        sdto_i.setEtitle("title_test1");
//        sdto_i.setEcontent("content_test1");
//        sdto_i.setEimg("img_test1.png");
//
//        int insertResult = sdao.insertsmart(sdto_i); 
//       // System.out.println("..... 2. " + sdto_i);	
//        assertEquals(1, insertResult); // insert 성공 확인
//
//        // 3. select (insert한 데이터 조회)
//        List<ExecsmartDto>  list = sdao.selectAllsmart();
//        assertFalse(list.isEmpty());
//        
//        ExecsmartDto selected = list.get(0);  //가장최근 insert
//        assertNotNull(selected);  
//        assertEquals("title_test1", selected.getEtitle());
//        assertEquals("content_test1", selected.getEcontent());
//        //System.out.println("..... 3. " + selected);	
//        
//        // 4. update
//        ExecsmartDto sdto_u = new ExecsmartDto();
//        //sdto_u.setPostid(sdto_i.getPostid()); // 반드시 PK 지정
//        sdto_u.setPostid(17);
//        sdto_u.setExecid(2);
//        sdto_u.setUserid(2);
//        sdto_u.setCourseid(2);
//        sdto_u.setEtitle("title_test2");
//        sdto_u.setEcontent("content_test2");
//        sdto_u.setEimg("img_test2.png");
//
//        int updateResult = sdao.updatesmart(sdto_u);
//        //System.out.println("..... 4. " + sdto_u);	
//        assertEquals(1, updateResult);
//
//        // 5. delete
//        ExecsmartDto sdto_d = new ExecsmartDto();
//        sdto_d.setPostid(17);
//        sdto_d.setExecid(2);
//
//        int deleteResult = sdao.deletesmart(sdto_d);
//        //System.out.println("..... 5. " + sdto_d);	
//        assertEquals(1, deleteResult);
//
//	}
//	
//	////////////////////////////////////////////////////////
//	// ExecinfoDao_Test	/ idao
//	@Disabled  @Test public void ExecinfoDao_Test () {
//		//1. selectAll
//        assertThat(idao.selectAllinfo()).isNotNull();
//        assertThat(idao.selectAllinfo()).isInstanceOfAny(java.util.List.class);
//		System.out.println(".... 1. " + idao.selectAllinfo());
//		
//		//2. insert
//		ExecinfoDto idto_i = new ExecinfoDto();
//		//idto_i.setExecid(1); 
//		idto_i.setExectype("type_test1"); 
//		idto_i.setDescription("description_test1");
//		idto_i.setAvgkcal30min(123); 
//		idto_i.setExectargetmin(123);
//		idto_i.setSuitablefor("suitable_test1");
//		idto_i.setIntensitylevel("intensity_test1");
//		
//		int insertResult_i = idao.insertinfo(idto_i);
//		System.out.println(".... 2. " + idto_i);
//		
//		//3. select
//        List<ExecinfoDto>  list = idao.selectAllinfo();
//        assertFalse(list.isEmpty());
//        
//        ExecinfoDto iselected = list.get(0);  //가장최근 insert
//        assertNotNull(iselected);  
//        assertEquals("type_test1", iselected.getExectype());
//        assertEquals("description_test1", iselected.getDescription());
//		System.out.println(".... 3." + iselected);
//		
//		//4. update/idto_u.set
//		ExecinfoDto idto_u = new ExecinfoDto();
//		idto_u.setExecid(4);
//		idto_u.setExectype("type_test2"); 
//		idto_u.setDescription("description_test2");
//		idto_u.setAvgkcal30min(234); 
//		idto_u.setExectargetmin(234); 
//		idto_u.setSuitablefor("suitable_test2");
//		idto_u.setIntensitylevel("intensity_test2");
//		
//		int updateResult_i = idao.updateinfo(idto_u);
//		System.out.println("....4." + idto_u);
//		
//		//5. delete/idto_d.set
//		ExecinfoDto idto_d = new ExecinfoDto();
//		idto_d.setExecid(4); 
//		idto_d.setExectype("type_test2");
//		
//		int deleteResult_i = idao.deleteinfo(idto_d);
//		System.out.println(".... 5." + idto_d);
//	}
////
////	
////	////////////////////////////////////////////////////////
//	// SaveweatherDao_Test / wdao
//	 @Disabled @Test public void SaveweatherDao_Test () {
//		//1. selectAll
//        assertThat(wdao.selectAllweather()).isNotNull();
//        assertThat(wdao.selectAllweather()).isInstanceOfAny(java.util.List.class);
//
//		System.out.println("....1. " + wdao.selectAllweather());
//		
//		//2. insert / wdto.set
//		SaveweatherDto wdto = new SaveweatherDto();
//		//wdto.setBasedate("2025-12-29");
//		wdto.setWeather("weather_test1"); 
//		wdto.setMaxtemp(123); 
//		wdto.setMintemp(321); 
//		wdto.setMoistpercent(12); 
//		wdto.setRainpercent(21);
//		
//		int insertResult_w = wdao.insertweather(wdto);
//		System.out.println(".... 2." + wdto);
//		
//		//3. select
//        List<SaveweatherDto>  list = wdao.selectAllweather();
//        assertFalse(list.isEmpty());
//        
//        SaveweatherDto wselected = list.get(0);  //가장최근 insert
//        assertNotNull(wselected);  
//        assertEquals(123, wselected.getMaxtemp());
//        assertEquals(321, wselected.getMintemp());
//        
//		System.out.println("....3. " + wselected); 
//		
//		//4. update/wdto_u.set
//		SaveweatherDto wdto_u = new SaveweatherDto();
////		wdto_u.setBasedate(2025-12-29);
//		wdto_u.setWeather("weather_test1"); 
//		wdto_u.setMaxtemp(234); 
//		wdto_u.setMintemp(432);
//		wdto_u.setMoistpercent(23); 
//		wdto_u.setRainpercent(32);
//		
//		int updateResult_w = wdao.updateweather(wdto_u);
//		System.out.println("....4. " + wdto_u);
//		
//		//5. delete/wdto_d.set
//		SaveweatherDto wdto_d = new SaveweatherDto();
//		wdto_d.setWeather("weather_test1"); 
//		
//		int deleteResult_w = wdao.deleteweather(wdto_d);
//		System.out.println(".... 5. " + wdto_d);
//	}
//	
//	////////////////////////////////////////////////////////
//	// WalkingcourseDao_Test / cdao
//	 @Disabled  @Test public void WalkingcourseDao_Test () {
//		//1. selectAll
//        assertThat(cdao.selectAllwalking()).isNotNull();
//        assertThat(cdao.selectAllwalking()).isInstanceOfAny(java.util.List.class);
//		System.out.println(".....1." + cdao.selectAllwalking());
//		
//		//2. insert / cdto.set
//		WalkingcourseDto cdto = new WalkingcourseDto();
//		//cdto.setCourseid(1); //seqeuence
//		cdto.setPostid(1); 
//		cdto.setLocation("location_test1"); 
//		cdto.setLat(123); 
//		cdto.setLng(321);
//		
//		int insertResult_c = cdao.insertwalking(cdto);
//		System.out.println(".....2. " + cdto);
//		
//		//3. select
//        List<WalkingcourseDto>  list = cdao.selectAllwalking();
//        assertFalse(list.isEmpty());
//        
//        WalkingcourseDto cselected = list.get(0);  //가장최근 insert
//        assertNotNull(cselected);  
//        assertEquals(1, cselected.getPostid());
//        assertEquals("location_test1", cselected.getLocation());
//
//		System.out.println(".....3. " + cselected);
//		
//		//4. update/cdto_u.set
//		WalkingcourseDto cdto_u = new WalkingcourseDto();
//		cdto_u.setCourseid(5);
//		cdto_u.setPostid(2); 
//		cdto_u.setLocation("location_test2");
//		cdto_u.setLat(234); 
//		cdto_u.setLng(432);
//		
//		int updateResult_c = cdao.updatewalking(cdto_u);
//		System.out.println(".....4. " + cdto_u);
//		
//		//5. delete/cdto_d.set  - 삭제하기 위한 입력받는 정보를 바꿀려면 mapper에서 바꾸기.
//		WalkingcourseDto cdto_d = new WalkingcourseDto();
//		cdto_d.setCourseid(5);
//		cdto_d.setPostid(2);
//		
//		int deleteResult_c = cdao.deletewalking(cdto_d);
//		System.out.println("......5. " + cdto_d);
//	}
	
	
	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////

		@Autowired ExecsmartService      sbservice;
		@Autowired ExecinfoService       iservice;
		@Autowired SaveweatherService    wservice;
		@Autowired WalkingcourseService  cservice;

	
	////////////////////////////////////////////////////////
	//ExecsmartService_Test / sbservice
	@Disabled  @Test public void ExecsmartService_Test () {
        // 1. selectAll (리스트가 null이 아님을 확인)
        assertThat(sbservice.selectAllsmart()).isNotNull();
        assertThat(sbservice.selectAllsmart()).isInstanceOfAny(java.util.List.class);
        System.out.println(".....1. " + sbservice.selectAllsmart());
        
        // 2. insert
        ExecsmartDto sdto_si = new ExecsmartDto();
        //sdto_i.setPostid(1);   // 테스트용 PK (실제 DB와 충돌 없도록 큰 값 사용)
        sdto_si.setExecid(1);
        sdto_si.setUserid(1);
        sdto_si.setCourseid(1);
        sdto_si.setEtitle("title_test3");
        sdto_si.setEcontent("content_test3");
		MockMultipartFile  file = new MockMultipartFile( "file2" , "file2.txt" , "text/plain" , "".getBytes());
		
		sdto_si.setEimg("img_test3.png");

        int insertResult = sbservice.insertsmart(file, sdto_si); 
        assertEquals(1, insertResult); // insert 성공 확인
        System.out.println("..... 2. " + sdto_si);	

        // 3. select (insert한 데이터 조회)
        List<ExecsmartDto>  list = sbservice.selectAllsmart();
        assertFalse(list.isEmpty());
        
        ExecsmartDto sbselected = list.get(0);  //가장최근 insert
        assertNotNull(sbselected);  
        assertEquals("title_test3", sbselected.getEtitle());
        assertEquals("content_test3", sbselected.getEcontent());
        System.out.println("..... 3. " + sbselected);	
        
        // 4. update
        ExecsmartDto sbdto_u = new ExecsmartDto();
        //sdto_u.setPostid(sdto_i.getPostid()); // 반드시 PK 지정
        sbdto_u.setPostid(21);
        sbdto_u.setExecid(2);
        sbdto_u.setUserid(2);
        sbdto_u.setCourseid(2);
        sbdto_u.setEtitle("title_test2");
        sbdto_u.setEcontent("content_test2");
        sbdto_u.setEimg("img_test2.png");

        int updateResult = sdao.updatesmart(sbdto_u);
        System.out.println("..... 4. " + sbdto_u);	
        assertEquals(1, updateResult);

        // 5. delete
        ExecsmartDto sbdto_d = new ExecsmartDto();
        sbdto_d.setPostid(21);
        sbdto_d.setExecid(2);

        int deleteResult = sdao.deletesmart(sbdto_d);
        System.out.println("..... 5. " + sbdto_d);	
        assertEquals(1, deleteResult);

	}
	
	////////////////////////////////////////////////////////
	//ExecinfoService_Test / iservice
	@Disabled  @Test public void ExecinfoService_Test () {
		//1. selectAll
       assertThat(iservice.selectAllinfo()).isNotNull();
       assertThat(iservice.selectAllinfo()).isInstanceOfAny(java.util.List.class);
		System.out.println(".... 1. " + iservice.selectAllinfo());
		
		//2. insert
		ExecinfoDto idto_si = new ExecinfoDto();
		//idto_i.setExecid(1); 
		idto_si.setExectype("type_test3"); 
		idto_si.setDescription("description_test3");
		idto_si.setAvgkcal30min(123); 
		idto_si.setExectargetmin(123);
		idto_si.setSuitablefor("suitable_test3");
		idto_si.setIntensitylevel("intensity_test3");
		
		int insertResult_i = iservice.insertinfo(idto_si);
		System.out.println(".... 2. " + idto_si);
		
		//3. select
       List<ExecinfoDto>  list = iservice.selectAllinfo();
       assertFalse(list.isEmpty());
       
       ExecinfoDto siselected = list.get(0);  //가장최근 insert
       assertNotNull(siselected);  
       assertEquals("type_test3", siselected.getExectype());
       assertEquals("description_test3", siselected.getDescription());
		System.out.println(".... 3." + siselected);
		
		//4. update/idto_u.set
		ExecinfoDto idto_su = new ExecinfoDto();
		idto_su.setExecid(8);
		idto_su.setExectype("type_test4"); 
		idto_su.setDescription("description_test4");
		idto_su.setAvgkcal30min(234); 
		idto_su.setExectargetmin(234); 
		idto_su.setSuitablefor("suitable_test4");
		idto_su.setIntensitylevel("intensity_test4");
		
		int updateResult_i = iservice.updateinfo(idto_su);
		System.out.println("....4." + idto_su);
		
		//5. delete/idto_d.set
		ExecinfoDto idto_sd = new ExecinfoDto();
		idto_sd.setExecid(8); 
		idto_sd.setExectype("type_test4");
		
		int deleteResult_i = iservice.deleteinfo(idto_sd);
		System.out.println(".... 5." + idto_sd);
	}

	
	////////////////////////////////////////////////////////
	//SaveweatherService_Test / wservice
	  @Disabled @Test public void SaveweatherService_Test () {
		//1. selectAll
	    assertThat(wservice.selectAllweather()).isNotNull();
	    assertThat(wservice.selectAllweather()).isInstanceOfAny(java.util.List.class);
		System.out.println(".... 1. " + wservice.selectAllweather());
		
		//2. insert / wdto.set
		SaveweatherDto wdto_si = new SaveweatherDto();
		//wdto_si.getBasedate();
		wdto_si.setWeather("weather_test3"); 
		wdto_si.setMaxtemp(123); 
		wdto_si.setMintemp(321); 
		wdto_si.setMoistpercent(12); 
		wdto_si.setRainpercent(21);
		
		int insertResult_w = wservice.insertweather(wdto_si);
		System.out.println(".... 2." + wdto_si);
		
		//3. select
	    List<SaveweatherDto>  list = wservice.selectAllweather();
	    assertFalse(list.isEmpty());
	      
	    SaveweatherDto swselected = list.get(0);  //가장최근 insert
	    assertNotNull(swselected);  
	    assertEquals(123, swselected.getMaxtemp());
	    assertEquals(321, swselected.getMintemp());
      
		System.out.println("....3. " + swselected); 
		
		//4. update/wdto_u.set
		SaveweatherDto wdto_su = new SaveweatherDto();
//		wdto_u.setBasedate(2025-12-29);
		wdto_su.setWeather("weather_test3"); 
		wdto_su.setMaxtemp(234); 
		wdto_su.setMintemp(432);
		wdto_su.setMoistpercent(23); 
		wdto_su.setRainpercent(32);
		
		int updateResult_w = wservice.updateweather(wdto_su);
		System.out.println("....4. " + wdto_su);
		
		//5. delete/wdto_d.set
		SaveweatherDto wdto_sd = new SaveweatherDto();
		wdto_sd.setWeather("weather_test3"); 
		
		int deleteResult_w = wservice.deleteweather(wdto_sd);
		System.out.println(".... 5. " + wdto_sd);
	}

	
	////////////////////////////////////////////////////////
	//WalkingcourseService_Test / cservice
	@Disabled @Test public void WalkingcourseService_Test () {
		//1. selectAll
      assertThat(cservice.selectAllwalking()).isNotNull();
      assertThat(cservice.selectAllwalking()).isInstanceOfAny(java.util.List.class);
		System.out.println(".....1." + cservice.selectAllwalking());
		
		//2. insert / cdto.set
		WalkingcourseDto cdto_si = new WalkingcourseDto();
		//cdto.setCourseid(1); //seqeuence
		cdto_si.setPostid(1); 
		cdto_si.setLocation("location_test3"); 
		cdto_si.setLat(123); 
		cdto_si.setLng(321);
		
		int insertResult_c = cservice.insertwalking(cdto_si);
		System.out.println(".....2. " + cdto_si);
		
		//3. select
      List<WalkingcourseDto>  list = cservice.selectAllwalking();
      assertFalse(list.isEmpty());
      
      WalkingcourseDto scselected = list.get(0);  //가장최근 insert
      assertNotNull(scselected);  
      assertEquals(1, scselected.getPostid());
      assertEquals("location_test3", scselected.getLocation());

		System.out.println(".....3. " + scselected);
		
		//4. update/cdto_u.set
		WalkingcourseDto cdto_su = new WalkingcourseDto();
		cdto_su.setCourseid(7);
		cdto_su.setPostid(2); 
		cdto_su.setLocation("location_test4");
		cdto_su.setLat(234); 
		cdto_su.setLng(432);
		
		int updateResult_c = cservice.updatewalking(cdto_su);
		System.out.println(".....4. " + cdto_su);
		
		//5. delete/cdto_d.set  - 삭제하기 위한 입력받는 정보를 바꿀려면 mapper에서 바꾸기.
		WalkingcourseDto cdto_sd = new WalkingcourseDto();
		cdto_sd.setCourseid(7);
		cdto_sd.setPostid(2);
		
		int deleteResult_c = cservice.deletewalking(cdto_sd);
		System.out.println("......5. " + cdto_sd);
	}	

	
	
	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////


	////////////////////////////////////////////////////////
	//execsmartTest_paging / sdao
	
	 @Disabled  @Test
	 public void execsmartTest_paging() {
		 //1. 10개씩 가져오기
		 HashMap<String, Integer> para = new HashMap<>(); 
		 para.put("start", 1);
		 para.put("end", 10);
		 System.out.println("............" + sdao.selectsmart10(para));
		 
		 //2. 전체갯수
		 System.out.println("............" + sdao.selectsmartTotalCnt());
		  
		 //3. 검색어 + 3개씩 가져오기
		 HashMap<String, Object> para2 = new HashMap<>(); 
		 para2.put("search", "반려동물");
		 para2.put("start", 1);  // (1) 1,3  (2)4,6 (3)7,9
		 para2.put("end"  , 3);
		 System.out.println("............" + sdao.selectsmart3(para2));		 

		 //4. 검색어 + 3개씩 가져오기  ( 검색어 있는걸로 테스트 - t)
		 System.out.println("............ " + sdao.selectsmartSearchTotalCnt("반려동물"));
	 }

	
		////////////////////////////////////////////////////////
		//execinfoTest_paging / idao
	     @Disabled  @Test		
		 public void execinfoTest_paging() {
			 //1. 10개씩 가져오기
			 HashMap<String, Integer> para = new HashMap<>(); 
			 para.put("start", 1);
			 para.put("end", 10);
			 System.out.println("............" + idao.selectinfo10(para));
			 
			 //2. 전체갯수
			 System.out.println("............" + idao.selectinfoTotalCnt());
			  
			 //3. 검색어 + 3개씩 가져오기
			 HashMap<String, Object> para2 = new HashMap<>(); 
			 para2.put("search", "산책");
			 para2.put("start", 1);  // (1) 1,3  (2)4,6 (3)7,9
			 para2.put("end"  , 3);
			 System.out.println("............" + idao.selectinfo3(para2));		 
	
			 //4. 검색어 + 3개씩 가져오기  ( 검색어 있는걸로 테스트 - 산책)
			 System.out.println("............ " + idao.selectinfoSearchTotalCnt("산책"));
		 }
	 
		////////////////////////////////////////////////////////
		//saveweatherTest_paging / wdao
	     @Disabled  @Test
		 public void saveweatherTest_paging() {
			 //1. 10개씩 가져오기
			 HashMap<String, Integer> para = new HashMap<>(); 
			 para.put("start", 1);
			 para.put("end", 10);
			 System.out.println("............" + wdao.selectweather10(para));
			 
			 //2. 전체갯수
			 System.out.println("............" + wdao.selectweatherTotalCnt());
			  
			 //3. 검색어 + 3개씩 가져오기
			 HashMap<String, Object> para2 = new HashMap<>(); 
			 para2.put("search", "맑음");
			 para2.put("start", 1);  // (1) 1,3  (2)4,6 (3)7,9
			 para2.put("end"  , 3);
			 System.out.println("............" + wdao.selectweather3(para2));		 
			 
			 //4. 검색어 + 3개씩 가져오기  ( 검색어 있는걸로 테스트 - t)
			 System.out.println("............ " + wdao.selectweatherSearchTotalCnt("맑음"));
		 }
	     

		////////////////////////////////////////////////////////
		//walkingcourseTest_paging / sdao
		@Disabled @Test
		 public void walkingcourseTest_paging() {
			 //1. 10개씩 가져오기
			 HashMap<String, Integer> para = new HashMap<>(); 
			 para.put("start", 1);
			 para.put("end", 10);
			 System.out.println("............" + cdao.selectwalking10(para));
			 
			 //2. 전체갯수
			 System.out.println("............" + cdao.selectwalkingTotalCnt());
			  
			 //3. 검색어 + 3개씩 가져오기
			 HashMap<String, Object> para2 = new HashMap<>(); 
			 para2.put("search", "인천대공원");
			 para2.put("start", 1);  // (1) 1,3  (2)4,6 (3)7,9
			 para2.put("end"  , 3);
			 System.out.println("............" + cdao.selectwalking3(para2));		 
	
			 //4. 검색어 + 3개씩 가져오기  ( 검색어 있는걸로 테스트 - t)
			 System.out.println("............ " + cdao.selectwalkingSearchTotalCnt("인천대공원"));
		 }

		////////////////////////////////////////////////////////

	
	
	
	
	
	
	
	
	
	
}
