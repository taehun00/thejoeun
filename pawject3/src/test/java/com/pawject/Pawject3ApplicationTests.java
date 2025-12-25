package com.pawject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.dao.UserDao2;
import com.pawject.dto.user.AuthDto;
import com.pawject.dto.user.UserDto;

@SpringBootTest
@Transactional
class Pawject3ApplicationTests {

	@Autowired
    private UserDao2 userDao;




}
