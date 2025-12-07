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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/root-context.xml")
public class Test1_Pawject {
	@Autowired ApplicationContext context;
	@Autowired DataSource ds;
	@Autowired SqlSession session;
	// 여기까지 테스트 완료
	

	@Test public void test3() { System.out.println(session);}
	@Test public void test2() { System.out.println(ds);}
	@Test public void test1() { System.out.println(context);}
	
	
}
