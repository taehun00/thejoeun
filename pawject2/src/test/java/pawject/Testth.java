package pawject;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pawject.dao.user.UserMapper;
import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/root-context.xml"})
public class Testth {
	@Autowired UserMapper dao;
	
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
		auth.setEmail("1@1");
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
	@Test
	public void test8() {
		 UserDto dto = new UserDto();
	     dto.setUserId(57);
	     
	     System.out.println(dao.deleteMember(dto));
	     
	}
}
