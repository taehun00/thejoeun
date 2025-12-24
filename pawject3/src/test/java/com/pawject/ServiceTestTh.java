package com.pawject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.dto.user.UserAuthDto;
import com.pawject.dto.user.UserDto;
import com.pawject.service.user.UserSecurityService;

@SpringBootTest
public class ServiceTestTh {

	@Autowired
    private UserSecurityService userSecurityService;



}
