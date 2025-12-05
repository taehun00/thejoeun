package pawject;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pawject.dao.pet.PetMapper;
import com.pawject.dao.user.UserMapper;
import com.pawject.dto.pet.PetDto;
import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;
import com.pawject.service.user.UserSecurityService;
import org.springframework.mock.web.*;  //## 가짜이미지파일

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/root-context.xml" , "classpath:config/security-context.xml"})
public class Testth {
	@Autowired UserMapper dao;
	@Autowired  UserSecurityService service; 
	@Autowired PetMapper pdao;
	
	@Ignore @Test
	public void test1() {
		UserDto dto = new UserDto();
		dto.setEmail("1@1");
		dto.setNickname("gg");
		dto.setPassword("123");
		dto.setUfile(null);
		dto.setMobile("010");
		System.out.println(dao.join(dto));
	}
	
	@Ignore @Test
	public void test2() {
		AuthDto auth = new AuthDto();
		auth.setAuth("ROLE_MEMBER");
		System.out.println(dao.joinAuth(auth));
	}
	@Ignore @Test
	public void test3() {
		UserDto dto = new UserDto();
        dto.setEmail("1@1");
        UserAuthDto userAuth = dao.readAuth(dto);
        System.out.println(userAuth);
	}
	@Ignore @Test
	public void test4() {
		UserDto dto = new UserDto();
        dto.setEmail("1@1");
        System.out.println(dao.myPage(dto));
	}
	@Ignore @Test
	public void test5() {
		UserDto dto = new UserDto();
        dto.setNickname("gg");
        System.out.println(dao.findId(dto));
	}
	@Ignore @Test
	public void test6() {
		UserDto dto = new UserDto();
        dto.setNickname("gg");
        dto.setEmail("1@1");
        System.out.println(dao.findPassword(dto));
	}
	@Ignore @Test
	public void test7() {
		UserDto dto = new UserDto();
        
        dto.setEmail("1@11");
        dto.setNickname("gg1");
        dto.setPassword("456");
        dto.setUfile(null);
        dto.setMobile("0101");
        dto.setUserId(57); // 실제 DB에 맞는 PK 값 넣어야 함
        System.out.println(dao.update(dto));
	}
	@Ignore @Test
	public void test8() {
		 UserDto dto = new UserDto();
	     dto.setUserId(57);
	     
	     System.out.println(dao.deleteMember(dto));
	     
	}
	@Ignore@Test
	public void test9() {
		UserDto dto = new UserDto();
		dto.setUserId(88);
		dto.setEmail("b@b");
		dto.setNickname("cc");
		dto.setPassword("123");
		MockMultipartFile  file = new MockMultipartFile( "file" , "file.txt" , "text/plain" , "dummy".getBytes());		
		dto.setMobile("010");
		System.out.println(service.update(file, dto));
		//System.out.println(service.readAuth("1@1"));
	}
	@Ignore@Test
	public void test10() {
		UserDto dto = new UserDto();
        dto.setEmail("a@a");
        dto.setNickname("hh");
        dto.setPassword("b");
        dto.setMobile("0101");
        MockMultipartFile  file = new MockMultipartFile( "file" , "file.txt" , "text/plain" , "".getBytes());		
		System.out.println(service.join(file, dto));
		//System.out.println(service.myPage("1@1"));
		//System.out.println(service.findId("gg"));
		//System.out.println(service.findPassword("gg", "1@1"));
	}
	@Ignore @Test
	public void test11() {
		
		UserDto dto = new UserDto(); String email = "a@a";
		System.out.println(service.deleteUser(email));
		
		
		//System.out.println(service.deleteMember(88));
	}
	
	@Ignore@Test
	public void test12() {
		//System.out.println(service.readAuth("a@a"));
		//System.out.println(service.readAuth1(88));
		//System.out.println(service.myPage("a@a"));
		//System.out.println(service.findId("hh"));
		//System.out.println(service.findPassword("hh", "a@a"));
	}
	@Ignore@Test
	public void testSearchUsers() {
	    String keyword = "th"; // 이메일 또는 닉네임 일부
	    List<UserDto> list = service.searchUsers(keyword);

	    for(UserDto u : list) {
	        System.out.println("검색 결과: " + u.getUserId() + ", " + u.getEmail() + ", " + u.getNickname());
	    }
	}
	@Ignore@Test
	public void test13() {
		PetDto pet = new PetDto();
        pet.setUserId(112);
        pet.setPetName("초코");
        pet.setPetBreed("푸들");
        pet.setBirthDate("2020-01-01");
        pet.setPetTypeId(2);
        pet.setPfile("choco.png");

        System.out.println(pdao.petJoin(pet));

	}
	@Ignore@Test
	public void test14() {
		//System.out.println(pdao.selectPetsByUserId(112));
		System.out.println(pdao.selectPetDetail(112, 26));
	}
	@Ignore@Test
	public void test15() {
		PetDto pet = new PetDto();
        pet.setPetId(26);          // 수정할 펫 ID
        pet.setPetName("초코 수정");
        pet.setPetBreed("푸들 수정");
        pet.setBirthDate("2020-02-02");
        pet.setPetTypeId(1);
        pet.setPfile("choco_update.png");
        System.out.println(pdao.updatePetByUser(pet, 112));
	}
	@Ignore@Test
	public void test16() {
		 PetDto pet = new PetDto();
	        pet.setPetId(26);          // 수정할 펫 ID
	        pet.setPetName("초코 수정2");
	        pet.setPetBreed("푸들 수정2");
	        pet.setBirthDate("2019-03-03");
	        pet.setPetTypeId(2);
	        pet.setPfile("cookie_update.png");
	        System.out.println(pdao.updatePetByAdmin(pet));
	}
	@Ignore@Test
	public void test17() {
		String keyword = "@"; // 이메일 또는 닉네임 일부
	    List<PetDto> list = pdao.searchPets(keyword);

	    for(PetDto pet : list) {
	    	System.out.println("펫 ID: " + pet.getPetId());
            System.out.println("펫 이름: " + pet.getPetName());
            System.out.println("품종: " + pet.getPetBreed());
            System.out.println("생일: " + pet.getBirthDate());
            System.out.println("타입 ID: " + pet.getPetTypeId());
            System.out.println("등록일: " + pet.getCreatedAt());
            System.out.println("파일: " + pet.getPfile());

	    }
	}
	@Test
	public void test18() {
		System.out.println(pdao.deletePetByUser(26, 112));
	}
}
